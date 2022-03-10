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
 * <p>desc: 布尔限制的测试类 </p>
 * <p>date: 2022/3/2 17:14 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class ValidateBooleanTest {

    @Test
    public void testBooleanValidate() throws Exception {
        JMap param = new JsonMap();
        param.put("boolean_param", true);
        try {
            Object result = LogicFlowEngine.execute("boolean_validate",param);
            System.out.println("返回结果：" + result);
            param.put("boolean_param", "abc");
            LogicFlowEngine.execute("boolean_validate",param);
            System.out.println("返回结果：" + result);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Before
    public void before() throws IOException {
        String[] flowConfigs = {
                "logicflow/validate/boolean/boolean_validate.xml"
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
