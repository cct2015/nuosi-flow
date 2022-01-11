package com.nuosi.flow.syntax;

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
 * <p>desc: 数据聚合逻辑相关语法展示 </p>
 * <p>date: 2022/1/11 17:46 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class AggregateSyntaxTest {

    @Test
    public void aggregateConvertTest(){
        JMap param = new JsonMap();
        param.put("id", "123456");
        param.put("name", "张三");
        try {
            LogicFlowEngine.execute("aggregate_convert", param);
            Assert.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("校验信息：" + e.getMessage());
            Assert.assertTrue(false);
        }
    }

    @Before
    public void before() throws IOException {
        String[] flowConfigs = {
                "syntax/aggregate_convert.xml"
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
