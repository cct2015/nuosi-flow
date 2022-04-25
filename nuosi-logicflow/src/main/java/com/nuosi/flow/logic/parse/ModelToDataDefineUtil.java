package com.nuosi.flow.logic.parse;

import com.nuosi.flow.data.BDataDefine;
import com.nuosi.flow.data.BDataDefine.BDataType;
import com.nuosi.flow.data.BDataValidator;
import com.nuosi.flow.data.impl.BizDataDefine;
import com.nuosi.flow.data.validate.*;
import com.nuosi.flow.logic.LogicFlowManager;
import com.nuosi.flow.logic.invoke.check.FlowDataDefine;
import com.nuosi.flow.logic.model.LogicFlow;
import com.nuosi.flow.logic.model.domain.Attr;
import com.nuosi.flow.logic.model.domain.DomainModel;
import com.nuosi.flow.logic.model.validate.*;

import java.util.List;

import static com.nuosi.flow.data.BDataDefine.BDataType.valueOf;

/**
 * <p>desc: Dto XML文件转换为DataDefine </p>
 * <p>date: 2021/4/8 21:01 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class ModelToDataDefineUtil {

    public static BDataDefine parse(String domainModelName) throws Exception {
        DomainModel domainModel = LogicFlowManager.getDomainModel(domainModelName);
        BDataDefine bDataDefine = new BizDataDefine(domainModel.getId());
        return parseByAttrs(bDataDefine, domainModel.getAttrs());
    }

    public static BDataDefine parseByFlow(LogicFlow logicFlow, List<Attr> flowAttrs) {
        BDataDefine fDataDefine = new FlowDataDefine(logicFlow.getId());
        return parseByAttrs(fDataDefine, flowAttrs);
    }

    private static BDataDefine parseByAttrs(BDataDefine dataDefine, List<Attr> attrs) {
        for (Attr attr : attrs) {
            BDataType dataType = valueOf(attr.getType().toUpperCase());

            BDataValidator bDataValidator = null;
            switch (dataType) {
                case STRING:
                    List<ValidateString> validateStrings = attr.getValidateStrings();
                    if (validateStrings != null && !validateStrings.isEmpty()) {
                        ValidateString validateString = validateStrings.get(0);
                        bDataValidator = parseStringValidate(validateString);
                    }
                    break;
                case INT:
                    List<ValidateInteger> validateIntegers = attr.getValidateIntegers();
                    if (validateIntegers != null && !validateIntegers.isEmpty()) {
                        ValidateInteger validateInt = validateIntegers.get(0);
                        bDataValidator = parseIntegerValidate(validateInt);
                    }
                    break;
                case DECIMAL:
                    List<ValidateDecimal> validateDecimals = attr.getValidateDecimals();
                    if (validateDecimals != null && !validateDecimals.isEmpty()) {
                        ValidateDecimal validateDecimal = validateDecimals.get(0);
                        bDataValidator = parseDecimalValidate(validateDecimal);
                    }
                    break;
                case DATE:
                    List<ValidateDate> validateDates = attr.getValidateDates();
                    if (validateDates != null && !validateDates.isEmpty()) {
                        ValidateDate validateDate = validateDates.get(0);
                        bDataValidator = parseDateValidate(validateDate);
                    }
                    break;
                case DATETIME:
                    List<ValidateDatetime> validateDatetimes = attr.getValidateDatetimes();
                    if (validateDatetimes != null && !validateDatetimes.isEmpty()) {
                        ValidateDatetime validateDatetime = validateDatetimes.get(0);
                        bDataValidator = parseDatetimeValidate(validateDatetime);
                    }
                    break;
                case LONG:
                    List<ValidateLong> validateLongs = attr.getValidateLongs();
                    if (validateLongs != null && !validateLongs.isEmpty()) {
                        ValidateLong validateLong = validateLongs.get(0);
                        bDataValidator = parseLongValidate(validateLong);
                    }
                    break;
                case BOOLEAN:
                    break;
                case OBJECT:
                    break;
                case ARRAY:
                    break;
                default:
                    break;
            }
            if (bDataValidator == null) {
                bDataValidator = createEmptyBDataValidate(dataType);    //BDataValidator不为空则可触发基础类型的校验
            }
            bDataValidator.setRegex(attr.getRegex());
            bDataValidator.setExists(attr.isExists());
            dataDefine.defineValidator(attr.getId(), bDataValidator);
        }
        return dataDefine;
    }

    private static BDataValidator parseIntegerValidate(ValidateInteger validateInt) {
        IntegerValidator integerValidator = new IntegerValidator();
        org.springframework.beans.BeanUtils.copyProperties(validateInt, integerValidator);
        return integerValidator;
    }

    private static BDataValidator parseStringValidate(ValidateString validateString) {
        StringValidator stringValidator = new StringValidator();
        org.springframework.beans.BeanUtils.copyProperties(validateString, stringValidator);
        return stringValidator;
    }

    private static BDataValidator parseDecimalValidate(ValidateDecimal validateDecimal) {
        DecimalValidator decimalValidator = new DecimalValidator();
        org.springframework.beans.BeanUtils.copyProperties(validateDecimal, decimalValidator);
        return decimalValidator;
    }

    private static BDataValidator parseDateValidate(ValidateDate validateDate) {
        DateValidator dateValidator = new DateValidator();
        org.springframework.beans.BeanUtils.copyProperties(validateDate, dateValidator);
        return dateValidator;
    }

    private static BDataValidator parseDatetimeValidate(ValidateDatetime validateDatetime) {
        DatetimeValidator datetimeValidator = new DatetimeValidator();
        org.springframework.beans.BeanUtils.copyProperties(validateDatetime, datetimeValidator);
        return datetimeValidator;
    }

    private static BDataValidator parseLongValidate(ValidateLong validateLong) {
        LongValidator longValidator = new LongValidator();
        org.springframework.beans.BeanUtils.copyProperties(validateLong, longValidator);
        return longValidator;
    }

    private static BDataValidator createEmptyBDataValidate(BDataType dataType) {
        BDataValidator bdataValidator = null;
        switch (dataType) {
            case INT:
                bdataValidator = new IntegerValidator();
                break;
            case STRING:
                bdataValidator = new StringValidator();
                break;
            case DECIMAL:
                bdataValidator = new DecimalValidator();
                break;
            case DATE:
                bdataValidator = new DateValidator();
                break;
            case DATETIME:
                bdataValidator = new DatetimeValidator();
                break;
            case LONG:
                bdataValidator = new LongValidator();
                break;
            case BOOLEAN:
                bdataValidator = new BooleanValidator();
                break;
            case OBJECT:
                bdataValidator = new ObjectValidator();
                break;
            case ARRAY:
                bdataValidator = new ArrayValidator();
                break;
            default:
                break;
        }
        return bdataValidator;
    }
}
