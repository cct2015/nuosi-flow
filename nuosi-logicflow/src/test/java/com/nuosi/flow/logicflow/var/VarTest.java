package com.nuosi.flow.logicflow.var;

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

    @Before
    public void before() throws IOException {
        String[] flowConfigs = {
                "logicflow/var/var_initial_method.xml",
                "logicflow/var/var_calculate_method.xml"
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
