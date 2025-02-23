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
 * <p>desc: 正则表达式限制的测试类 </p>
 * <p>date: 2022/3/5 14:22 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class ValidateRegexTest {

    @Test
    public void testRegexDefine(){
        JMap param = new JsonMap();
        try {
            param.put("email_regex_param","abc@");
            //param.put("email_regex_param","fsofs@163.com");
            LogicFlowEngine.execute("regex_define_validate",param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Before
    public void before() throws IOException {
        String[] flowConfigs = {
                "logicflow/validate/regex/regex_define_validate.xml"
        };
        LogicFlowUtil.loadLogicFlows(flowConfigs);
    }
}
