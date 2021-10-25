package com.nuosi.flow.syntax;

import com.ai.ipu.data.JMap;
import com.ai.ipu.data.impl.JsonMap;
import com.nuosi.flow.data.BDataDefine;
import com.nuosi.flow.data.BizDataManager;
import com.nuosi.flow.logic.LogicFlowEngine;
import com.nuosi.flow.logic.LogicFlowManager;
import com.nuosi.flow.logic.parse.DtoToDataDefineParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * <p>desc: 子流程逻辑相关语法展示 </p>
 * <p>date: 2021/5/16 10:16 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class SubflowSyntaxTest {

    @Test
    public void testSubflowSimple() throws Exception {
        JMap param = new JsonMap();
        param.put("staff_id","zhangs");
        Object result = LogicFlowEngine.execute("subflow_syntax",param);
        System.out.println("result===" + result);
    }

    @Before
    public void setUp() {
        String flowConfig = "syntax/subflow_syntax.xml";
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
