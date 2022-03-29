package com.nuosi.flow.logic;

import com.ai.ipu.basic.util.IpuUtility;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nuosi.flow.logic.model.LogicFlow;
import com.nuosi.flow.logic.model.domain.DomainModel;
import com.nuosi.flow.logic.parse.XmlToBizDataParser;
import com.nuosi.flow.logic.parse.XmlToLogicFlowParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>desc: 逻辑流管理类，管理逻辑流模型和领域模型 </p>
 * <p>date: 2021/3/29 18:27 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class LogicFlowManager {
    private static Map<String, DomainModel> domainModelCache = new ConcurrentHashMap<String, DomainModel>();
    private static Map<String, LogicFlow> logicFlowlCache = new ConcurrentHashMap<String, LogicFlow>();

    public static DomainModel registerDomainModel(InputStream is) {
        try {
            JSONObject beanJson = new XmlToBizDataParser(is).getBeanJson();
            DomainModel domainModel = JSON.toJavaObject(beanJson, DomainModel.class);
            domainModelCache.put(domainModel.getId(), domainModel);
            return domainModel;
        } catch (Exception e) {
            IpuUtility.error(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    IpuUtility.error(e);
                }
            }
        }
        return null;
    }

    public static LogicFlow registerLogicFlow(InputStream is) {
        try {
            JSONObject flowJson = new XmlToLogicFlowParser(is).getBeanJson();
            LogicFlow logicFlow = JSON.toJavaObject(flowJson, LogicFlow.class);
            logicFlowlCache.put(logicFlow.getId(), logicFlow);
            return logicFlow;
        } catch (Exception e) {
            IpuUtility.error(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    IpuUtility.error(e);
                }
            }
        }
        return null;
    }

    public static DomainModel getDomainModel(String dtoName) {
        return domainModelCache.get(dtoName);
    }

    public static LogicFlow getLogicFlow(String logicFlow) {
        return logicFlowlCache.get(logicFlow);
    }

    public static Set<String> getLogicFlowlList() {
        return logicFlowlCache.keySet();
    }

    public static Set<String> getDomainModelList() {
        return domainModelCache.keySet();
    }
}
