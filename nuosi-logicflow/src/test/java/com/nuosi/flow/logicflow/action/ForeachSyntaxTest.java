package com.nuosi.flow.logicflow.action;

import com.ai.ipu.data.JMap;
import com.ai.ipu.data.impl.JsonMap;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nuosi.flow.logic.LogicFlowEngine;
import com.nuosi.flow.util.LogicFlowUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>desc: 循环逻辑相关语法展示 </p>
 * <p>date: 2021/5/11 18:28 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class ForeachSyntaxTest {

    @Test
    public void testForeachObject() {
        try {
            JSONObject paramMap = new JSONObject();
            paramMap.put("name", "zhangsan");
            paramMap.put("age", 18);
            JMap param = new JsonMap();
            param.put("input_object", paramMap);

            LogicFlowEngine.execute("foreach_object", param);
            Assert.assertTrue(true);
        } catch (Exception e) {
            System.out.println("抛出信息：" + e.getMessage());
            Assert.assertTrue(e.getMessage(), false);
        }
    }

    @Test
    public void testForeachArray() {
        try {
            JSONArray paramList = new JSONArray();
            for (int i = 0; i < 10; i++) {
                JSONObject p = new JSONObject();
                p.put("index", i);
                paramList.add(p);
            }
            JMap param = new JsonMap();
            param.put("input_array", paramList);

            LogicFlowEngine.execute("foreach_array", param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println("抛出信息：" + e.getMessage());
            Assert.assertTrue(e.getMessage(), true);
        }
    }

    @Test
    public void testForeachDataTypeExcept() {
        try {
            JMap param = new JsonMap();
            param.put("no_match_data_type", "abc");

            LogicFlowEngine.execute("foreach_data_type_except", param);
            Assert.assertTrue(false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.assertTrue(e.getMessage(), true);
        }
    }

    @Before
    public void setUp() {
        String[] flowConfigs = {
                "logicflow/action/foreach/foreach_array.xml",
                "logicflow/action/foreach/foreach_object.xml",
                "logicflow/action/foreach/foreach_data_type_except.xml"
        };
        LogicFlowUtil.loadLogicFlows(flowConfigs);
    }
}
