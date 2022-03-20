package com.nuosi.flow.logicflow.action;

import com.ai.ipu.data.JMap;
import com.ai.ipu.data.impl.JsonMap;
import com.nuosi.flow.logic.LogicFlowEngine;
import com.nuosi.flow.util.LogicFlowUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * <p>desc: 函数功能逻辑相关语法展示 </p>
 * <p>date: 2021/5/17 22:34 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class FunctionSyntaxTest {

    @Test
    public void testFunctionSimple() throws Exception {
        JMap param = new JsonMap();
        param.put("fill_id","202104160001");
        Object result = LogicFlowEngine.execute("function_syntax",param);
        System.out.println("result===" + result);
    }

    @Test
    public void testFunctionMethodNotExists() throws Exception {
        try {
            JMap param = new JsonMap();
            LogicFlowEngine.execute("function_method_not_exists", param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("错误信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Before
    public void before() throws IOException {
        String[] models = {
                "working_hours/model/working_hours_entity.xml"
        };
        LogicFlowUtil.loadLogicModels(models);

        String[] flows = {
                "logicflow/action/function/function_syntax.xml",
                "working_hours/flow/working_hours_query.xml",
                "logicflow/action/function/function_method_not_exists.xml",
        };

        LogicFlowUtil.loadLogicFlows(flows);
    }
}
