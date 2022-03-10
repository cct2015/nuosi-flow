package com.nuosi.flow.data.validate;

import com.nuosi.flow.data.BDataDefine;
import com.nuosi.flow.util.BizDataValidityUtil;

/**
 * <p>desc: 整型数据限制和校验 </p>
 * <p>date: 2021/4/8 11:27 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class IntegerValidator extends AbstractDataValidator {
    private Integer max = null;
    private Integer min = null;
    private Integer less = null;
    private Integer more = null;
    private Integer equal = null;
    private Integer unequal = null;

    public IntegerValidator() {
        super(BDataDefine.BDataType.INT);
    }

    @Override
    public void checkValidity(String bizName, String attr, Object value) {
        Integer intValue = BizDataValidityUtil.checkInt(value, bizName, attr);
        BizDataValidityUtil.checkIntValidate(intValue, this, bizName, attr);
    }

    public Integer getMax() {
        return max;
    }

    public IntegerValidator setMax(Integer max) {
        this.max = max;
        return this;
    }

    public Integer getMin() {
        return min;
    }

    public IntegerValidator setMin(Integer min) {
        this.min = min;
        return this;
    }

    public Integer getLess() {
        return less;
    }

    public IntegerValidator setLess(Integer less) {
        this.less = less;
        return this;
    }

    public Integer getMore() {
        return more;
    }

    public IntegerValidator setMore(Integer more) {
        this.more = more;
        return this;
    }

    public Integer getEqual() {
        return equal;
    }

    public IntegerValidator setEqual(Integer equal) {
        this.equal = equal;
        return this;
    }

    public Integer getUnequal() {
        return unequal;
    }

    public IntegerValidator setUnequal(Integer unequal) {
        this.unequal = unequal;
        return this;
    }
}
