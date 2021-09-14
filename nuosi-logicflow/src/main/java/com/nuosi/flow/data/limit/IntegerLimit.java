package com.nuosi.flow.data.limit;

import com.nuosi.flow.data.BDataDefine;
import com.nuosi.flow.util.BizDataValidityUtil;

/**
 * <p>desc: 整型数据限制和校验 </p>
 * <p>date: 2021/4/8 11:27 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class IntegerLimit extends AbstractDataLimit {
    private Integer max = null;
    private Integer min = null;
    private Integer less = null;
    private Integer more = null;
    private Integer equal = null;
    private Integer unequal = null;

    public IntegerLimit() {
        super(BDataDefine.BDataType.INT);
    }

    @Override
    public void checkValidity(String bizName, String attr, Object value) {
        Integer intValue = BizDataValidityUtil.checkInt(value, bizName, attr);
        BizDataValidityUtil.checkIntLimit(intValue, this, bizName, attr);
    }

    public Integer getMax() {
        return max;
    }

    public IntegerLimit setMax(Integer max) {
        this.max = max;
        return this;
    }

    public Integer getMin() {
        return min;
    }

    public IntegerLimit setMin(Integer min) {
        this.min = min;
        return this;
    }

    public Integer getLess() {
        return less;
    }

    public IntegerLimit setLess(Integer less) {
        this.less = less;
        return this;
    }

    public Integer getMore() {
        return more;
    }

    public IntegerLimit setMore(Integer more) {
        this.more = more;
        return this;
    }

    public Integer getEqual() {
        return equal;
    }

    public IntegerLimit setEqual(Integer equal) {
        this.equal = equal;
        return this;
    }

    public Integer getUnequal() {
        return unequal;
    }

    public IntegerLimit setUnequal(Integer unequal) {
        this.unequal = unequal;
        return this;
    }
}
