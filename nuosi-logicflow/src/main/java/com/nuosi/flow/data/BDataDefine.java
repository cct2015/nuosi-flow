package com.nuosi.flow.data;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nuosi.flow.data.impl.BizDataDefine;

import java.util.Map;

/**
 * <p>desc: 业务传输对象数据定义的接口抽象</p>
 * <p>date: 2021/3/16 14:36</p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public interface BDataDefine {

    public enum BDataType {
        INT, STRING, DECIMAL, DATE, DATETIME, BOOLEAN, BDATA; //后续扩展更多类型
    }

    public String getBizName();

    public BizDataDefine defineLimit(String attr, BDataLimit dataLimit);

    public Map<String, BDataLimit> getDataLimits();

    public BDataLimit getDataLimit(String bizName);

    public String[] getAttrs();

    public boolean containsAttr(String attr);

    /**校验数据的基础类型*/
    public boolean checkData(String key, Object value);
    /**校验数据的对象类型*/
    public boolean checkData(JSONObject value);

    public boolean checkData(JSONArray value);
}
