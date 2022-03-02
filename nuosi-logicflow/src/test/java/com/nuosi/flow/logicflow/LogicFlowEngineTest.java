package com.nuosi.flow.logicflow;

import com.ai.ipu.data.JMap;
import com.ai.ipu.data.impl.JsonMap;
import com.nuosi.flow.logic.LogicFlowEngine;
import com.nuosi.flow.util.LogicFlowUtil;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>desc: 逻辑流引擎单元测试</p>
 * <p>date: 2021/3/29 21:17</p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class LogicFlowEngineTest {

    @Test
    public void testExecute() throws Exception {
        JMap param = new JsonMap();
        param.put("goods_name","橙汁");
        param.put("goods_type",1);
        Object result = LogicFlowEngine.execute("simple_logic_flow_example",param);
        System.out.println("result===" + result);
    }

    @Before
    public void before(){
        String[] modelConfigs = {
                "model/goods_model.xml"
        };
        LogicFlowUtil.loadLogicModels(modelConfigs);

        String[] flowConfigs = {
                "logicflow/simple_flow.xml"
        };
        LogicFlowUtil.loadLogicFlows(flowConfigs);
    }
}
