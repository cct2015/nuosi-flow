package com.nuosi.flow.logic.invoke.processer;

import com.nuosi.flow.logic.model.body.Action;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>desc: 描述这个类功能的注释 </p>
 * <p>date: 2021/3/30 18:32 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class ProcesserManager {
    private static final Map<Action.ActionType, IActionProcesser> actionProcessers;
    private static final Map<Action.ActionType, IBehaviorProcesser> behaviorProcessers;

    static {
        Map<Action.ActionType, IActionProcesser> actionMap = new HashMap<Action.ActionType, IActionProcesser>(6);
        actionMap.put(Action.ActionType.SQL, new SqlProcesser());
        actionMap.put(Action.ActionType.EXPRESSION, new ExpressionProcesser());
        actionMap.put(Action.ActionType.IF, new IfProcesser());
        actionMap.put(Action.ActionType.FOREACH, new ForeachProcesser());
        actionMap.put(Action.ActionType.BEHAVIOR, new BehaviorProcesser());
        actionMap.put(Action.ActionType.SUBFLOW, new SubflowProcesser());
        actionMap.put(Action.ActionType.FUNCTION, new FunctionProcesser());
        actionMap.put(Action.ActionType.HTTP, new HttpProcesser());
        actionProcessers = Collections.unmodifiableMap(actionMap);

        Map<Action.ActionType, IBehaviorProcesser> behaviorMap = new HashMap<Action.ActionType, IBehaviorProcesser>(3);
        behaviorMap.put(Action.ActionType.SQL, new SqlProcesser());
        behaviorMap.put(Action.ActionType.EXPRESSION, new ExpressionProcesser());
        behaviorMap.put(Action.ActionType.FUNCTION, new FunctionProcesser());
        behaviorProcessers = Collections.unmodifiableMap(behaviorMap);
    }

    public static IActionProcesser getActionProcesser(Action.ActionType actionType) {
        return actionProcessers.get(actionType);
    }

    public static IBehaviorProcesser getBehaviorProcesser(Action.ActionType actionType) {
        return behaviorProcessers.get(actionType);
    }

}
