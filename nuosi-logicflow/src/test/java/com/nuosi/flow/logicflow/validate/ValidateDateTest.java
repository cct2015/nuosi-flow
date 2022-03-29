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
 * <p>desc: 日期限制的测试类 </p>
 * <p>date: 2021/9/15 22:24 </p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class ValidateDateTest {

    @Test
    public void testDateFormatValidate(){
        JMap param = new JsonMap();
        param.put("date_param","abc");
        try {
            LogicFlowEngine.execute("date_format_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDateMinValidate(){
        JMap param = new JsonMap();
        param.put("date_param","2021-07-06");
        try {
            LogicFlowEngine.execute("date_min_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDateMaxValidate(){
        JMap param = new JsonMap();
        param.put("date_param","2021-07-08");
        try {
            LogicFlowEngine.execute("date_max_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDateLessValidate(){
        JMap param = new JsonMap();
        param.put("date_param","2021-07-07");
        try {
            LogicFlowEngine.execute("date_less_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDateMoreValidate() throws Exception {
        JMap param = new JsonMap();
        param.put("date_param","2021-07-07");
        try {
            LogicFlowEngine.execute("date_more_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDateEqualValidate() throws Exception {
        JMap param = new JsonMap();
        param.put("date_param","2021-07-06");
        try {
            LogicFlowEngine.execute("date_equal_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDateUnequalValidate() throws Exception {
        JMap param = new JsonMap();
        param.put("date_param","2021-07-07");
        try {
            LogicFlowEngine.execute("date_unequal_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDateNullableValidate() throws Exception {
        JMap param = new JsonMap();
        param.put("date_param", null);
        try {
            LogicFlowEngine.execute("date_nullable_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Before
    public void before() throws IOException {
        String[] flowConfigs = {
                "logicflow/validate/date/date_format_validate.xml",
                "logicflow/validate/date/date_min_validate.xml",
                "logicflow/validate/date/date_max_validate.xml",
                "logicflow/validate/date/date_less_validate.xml",
                "logicflow/validate/date/date_more_validate.xml",
                "logicflow/validate/date/date_equal_validate.xml",
                "logicflow/validate/date/date_unequal_validate.xml",
                "logicflow/validate/date/date_nullable_validate.xml"
        };
        LogicFlowUtil.loadLogicFlows(flowConfigs);
    }
}
