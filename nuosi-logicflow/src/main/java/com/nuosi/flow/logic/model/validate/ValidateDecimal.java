package com.nuosi.flow.logic.model.validate;

import java.math.BigDecimal;

/**
 * <p>desc: 逻辑流元素：小数限制 </p>
 * <p>date: 2021/9/4 23:16 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class ValidateDecimal {
    private Integer precision = null;
    private Integer scale = null;
    private BigDecimal min = null;
    private BigDecimal max = null;

    private BigDecimal less = null;
    private BigDecimal more = null;
    private BigDecimal equal = null;
    private BigDecimal unequal = null;

    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public BigDecimal getLess() {
        return less;
    }

    public void setLess(BigDecimal less) {
        this.less = less;
    }

    public BigDecimal getMore() {
        return more;
    }

    public void setMore(BigDecimal more) {
        this.more = more;
    }

    public BigDecimal getEqual() {
        return equal;
    }

    public void setEqual(BigDecimal equal) {
        this.equal = equal;
    }

    public BigDecimal getUnequal() {
        return unequal;
    }

    public void setUnequal(BigDecimal unequal) {
        this.unequal = unequal;
    }
}
