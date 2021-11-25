package com.nuosi.flow.logic.model.action.sub;

/**
 * <p>desc: 逻辑流元素：http请求头参数 </p>
 * <p>date: 2021/10/27 14:40 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class Header {
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
