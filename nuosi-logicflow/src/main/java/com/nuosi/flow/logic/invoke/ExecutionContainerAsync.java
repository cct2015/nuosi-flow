package com.nuosi.flow.logic.invoke;

import com.ai.ipu.basic.util.IpuUtility;
import com.ai.ipu.data.JMap;
import com.ai.ipu.data.impl.JsonMap;
import com.ai.ipu.database.conn.SqlSessionManager;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.*;
import com.nuosi.flow.data.BDataDefine;
import com.nuosi.flow.data.BizDataManager;
import com.nuosi.flow.logic.inject.calculate.CalculateMethodManager;
import com.nuosi.flow.logic.inject.common.UnmodifiableDatabus;
import com.nuosi.flow.logic.inject.initial.InitialMethodManager;
import com.nuosi.flow.logic.invoke.check.FlowDataDefine;
import com.nuosi.flow.logic.invoke.debug.LogicDebugManager;
import com.nuosi.flow.logic.invoke.processer.IActionProcesser;
import com.nuosi.flow.logic.invoke.processer.ProcesserManager;
import com.nuosi.flow.logic.model.LogicFlow;
import com.nuosi.flow.logic.model.body.Action;
import com.nuosi.flow.logic.model.body.End;
import com.nuosi.flow.logic.model.body.Start;
import com.nuosi.flow.logic.model.domain.Attr;
import com.nuosi.flow.logic.model.element.Input;
import com.nuosi.flow.logic.model.element.Output;
import com.nuosi.flow.logic.model.element.Var;
import com.nuosi.flow.logic.model.global.Declare;
import com.nuosi.flow.logic.model.global.Import;
import com.nuosi.flow.logic.parse.ModelToDataDefineUtil;
import com.nuosi.flow.util.LogicFlowConstants;

import java.util.*;
import java.util.concurrent.*;

/**
 * <p>desc: 业务逻辑流异步执行容器
 * CountDownLatch闭锁
 * </p>
 * <p>date: 2022/4/26 17:12 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class ExecutionContainerAsync {
    private Map<String, Object> databus = new HashMap<String, Object>();    //数据总线
    private UnmodifiableDatabus unmodifiableDatabus;
    private Map<String, Object> inputParameters;

    private BDataDefine flowDataDefine;    //将declare中的变量定义转化成BDataDefine，使用其数据校验逻辑
    private Set<String> modelSet = new HashSet<String>();  //记录引用的业务对象
    private Map<String, Action> actionMap = new LinkedHashMap<String, Action>();  //节点名和节点实体映射关系

    private Map<String, Object> nodeResult = new HashMap<String, Object>(); //节点返回数据
    private LogicFlow logicFlow;
    private ExecutorService debugExecutor;

    private CountDownLatch latch = new CountDownLatch(1);
    private ListeningExecutorService executor = MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());

    public ExecutionContainerAsync(LogicFlow logicFlow) {
        this.logicFlow = logicFlow;
        init();
    }

    private void init() {
        if(LogicDebugManager.isDebug()){
            // 等同newSingleThreadExecutor
            debugExecutor = new ThreadPoolExecutor(1, 1,
                    0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>());
            debugExecutor.execute(new Runnable(){
                @Override
                public void run() {
                    LogicDebugManager.init(logicFlow.getId());
                }
            });
        }
        initGlobalDeclare();
        initGlobalAction();
        unmodifiableDatabus = new UnmodifiableDatabus(databus);
    }

    private void initGlobalDeclare() {
        List<Declare> declares = logicFlow.getDeclares();
        Declare bus = declares.get(0);
        List<Import> imports = bus.getImports();
        if (imports != null) {
            initGlobalImport(imports);
        }
        List<Attr> attrs = bus.getAttrs();
        initGlobalAttr(attrs);
    }

    private void initGlobalImport(List<Import> imports) {
        for (Import imp : imports) {
            modelSet.add(imp.getModel());
        }
    }

    private void initGlobalAttr(List<Attr> attrs) {
        if (attrs != null) {
            this.flowDataDefine = ModelToDataDefineUtil.parseByFlow(logicFlow, attrs);
        } else {
            this.flowDataDefine = new FlowDataDefine(logicFlow.getId()); //初始化，修复入参没有检测是否声明的BUG。
        }
    }

    private void initGlobalAction() {
        List<Action> actions = logicFlow.getActions();
        for (Action action : actions) {
            actionMap.put(action.getId(), action);
        }
    }

    public JMap execute(JMap param, boolean isTransactionCommit) throws Exception {
        this.inputParameters = param;
        final JMap[] result = {null};
        final Throwable[] throwable = {null};
        try {
            ListenableFuture<String> startFuture = executeStart();

            ListenableFuture<String> nextFuture = executeAction(startFuture);

            ListenableFuture<JMap> resultFuture = executeEnd(nextFuture);

            Futures.addCallback(resultFuture, new FutureCallback<JMap>() {
                public void onSuccess(JMap _result) {
                    result[0] = _result;
                    latch.countDown();
                }

                public void onFailure(Throwable t) {
                    throwable[0] = t;
                    latch.countDown();
                }
            }, executor);

            latch.await(10, TimeUnit.SECONDS); //10秒超时，需要捕获超时
            if(latch.getCount()>0){
                IpuUtility.error("执行超时了");
            }
            if(throwable[0]!=null){
                IpuUtility.error(throwable[0]);
            }

            if (isTransactionCommit) {  //事务开关
                SqlSessionManager.commitAll();
            } else {
                SqlSessionManager.rollbackAll();
            }
        } catch (Exception e) {
            SqlSessionManager.rollbackAll();
            throw e;
        } finally {
            SqlSessionManager.closeAll();
            executor.shutdown();
        }
        return result[0];
    }

    public JMap execute(JMap param) throws Exception {
        return execute(param, true);
    }

    private ListenableFuture<String> executeStart() {
        ListenableFuture<String> startFuture = executor.submit(new Callable<String>(){
            @Override
            public String call() throws Exception {
                return checkStart();
            }
        });
        return startFuture;
    }

    private String checkStart() {
        Start start = getStart();
        List<Var> vars = start.getVars();
        if (vars != null) {
            Map<String, Object> startInput = null;
            if (LogicDebugManager.isDebug()) {
                startInput = new HashMap<String, Object>();
            }
            // 校验调用业务逻辑的入参数据
            String key;
            Object value;
            for (Var var : vars) {
                key = var.getKey();

                value = getValueFromVar(inputParameters, var);
                checkDataFromVar(var, key, value);

                key = var.getAlias() != null ? var.getAlias() : key; //alias不为空时，代替key成为入参别名
                databus.put(key, value);

                if (LogicDebugManager.isDebug()) {
                    startInput.put(key, value);
                }
            }

            if (LogicDebugManager.isDebug()) {
                final Map<String, Object> _startInput = startInput;
                debugExecutor.execute(new Runnable(){
                    @Override
                    public void run() {
                        LogicDebugManager.recordStartData(_startInput);
                    }
                });
            }
        }
        return start.getNext();
    }

    private ListenableFuture<String> executeAction(ListenableFuture<String> startFuture) throws ExecutionException, InterruptedException {
        ListenableFuture<String> lastFuture = startFuture;
        AsyncFunction asyncFunction = new AsyncFunction<String, String>() {
            public ListenableFuture<String> apply(final String next) throws Exception {
                ListenableFuture<String> temp = executor.submit(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return executeAction(next);
                    }
                });
                return temp;
            }
        };
        lastFuture = Futures.transformAsync(lastFuture, asyncFunction, executor);
        String next = lastFuture.get();
        if (actionMap.containsKey(next)) {
            return executeAction(lastFuture);
        } else {
            return lastFuture;
        }
    }

    private String executeAction(String next) throws Exception {
        Action action = actionMap.get(next);
        JMap param = prepareNodeInput(action);

        Object result = executeProcesser(action, param);

        prepareNodeOutput(action, result);

        if (action.getActionType() == Action.ActionType.IF) {
            // 条件判断节点则不取默认的next属性
            next = result == null ? action.getNext() : result.toString();
        } else {
            next = action.getNext();
        }
        return next;
    }

    private Object executeProcesser(Action action, JMap input) throws Exception {
        Object result = null;
        try {
            IActionProcesser actionProcesser = ProcesserManager.getActionProcesser(action.getActionType());
            result = actionProcesser.execute(unmodifiableDatabus, action, input);
        } catch (Exception e) {
            /* NullPointerException异常时，e.getMessage()为null,会导致后续异常报错。 */
            String eMsg = e.getMessage() == null ? "空信息" : e.getMessage();
            IpuUtility.errorCode(LogicFlowConstants.FLOW_ACTION_ERROR, logicFlow.getId(), action.getId(), eMsg);
        }
        return result;
    }

    private JMap prepareNodeInput(Action action) {
        List<Input> inputs = action.getInputs();
        if (inputs == null) {
            return null;
        }
        Input input = inputs.get(0);
        List<Var> vars = input.getVars();
        if (vars == null) {
            return null;
        }
        JMap param = new JsonMap();
        String key;
        Object value = null;
        for (Var var : vars) {
            key = var.getKey();

            value = getValueFromVar(databus, var);
            checkDataFromVar(var, key, value);

            key = var.getAlias() != null ? var.getAlias() : key; //alias不为空时，代替key成为入参别名
            param.put(key, value);
        }

        if(LogicDebugManager.isDebug()){
            debugExecutor.execute(new Runnable(){
                @Override
                public void run() {
                    LogicDebugManager.recordInputData(action.getId(), param);
                }
            });
        }
        return param;
    }

    private void prepareNodeOutput(Action action, Object result) {
        if(LogicDebugManager.isDebug()){
            debugExecutor.execute(new Runnable(){
                @Override
                public void run() {
                    LogicDebugManager.recordOutputData(action.getId(), result);
                }
            });
        }

        if (result == null) {
            return;
        }
        List<Output> outputs = action.getOutputs();
        if (outputs == null) {
            return;
        }
        Output output = outputs.get(0);
        List<Var> vars = output.getVars();
        if (vars == null || vars.size() == 0) {
            return;
        }

        if (output.isMapping()) {  //是否映射数据：表示要从结果集中获取多个值存储到数据总线中
            if (result instanceof Map) {
                Map resultMap = (Map) result;
                String key;
                for (Var var : vars) {
                    key = var.getKey();
                    if (databus.containsKey(key)) {
                        // 覆盖数据总线的数据需要记录日志，便于debug。
                    }
                    key = var.getAlias() != null ? var.getAlias() : key; //alias不为空时，代替key成为入参别名
                    databus.put(key, resultMap.get(key));
                }
            } else {
                IpuUtility.errorCode(LogicFlowConstants.FLOW_NOT_SUPPORT_MULTIPLE_KEY, logicFlow.getId(), action.getId());
            }
        } else {
            String key = vars.get(0).getKey();
            databus.put(key, result);
        }
    }

    public ListenableFuture<JMap> executeEnd(ListenableFuture<String> nextFuture){
        AsyncFunction asyncFunction = new AsyncFunction<String, JMap>() {
            public ListenableFuture<JMap> apply(final String next) throws Exception {
                ListenableFuture<JMap> temp = executor.submit(new Callable<JMap>() {
                    @Override
                    public JMap call() throws Exception {
                        return checkEnd(next);
                    }
                });
                return temp;
            }
        };
        ListenableFuture<JMap> endFuture = Futures.transformAsync(nextFuture, asyncFunction, executor);
        return endFuture;
    }

    public JMap checkEnd(String next) {
        End end = getEnd();

        JMap result = null;
        List<Var> vars = end.getVars();
        if (vars != null) {
            result = new JsonMap();
            String key;
            Object value;
            for (Var var : vars) {
                key = var.getKey();
                value = databus.get(key);
                key = var.getAlias() != null ? var.getAlias() : key; //alias不为空时，代替key成为入参别名
                result.put(key, value);
            }

            if (LogicDebugManager.isDebug()) {
                final JMap _result = result;
                debugExecutor.execute(new Runnable(){
                    @Override
                    public void run() {
                        LogicDebugManager.recordEndData(_result);
                    }
                });
            }
        }
        return result;
    }

    private Start getStart() {
        List<Start> starts = logicFlow.getStarts();
        return starts.get(0);
    }

    private End getEnd() {
        List<End> ends = logicFlow.getEnds();
        return ends.get(0);
    }

    private void checkData(String key, Object value) {
        if (flowDataDefine.getDataValidators().containsKey(key)) {
            flowDataDefine.checkData(key, value);
        }
    }

    private Object getValueFromVar(Map<String, Object> varParams, Var var) {
        String key = var.getKey();
        Object value = varParams.get(key);
        /*1.初始化值*/
        if (value == null) {
            value = var.getInitial();
        }
        /*2.初始化方法*/
        if (value == null) {
            String initialMethod = var.getInitialMethod();
            if (initialMethod != null) {
                try {
                    value = InitialMethodManager.getInitialMethod().invoke(initialMethod);
                } catch (Exception e) {
                    Throwable tr = IpuUtility.getBottomException(e);
                    IpuUtility.errorCode(LogicFlowConstants.FLOW_INITIAL_METHOD_ERROR, logicFlow.getId(), var.getKey(), initialMethod, tr.getMessage());
                }
            }
        }
        /*3.数值计算方法*/
        String calculateMethod = var.getCalculateMethod();
        if (calculateMethod != null) {
            try {
                value = CalculateMethodManager.getCalculateMethod().invoke(calculateMethod, value, unmodifiableDatabus); //start传参时，数据总线为空
            } catch (Exception e) {
                Throwable tr = IpuUtility.getBottomException(e);
                IpuUtility.errorCode(LogicFlowConstants.FLOW_CALCULATE_METHOD_ERROR, logicFlow.getId(), var.getKey(), calculateMethod, tr.getMessage());
            }
        }
        return value;
    }

    private void checkDataFromVar(Var var, String key, Object value) {
        if (var.getModel() != null) {
            if (var.getAttr() != null) {
                // 根据引入的业务模型做基础数据校验
                BDataDefine bDataDefine = BizDataManager.getDataDefine(var.getModel());
                bDataDefine.checkData(var.getAttr(), value);
            } else {
                // 根据引入的业务模型做模型数据校验
                BDataDefine bDataDefine = BizDataManager.getDataDefine(var.getModel());
                if (value instanceof JSONObject) {
                    bDataDefine.checkData((JSONObject) value, var.isAttrExists());
                } else if (value instanceof JSONArray) {
                    bDataDefine.checkData((JSONArray) value, var.isAttrExists());
                } else {
                    IpuUtility.errorCode(LogicFlowConstants.FLOW_NOT_MATCH_DATA_TYPE, logicFlow.getId(), key, String.valueOf(value));
                }
            }
        } else {
            // 根据定义的数据模型做数据校验
            checkData(key, value);
        }
    }
}
