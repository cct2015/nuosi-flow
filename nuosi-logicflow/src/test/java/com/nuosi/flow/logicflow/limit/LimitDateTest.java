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
 * <p>desc: 日期限制的测试类 </p>
 * <p>date: 2021/9/15 22:24 </p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class LimitDateTest {

    @Test
    public void testDateFormatLimit(){
        JMap param = new JsonMap();
        param.put("date_param","abc");
        try {
            LogicFlowEngine.execute("date_format_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDateMinLimit(){
        JMap param = new JsonMap();
        param.put("date_param","2021-07-06");
        try {
            LogicFlowEngine.execute("date_min_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDateMaxLimit(){
        JMap param = new JsonMap();
        param.put("date_param","2021-07-08");
        try {
            LogicFlowEngine.execute("date_max_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDateLessLimit(){
        JMap param = new JsonMap();
        param.put("date_param","2021-07-07");
        try {
            LogicFlowEngine.execute("date_less_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDateMoreLimit() throws Exception {
        JMap param = new JsonMap();
        param.put("date_param","2021-07-07");
        try {
            LogicFlowEngine.execute("date_more_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDateEqualLimit() throws Exception {
        JMap param = new JsonMap();
        param.put("date_param","2021-07-06");
        try {
            LogicFlowEngine.execute("date_equal_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDateUnequalLimit() throws Exception {
        JMap param = new JsonMap();
        param.put("date_param","2021-07-07");
        try {
            LogicFlowEngine.execute("date_unequal_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDateNullableLimit() throws Exception {
        JMap param = new JsonMap();
        param.put("date_param", null);
        try {
            LogicFlowEngine.execute("date_nullable_limit",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Before
    public void before() throws IOException {
        String[] flowConfigs = {
                "logicflow/limit/date/date_format_limit.xml",
                "logicflow/limit/date/date_min_limit.xml",
                "logicflow/limit/date/date_max_limit.xml",
                "logicflow/limit/date/date_less_limit.xml",
                "logicflow/limit/date/date_more_limit.xml",
                "logicflow/limit/date/date_equal_limit.xml",
                "logicflow/limit/date/date_unequal_limit.xml",
                "logicflow/limit/date/date_nullable_limit.xml"
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
