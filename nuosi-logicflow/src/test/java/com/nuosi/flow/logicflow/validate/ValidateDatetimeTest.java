package com.nuosi.flow.logicflow.validate;

import com.ai.ipu.data.JMap;
import com.ai.ipu.data.impl.JsonMap;
import com.nuosi.flow.logic.LogicFlowEngine;
import com.nuosi.flow.logic.LogicFlowManager;
import com.nuosi.flow.util.LogicFlowUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>desc: 日期时间限制的测试类 </p>
 * <p>date: 2021/9/15 22:24 </p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class ValidateDatetimeTest {

    @Test
    public void testDatetimeFormatValidate(){
        JMap param = new JsonMap();
        param.put("datetime_param","abc");
        try {
            LogicFlowEngine.execute("datetime_format_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDatetimeMinValidate(){
        JMap param = new JsonMap();
        param.put("datetime_param","2021-07-07 07:07:06");
        try {
            LogicFlowEngine.execute("datetime_min_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDatetimeMaxValidate(){
        JMap param = new JsonMap();
        param.put("datetime_param","2021-07-07 07:07:08");
        try {
            LogicFlowEngine.execute("datetime_max_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDatetimeLessValidate(){
        JMap param = new JsonMap();
        param.put("datetime_param","2021-07-07 07:07:07");
        try {
            LogicFlowEngine.execute("datetime_less_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDatetimeMoreValidate() throws Exception {
        JMap param = new JsonMap();
        param.put("datetime_param","2021-07-07 07:07:07");
        try {
            LogicFlowEngine.execute("datetime_more_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDatetimeEqualValidate() throws Exception {
        JMap param = new JsonMap();
        param.put("datetime_param","2021-07-07 07:07:06");
        try {
            LogicFlowEngine.execute("datetime_equal_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDatetimeUnequalValidate() throws Exception {
        JMap param = new JsonMap();
        param.put("datetime_param","2021-07-07 07:07:07");
        try {
            LogicFlowEngine.execute("datetime_unequal_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDatetimeNullableValidate() throws Exception {
        JMap param = new JsonMap();
        param.put("datetime_param", null);
        try {
            LogicFlowEngine.execute("datetime_nullable_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Before
    public void before() throws IOException {
        String[] flowConfigs = {
                "logicflow/validate/datetime/datetime_format_validate.xml",
                "logicflow/validate/datetime/datetime_min_validate.xml",
                "logicflow/validate/datetime/datetime_max_validate.xml",
                "logicflow/validate/datetime/datetime_less_validate.xml",
                "logicflow/validate/datetime/datetime_more_validate.xml",
                "logicflow/validate/datetime/datetime_equal_validate.xml",
                "logicflow/validate/datetime/datetime_unequal_validate.xml",
                "logicflow/validate/datetime/datetime_nullable_validate.xml"
        };
        LogicFlowUtil.loadLogicFlows(flowConfigs);
    }
}
