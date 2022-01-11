package com.nuosi.flow.logic.model.action;

/**
 * <p>desc: 逻辑流元素：数据聚合节点 </p>
 * <p>date: 2022/1/11 16:46 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class Aggregate {
    private String convert;
    private String type;
//    private String model;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConvert() {
        return convert;
    }

    public void setConvert(String convert) {
        this.convert = convert;
    }
}
