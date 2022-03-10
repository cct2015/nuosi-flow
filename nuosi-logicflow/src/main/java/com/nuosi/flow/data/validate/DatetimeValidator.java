package com.nuosi.flow.data.validate;

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
public class DatetimeValidator extends AbstractDataValidator {
    private Timestamp min = null;
    private Timestamp max = null;

    private Timestamp less = null;
    private Timestamp more = null;
    private Timestamp equal = null;
    private Timestamp unequal = null;

    private boolean nullable = true;

    public DatetimeValidator() {
        super(BDataDefine.BDataType.DATETIME);
    }

    @Override
    public void checkValidity(String bizName, String attr, Object value) {
        Timestamp timestampValue = BizDataValidityUtil.checkDatetime(value, bizName, attr);
        BizDataValidityUtil.checkDatetimeValidate(timestampValue, this, bizName, attr);
    }

    public Timestamp getMin() {
        return min;
    }

    public DatetimeValidator setMin(Timestamp min) {
        this.min = min;
        return this;
    }

    public Timestamp getMax() {
        return max;
    }

    public DatetimeValidator setMax(Timestamp max) {
        this.max = max;
        return this;
    }

    public Timestamp getLess() {
        return less;
    }

    public DatetimeValidator setLess(Timestamp less) {
        this.less = less;
        return this;
    }

    public Timestamp getMore() {
        return more;
    }

    public DatetimeValidator setMore(Timestamp more) {
        this.more = more;
        return this;
    }

    public Timestamp getEqual() {
        return equal;
    }

    public DatetimeValidator setEqual(Timestamp equal) {
        this.equal = equal;
        return this;
    }

    public Timestamp getUnequal() {
        return unequal;
    }

    public DatetimeValidator setUnequal(Timestamp unequal) {
        this.unequal = unequal;
        return this;
    }

    public boolean isNullable() {
        return nullable;
    }

    public DatetimeValidator setNullable(boolean nullable) {
        this.nullable = nullable;
        return this;
    }
}
