package com.nuosi.flow.logicflow.validate;

import com.ai.ipu.data.JMap;
import com.ai.ipu.data.impl.JsonMap;
import com.nuosi.flow.logic.LogicFlowEngine;
import com.nuosi.flow.util.LogicFlowUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * <p>desc: 长整型限制的测试类 </p>
 * <p>date: 2022/4/25 19:13 </p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class ValidateLongTest {

    @Test
    public void testLongFormatValidate() {
        JMap param = new JsonMap();
        param.put("long_param", "abc");
        try {
            LogicFlowEngine.execute("long_format_validate", param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testLongMinValidate() {
        JMap param = new JsonMap();
        param.put("long_param", "7777777776");
        try {
            LogicFlowEngine.execute("long_min_validate", param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testLongMaxValidate() {
        JMap param = new JsonMap();
        param.put("long_param", "7777777778");
        try {
            LogicFlowEngine.execute("long_max_validate", param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testLongLessValidate() {
        JMap param = new JsonMap();
        param.put("long_param", "7777777777");
        try {
            LogicFlowEngine.execute("long_less_validate", param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testLongMoreValidate() throws Exception {
        JMap param = new JsonMap();
        param.put("long_param", "7777777777");
        try {
            LogicFlowEngine.execute("long_more_validate", param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testLongEqualValidate() throws Exception {
        JMap param = new JsonMap();
        param.put("long_param", "77777777777");
        try {
            LogicFlowEngine.execute("long_equal_validate", param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testLongUnequalValidate() throws Exception {
        JMap param = new JsonMap();
        param.put("long_param", "7777777777");
        try {
            LogicFlowEngine.execute("long_unequal_validate", param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Before
    public void before() throws IOException {
        String[] flowConfigs = {
                "logicflow/validate/long/long_format_validate.xml",
                "logicflow/validate/long/long_min_validate.xml",
                "logicflow/validate/long/long_max_validate.xml",
                "logicflow/validate/long/long_less_validate.xml",
                "logicflow/validate/long/long_more_validate.xml",
                "logicflow/validate/long/long_equal_validate.xml",
                "logicflow/validate/long/long_unequal_validate.xml"
        };
        LogicFlowUtil.loadLogicFlows(flowConfigs);
    }
}
