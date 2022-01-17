package com.nuosi.flow.logic.model.action;

import com.nuosi.flow.logic.model.action.sub.Param;

import java.util.List;

/**
 * <p>desc: 逻辑流元素：函数功能行为 </p>
 * <p>date: 2021/5/17 23:03 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class Function {
    private String domain;
    private String name;
    private String outkey;
    private List<Param> params;
    private String isContext;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOutkey() {
        return outkey;
    }

    public void setOutkey(String outkey) {
        this.outkey = outkey;
    }

    public List<Param> getParams() {
        return params;
    }

    public void setParams(List<Param> params) {
        this.params = params;
    }

    public String getIsContext() {
        return isContext;
    }

    public void setIsContext(String isContext) {
        this.isContext = isContext;
    }
}
