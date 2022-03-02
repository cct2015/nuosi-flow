package com.nuosi.flow.logic.parse.backup;

import org.junit.Test;

/**
 * <p>desc: 业务传输对象解析单元测试</p>
 * <p>date: 2021/3/25 13:53</p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class BizDataParserTest {

    @Test
    public void parseAllTest() throws Exception {
        /*try{
            String flowConfig = "model/goods_model.xml";
            InputStream is = getClass().getClassLoader().getResourceAsStream(flowConfig);
            DomainModel domainModel = new BizDataParser().parser(is);
            String strDomainModel = JSON.toJSONString(domainModel);
            System.out.println("domainModel1==="+ strDomainModel);
            DomainModel domainModel1 = JSON.toJavaObject(JSON.parseObject(strDomainModel), DomainModel.class);
            System.out.println("domainModel2==="+ JSON.toJSONString(domainModel1));
            Assert.assertEquals(strDomainModel, JSON.toJSONString(domainModel1));
        }catch (Exception e){
            e.printStackTrace();
            Assert.assertTrue(false);
        }*/
    }
}
