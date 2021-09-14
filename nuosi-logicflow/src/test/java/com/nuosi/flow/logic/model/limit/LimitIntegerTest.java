package com.nuosi.flow.logic.model.limit;

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
public class LimitIntegerTest {

    @Test
    public void testIntMinLimit(){
        JMap param = new JsonMap();
        param.put("int_param","6");
        try {
            LogicFlowEngine.execute("int_min_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testIntMaxLimit(){
        JMap param = new JsonMap();
        param.put("int_param","8");
        try {
            LogicFlowEngine.execute("int_max_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testIntLessLimit(){
        JMap param = new JsonMap();
        param.put("int_param","7");
        try {
            LogicFlowEngine.execute("int_less_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testIntMoreLimit() throws Exception {
        JMap param = new JsonMap();
        param.put("int_param","7");
        try {
            LogicFlowEngine.execute("int_more_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testIntEqualLimit() throws Exception {
        JMap param = new JsonMap();
        param.put("int_param","77");
        try {
            LogicFlowEngine.execute("int_equal_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testIntUnequalLimit() throws Exception {
        JMap param = new JsonMap();
        param.put("int_param","7");
        try {
            LogicFlowEngine.execute("int_unequal_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Before
    public void before() throws IOException {
        String[] flowConfigs = {
                "limit/int/int_min_limit.xml",
                "limit/int/int_max_limit.xml",
                "limit/int/int_less_limit.xml",
                "limit/int/int_more_limit.xml",
                "limit/int/int_equal_limit.xml",
                "limit/int/int_unequal_limit.xml"
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
