package com.nuosi.flow.logic.parse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nuosi.flow.logic.LogicFlowManager;
import com.nuosi.flow.logic.model.domain.DomainModel;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

/**
 * <p>desc: 业务传输对象解析单元测试</p>
 * <p>date: 2021/4/7 16:50</p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class XmlToBizDataParserTest {

    @Test
    public void testGetBeanJson(){
        try{
            String flowConfig = "model/goods_model.xml";
            InputStream is = getClass().getClassLoader().getResourceAsStream(flowConfig);
            XmlToBizDataParser parser = new XmlToBizDataParser(is);
            JSONObject beanJson = parser.getBeanJson();
            System.out.println("beanJson==="+beanJson);
            DomainModel domainModel = parser.getDomainModel();
            System.out.println("domainModel==="+JSON.toJSONString(domainModel));
            Assert.assertTrue(true);
        }catch (Exception e){
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testDomainModeAction(){
        try{
            String flowConfig = "working_hours/model/working_hours_entity.xml";
            InputStream is = getClass().getClassLoader().getResourceAsStream(flowConfig);
            LogicFlowManager.registerDomainModel(is);
            DomainModel domainModel = LogicFlowManager.getDomainModel("working_hours_entity");
            System.out.println("action==="+JSON.toJSONString(domainModel.getBehaviors()));
            Assert.assertTrue(true);
        }catch (Exception e){
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
}
