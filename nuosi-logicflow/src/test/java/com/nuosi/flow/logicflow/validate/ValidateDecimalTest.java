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
 * <p>desc: 小数限制的测试类 </p>
 * <p>date: 2021/9/15 10:25 </p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class ValidateDecimalTest {

    @Test
    public void testDecimalFormatValidate(){
        JMap param = new JsonMap();
        param.put("decimal_param","abc");
        try {
            LogicFlowEngine.execute("decimal_format_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDecimalPrecisionValidate(){
        JMap param = new JsonMap();
        param.put("decimal_param","77.77");
        try {
            LogicFlowEngine.execute("decimal_precision_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDecimalScaleValidate(){
        JMap param = new JsonMap();
        param.put("decimal_param","7.777");
        try {
            LogicFlowEngine.execute("decimal_scale_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDecimalMinValidate(){
        JMap param = new JsonMap();
        param.put("decimal_param","7.76");
        try {
            LogicFlowEngine.execute("decimal_min_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDecimalMaxValidate(){
        JMap param = new JsonMap();
        param.put("decimal_param","7.78");
        try {
            LogicFlowEngine.execute("decimal_max_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDecimalLessValidate(){
        JMap param = new JsonMap();
        param.put("decimal_param","7.77");
        try {
            LogicFlowEngine.execute("decimal_less_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDecimalMoreValidate() throws Exception {
        JMap param = new JsonMap();
        param.put("decimal_param","7.77");
        try {
            LogicFlowEngine.execute("decimal_more_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDecimalEqualValidate() throws Exception {
        JMap param = new JsonMap();
        param.put("decimal_param","7.78");
        try {
            LogicFlowEngine.execute("decimal_equal_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDecimalUnequalValidate() throws Exception {
        JMap param = new JsonMap();
        param.put("decimal_param","7.77");
        try {
            LogicFlowEngine.execute("decimal_unequal_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Before
    public void before() throws IOException {
        String[] flowConfigs = {
                "logicflow/validate/decimal/decimal_format_validate.xml",
                "logicflow/validate/decimal/decimal_precision_validate.xml",
                "logicflow/validate/decimal/decimal_scale_validate.xml",
                "logicflow/validate/decimal/decimal_min_validate.xml",
                "logicflow/validate/decimal/decimal_max_validate.xml",
                "logicflow/validate/decimal/decimal_less_validate.xml",
                "logicflow/validate/decimal/decimal_more_validate.xml",
                "logicflow/validate/decimal/decimal_equal_validate.xml",
                "logicflow/validate/decimal/decimal_unequal_validate.xml"
        };
        LogicFlowUtil.loadLogicFlows(flowConfigs);
    }
}
