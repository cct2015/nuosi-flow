package com.nuosi.flow.logicflow.action;

import com.ai.ipu.data.JMap;
import com.ai.ipu.data.impl.JsonMap;
import com.nuosi.flow.logic.LogicFlowEngine;
import com.nuosi.flow.util.LogicFlowUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * <p>desc: 条件分支相关语法展示 </p>
 * <p>date: 2022/3/8 22:32 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class IfSyntaxTest {

    @Test
    public void testIfAttrExcept(){
        JMap param = new JsonMap();
        try {
            LogicFlowEngine.execute("if_attr_except", param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Before
    public void before() throws IOException {
        String[] flowConfigs = {
                "logicflow/action/if/if_attr_except.xml"
        };
        LogicFlowUtil.loadLogicFlows(flowConfigs);
    }
}
