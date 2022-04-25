package com.nuosi.flow.data.validate;

import com.nuosi.flow.data.BDataDefine;
import com.nuosi.flow.util.BizDataValidityUtil;

/**
 * <p>desc: 长整型数据限制和校验 </p>
 * <p>date: 2022/4/25 19:21 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class LongValidator extends AbstractDataValidator {
    private Long max = null;
    private Long min = null;
    private Long less = null;
    private Long more = null;
    private Long equal = null;
    private Long unequal = null;

    public LongValidator() {
        super(BDataDefine.BDataType.LONG);
    }

    @Override
    public void checkValidity(String bizName, String attr, Object value) {
        Long longValue = BizDataValidityUtil.checkLong(value, bizName, attr);
        BizDataValidityUtil.checkLongValidate(this, longValue, bizName, attr);
    }

    public Long getMax() {
        return max;
    }

    public LongValidator setMax(Long max) {
        this.max = max;
        return this;
    }

    public Long getMin() {
        return min;
    }

    public LongValidator setMin(Long min) {
        this.min = min;
        return this;
    }

    public Long getLess() {
        return less;
    }

    public LongValidator setLess(Long less) {
        this.less = less;
        return this;
    }

    public Long getMore() {
        return more;
    }

    public LongValidator setMore(Long more) {
        this.more = more;
        return this;
    }

    public Long getEqual() {
        return equal;
    }

    public LongValidator setEqual(Long equal) {
        this.equal = equal;
        return this;
    }

    public Long getUnequal() {
        return unequal;
    }

    public LongValidator setUnequal(Long unequal) {
        this.unequal = unequal;
        return this;
    }
}
