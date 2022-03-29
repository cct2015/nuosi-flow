package com.nuosi.flow.logicflow.action;

import com.ai.ipu.data.JMap;
import com.ai.ipu.data.impl.JsonMap;
import com.nuosi.flow.logic.LogicFlowEngine;
import com.nuosi.flow.util.LogicFlowUtil;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>desc: 子流程逻辑相关语法展示 </p>
 * <p>date: 2021/5/16 10:16 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class SubflowSyntaxTest {

    @Test
    public void testSubflowSimple() throws Exception {
        JMap param = new JsonMap();
        param.put("staff_id","zhangs");
        Object result = LogicFlowEngine.execute("subflow_syntax",param);
        System.out.println("result===" + result);
    }

    @Before
    public void setUp() {
        String[] flowConfigs = {
                "logicflow/action/subflow/subflow_syntax.xml",
                "working_hours/flow/working_hours_query.xml"
        };

        String[] modelConfigs = {
                "working_hours/model/working_hours_entity.xml"
        };
        LogicFlowUtil.loadLogicFlows(flowConfigs);
        LogicFlowUtil.loadLogicModels(modelConfigs);
    }
}
