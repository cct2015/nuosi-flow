package com.nuosi.flow.logic.model.element;

/**
 * <p>desc: 逻辑流元素：变量定义</p>
 * <p>date: 2021/3/6 12:19</p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class Var {
    private String model;
    private String attr;
    private String key;
    private String alias;
    private String initial;
    private String initialMethod;
    private String calculateMethod;
    private boolean attrExists = true; // 默认模型属性都校验，严谨

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getInitialMethod() {
        return initialMethod;
    }

    public void setInitialMethod(String initialMethod) {
        this.initialMethod = initialMethod;
    }

    public String getCalculateMethod() {
        return calculateMethod;
    }

    public void setCalculateMethod(String calculateMethod) {
        this.calculateMethod = calculateMethod;
    }

    public boolean isAttrExists() {
        return attrExists;
    }

    public void setAttrExists(boolean attrExists) {
        this.attrExists = attrExists;
    }
}
