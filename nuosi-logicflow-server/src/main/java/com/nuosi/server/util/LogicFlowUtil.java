package com.nuosi.server.util;

import com.nuosi.flow.data.BDataDefine;
import com.nuosi.flow.data.BizDataManager;
import com.nuosi.flow.logic.LogicFlowManager;
import com.nuosi.flow.logic.parse.DtoToDataDefineParser;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>desc: LogicFlow工具类 </p>
 * <p>date: 2021/12/18 10:40 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class LogicFlowUtil {
    public static void loadLogicFlow(String logicName){
        String logicPath = LogicFlowConfig.getFlowPathMap().get(logicName);
        InputStream is = LogicFlowUtil.class.getClassLoader().getResourceAsStream(logicPath);
        try {
            BDataDefine dataDefine = new DtoToDataDefineParser().parse("goods_info");
            BizDataManager.registerDto(dataDefine, true);

            LogicFlowManager.registerLogicFlow(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(is!=null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void loadLogicModel(String modelName){
        String modelPath = LogicFlowConfig.getModelPathMap().get(modelName);
        InputStream is = LogicFlowUtil.class.getClassLoader().getResourceAsStream(modelPath);
        try {
            LogicFlowManager.registerDomainModel(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(is!=null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
