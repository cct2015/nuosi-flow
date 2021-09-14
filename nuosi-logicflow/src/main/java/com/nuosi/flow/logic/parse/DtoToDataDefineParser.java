package com.nuosi.flow.logic.parse;

import com.nuosi.flow.data.BDataDefine;
import com.nuosi.flow.data.BDataLimit;
import com.nuosi.flow.data.impl.BizDataDefine;
import com.nuosi.flow.data.limit.*;
import com.nuosi.flow.logic.LogicFlowManager;
import com.nuosi.flow.logic.model.domain.Attr;
import com.nuosi.flow.logic.model.domain.DomainModel;
import com.nuosi.flow.logic.model.limit.*;

import java.util.List;

/**
 * <p>desc: Dto XML文件转换为DataDefine </p>
 * <p>date: 2021/4/8 21:01 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class DtoToDataDefineParser {

    public DtoToDataDefineParser() {
    }

    public BDataDefine parse(String domainModelName) throws Exception {
        DomainModel domainModel = LogicFlowManager.getDomainModel(domainModelName);
        return parse(domainModel);
    }

    public BDataDefine parse(DomainModel domainModel) throws Exception {
        BDataDefine dataDefine = new BizDataDefine(domainModel.getId());
        List<Attr> attrs = domainModel.getAttrs();
        for (Attr attr : attrs) {
            BDataDefine.BDataType dataType = BDataDefine.BDataType.valueOf(attr.getType().toUpperCase());
            /*List<Limit> limits = attr.getLimits();
            if (limits == null || limits.isEmpty()) {
                dataDefine.defineLimit(attr.getId(), BizDataLimitManager.create(dataType));
            }else{
                Limit limit = limits.get(0);
                BDataLimit dataLimit = parseLimitToBDataLimit(dataType, limit);
                dataDefine.defineLimit(attr.getId(), dataLimit);
            }*/
        }
        return dataDefine;
    }

    public BDataDefine parseByAttrs(String bizName, List<Attr> attrs){
        BDataDefine dataDefine = new BizDataDefine(bizName);
        for (Attr attr : attrs) {
            BDataDefine.BDataType dataType = BDataDefine.BDataType.valueOf(attr.getType().toUpperCase());

            List<LimitInteger> limitIntegers = attr.getLimitIntegers();
            List<LimitString> limitStrings = attr.getLimitStrings();
            List<LimitDecimal> LimitDecimals = attr.getLimitDecimals();
            List<LimitDate> LimitDates = attr.getLimitDates();
            List<LimitDatetime> LimitDatetime = attr.getLimitDatetimes();

            BDataLimit dataLimit = null;
            if(limitIntegers!=null&&!limitIntegers.isEmpty()){
                LimitInteger limitInt = limitIntegers.get(0);
                dataLimit = parseIntegerLimit(limitInt);
            }else if(limitStrings!=null&&!limitStrings.isEmpty()){
                LimitString limitString = limitStrings.get(0);
                dataLimit = parseStringLimit(limitString);
            }else if(LimitDecimals!=null&&!LimitDecimals.isEmpty()){
                LimitDecimal limitDecimal = LimitDecimals.get(0);
                dataLimit = parseDecimalLimit(limitDecimal);
            }else if(LimitDates!=null&&!LimitDates.isEmpty()){
                LimitDate limitDate = LimitDates.get(0);
                dataLimit = parseDateLimit(limitDate);
            }else if(LimitDatetime!=null&&!LimitDatetime.isEmpty()){
                LimitDecimal limitDecimal = LimitDecimals.get(0);
                parseDecimalLimit(limitDecimal);
            }

            if(dataLimit != null)
                dataDefine.defineLimit(attr.getId(), dataLimit);

            /*List<Limit> limits = attr.getLimits();
            if (limits == null || limits.isEmpty()) {
                dataDefine.defineLimit(attr.getId(), BizDataLimitManager.create(dataType));
            }else{
                Limit limit = limits.get(0);
                BDataLimit dataLimit = parseLimitToBDataLimit(dataType, limit);
                dataDefine.defineLimit(attr.getId(), dataLimit);
            }*/
        }
        return dataDefine;
    }

    private BDataLimit parseIntegerLimit(LimitInteger limitInt) {
        IntegerLimit integerLimit = new IntegerLimit();
        org.springframework.beans.BeanUtils.copyProperties(limitInt, integerLimit);
        return integerLimit;
    }

    private BDataLimit parseStringLimit(LimitString limitString) {
        StringLimit stringLimit = new StringLimit();
        org.springframework.beans.BeanUtils.copyProperties(limitString, stringLimit);
        return stringLimit;
    }

    private BDataLimit parseDecimalLimit(LimitDecimal limitDecimal) {
        DecimalLimit decimalLimit = new DecimalLimit();
        org.springframework.beans.BeanUtils.copyProperties(limitDecimal, decimalLimit);
        return decimalLimit;
    }

    private BDataLimit parseDateLimit(LimitDate limitDate) {
        DateLimit dateLimit = new DateLimit();
        org.springframework.beans.BeanUtils.copyProperties(limitDate, dateLimit);
        return dateLimit;
    }

    private BDataLimit parseDatetimeLimit(LimitDatetime limitDatetime) {
        DatetimeLimit datetimeLimit = new DatetimeLimit();
        org.springframework.beans.BeanUtils.copyProperties(limitDatetime, datetimeLimit);
        return datetimeLimit;
    }
}
