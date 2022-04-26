package com.nuosi.flow.logic;

import com.ai.ipu.basic.util.IpuBaseException;
import com.ai.ipu.basic.util.IpuUtility;
import com.ai.ipu.data.JMap;
import com.nuosi.flow.logic.inject.calculate.CalculateMethodManager;
import com.nuosi.flow.logic.inject.function.FunctionManager;
import com.nuosi.flow.logic.inject.initial.InitialMethodManager;
import com.nuosi.flow.logic.invoke.ExecutionContainer;
import com.nuosi.flow.logic.invoke.ExecutionContainerAsync;
import com.nuosi.flow.logic.invoke.validate.ExecutionValidator;
import com.nuosi.flow.logic.model.LogicFlow;
import com.nuosi.flow.mgmt.message.MessageManager;
import com.nuosi.flow.util.LogicFlowConstants;

/**
 * <p>desc: 逻辑流引擎入口类</p>
 * <p>date: 2021/3/6 11:26</p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class LogicFlowEngine {
    static {
        /*
        * 解决javax.xml.parsers.DocumentBuilderFactory.setFeature(Ljava/lang/String;Z)V异常
        * https://www.cnblogs.com/kzd666/p/14364287.html
        */
        System.setProperty("javax.xml.parsers.DocumentBuilderFactory",
                "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl");
        init();
        MessageManager.init();
        FunctionManager.init();
        InitialMethodManager.init();
        CalculateMethodManager.init();
    }

    public static JMap execute(String flowName, JMap param, boolean isTransactionCommit) throws Exception {
        /*1.获取逻辑流程的配置*/
        LogicFlow logicFlow = LogicFlowManager.getLogicFlow(flowName);
        if(logicFlow==null){
            IpuUtility.errorCode(LogicFlowConstants.FLOW_NOT_EXISTS, flowName);
        }
        /*2.校验服务逻辑流配置*/
        //new ExecutionValidator(logicFlow).validate();
        /*3.解析配置执行逻辑*/
        JMap result = new ExecutionContainer(logicFlow).execute(param, isTransactionCommit);
        return result;
    }

    public static JMap execute(String flowName, JMap param) throws Exception {
        return execute(flowName, param, true);
    }

    public static JMap executeAsync(String flowName, JMap param, boolean isTransactionCommit) throws Exception {
        /*1.获取逻辑流程的配置*/
        LogicFlow logicFlow = LogicFlowManager.getLogicFlow(flowName);
        if(logicFlow==null){
            IpuUtility.errorCode(LogicFlowConstants.FLOW_NOT_EXISTS, flowName);
        }
        /*2.校验服务逻辑流配置*/
        //new ExecutionValidator(logicFlow).validate();
        /*3.解析配置执行逻辑*/
        JMap result = new ExecutionContainerAsync(logicFlow).execute(param, isTransactionCommit);
        return result;
    }

    public static JMap executeAsync(String flowName, JMap param) throws Exception {
        return executeAsync(flowName, param, true);
    }

    public static void validate(String flowName) throws Exception {
        /*1.获取逻辑流程的配置*/
        LogicFlow logicFlow = LogicFlowManager.getLogicFlow(flowName);
        if(logicFlow==null){
            IpuUtility.errorCode(LogicFlowConstants.FLOW_NOT_EXISTS, flowName);
        }
        /*2.校验服务逻辑流配置*/
        new ExecutionValidator(logicFlow).validate();
    }

    public static void init() {
        initExceptionCode();
    }

    static void initExceptionCode(){
        // 热部署会多次加载，因此需要捕获并忽略异常
        IpuBaseException.registerCode(LogicFlowConstants.PACKAGE_PATH + "exception_messages", true);
    }
}
