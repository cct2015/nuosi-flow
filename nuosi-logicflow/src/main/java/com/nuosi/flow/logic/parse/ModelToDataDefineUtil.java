package com.nuosi.flow.logic.parse;

import com.nuosi.flow.data.BDataDefine;
import com.nuosi.flow.data.BDataDefine.BDataType;
import com.nuosi.flow.data.BDataLimit;
import com.nuosi.flow.data.impl.BizDataDefine;
import com.nuosi.flow.data.limit.*;
import com.nuosi.flow.logic.LogicFlowManager;
import com.nuosi.flow.logic.invoke.check.FlowDataDefine;
import com.nuosi.flow.logic.model.LogicFlow;
import com.nuosi.flow.logic.model.domain.Attr;
import com.nuosi.flow.logic.model.domain.DomainModel;
import com.nuosi.flow.logic.model.limit.*;

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

    public static BDataDefine parseByFlow(LogicFlow logicFlow, List<Attr> flowAttrs){
        BDataDefine fDataDefine = new FlowDataDefine(logicFlow.getId());
        return parseByAttrs(fDataDefine, flowAttrs);
    }

    private static BDataDefine parseByAttrs(BDataDefine dataDefine, List<Attr> attrs){
        for (Attr attr : attrs) {
            BDataType dataType = valueOf(attr.getType().toUpperCase());

            BDataLimit bDataLimit = null;
            switch (dataType) {
                case STRING:
                    List<LimitString> limitStrings = attr.getLimitStrings();
                    if (limitStrings != null && !limitStrings.isEmpty()) {
                        LimitString limitString = limitStrings.get(0);
                        bDataLimit = parseStringLimit(limitString);
                    }
                    break;
                case INT:
                    List<LimitInteger> limitIntegers = attr.getLimitIntegers();
                    if (limitIntegers != null && !limitIntegers.isEmpty()) {
                        LimitInteger limitInt = limitIntegers.get(0);
                        bDataLimit = parseIntegerLimit(limitInt);
                    }
                    break;
                case DECIMAL:
                    List<LimitDecimal> LimitDecimals = attr.getLimitDecimals();
                    if (LimitDecimals != null && !LimitDecimals.isEmpty()) {
                        LimitDecimal limitDecimal = LimitDecimals.get(0);
                        bDataLimit = parseDecimalLimit(limitDecimal);
                    }
                    break;
                case DATE:
                    List<LimitDate> LimitDates = attr.getLimitDates();
                    if (LimitDates != null && !LimitDates.isEmpty()) {
                        LimitDate limitDate = LimitDates.get(0);
                        bDataLimit = parseDateLimit(limitDate);
                    }
                    break;
                case DATETIME:
                    List<LimitDatetime> LimitDatetimes = attr.getLimitDatetimes();
                    if (LimitDatetimes != null && !LimitDatetimes.isEmpty()) {
                        LimitDatetime limitDatetime = LimitDatetimes.get(0);
                        bDataLimit = parseDatetimeLimit(limitDatetime);
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
            if (bDataLimit == null) {
                bDataLimit = createEmptyBDataLimit(dataType);    //BDataLimit不为空则可触发基础类型的校验
            }
            bDataLimit.setRegex(attr.getRegex());
            dataDefine.defineLimit(attr.getId(), bDataLimit);
        }
        return dataDefine;
    }

    private static BDataLimit parseIntegerLimit(LimitInteger limitInt) {
        IntegerLimit integerLimit = new IntegerLimit();
        org.springframework.beans.BeanUtils.copyProperties(limitInt, integerLimit);
        return integerLimit;
    }

    private static BDataLimit parseStringLimit(LimitString limitString) {
        StringLimit stringLimit = new StringLimit();
        org.springframework.beans.BeanUtils.copyProperties(limitString, stringLimit);
        return stringLimit;
    }

    private static BDataLimit parseDecimalLimit(LimitDecimal limitDecimal) {
        DecimalLimit decimalLimit = new DecimalLimit();
        org.springframework.beans.BeanUtils.copyProperties(limitDecimal, decimalLimit);
        return decimalLimit;
    }

    private static BDataLimit parseDateLimit(LimitDate limitDate) {
        DateLimit dateLimit = new DateLimit();
        org.springframework.beans.BeanUtils.copyProperties(limitDate, dateLimit);
        return dateLimit;
    }

    private static BDataLimit parseDatetimeLimit(LimitDatetime limitDatetime) {
        DatetimeLimit datetimeLimit = new DatetimeLimit();
        org.springframework.beans.BeanUtils.copyProperties(limitDatetime, datetimeLimit);
        return datetimeLimit;
    }

    private static BDataLimit createEmptyBDataLimit(BDataType dataType){
        BDataLimit bdataLimit = null;
        switch(dataType){
            case INT:
                bdataLimit = new IntegerLimit();
                break;
            case STRING:
                bdataLimit = new StringLimit();
                break;
            case DECIMAL:
                bdataLimit = new DecimalLimit();
                break;
            case DATE:
                bdataLimit = new DateLimit();
                break;
            case DATETIME:
                bdataLimit = new DatetimeLimit();
                break;
            case BOOLEAN:
                bdataLimit = new BooleanLimit();
                break;
            case OBJECT:
                bdataLimit = new ObjectLimit();
                break;
            case ARRAY:
                bdataLimit = new ArrayLimit();
                break;
            default:
                break;
        }
        return bdataLimit;
    }
}
