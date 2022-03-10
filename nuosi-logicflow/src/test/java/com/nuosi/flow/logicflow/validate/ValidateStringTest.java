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
 * <p>desc: 字符串限制的测试类 </p>
 * <p>date: 2021/9/4 23:34 </p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class ValidateStringTest {

    @Test
    public void testStringMinValidate(){
        JMap param = new JsonMap();
        param.put("string_param","123456");
        try {
            LogicFlowEngine.execute("string_min_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testStringMaxValidate(){
        JMap param = new JsonMap();
        param.put("string_param","12345678");
        try {
            LogicFlowEngine.execute("string_max_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testStringLessValidate(){
        JMap param = new JsonMap();
        param.put("string_param","1234567");
        try {
            LogicFlowEngine.execute("string_less_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testStringMoreValidate(){
        JMap param = new JsonMap();
        param.put("string_param","1234567");
        try {
            LogicFlowEngine.execute("string_more_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testStringEqualValidate() throws Exception {
        JMap param = new JsonMap();
        param.put("string_param","123456");
        try {
            LogicFlowEngine.execute("string_equal_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testStringUnequalValidate() throws Exception {
        JMap param = new JsonMap();
        param.put("string_param","1234567");
        try {
            LogicFlowEngine.execute("string_unequal_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testStringNullableValidate() throws Exception {
        JMap param = new JsonMap();
        param.put("string_param", null);
        try {
            LogicFlowEngine.execute("string_nullable_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Before
    public void before() throws IOException {
        String[] flowConfigs = {
                "logicflow/validate/string/string_min_validate.xml",
                "logicflow/validate/string/string_max_validate.xml",
                "logicflow/validate/string/string_less_validate.xml",
                "logicflow/validate/string/string_more_validate.xml",
                "logicflow/validate/string/string_equal_validate.xml",
                "logicflow/validate/string/string_unequal_validate.xml",
                "logicflow/validate/string/string_nullable_validate.xml"
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
