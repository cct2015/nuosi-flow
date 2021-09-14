package com.nuosi.flow.logic.model.domain;

import com.nuosi.flow.logic.model.limit.*;

import java.util.List;

/**
 * <p>desc: 逻辑流元素：领域模型属性 </p>
 * <p>date: 2021/3/25 18:08 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class Attr {
    private String id;
    private String type;
    private String name;
    private List<Limit> limits;
    private List<LimitInteger> limitIntegers;
    private List<LimitString> limitStrings;
    private List<LimitDecimal> LimitDecimals;
    private List<LimitDate> LimitDates;
    private List<LimitDatetime> LimitDatetimes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Limit> getLimits() {
        return limits;
    }

    public void setLimits(List<Limit> limits) {
        this.limits = limits;
    }

    public List<LimitInteger> getLimitIntegers() {
        return limitIntegers;
    }

    public void setLimitIntegers(List<LimitInteger> limitIntegers) {
        this.limitIntegers = limitIntegers;
    }

    public List<LimitString> getLimitStrings() {
        return limitStrings;
    }

    public void setLimitStrings(List<LimitString> limitStrings) {
        this.limitStrings = limitStrings;
    }

    public List<LimitDecimal> getLimitDecimals() {
        return LimitDecimals;
    }

    public void setLimitDecimals(List<LimitDecimal> limitDecimals) {
        LimitDecimals = limitDecimals;
    }

    public List<LimitDate> getLimitDates() {
        return LimitDates;
    }

    public void setLimitDates(List<LimitDate> limitDates) {
        LimitDates = limitDates;
    }

    public List<LimitDatetime> getLimitDatetimes() {
        return LimitDatetimes;
    }

    public void setLimitDatetimes(List<LimitDatetime> limitDatetimes) {
        LimitDatetimes = limitDatetimes;
    }
}
