package com.nuosi.flow.logicflow.debug;

import com.ai.ipu.data.JMap;
import com.ai.ipu.data.impl.JsonMap;
import com.nuosi.flow.logic.LogicFlowEngine;
import com.nuosi.flow.logic.invoke.debug.LogicDebugManager;
import com.nuosi.flow.util.LogicFlowUtil;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>desc: 逻辑流引擎在线调试功能测试 </p>
 * <p>date: 2022/4/11 23:13 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class LogicFlowDebugTest {

    @Test
    public void testDebug() throws Exception {
        LogicDebugManager.openMainSwitch();     // 打开总开关
        LogicDebugManager.openLogicSwitch("simple_logic_flow_example");    // 打开指定逻辑开关
        LogicDebugManager.openUserSwitch("zhangsan");     // 打开指定用户开关

        JMap param = new JsonMap();
        param.put("goods_name","橙汁");
        param.put("goods_type",1);
        Object result = LogicFlowEngine.execute("simple_logic_flow_example",param);
        System.out.println("调试信息：" + LogicDebugManager.getLogicDebugData());
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
