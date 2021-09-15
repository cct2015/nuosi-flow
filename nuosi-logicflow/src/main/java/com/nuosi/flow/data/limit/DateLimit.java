package com.nuosi.flow.data.limit;

import com.nuosi.flow.data.BDataDefine;
import com.nuosi.flow.data.BDataLimit;
import com.nuosi.flow.util.BizDataValidityUtil;

import java.sql.Date;

/**
 * <p>desc: 日期数据限制和校验 </p>
 * <p>date: 2021/4/8 15:31 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class DateLimit extends AbstractDataLimit {
    private java.sql.Date min = null;
    private java.sql.Date max = null;

    private java.sql.Date less = null;
    private java.sql.Date more = null;
    private java.sql.Date equal = null;
    private java.sql.Date unequal = null;

    public DateLimit() {
        super(BDataDefine.BDataType.DATE);
    }

    @Override
    public void checkValidity(String bizName, String attr, Object value){
        java.sql.Date dateValue = BizDataValidityUtil.checkDate(value, bizName, attr);
        BizDataValidityUtil.checkDateLimit(dateValue, this, bizName, attr);
    }

    public Date getMin() {
        return min;
    }

    public DateLimit setMin(Date min) {
        this.min = min;
        return this;
    }

    public Date getMax() {
        return max;
    }

    public DateLimit setMax(Date max) {
        this.max = max;
        return this;
    }

    public Date getLess() {
        return less;
    }

    public DateLimit setLess(Date less) {
        this.less = less;
        return this;
    }

    public Date getMore() {
        return more;
    }

    public DateLimit setMore(Date more) {
        this.more = more;
        return this;
    }

    public Date getEqual() {
        return equal;
    }

    public DateLimit setEqual(Date equal) {
        this.equal = equal;
        return this;
    }

    public Date getUnequal() {
        return unequal;
    }

    public DateLimit setUnequal(Date unequal) {
        this.unequal = unequal;
        return this;
    }
}
