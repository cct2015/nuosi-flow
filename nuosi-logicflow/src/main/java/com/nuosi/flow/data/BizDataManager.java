package com.nuosi.flow.data;

import com.ai.ipu.basic.util.IpuUtility;
import com.nuosi.flow.util.LogicFlowConstants;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>desc: 业务传输对象管理类</p>
 * <p>date: 2021/3/6 0:32</p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class BizDataManager {
    private static Map<String, BDataDefine> bizDataDefine = new ConcurrentHashMap<String, BDataDefine>();
    static {
        //从配置文件中加载
        loadDtoConfig(bizDataDefine);
    }

    /**
     * @name registerDto
     * @version 0.1.0
     * @desc 使用弱类型的BizData之前需要提前注册
     * @author nuosi fsofs@163.com
     * @date 2021/3/16 13:40
     */
    public static void registerDto(BDataDefine dataDefine, boolean isOverride){
        String bizName = dataDefine.getBizName();
        if(bizDataDefine.containsKey(bizName)){
            if(!isOverride){
                //抛出异常，防止重复注册
                IpuUtility.errorCode(LogicFlowConstants.BDATA_STRUCTURE_EXISTS);
            }
        }
        bizDataDefine.put(bizName, dataDefine);
    }

    public static void registerDto(BDataDefine dataDefine){
        registerDto(dataDefine, false);
    }

    public static boolean unregisterDto(String bizName){
        return bizDataDefine.remove(bizName)!=null;
    }

    public static BDataDefine getDataDefine(String bizName){
        BDataDefine bDataDefine = bizDataDefine.get(bizName);
        if(bDataDefine==null){
            IpuUtility.errorCode(LogicFlowConstants.BDATA_DEFINE_NOT_EXISTS, bizName);
        }
        return bizDataDefine.get(bizName);
    }

    public static boolean contains(String bizName){
        return bizDataDefine.containsKey(bizName);
    }

    private static void loadDtoConfig(Map<String, BDataDefine> javaDtoConfig) {
    }
}
