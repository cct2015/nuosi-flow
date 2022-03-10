package com.nuosi.flow.logicflow.validate;

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
 * <p>desc: 整型限制的测试类 </p>
 * <p>date: 2021/9/4 14:38 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class ValidateIntegerTest {

    @Test
    public void testIntFormatValidate(){
        JMap param = new JsonMap();
        param.put("int_param","abc");
        try {
            LogicFlowEngine.execute("int_format_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testIntMinValidate(){
        JMap param = new JsonMap();
        param.put("int_param","6");
        try {
            LogicFlowEngine.execute("int_min_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testIntMaxValidate(){
        JMap param = new JsonMap();
        param.put("int_param","8");
        try {
            LogicFlowEngine.execute("int_max_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testIntLessValidate(){
        JMap param = new JsonMap();
        param.put("int_param","7");
        try {
            LogicFlowEngine.execute("int_less_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testIntMoreValidate() throws Exception {
        JMap param = new JsonMap();
        param.put("int_param","7");
        try {
            LogicFlowEngine.execute("int_more_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testIntEqualValidate() throws Exception {
        JMap param = new JsonMap();
        param.put("int_param","77");
        try {
            LogicFlowEngine.execute("int_equal_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testIntUnequalValidate() throws Exception {
        JMap param = new JsonMap();
        param.put("int_param","7");
        try {
            LogicFlowEngine.execute("int_unequal_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Before
    public void before() throws IOException {
        String[] flowConfigs = {
                "logicflow/validate/int/int_format_validate.xml",
                "logicflow/validate/int/int_min_validate.xml",
                "logicflow/validate/int/int_max_validate.xml",
                "logicflow/validate/int/int_less_validate.xml",
                "logicflow/validate/int/int_more_validate.xml",
                "logicflow/validate/int/int_equal_validate.xml",
                "logicflow/validate/int/int_unequal_validate.xml"
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
