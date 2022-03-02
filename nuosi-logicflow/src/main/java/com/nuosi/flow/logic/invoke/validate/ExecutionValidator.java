package com.nuosi.flow.logic.invoke.validate;

import com.ai.ipu.basic.util.IpuUtility;
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
import com.nuosi.flow.util.LogicFlowConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>desc: 根据运行规则校验逻辑编排配置文件 </p>
 * <p>date: 2022/2/14 17:34 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class ExecutionValidator {
    private Map<String, Import> modelMap = new HashMap<String, Import>();   //数据模型
    private Map<String, Attr> attrMap = new HashMap<String, Attr>();        //数据属性
    private Map<String, Action> actionMap = new HashMap<String, Action>();  //节点名和节点实体映射关系
    private LogicFlow logicFlow;

    public ExecutionValidator(LogicFlow logicFlow) {
        this.logicFlow = logicFlow;
    }

    public void validate() throws Exception {
        validateGlobalDatabus();
        validateGlobalAction();
        String next = validateStart();
        next = validateAction(next);
        validateEnd(next);
    }

    private void validateGlobalDatabus() {
        List<Declare> declares = logicFlow.getDeclares();
        Declare bus = declares.get(0);
        /*记录数据模型*/
        List<Import> imports = bus.getImports();
        if (imports != null) {
            for (Import imp : imports) {
                modelMap.put(imp.getModel(), imp);
            }
        }
        /*记录数据属性*/
        List<Attr> attrs = bus.getAttrs();
        if (attrs != null) {
            for (Attr attr : attrs) {
                attrMap.put(attr.getId(), attr);
            }
        }
    }

    private void validateGlobalAction() {
        List<Action> actions = logicFlow.getActions();
        if (actions == null) {
            //校验：逻辑事件不能为空
            IpuUtility.errorCode(LogicFlowConstants.VALIDATE_ACTION_NULL, logicFlow.getId(), logicFlow.getName());
        }
        /*记录流程节点*/
        for (Action action : actions) {
            actionMap.put(action.getId(), action);
        }
    }

    private String validateStart() {
        Start start = getStart();
        List<Var> vars = start.getVars();
        if (vars != null) {
            String key, model;
            for (Var var : vars) {
                key = var.getKey();
                if (attrMap.containsKey(key)) {
                    continue;
                } else {
                    model = var.getModel();
                    if (model == null) {
                        //校验：变量键值没有被定义
                        IpuUtility.errorCode(LogicFlowConstants.VALIDATE_START_VAR_ATTR_UNDEFINE, key);
                    } else {
                        if (!modelMap.containsKey(model)) {
                            //校验：变量模型没有被定义
                            IpuUtility.errorCode(LogicFlowConstants.VALIDATE_START_VAR_MODEL_UNDEFINE, model);
                        }
                    }
                }
            }
        }

        String next = start.getNext();
        Action action = actionMap.get(next);
        if (action == null) {
            IpuUtility.errorCode(LogicFlowConstants.VALIDATE_ACTION_NEXT_NOT_EXIST, next);
        }
        return next;
    }

    private String validateAction(String next) throws Exception {
        Action action = actionMap.get(next);
        if (action == null) {
            End end = getEnd();
            if (next.equals(end.getId())) {
                return next;
            }
            IpuUtility.errorCode(LogicFlowConstants.VALIDATE_ACTION_NEXT_NOT_EXIST, next);
        }
        validateNodeInput(action);
        validateNodeOutput(action);
        next = validateAction(action.getNext());
        return next;
    }

    private void validateNodeInput(Action action) {
        List<Input> inputs = action.getInputs();
        if (inputs == null) {
            return;
        }
        Input input = inputs.get(0);
        List<Var> vars = input.getVars();
        if (vars == null) {
            return;
        }
        String key, model;
        for (Var var : vars) {
            key = var.getKey();
            if (attrMap.containsKey(key)) {
                continue;
            } else {
                model = var.getModel();
                if (model == null) {
                    //校验：节点变量键值没有被定义
                    IpuUtility.errorCode(LogicFlowConstants.VALIDATE_INPUT_VAR_ATTR_UNDEFINE, action.getId(), action.getName(), key);
                } else {
                    if (!modelMap.containsKey(model)) {
                        //校验：节点变量模型没有被定义
                        IpuUtility.errorCode(LogicFlowConstants.VALIDATE_INPUT_VAR_MODEL_UNDEFINE, action.getId(), action.getName(), model);
                    }
                }
            }
        }
    }

    private void validateNodeOutput(Action action) {
        List<Output> outputs = action.getOutputs();
        if (outputs == null) {
            return;
        }
        Output output = outputs.get(0);
        List<Var> vars = output.getVars();
        if (vars == null || vars.size() == 0) {
            return;
        }
    }

    public void validateEnd(String next) {
        End end = getEnd();
        List<Var> vars = end.getVars();
        if (vars == null||vars.size()<1) {
            //校验：结束节点的返回值不能为空
            IpuUtility.errorCode(LogicFlowConstants.VALIDATE_END_VAR_NULL, logicFlow.getId());
        }
    }

    private Start getStart() {
        List<Start> starts = logicFlow.getStarts();
        if (starts == null || starts.size() != 1) {
            IpuUtility.errorCode(LogicFlowConstants.VALIDATE_START_SINGLE, logicFlow.getId());
        }
        return starts.get(0);
    }

    private End getEnd() {
        List<End> ends = logicFlow.getEnds();
        if (ends == null || ends.size() != 1) {
            IpuUtility.errorCode(LogicFlowConstants.VALIDATE_END_SINGLE, logicFlow.getId());
        }
        return ends.get(0);
    }
}
