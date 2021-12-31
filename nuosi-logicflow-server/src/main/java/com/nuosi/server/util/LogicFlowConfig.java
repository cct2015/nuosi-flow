package com.nuosi.server.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <p>desc: LogicFlow配置类 </p>
 * <p>date: 2021/12/30 23:58 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class LogicFlowConfig {
    private static Map<String, String> modelPathMap = new HashMap<String, String>();
    private static Map<String, String> flowPathMap = new HashMap<String, String>();

    public static void init(){
        initModel();
        initFlow();
    }

    public static void initModel(){
        modelPathMap.put("goods_info", "flow/model/goods_model.xml");

        Iterator<String> it = modelPathMap.keySet().iterator();
        while(it.hasNext()){
            LogicFlowUtil.loadLogicModel(it.next());
        }
    }

    public static void initFlow(){
        flowPathMap.put("simple_logic_flow_example", "flow/simple_flow.xml");

        Iterator<String> it = flowPathMap.keySet().iterator();
        while(it.hasNext()){
            LogicFlowUtil.loadLogicFlow(it.next());
        }
    }

    public static Map<String, String> getModelPathMap() {
        return modelPathMap;
    }

    public static Map<String, String> getFlowPathMap() {
        return flowPathMap;
    }
}
