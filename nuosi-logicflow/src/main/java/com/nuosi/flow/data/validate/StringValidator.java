package com.nuosi.flow.data.validate;

import com.nuosi.flow.data.BDataDefine;
import com.nuosi.flow.util.BizDataValidityUtil;

/**
 * <p>desc: 字符串数据限制和校验 </p>
 * <p>date: 2021/4/8 14:45 </p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class StringValidator extends AbstractDataValidator {
    private Integer min = null;
    private Integer max = null;
    private Integer less = null;
    private Integer more = null;
    private Integer equal = null;
    private Integer unequal = null;
    private boolean nullable = true;

    public StringValidator() {
        super(BDataDefine.BDataType.STRING);
    }

    @Override
    public void checkValidity(String bizName, String attr, Object value) {
        String stringValue = BizDataValidityUtil.checkString(value, bizName, attr);
        BizDataValidityUtil.checkStringValidate(stringValue, this, bizName, attr);
    }

    public Integer getMin() {
        return min;
    }

    public StringValidator setMin(Integer min) {
        this.min = min;
        return this;
    }

    public Integer getMax() {
        return max;
    }

    public StringValidator setMax(Integer max) {
        this.max = max;
        return this;
    }

    public Integer getLess() {
        return less;
    }

    public StringValidator setLess(Integer less) {
        this.less = less;
        return this;
    }

    public Integer getMore() {
        return more;
    }

    public StringValidator setMore(Integer more) {
        this.more = more;
        return this;
    }

    public Integer getEqual() {
        return equal;
    }

    public StringValidator setEqual(Integer equal) {
        this.equal = equal;
        return this;
    }

    public Integer getUnequal() {
        return unequal;
    }

    public StringValidator setUnequal(Integer unequal) {
        this.unequal = unequal;
        return this;
    }

    public boolean isNullable() {
        return nullable;
    }

    public StringValidator setNullable(boolean nullable) {
        this.nullable = nullable;
        return this;
    }
}
