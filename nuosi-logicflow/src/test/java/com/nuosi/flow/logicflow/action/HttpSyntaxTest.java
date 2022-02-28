package com.nuosi.flow.logicflow.action;

import com.ai.ipu.data.JMap;
import com.ai.ipu.data.impl.JsonMap;
import com.nuosi.flow.logic.LogicFlowEngine;
import com.nuosi.flow.logic.LogicFlowManager;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * <p>desc: http请求逻辑相关语法展示 </p>
 * <p>date: 2021/10/27 15:14 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class HttpSyntaxTest {

    @Test
    public void testHttpSyntax() throws Exception {
        JMap param = new JsonMap();
        param.put("fill_staff","zhangs");
        Object result = LogicFlowEngine.execute("http_syntax",param);
        System.out.println("result===" + result);
    }

    @Before
    public void setUp() {
        String flowConfig = "logicflow/action/http/http_syntax.xml";
        InputStream is = getClass().getClassLoader().getResourceAsStream(flowConfig);
        LogicFlowManager.registerLogicFlow(is);
    }
}
