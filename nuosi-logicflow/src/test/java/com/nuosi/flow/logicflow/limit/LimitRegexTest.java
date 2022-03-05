package com.nuosi.flow.logicflow.limit;

import com.ai.ipu.data.JMap;
import com.ai.ipu.data.impl.JsonMap;
import com.nuosi.flow.logic.LogicFlowEngine;
import com.nuosi.flow.util.LogicFlowUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * <p>desc: 正则表达式限制的测试类 </p>
 * <p>date: 2022/3/5 14:22 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class LimitRegexTest {

    @Test
    public void testRegexDefine(){
        JMap param = new JsonMap();
        /*try {
            LogicFlowEngine.execute("regex_define",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }*/
    }

    @Before
    public void before() throws IOException {
        String[] flowConfigs = {
                "logicflow/limit/regex/regex_define.xml"
        };
        LogicFlowUtil.loadLogicFlows(flowConfigs);
    }
}
