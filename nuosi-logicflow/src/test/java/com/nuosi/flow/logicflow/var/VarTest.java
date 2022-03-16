package com.nuosi.flow.logicflow.var;

import com.ai.ipu.data.JMap;
import com.ai.ipu.data.impl.JsonMap;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nuosi.flow.logic.LogicFlowEngine;
import com.nuosi.flow.util.LogicFlowUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * <p>desc: Var标签测试 </p>
 * <p>date: 2022/1/11 11:04 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class VarTest {

    @Test
    public void testVarInitialMethod(){
        JMap param = new JsonMap();
        try {
            LogicFlowEngine.execute("var_initial_method", param);
            Assert.assertTrue(true);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testVarCalculateMethod(){
        JMap param = new JsonMap();
        try {
            LogicFlowEngine.execute("var_calculate_method", param);
            Assert.assertTrue(true);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testStartVarModel(){
        JMap param = new JsonMap();
        JSONObject user = new JSONObject();
        user.put("user_id", 123456);
        user.put("user_name", "张三");
        user.put("age", 20);
        user.put("email", "abc@163.com");
        user.put("phone", "17777777777");
        param.put("user", user);
        try {
            LogicFlowEngine.execute("start_var_model", param);
            LogicFlowEngine.execute("start_var_model", new JsonMap(param.toJSONString()));
            Assert.assertTrue(true);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testStartVarModels(){
        JMap param = new JsonMap();
        JSONArray users = new JSONArray();
        for(int i=1;i<3;i++){
            JSONObject user = new JSONObject();
            user.put("user_id", 10000+i);
            user.put("user_name", "张三");
            user.put("age", 20);
            user.put("email", "abc@163.com");
            user.put("phone", "17777777777");
            users.add(user);
        }
        param.put("users", users);
        try {
            LogicFlowEngine.execute("start_var_models", param);
            LogicFlowEngine.execute("start_var_models", new JsonMap(param.toJSONString()));
            Assert.assertTrue(true);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testStartVarModelError(){
        JMap param = new JsonMap();
        param.put("user", "abc");
        try {
            LogicFlowEngine.execute("start_var_model", param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testStartVarModelAttrExists(){
        JMap param = new JsonMap();
        JSONObject user = new JSONObject();
        user.put("user_id", 123456);
        param.put("user", user);
        try {
            LogicFlowEngine.execute("start_var_model_attr_exists", param);
            Assert.assertTrue(true);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testStartVarModelAttrMissing(){
        JMap param = new JsonMap();
        JSONObject user = new JSONObject();
        user.put("user_id", 123456);
        param.put("user", user);
        try {
            LogicFlowEngine.execute("start_var_model", param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Before
    public void before() throws IOException {
        String[] modelConfigs = {
                "model/user_model.xml"
        };
        LogicFlowUtil.loadLogicModels(modelConfigs);

        String[] flowConfigs = {
                "logicflow/var/var_initial_method.xml",
                "logicflow/var/var_calculate_method.xml",
                "logicflow/var/start_var_model.xml",
                "logicflow/var/start_var_models.xml",
                "logicflow/var/start_var_model_attr_exists.xml"
        };
        LogicFlowUtil.loadLogicFlows(flowConfigs);
    }
}
