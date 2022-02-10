package com.nuosi.flow.logic.parse;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.InputStream;

/**
 * <p>desc: 逻辑编排配置文件检查 </p>
 * <p>date: 2021/4/28 17:40 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class LogicFlowValidator {

    /**
     * <p>desc: 使用XSD检查逻辑编排配置文件</p>
     * <p>date: 2022/2/10 11:14</p>
     * @author nuosi fsofs@163.com
     * @version v1.0.0
     * @param xsdPath 1
     * @param xmlInputStream 2
     * @return void
     */
    public static void validateXMLSchema(String xsdPath, InputStream xmlInputStream) throws Exception {
        Source xsdSource = new StreamSource(LogicFlowValidator.class.getClassLoader().getResourceAsStream(xsdPath));
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(xsdSource);
        Validator validator = schema.newValidator();

        Source xmlSource = new StreamSource(xmlInputStream);
        validator.validate(xmlSource);
    }

}
