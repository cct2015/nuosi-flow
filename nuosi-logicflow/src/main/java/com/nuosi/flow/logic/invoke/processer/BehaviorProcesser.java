package com.nuosi.flow.logic.invoke.processer;

import com.ai.ipu.basic.util.IpuUtility;
import com.ai.ipu.data.JMap;
import com.nuosi.flow.logic.model.body.Action;
import com.nuosi.flow.logic.model.domain.Behavior;
import com.nuosi.flow.logic.model.domain.BehaviorManager;
import com.nuosi.flow.util.LogicFlowConstants;

import java.util.List;
import java.util.Map;

/**
 * <p>desc: 节点执行处理器的模型行为类型实现 </p>
 * <p>date: 2021/5/13 18:41 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class BehaviorProcesser implements IActionProcesser {

    @Override
    public Object execute(Map<String, Object> databus, Action action, JMap input, Object... param) throws Exception {
        List<Behavior> behaviors = action.getBehaviors();
        Behavior behavior = behaviors.get(0);
        Behavior modelBehavior = getModelBehavior(behavior.getModel(), behavior.getId());
        if(modelBehavior==null){
            IpuUtility.errorCode(LogicFlowConstants.FLOW_BEHAVIOR_NOT_EXIST, behavior.getModel(), behavior.getId());
        }
        IBehaviorProcesser behaviorProcesser = ProcesserManager.getBehaviorProcesser(modelBehavior.getActionType());
        Object result = behaviorProcesser.execute(databus, modelBehavior, input, param);
        return result;
    }

    private Behavior getModelBehavior(String model, String id) {
        Behavior behavior = BehaviorManager.getBehavior(model, id);
        return behavior;
    }
}
