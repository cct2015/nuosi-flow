package com.nuosi.flow.logicflow;

import com.ai.ipu.data.JMap;
import com.ai.ipu.data.impl.JsonMap;
import com.nuosi.flow.data.BDataDefine;
import com.nuosi.flow.data.BizDataManager;
import com.nuosi.flow.logic.LogicFlowEngine;
import com.nuosi.flow.logic.LogicFlowManager;
import com.nuosi.flow.logic.parse.DtoToDataDefineParser;
import com.nuosi.flow.logic.parse.XmlToLogicFlowParserTest;
import com.nuosi.flow.mgmt.message.MessageManager;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>desc: 逻辑流引擎单元测试</p>
 * <p>date: 2021/3/29 21:17</p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class LogicFlowEngineTest {

    @Test
    public void testExecute() throws Exception {
        JMap param = new JsonMap();
        param.put("goods_name","橙汁");
        param.put("goods_type",1);
        Object result = LogicFlowEngine.execute("simple_logic_flow_example",param);
        System.out.println("result===" + result);
    }

    @Before
    public void setUp(){
        String dtoConfig = "model/goods_model.xml";
        InputStream is1 = XmlToLogicFlowParserTest.class.getClassLoader().getResourceAsStream(dtoConfig);
        String flowConfig = "logicflow/simple_flow.xml";
        InputStream is2 = getClass().getClassLoader().getResourceAsStream(flowConfig);
        try {
            LogicFlowManager.registerDomainModel(is1);

            BDataDefine dataDefine = new DtoToDataDefineParser().parse("goods_info");
            BizDataManager.registerDto(dataDefine, true);

            LogicFlowManager.registerLogicFlow(is2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(is1!=null)
                    is1.close();
                if(is2!=null)
                    is2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
