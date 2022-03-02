package com.nuosi.flow.logic.parse;

import com.ai.ipu.basic.util.IpuBaseException;
import com.nuosi.flow.data.BData;
import com.nuosi.flow.data.impl.BizData;
import com.nuosi.flow.util.LogicFlowUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>desc: 描述这个类功能的注释 </p>
 * <p>date: 2021/4/8 19:13 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class DtoToDataDefineParserTest {

    @Test
    public void testParseDataDefine() throws Exception {
        BData bData = new BizData("goods_info");
        try{
            bData.put("price", 99.999999999);
            Assert.assertTrue(false);
        }catch (Exception e){
            System.out.println(e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testImportBData() throws Exception {

    }

    @Before
    public void before() throws Exception {
        String exceptionMessagesConfig = "com/nuosi/flow/exception_messages";
        IpuBaseException.registerCode(exceptionMessagesConfig, true);

        String[] modelConfigs = {
                "model/goods_model.xml"
        };
        LogicFlowUtil.loadLogicModels(modelConfigs);
    }
}
