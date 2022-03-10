package com.nuosi.flow.logic.model.validate;

import java.sql.Date;

/**
 * <p>desc: 逻辑流元素：日期限制 </p>
 * <p>date: 2021/9/4 23:16 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class ValidateDate {
    private java.sql.Date min = null;
    private java.sql.Date max = null;

    private java.sql.Date less = null;
    private java.sql.Date more = null;
    private java.sql.Date equal = null;
    private java.sql.Date unequal = null;

    private boolean nullable = true;

    public Date getMin() {
        return min;
    }

    public void setMin(Date min) {
        this.min = min;
    }

    public Date getMax() {
        return max;
    }

    public void setMax(Date max) {
        this.max = max;
    }

    public Date getLess() {
        return less;
    }

    public void setLess(Date less) {
        this.less = less;
    }

    public Date getMore() {
        return more;
    }

    public void setMore(Date more) {
        this.more = more;
    }

    public Date getEqual() {
        return equal;
    }

    public void setEqual(Date equal) {
        this.equal = equal;
    }

    public Date getUnequal() {
        return unequal;
    }

    public void setUnequal(Date unequal) {
        this.unequal = unequal;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }
}
