package com.nuosi.flow.logic.model.validate;

import java.sql.Timestamp;

/**
 * <p>desc: 逻辑流元素：时间戳限制 </p>
 * <p>date: 2021/9/4 23:16 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class ValidateDatetime {
    private Timestamp min = null;
    private Timestamp max = null;

    private Timestamp less = null;
    private Timestamp more = null;
    private Timestamp equal = null;
    private Timestamp unequal = null;

    private boolean nullable = true;

    public Timestamp getMin() {
        return min;
    }

    public void setMin(Timestamp min) {
        this.min = min;
    }

    public Timestamp getMax() {
        return max;
    }

    public void setMax(Timestamp max) {
        this.max = max;
    }

    public Timestamp getLess() {
        return less;
    }

    public void setLess(Timestamp less) {
        this.less = less;
    }

    public Timestamp getMore() {
        return more;
    }

    public void setMore(Timestamp more) {
        this.more = more;
    }

    public Timestamp getEqual() {
        return equal;
    }

    public void setEqual(Timestamp equal) {
        this.equal = equal;
    }

    public Timestamp getUnequal() {
        return unequal;
    }

    public void setUnequal(Timestamp unequal) {
        this.unequal = unequal;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }
}
