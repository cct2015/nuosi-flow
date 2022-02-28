package com.nuosi.flow.logicflow.limit;

import com.ai.ipu.data.JMap;
import com.ai.ipu.data.impl.JsonMap;
import com.nuosi.flow.logic.LogicFlowEngine;
import com.nuosi.flow.logic.LogicFlowManager;
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
public class LimitDecimalTest {

    @Test
    public void testDecimalFormatLimit(){
        JMap param = new JsonMap();
        param.put("decimal_param","abc");
        try {
            LogicFlowEngine.execute("decimal_format_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDecimalPrecisionLimit(){
        JMap param = new JsonMap();
        param.put("decimal_param","77.77");
        try {
            LogicFlowEngine.execute("decimal_precision_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDecimalScaleLimit(){
        JMap param = new JsonMap();
        param.put("decimal_param","7.777");
        try {
            LogicFlowEngine.execute("decimal_scale_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDecimalMinLimit(){
        JMap param = new JsonMap();
        param.put("decimal_param","7.76");
        try {
            LogicFlowEngine.execute("decimal_min_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDecimalMaxLimit(){
        JMap param = new JsonMap();
        param.put("decimal_param","7.78");
        try {
            LogicFlowEngine.execute("decimal_max_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDecimalLessLimit(){
        JMap param = new JsonMap();
        param.put("decimal_param","7.77");
        try {
            LogicFlowEngine.execute("decimal_less_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDecimalMoreLimit() throws Exception {
        JMap param = new JsonMap();
        param.put("decimal_param","7.77");
        try {
            LogicFlowEngine.execute("decimal_more_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDecimalEqualLimit() throws Exception {
        JMap param = new JsonMap();
        param.put("decimal_param","7.78");
        try {
            LogicFlowEngine.execute("decimal_equal_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDecimalUnequalLimit() throws Exception {
        JMap param = new JsonMap();
        param.put("decimal_param","7.77");
        try {
            LogicFlowEngine.execute("decimal_unequal_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Before
    public void before() throws IOException {
        String[] flowConfigs = {
                "logicflow/limit/decimal/decimal_format_limit.xml",
                "logicflow/limit/decimal/decimal_precision_limit.xml",
                "logicflow/limit/decimal/decimal_scale_limit.xml",
                "logicflow/limit/decimal/decimal_min_limit.xml",
                "logicflow/limit/decimal/decimal_max_limit.xml",
                "logicflow/limit/decimal/decimal_less_limit.xml",
                "logicflow/limit/decimal/decimal_more_limit.xml",
                "logicflow/limit/decimal/decimal_equal_limit.xml",
                "logicflow/limit/decimal/decimal_unequal_limit.xml"
        };
        for(String flowConfig : flowConfigs){
            InputStream is = getClass().getClassLoader().getResourceAsStream(flowConfig);
            try {
                LogicFlowManager.registerLogicFlow(is);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(is!=null)
                    is.close();
            }
        }
    }
}
