package com.nuosi.flow.syntax;

import com.ai.ipu.data.JMap;
import com.ai.ipu.data.impl.JsonMap;
import com.nuosi.flow.data.BDataDefine;
import com.nuosi.flow.data.BizDataManager;
import com.nuosi.flow.logic.LogicFlowEngine;
import com.nuosi.flow.logic.LogicFlowManager;
import com.nuosi.flow.logic.parse.DtoToDataDefineParser;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

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

    @Before
    public void setUp() {
        String flowConfig = "syntax/function_syntax.xml";
        InputStream is = getClass().getClassLoader().getResourceAsStream(flowConfig);
        LogicFlowManager.registerLogicFlow(is);

        flowConfig = "working_hours/flow/working_hours_query.xml";
        is = getClass().getClassLoader().getResourceAsStream(flowConfig);
        LogicFlowManager.registerLogicFlow(is);

        String modelConfig = "working_hours/model/working_hours_entity.xml";
        is = getClass().getClassLoader().getResourceAsStream(modelConfig);
        LogicFlowManager.registerDomainModel(is);

        try {
            BDataDefine dataDefine = new DtoToDataDefineParser().parse("working_hours_entity");
            BizDataManager.registerDto(dataDefine, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
