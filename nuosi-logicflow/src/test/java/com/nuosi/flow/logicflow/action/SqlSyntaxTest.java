package com.nuosi.flow.logicflow.action;

import com.ai.ipu.data.JMap;
import com.ai.ipu.data.impl.JsonMap;
import com.nuosi.flow.logic.LogicFlowEngine;
import com.nuosi.flow.util.LogicFlowUtil;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>desc: SQL逻辑相关语法展示 </p>
 * <p>date: 2021/5/19 23:16 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class SqlSyntaxTest {

    @Test
    public void testSqlSimple() throws Exception {
        JMap param = new JsonMap();
        param.put("fill_staff","zhangs");
        Object result = LogicFlowEngine.execute("sql_syntax",param);
        System.out.println("result===" + result);
    }

    @Before
    public void setUp() {
        String flowConfig = "logicflow/action/sql/sql_syntax.xml";
        LogicFlowUtil.loadLogicFlow(flowConfig);
    }
}
