package com.nuosi.flow.data.limit;

import com.nuosi.flow.data.BDataDefine;
import com.nuosi.flow.util.BizDataValidityUtil;

import java.sql.Timestamp;

/**
 * <p>desc: 时间戳数据限制和校验 </p>
 * <p>date: 2021/4/8 15:31 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class DatetimeLimit extends AbstractDataLimit {
    private Timestamp min = null;
    private Timestamp max = null;

    private Timestamp less = null;
    private Timestamp more = null;
    private Timestamp equal = null;
    private Timestamp unequal = null;

    public DatetimeLimit() {
        super(BDataDefine.BDataType.DATETIME);
    }

    @Override
    public void checkValidity(String bizName, String attr, Object value) {
        Timestamp timestampValue = BizDataValidityUtil.checkDatetime(value, bizName, attr);
        BizDataValidityUtil.checkDatetimeLimit(timestampValue, this, bizName, attr);
    }

    public Timestamp getMin() {
        return min;
    }

    public DatetimeLimit setMin(Timestamp min) {
        this.min = min;
        return this;
    }

    public Timestamp getMax() {
        return max;
    }

    public DatetimeLimit setMax(Timestamp max) {
        this.max = max;
        return this;
    }

    public Timestamp getLess() {
        return less;
    }

    public DatetimeLimit setLess(Timestamp less) {
        this.less = less;
        return this;
    }

    public Timestamp getMore() {
        return more;
    }

    public DatetimeLimit setMore(Timestamp more) {
        this.more = more;
        return this;
    }

    public Timestamp getEqual() {
        return equal;
    }

    public DatetimeLimit setEqual(Timestamp equal) {
        this.equal = equal;
        return this;
    }

    public Timestamp getUnequal() {
        return unequal;
    }

    public DatetimeLimit setUnequal(Timestamp unequal) {
        this.unequal = unequal;
        return this;
    }
}
