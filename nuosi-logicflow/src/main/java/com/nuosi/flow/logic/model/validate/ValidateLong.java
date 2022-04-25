package com.nuosi.flow.logic.model.validate;

/**
 * <p>desc: 逻辑流元素：长整数限制 </p>
 * <p>date: 2022/4/25 19:17 </p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class ValidateLong {
    private Long min = null;
    private Long max = null;
    private Long less = null;
    private Long more = null;
    private Long equal = null;
    private Long unequal = null;

    public Long getMin() {
        return min;
    }

    public void setMin(Long min) {
        this.min = min;
    }

    public Long getMax() {
        return max;
    }

    public void setMax(Long max) {
        this.max = max;
    }

    public Long getLess() {
        return less;
    }

    public void setLess(Long less) {
        this.less = less;
    }

    public Long getMore() {
        return more;
    }

    public void setMore(Long more) {
        this.more = more;
    }

    public Long getEqual() {
        return equal;
    }

    public void setEqual(Long equal) {
        this.equal = equal;
    }

    public Long getUnequal() {
        return unequal;
    }

    public void setUnequal(Long unequal) {
        this.unequal = unequal;
    }
}
