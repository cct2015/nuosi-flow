package com.nuosi.flow.logic.parse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nuosi.flow.logic.LogicFlowManager;
import com.nuosi.flow.logic.model.LogicFlow;
import com.nuosi.flow.logic.model.domain.DomainModel;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>desc: 描述这个类功能的注释 </p>
 * <p>date: 2021/3/29 16:27 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 * update:[序号][日期YYYY-MM-DD] [更改人姓名][变更描述]
 */
public class XmlToLogicFlowParserTest {
    static {
        String flowConfig = "dto/goods_info_dto.xml";
        InputStream is = XmlToLogicFlowParserTest.class.getClassLoader().getResourceAsStream(flowConfig);
        try {
            JSONObject beanJson = new XmlToBizDataParser(is).getBeanJson();
            DomainModel domainModel = JSON.toJavaObject(beanJson, DomainModel.class);
            LogicFlowManager.storageDomainModel(domainModel);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testGetBeanJson(){
        try{
            String flowConfig = "flow/simple_flow.xml";
            InputStream is = getClass().getClassLoader().getResourceAsStream(flowConfig);
            JSONObject flowJson = new XmlToLogicFlowParser(is).getBeanJson();
            System.out.println("flowJson==="+flowJson);
            LogicFlow logicFlow = JSON.toJavaObject(flowJson, LogicFlow.class);
            System.out.println("logicFlow==="+JSON.toJSONString(logicFlow));
            Assert.assertTrue(true);
        }catch (Exception e){
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
}
