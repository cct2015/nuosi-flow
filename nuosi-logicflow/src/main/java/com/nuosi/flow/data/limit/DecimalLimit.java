package com.nuosi.flow.data.limit;

import com.nuosi.flow.data.BDataDefine;
import com.nuosi.flow.util.BizDataValidityUtil;

import java.math.BigDecimal;

/**
 * <p>desc: 小数数据限制和校验 </p>
 * <p>date: 2021/4/8 15:30 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class DecimalLimit extends AbstractDataLimit {
    private Integer precision = null;
    private Integer scale = null;
    private BigDecimal min = null;
    private BigDecimal max = null;
    private BigDecimal less = null;
    private BigDecimal more = null;
    private BigDecimal equal = null;
    private BigDecimal unequal = null;

    public DecimalLimit() {
        super(BDataDefine.BDataType.DECIMAL);
    }

    @Override
    public void checkValidity(String bizName, String attr, Object value) {
        BigDecimal decimalValue = BizDataValidityUtil.checkDecimal(value, bizName, attr);
        BizDataValidityUtil.checkDecimalLimit(decimalValue, this, bizName, attr);
    }

    public Integer getPrecision() {
        return precision;
    }

    public DecimalLimit setPrecision(Integer precision) {
        this.precision = precision;
        return this;
    }

    public Integer getScale() {
        return scale;
    }

    public DecimalLimit setScale(Integer scale) {
        this.scale = scale;
        return this;
    }

    public BigDecimal getMin() {
        return min;
    }

    public DecimalLimit setMin(BigDecimal min) {
        this.min = min;
        return this;
    }

    public BigDecimal getMax() {
        return max;
    }

    public DecimalLimit setMax(BigDecimal max) {
        this.max = max;
        return this;
    }

    public BigDecimal getLess() {
        return less;
    }

    public DecimalLimit setLess(BigDecimal less) {
        this.less = less;
        return this;
    }

    public BigDecimal getMore() {
        return more;
    }

    public DecimalLimit setMore(BigDecimal more) {
        this.more = more;
        return this;
    }

    public BigDecimal getEqual() {
        return equal;
    }

    public DecimalLimit setEqual(BigDecimal equal) {
        this.equal = equal;
        return this;
    }

    public BigDecimal getUnequal() {
        return unequal;
    }

    public DecimalLimit setUnequal(BigDecimal unequal) {
        this.unequal = unequal;
        return this;
    }
}
