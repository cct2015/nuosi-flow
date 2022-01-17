package com.nuosi.flow.logic.model.action.sub;

/**
 * <p>desc: 逻辑流元素：函数功能入参 </p>
 * <p>date: 2021/5/17 23:06 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class Param {
    private String key;
    private String type;
    private String value;
    private String context;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
