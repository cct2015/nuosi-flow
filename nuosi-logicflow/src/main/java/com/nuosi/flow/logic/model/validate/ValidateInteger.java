package com.nuosi.flow.logic.model.validate;

/**
 * <p>desc: 逻辑流元素：整数限制 </p>
 * <p>date: 2021/9/4 14:31 </p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class ValidateInteger {
    private Integer min = null;
    private Integer max = null;
    private Integer less = null;
    private Integer more = null;
    private Integer equal = null;
    private Integer unequal = null;

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getLess() {
        return less;
    }

    public void setLess(Integer less) {
        this.less = less;
    }

    public Integer getMore() {
        return more;
    }

    public void setMore(Integer more) {
        this.more = more;
    }

    public Integer getEqual() {
        return equal;
    }

    public void setEqual(Integer equal) {
        this.equal = equal;
    }

    public Integer getUnequal() {
        return unequal;
    }

    public void setUnequal(Integer unequal) {
        this.unequal = unequal;
    }
}
