package com.nuosi.flow.logic.model.action;

/**
 * <p>desc: 逻辑流元素：数据聚合节点 </p>
 * <p>date: 2022/1/11 16:46 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class Aggregate {
    private String aggregate;

    public String getAggregate() {
        return aggregate;
    }

    public void setAggregate(String aggregate) {
        this.aggregate = aggregate == null ? aggregate : aggregate.trim();
    }
}
