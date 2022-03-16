package com.nuosi.flow.logic.model.domain;

import com.nuosi.flow.logic.model.validate.*;

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
    private String regex;
    private boolean exists = true; // 默认模型属性要校验，严谨
    private List<ValidateInteger> validateIntegers;
    private List<ValidateString> validateStrings;
    private List<ValidateDecimal> validateDecimals;
    private List<ValidateDate> validateDates;
    private List<ValidateDatetime> validateDatetimes;

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

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public List<ValidateInteger> getValidateIntegers() {
        return validateIntegers;
    }

    public void setValidateIntegers(List<ValidateInteger> validateIntegers) {
        this.validateIntegers = validateIntegers;
    }

    public List<ValidateString> getValidateStrings() {
        return validateStrings;
    }

    public void setValidateStrings(List<ValidateString> validateStrings) {
        this.validateStrings = validateStrings;
    }

    public List<ValidateDecimal> getValidateDecimals() {
        return validateDecimals;
    }

    public void setValidateDecimals(List<ValidateDecimal> validateDecimals) {
        this.validateDecimals = validateDecimals;
    }

    public List<ValidateDate> getValidateDates() {
        return validateDates;
    }

    public void setValidateDates(List<ValidateDate> validateDates) {
        this.validateDates = validateDates;
    }

    public List<ValidateDatetime> getValidateDatetimes() {
        return validateDatetimes;
    }

    public void setValidateDatetimes(List<ValidateDatetime> validateDatetimes) {
        this.validateDatetimes = validateDatetimes;
    }
}
