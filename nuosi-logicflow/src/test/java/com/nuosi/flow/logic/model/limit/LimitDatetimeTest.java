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
 * <p>desc: 日期时间限制的测试类 </p>
 * <p>date: 2021/9/15 22:24 </p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class LimitDatetimeTest {

    @Test
    public void testDatetimeFormatLimit(){
        JMap param = new JsonMap();
        param.put("datetime_param","abc");
        try {
            LogicFlowEngine.execute("datetime_format_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDatetimeMinLimit(){
        JMap param = new JsonMap();
        param.put("datetime_param","2021-07-07 07:07:06");
        try {
            LogicFlowEngine.execute("datetime_min_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDatetimeMaxLimit(){
        JMap param = new JsonMap();
        param.put("datetime_param","2021-07-07 07:07:08");
        try {
            LogicFlowEngine.execute("datetime_max_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDatetimeLessLimit(){
        JMap param = new JsonMap();
        param.put("datetime_param","2021-07-07 07:07:07");
        try {
            LogicFlowEngine.execute("datetime_less_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDatetimeMoreLimit() throws Exception {
        JMap param = new JsonMap();
        param.put("datetime_param","2021-07-07 07:07:07");
        try {
            LogicFlowEngine.execute("datetime_more_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDatetimeEqualLimit() throws Exception {
        JMap param = new JsonMap();
        param.put("datetime_param","2021-07-07 07:07:06");
        try {
            LogicFlowEngine.execute("datetime_equal_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDatetimeUnequalLimit() throws Exception {
        JMap param = new JsonMap();
        param.put("datetime_param","2021-07-07 07:07:07");
        try {
            LogicFlowEngine.execute("datetime_unequal_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Before
    public void before() throws IOException {
        String[] flowConfigs = {
                "limit/datetime/datetime_format_limit.xml",
                "limit/datetime/datetime_min_limit.xml",
                "limit/datetime/datetime_max_limit.xml",
                "limit/datetime/datetime_less_limit.xml",
                "limit/datetime/datetime_more_limit.xml",
                "limit/datetime/datetime_equal_limit.xml",
                "limit/datetime/datetime_unequal_limit.xml"
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
