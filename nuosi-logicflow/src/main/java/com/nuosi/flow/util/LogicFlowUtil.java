package com.nuosi.flow.util;

import com.nuosi.flow.data.BDataDefine;
import com.nuosi.flow.data.BizDataManager;
import com.nuosi.flow.logic.LogicFlowManager;
import com.nuosi.flow.logic.model.LogicFlow;
import com.nuosi.flow.logic.model.domain.DomainModel;
import com.nuosi.flow.logic.parse.ModelToDataDefineUtil;

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

    public static String loadLogicFlow(String logicflowPath) {
        InputStream is = LogicFlowUtil.class.getClassLoader().getResourceAsStream(logicflowPath);
        return loadLogicFlow(is);
    }

    public static String loadLogicFlow(InputStream is) {
        try {
            LogicFlow logicFlow = LogicFlowManager.registerLogicFlow(is);
            return logicFlow.getId();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static String loadLogicModel(String logicModelPath) {
        InputStream is = LogicFlowUtil.class.getClassLoader().getResourceAsStream(logicModelPath);
        return loadLogicModel(is);
    }

    public static String loadLogicModel(InputStream is) {
        try {
            DomainModel domainModel = LogicFlowManager.registerDomainModel(is);
            BDataDefine dataDefine = ModelToDataDefineUtil.parse(domainModel.getId());
            BizDataManager.registerDto(dataDefine, true);
            return domainModel.getId();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String[] loadLogicFlows(String[] logicFlows) {
        if (logicFlows == null) {
            return null;
        }
        String[] logicFlowIds = new String[logicFlows.length];
        for (String logicflow : logicFlows) {
            loadLogicFlow(logicflow);
        }
        return logicFlowIds;
    }

    public static String[] loadLogicModels(String[] logicModels) {
        if (logicModels == null) {
            return null;
        }
        String[] LogicModelIds = new String[logicModels.length];
        for (String logicmodel : logicModels) {
            loadLogicModel(logicmodel);
        }
        return LogicModelIds;
    }
}
