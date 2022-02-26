package com.nuosi.flow.logic.parse;

import com.nuosi.flow.logic.invoke.validate.LogicFlowValidator;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>desc: 逻辑编排配置文件检查测试类 </p>
 * <p>date: 2021/4/28 17:42 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class LogicFlowValidatorTest {

    @Test
    public void testValidateLogicFlow(){
        String xsdPath = "com/nuosi/flow/xsd/logic_flow.xsd";
        InputStream xmlInputStream = LogicFlowValidatorTest.class.getClassLoader()
                .getResourceAsStream("full_flow.xml");
        try {
            LogicFlowValidator.validateXMLSchema(xsdPath, xmlInputStream);
            Assert.assertTrue(false);
        } catch (Exception e) {
            Assert.assertTrue(true);
            System.out.println(e.getMessage());;
        }finally {
            if(xmlInputStream!=null){
                try {
                    xmlInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
