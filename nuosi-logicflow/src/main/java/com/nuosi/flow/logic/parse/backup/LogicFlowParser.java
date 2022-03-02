package com.nuosi.flow.logic.parse.backup;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nuosi.flow.logic.model.LogicFlow;
import com.nuosi.flow.logic.model.action.Sql;
import com.nuosi.flow.logic.model.body.Action;
import com.nuosi.flow.logic.model.body.End;
import com.nuosi.flow.logic.model.body.Start;
import com.nuosi.flow.logic.model.global.Declare;
import com.nuosi.flow.logic.util.DtoUtil;
import com.nuosi.flow.logic.util.XmlToJsonHelper;

import java.io.InputStream;

/**
 * <p>desc: 逻辑流模型解析</p>
 * <p>date: 2021/3/7 16:37</p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class LogicFlowParser {
    public static final String LOGIC_FLOW = "logic-flow";
    public static final String IMPORT = "import";
    public static final String DATABUS = "databus";
    public static final String VAR = "var";

    public static final String NODE = "node";
    public static final String GLOBAL_DOMAIN = "global-domain";
    public static final String START = "start";
    public static final String END = "end";

    public static final String INPUT = "input";
    public static final String OUTPUT = "output";
    public static final String NODE_DOMAIN = "node_domain";
    public static final String SQL = "sql";

    public static final String CHILDREN = XmlToJsonHelper.CHILDREN_TAG;
    public static final String SUFFIX_ATTR = com.ai.ipu.common.xml.Dom4jHelper.SUFFIX_ATTR;
    public static final String SUFFIX_TEXT = com.ai.ipu.common.xml.Dom4jHelper.SUFFIX_TEXT;

    public LogicFlow parser(InputStream flowInputStream) throws Exception {
        XmlToJsonHelper dh = new XmlToJsonHelper(flowInputStream);
        JSONObject originData = dh.getAllJson();
        LogicFlow logicFlow = parserLogicFlow(originData);
        return logicFlow;
    }

    public LogicFlow parserLogicFlow(JSONObject originData) {
        JSONObject logicFlowObject = originData.getJSONObject(LOGIC_FLOW);
        JSONObject logicFlowAttr = logicFlowObject.getJSONObject(LOGIC_FLOW + SUFFIX_ATTR);
        LogicFlow logicFlow = logicFlowAttr.toJavaObject(LogicFlow.class);

        JSONArray children = logicFlowObject.getJSONArray(CHILDREN);
        if (children != null && !children.isEmpty()) {
            for (int i = 0; i < children.size(); i++) {
                parserLogicFlowChildren(children.getJSONObject(i));
            }
        }
        return logicFlow;
    }

    /**
     * <p>desc: 解析逻辑流子节点，频率node>其他</p>
     * <p>date: 2021/3/24 11:43</p>
     * @author nuosi fsofs@163.com
     * @version v1.0.0
     * @param logicFlowChildren 1
     * @return com.nuosi.flow.logic.model.LogicFlow
     */
    public LogicFlow parserLogicFlowChildren(JSONObject logicFlowChildren) {
        if (logicFlowChildren.containsKey(NODE)) {
            parserNode(logicFlowChildren.getJSONObject(NODE));
        } else if (logicFlowChildren.containsKey(GLOBAL_DOMAIN)) {

        } else if (logicFlowChildren.containsKey(START)) {

        } else if (logicFlowChildren.containsKey(END)) {

        } else {
            // 抛无指定节点异常
        }
        return null;
    }

    public Action parserNode(JSONObject node) {
        // 生成node的Java对象。
        JSONObject nodeAttr = node.getJSONObject(NODE + SUFFIX_ATTR);
        Sql sqlNode = nodeAttr.toJavaObject(Sql.class);
        System.out.println("node===" + DtoUtil.toString(sqlNode));

        // 判断children并循环。
        JSONArray childrenNode = node.getJSONArray(CHILDREN);
        if (childrenNode != null && !childrenNode.isEmpty()) {
            for (int i = 0; i < childrenNode.size(); i++) {
                parserLogicFlowChildren(childrenNode.getJSONObject(i));
            }
        }
        return null;
    }

    public Start parserStart(JSONObject node) {
        // 生成node的Java对象。

        //判断children并循环。

        return null;
    }

    public End parserEnd(JSONObject node) {
        // 生成node的Java对象。

        //判断children并循环。

        return null;
    }

    public Declare parserGlobalDomain(JSONObject node) {
        // 生成node的Java对象。

        //判断children并循环。

        return null;
    }

    public Declare parserNodeChildren(JSONObject node) {

        return null;
    }
}
