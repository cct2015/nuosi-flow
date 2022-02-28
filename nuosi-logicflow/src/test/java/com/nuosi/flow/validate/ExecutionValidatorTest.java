package com.nuosi.flow.validate;

import com.nuosi.flow.logic.LogicFlowEngine;
import com.nuosi.flow.logic.LogicFlowManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>desc: 逻辑编排DSL合理性校验单元测试 </p>
 * <p>date: 2022/2/22 0:35 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class ExecutionValidatorTest {

    @Test
    public void testValidateActionNull() {
        try {
            LogicFlowEngine.validate("action_null");
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testValidateStartVarAttrUndefine() {
        try {
            LogicFlowEngine.validate("start_var_attr_undefine");
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testValidateStartVarModelUndefine() {
        try {
            LogicFlowEngine.validate("start_var_model_undefine");
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testValidateStartNextNotExist() {
        try {
            LogicFlowEngine.validate("start_next_not_exist");
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testValidateActionNextNotExist() {
        try {
            LogicFlowEngine.validate("action_next_not_exist");
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testValidateInputVarAttrUndefine() {
        try {
            LogicFlowEngine.validate("input_var_attr_undefine");
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testValidateInputVarModelUndefine() {
        try {
            LogicFlowEngine.validate("input_var_model_undefine");
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testValidateEndNotMatch() {
        try {
            LogicFlowEngine.validate("end_not_match");
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testValidateEndVarNull() {
        try {
            LogicFlowEngine.validate("end_var_null");
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testValidateStartMultiple() {
        try {
            LogicFlowEngine.validate("start_multiple");
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testValidateEndMultiple() {
        try {
            LogicFlowEngine.validate("end_multiple");
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Before
    public void before() throws IOException {
        String[] flowConfigs = {
                "validate/action_null.xml",
                "validate/start_var_attr_undefine.xml",
                "validate/start_var_model_undefine.xml",
                "validate/start_next_not_exist.xml",
                "validate/action_next_not_exist.xml",
                "validate/input_var_attr_undefine.xml",
                "validate/input_var_model_undefine.xml",
                "validate/end_not_match.xml",
                "validate/end_var_null.xml",
                "validate/start_multiple.xml",
                "validate/end_multiple.xml"
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
