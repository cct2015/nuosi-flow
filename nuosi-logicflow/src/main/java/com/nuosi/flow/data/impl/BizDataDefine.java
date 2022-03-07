package com.nuosi.flow.data.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nuosi.flow.data.BDataDefine;
import com.nuosi.flow.data.BDataLimit;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>desc: 业务传输对象的数据类型和格式定义</p>
 * <p>date: 2021/3/16 14:36</p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class BizDataDefine implements BDataDefine {
    private final String bizName;
    private final Map<String, BDataLimit> dataLimits;

    public BizDataDefine(String bizName) {
        this.bizName = bizName;
        this.dataLimits = new HashMap<String, BDataLimit>();
    }

    @Override
    public String getBizName() {
        return bizName;
    }

    @Override
    public BizDataDefine defineLimit(String attr, BDataLimit dataLimit) {
        dataLimits.put(attr, dataLimit);
        return this;
    }

    @Override
    public BDataLimit getDataLimit(String bizName) {
        return dataLimits.get(bizName);
    }

    @Override
    public Map<String, BDataLimit> getDataLimits() {
        return dataLimits;
    }

    @Override
    public boolean containsAttr(String attr) {
        return dataLimits.containsKey(attr);
    }

    @Override
    public boolean checkData(String attr, Object value) {
        BDataLimit dataLimit = this.getDataLimit(attr);
        if(dataLimit!=null){
            /*1.进行指定规则校验*/
            dataLimit.checkValidity(bizName, attr, value);
            /*2.进行正则表达式规则校验*/
            dataLimit.checkRegex(bizName, attr, value);
        }
        return true;
    }

    @Override
    public boolean checkData(JSONObject value) {
        BDataLimit dataLimit;
        String attr;
        Object val;
        for (Map.Entry<String, BDataLimit> entry : dataLimits.entrySet()) {
            dataLimit = entry.getValue();
            attr = entry.getKey();
            val = value.get(attr);
            /*1.进行指定规则校验*/
            dataLimit.checkValidity(bizName, attr, val);
            /*2.进行正则表达式规则校验*/
            dataLimit.checkRegex(bizName, attr, val);
        }
        return true;
    }

    @Override
    public boolean checkData(JSONArray value) {
        for(Object model : value){
            checkData((JSONObject)model);
        }
        return true;
    }
}
