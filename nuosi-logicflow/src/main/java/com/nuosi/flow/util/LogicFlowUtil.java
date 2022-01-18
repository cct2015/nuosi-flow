package com.nuosi.flow.util;

import com.nuosi.flow.data.BDataDefine;
import com.nuosi.flow.data.BizDataManager;
import com.nuosi.flow.logic.LogicFlowManager;
import com.nuosi.flow.logic.model.domain.DomainModel;
import com.nuosi.flow.logic.parse.DtoToDataDefineParser;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>desc: 逻辑流工具类
 * 支持单个和批量加载逻辑和模型
 * </p>
 * <p>date: 2022/1/18 19:51 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class LogicFlowUtil {

    public static void loadLogicFlow(String logicflowPath){
        InputStream is = LogicFlowUtil.class.getClassLoader().getResourceAsStream(logicflowPath);
        try {
            LogicFlowManager.registerLogicFlow(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(is!=null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void loadLogicModel(String logicModelPath){
        InputStream is = LogicFlowUtil.class.getClassLoader().getResourceAsStream(logicModelPath);
        try {
            DomainModel domainModel =LogicFlowManager.registerDomainModel(is);
            BDataDefine dataDefine = new DtoToDataDefineParser().parse(domainModel.getId());
            BizDataManager.registerDto(dataDefine, true);
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

    public static void loadLogicFlows(String[] logicflows){
        for(String logicflow : logicflows){
            loadLogicFlow(logicflow);
        }
    }

    public static void loadLogicModels(String[] logicmodels){
        for(String logicmodel : logicmodels){
            loadLogicModel(logicmodel);
        }
    }
}
