package com.nuosi.flow.logicflow.action;

import com.ai.ipu.data.JMap;
import com.nuosi.flow.logic.LogicFlowEngine;
import com.nuosi.flow.util.LogicFlowUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * <p>desc: 表达式逻辑相关语法展示 </p>
 * <p>date: 2021/5/11 12:20 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class ExpressionSyntaxTest {

    @Test
    public void testExpressionExcept(){
        try{
            LogicFlowEngine.execute("expression_except",null);
            Assert.assertTrue(false);
        }catch (Exception e){
            System.out.println("抛出信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testExpressionReturn() {
        try {
            JMap jmap = LogicFlowEngine.execute("expression_return", null);
            System.out.println("返回结果：" + jmap);
            Assert.assertTrue("abc".equals(jmap.get("result")));
        } catch (Exception e) {
            System.out.println("抛出信息：" + e.getMessage());
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testExpressionDatabusUnmodifiable() {
        try {
            JMap jmap = LogicFlowEngine.execute("expression_databus_unmodifiable", null);
            System.out.println("返回结果：" + jmap);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("抛出信息：" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Before
    public void setUp() throws IOException {
        String[] flowConfigs = {
                "logicflow/action/expression/expression_except.xml",
                "logicflow/action/expression/expression_return.xml",
                "logicflow/action/expression/expression_databus_unmodifiable.xml"
        };
        LogicFlowUtil.loadLogicFlows(flowConfigs);
    }
}
