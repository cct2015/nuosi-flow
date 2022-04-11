package com.nuosi.flow.logic.invoke.debug;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>desc: 逻辑对象在线调试管理类 </p>
 * <p>date: 2022/4/8 22:59 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class LogicDebugManager {
    private static final String INPUT_SUFFIX = "_input";
    private static final String OUTPUT_SUFFIX = "_output";
    private static final String START = "start";
    private static final String END = "end";
    private static final String LOGIC_FLOW = "logic_flow";

    private static boolean mainSwitch = false; //调试总开关，默认关闭
    private static Map<String, Boolean> logicDebugSwitch = new HashMap<String, Boolean>();
    private static Map<String, LinkedHashMap> logicDebugData = new HashMap<String, LinkedHashMap>();
    private static ThreadLocal<String> userThreadLocal = new ThreadLocal<String>();
    private static ThreadLocal<String> logicThreadLocal = new ThreadLocal<String>();

    public static Map<String, LinkedHashMap> getLogicDebugData() {
        String userId = userThreadLocal.get();
        return logicDebugData.get(userId);
    }

    public static void init(String logicFlowId) {
        LinkedHashMap dataMap = new LinkedHashMap();
        dataMap.put(LOGIC_FLOW, logicFlowId);
        String userId = userThreadLocal.get();
        logicDebugData.put(userId, dataMap);
    }

    public static void recordStartData(Map<String, Object> inputData) {
        String userId = userThreadLocal.get();
        LinkedHashMap dataMap = logicDebugData.get(userId);
        dataMap.put(START, inputData);
    }

    public static void recordInputData(String actionId, Map<String, Object> inputData) {
        String userId = userThreadLocal.get();
        LinkedHashMap dataMap = logicDebugData.get(userId);
        dataMap.put(actionId + INPUT_SUFFIX, inputData);
    }

    public static void recordOutputData(String actionId, Object outputData) {
        String userId = userThreadLocal.get();
        LinkedHashMap dataMap = logicDebugData.get(userId);
        dataMap.put(actionId + OUTPUT_SUFFIX, outputData);
    }

    public static void recordEndData(Map<String, Object> inputData) {
        String userId = userThreadLocal.get();
        LinkedHashMap dataMap = logicDebugData.get(userId);
        dataMap.put(END, inputData);
    }

    public static boolean isDebug() {
        return isMainSwitch() && isUserDebug() && isLogicDebug();
    }

    /**
     * 基于用户的在线调试开关
     */
    public static boolean isUserDebug() {
        String userId = userThreadLocal.get();
        return userId != null;
    }

    public static void openUserSwitch(String userId) {
        userThreadLocal.set(userId);
    }

    /**
     * 基于逻辑对象的在线调试开关
     */
    public static boolean isLogicDebug() {
        String logicFlowId = logicThreadLocal.get();
        if (logicFlowId == null) {
            return false;
        }
        return logicDebugSwitch.get(logicFlowId) == null ? false : logicDebugSwitch.get(logicFlowId);
    }

    public static void openLogicSwitch(String logicFlowId) {
        logicThreadLocal.set(logicFlowId);
        boolean isDebug = logicDebugSwitch.get(logicFlowId) == null ? false : logicDebugSwitch.get(logicFlowId);
        if (!isDebug) {
            logicDebugSwitch.put(logicFlowId, true);
        }
    }

    public static void closeLogicSwitch() {
        String logicFlowId = logicThreadLocal.get();
        boolean isDebug = logicDebugSwitch.get(logicFlowId) == null ? false : logicDebugSwitch.get(logicFlowId);
        if (isDebug) {
            logicDebugSwitch.put(logicFlowId, false);
        }
    }

    /**
     * 在线调试主开关
     */
    public static boolean isMainSwitch() {
        return mainSwitch;
    }

    public static void openMainSwitch() {
        LogicDebugManager.mainSwitch = true;
    }

    public static void closeMainSwitch() {
        LogicDebugManager.mainSwitch = false;
    }
}
