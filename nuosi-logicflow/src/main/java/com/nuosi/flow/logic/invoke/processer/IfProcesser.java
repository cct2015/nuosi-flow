package com.nuosi.flow.logic.invoke.processer;

import com.ai.ipu.basic.util.IpuUtility;
import com.ai.ipu.data.JMap;
import com.nuosi.flow.logic.model.action.If;
import com.nuosi.flow.logic.model.body.Action;
import com.nuosi.flow.util.IfUtil;
import com.nuosi.flow.util.LogicFlowConstants;
import org.mvel2.MVEL;

import java.util.List;
import java.util.Map;

/**
 * <p>desc: 节点执行处理器的条件判断类型实现 </p>
 * <p>date: 2021/4/15 13:30 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class IfProcesser implements IActionProcesser {
    private static final String INPUT = LogicFlowConstants.INPUT;
    private static final String DATABUS = LogicFlowConstants.DATABUS;
    private static final Boolean TURE = Boolean.valueOf(true);

    @Override
    public Object execute(Map<String, Object> databus, Action action, JMap input, Object ... param) throws Exception {
        List<If> ifs = action.getIfs();

        Object result = null;
        for (If ifttt : ifs) {
            result = MVEL.eval(ifttt.getTest(), databus);
            if (TURE.equals(result)) {
                if (ifttt.getInterrupt() != null) {
                    String interrupt = IfUtil.renderTemplate(ifttt.getInterrupt(), databus);
                    IpuUtility.error(interrupt);
                } else if (ifttt.getNext() != null) {
                    return ifttt.getNext();
                } else {
                    IpuUtility.errorCode(LogicFlowConstants.FLOW_IF_ERROR);
                }
            }
        }
        return null;
    }
}
