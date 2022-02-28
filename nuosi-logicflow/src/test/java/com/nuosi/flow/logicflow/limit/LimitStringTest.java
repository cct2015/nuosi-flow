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
 * <p>desc: 字符串限制的测试类 </p>
 * <p>date: 2021/9/4 23:34 </p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class LimitStringTest {

    @Test
    public void testStringMinLimit(){
        JMap param = new JsonMap();
        param.put("string_param","123456");
        try {
            LogicFlowEngine.execute("string_min_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testStringMaxLimit(){
        JMap param = new JsonMap();
        param.put("string_param","12345678");
        try {
            LogicFlowEngine.execute("string_max_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testStringLessLimit(){
        JMap param = new JsonMap();
        param.put("string_param","1234567");
        try {
            LogicFlowEngine.execute("string_less_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testStringMoreLimit(){
        JMap param = new JsonMap();
        param.put("string_param","1234567");
        try {
            LogicFlowEngine.execute("string_more_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testStringEqualLimit() throws Exception {
        JMap param = new JsonMap();
        param.put("string_param","123456");
        try {
            LogicFlowEngine.execute("string_equal_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testStringUnequalLimit() throws Exception {
        JMap param = new JsonMap();
        param.put("string_param","1234567");
        try {
            LogicFlowEngine.execute("string_unequal_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testStringNullableLimit() throws Exception {
        JMap param = new JsonMap();
        param.put("string_param", null);
        try {
            LogicFlowEngine.execute("string_nullable_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Before
    public void before() throws IOException {
        String[] flowConfigs = {
                "logicflow/limit/string/string_min_limit.xml",
                "logicflow/limit/string/string_max_limit.xml",
                "logicflow/limit/string/string_less_limit.xml",
                "logicflow/limit/string/string_more_limit.xml",
                "logicflow/limit/string/string_equal_limit.xml",
                "logicflow/limit/string/string_unequal_limit.xml",
                "logicflow/limit/string/string_nullable_limit.xml"
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
