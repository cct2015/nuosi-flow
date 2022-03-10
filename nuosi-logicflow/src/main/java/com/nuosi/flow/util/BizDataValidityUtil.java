package com.nuosi.flow.util;

import com.ai.ipu.basic.string.StringUtil;
import com.ai.ipu.basic.util.IpuUtility;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nuosi.flow.data.BDataDefine;
import com.nuosi.flow.data.BDataValidator;
import com.nuosi.flow.data.BizDataManager;
import com.nuosi.flow.data.validate.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * <p>desc: 数据类型校验工具类</p>
 * <p>date: 2021/3/16 23:24</p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class BizDataValidityUtil {

    /**
     * <p>desc: 描述这个方法功能的注释</p>
     * <p>date: 2021/3/20 20:15</p>
     * @param bizName 业务对象名
     * @param attr    业务对象属性
     * @param value   业务对象属性值
     * @author nuosi fsofs@163.com
     * @version v1.0.0
     */
    public static void checkData(String bizName, String attr, Object value) {
        BDataDefine dataDefine = BizDataManager.getDataDefine(bizName);
        BDataValidator dataValidator = dataDefine.getDataValidator(attr);
        switch (dataValidator.getDataType()) {
            /*校验整数类型*/
            case INT:
                Integer intValue = checkInt(value, bizName, attr);
                checkIntValidate(intValue, dataValidator, bizName, attr);
                break;
            /*校验字符类型*/
            case STRING:
                String stringValue = checkString(value, bizName, attr);
                checkStringValidate(stringValue, dataValidator, bizName, attr);
                break;
            /*校验小数类型*/
            case DECIMAL:
                BigDecimal decimalValue = checkDecimal(value, bizName, attr);
                checkDecimalValidate(decimalValue, dataValidator, bizName, attr);
                break;
            /*校验日期类型*/
            case DATE:
                java.sql.Date dateValue = checkDate(value, bizName, attr);
                checkDateValidate(dateValue, dataValidator, bizName, attr);
                break;
            /*校验日期时间类型*/
            case DATETIME:
                Timestamp timestampValue = checkDatetime(value, bizName, attr);
                checkDatetimeValidate(timestampValue, dataValidator, bizName, attr);
                break;
            default:
                break;
        }
    }

    public static Integer checkInt(Object value, String bizName, String attr) {
        Integer val = null;
        try {
            val = Integer.parseInt(String.valueOf(value));
        } catch (Exception e) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_INT, bizName, attr);
        }
        return val;
    }

    public static void checkIntValidate(Integer val, BDataValidator bdataValidator, String bizName, String attr) {
        if (bdataValidator == null) {
            return;
        }
        IntegerValidator dataValidator = (IntegerValidator) bdataValidator;
        if (dataValidator.getMax() != null && val > dataValidator.getMax()) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_INT_MAX, bizName, attr, String.valueOf(dataValidator.getMax()));
        }
        if (dataValidator.getMin() != null && val < dataValidator.getMin()) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_INT_MIN, bizName, attr, String.valueOf(dataValidator.getMin()));
        }
        if (dataValidator.getMore() != null && val >= dataValidator.getMore()) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_INT_MORE, bizName, attr, String.valueOf(dataValidator.getMore()));
        }
        if (dataValidator.getLess() != null && val <= dataValidator.getLess()) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_INT_LESS, bizName, attr, String.valueOf(dataValidator.getLess()));
        }
        if (dataValidator.getEqual() != null && val >= dataValidator.getEqual()) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_INT_EQUAL, bizName, attr, String.valueOf(dataValidator.getEqual()));
        }
        if (dataValidator.getUnequal() != null && val <= dataValidator.getUnequal()) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_INT_UNEQUAL, bizName, attr, String.valueOf(dataValidator.getUnequal()));
        }
    }

    public static String checkString(Object value, String bizName, String attr) {
        String val = (String)value;
        return val;
    }

    public static void checkStringValidate(String val, BDataValidator bdataValidator, String bizName, String attr) {
        if (bdataValidator == null) {
            return;
        }
        StringValidator dataValidator = (StringValidator) bdataValidator;

        if(StringUtil.isEmpty(val)){
            if(dataValidator.isNullable()==false){
                IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_STRING_NULLABLE, bizName, attr);
            }
            return;
        }

        if (dataValidator.getMin() != null && val.length() < dataValidator.getMin()) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_STRING_LENGTH_MIN, bizName, attr, String.valueOf(dataValidator.getMin()));
        }
        if (dataValidator.getMax() != null && val.length() > dataValidator.getMax()) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_STRING_LENGTH_MAX, bizName, attr, String.valueOf(dataValidator.getMax()));
        }
        if (dataValidator.getLess() != null && val.length() <= dataValidator.getLess()) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_STRING_LENGTH_LESS, bizName, attr, String.valueOf(dataValidator.getLess()));
        }
        if (dataValidator.getMore() != null && val.length() >= dataValidator.getMore()) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_STRING_LENGTH_MORE, bizName, attr, String.valueOf(dataValidator.getMore()));
        }
        if (dataValidator.getEqual() != null && val.length()!=dataValidator.getEqual()) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_STRING_LENGTH_EQUAL, bizName, attr, String.valueOf(dataValidator.getEqual()));
        }
        if (dataValidator.getUnequal() != null && val.length()==dataValidator.getUnequal()) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_STRING_LENGTH_UNEQUAL, bizName, attr, String.valueOf(dataValidator.getUnequal()));
        }
    }

    public static BigDecimal checkDecimal(Object value, String bizName, String attr) {
        BigDecimal val = null;
        try {
            val = new BigDecimal(String.valueOf(value));
        } catch (Exception e) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DECIMAL, bizName, attr);
        }
        return val;
    }

    public static void checkDecimalValidate(BigDecimal val, BDataValidator bdataValidator, String bizName, String attr) {
        if (bdataValidator == null) {
            return;
        }
        DecimalValidator dataValidator = (DecimalValidator) bdataValidator;

        if (dataValidator.getPrecision() != null && val.precision() > dataValidator.getPrecision()) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DECIMAL_PRECISION, bizName, attr, String.valueOf(dataValidator.getPrecision()));
        }
        if (dataValidator.getScale() != null && val.scale() > dataValidator.getScale()) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DECIMAL_SCALE, bizName, attr, String.valueOf(dataValidator.getScale()));
        }
        if (dataValidator.getMin() != null && val.compareTo(dataValidator.getMin()) < 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DECIMAL_MIN, bizName, attr, String.valueOf(dataValidator.getMin()));
        }
        if (dataValidator.getMax() != null && val.compareTo(dataValidator.getMax()) > 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DECIMAL_MAX, bizName, attr, String.valueOf(dataValidator.getMax()));
        }
        if (dataValidator.getLess() != null && val.compareTo(dataValidator.getLess()) <= 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DECIMAL_LESS, bizName, attr, String.valueOf(dataValidator.getLess()));
        }
        if (dataValidator.getMore() != null && val.compareTo(dataValidator.getMore()) >= 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DECIMAL_MORE, bizName, attr, String.valueOf(dataValidator.getMore()));
        }
        if (dataValidator.getEqual() != null && val.compareTo(dataValidator.getEqual()) != 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DECIMAL_EQUAL, bizName, attr, String.valueOf(dataValidator.getEqual()));
        }
        if (dataValidator.getUnequal() != null && val.compareTo(dataValidator.getUnequal()) == 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DECIMAL_UNEQUAL, bizName, attr, String.valueOf(dataValidator.getUnequal()));
        }
    }

    public static java.sql.Date checkDate(Object value, String bizName, String attr) {
        java.sql.Date val = null;
        if(StringUtil.isEmpty((String)value)){
            return null;
        }
        try {
            val = java.sql.Date.valueOf(String.valueOf(value));
        } catch (Exception e) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATE, bizName, attr);
        }
        return val;
    }

    public static void checkDateValidate(java.sql.Date val, BDataValidator bdataValidator, String bizName, String attr) {
        if (bdataValidator == null) {
            return;
        }
        DateValidator dataValidator = (DateValidator) bdataValidator;

        if(val==null){
            if(dataValidator.isNullable()==false){
                IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATE_NULLABLE, bizName, attr);
            }
            return;
        }

        if (dataValidator.getMin() != null && val.compareTo(dataValidator.getMin()) < 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATE_MIN, bizName, attr, String.valueOf(dataValidator.getMin()));
        }
        if (dataValidator.getMax() != null && val.compareTo(dataValidator.getMax()) > 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATE_MAX, bizName, attr, String.valueOf(dataValidator.getMax()));
        }
        if (dataValidator.getLess() != null && val.compareTo(dataValidator.getLess()) <= 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATE_LESS, bizName, attr, String.valueOf(dataValidator.getLess()));
        }
        if (dataValidator.getMore() != null && val.compareTo(dataValidator.getMore()) >= 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATE_MORE, bizName, attr, String.valueOf(dataValidator.getMore()));
        }
        if (dataValidator.getEqual() != null && val.compareTo(dataValidator.getEqual()) != 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATE_EQUAL, bizName, attr, String.valueOf(dataValidator.getEqual()));
        }
        if (dataValidator.getUnequal() != null && val.compareTo(dataValidator.getUnequal()) == 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATE_UNEQUAL, bizName, attr, String.valueOf(dataValidator.getUnequal()));
        }
    }


    public static Timestamp checkDatetime(Object value, String bizName, String attr) {
        Timestamp val = null;
        if(StringUtil.isEmpty((String)value)){
            return null;
        }
        try {
            val = Timestamp.valueOf(String.valueOf(value));
        } catch (Exception e) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATETIME, bizName, attr);
        }
        return val;
    }

    public static void checkDatetimeValidate(Timestamp val, BDataValidator bdataValidator, String bizName, String attr) {
        if (bdataValidator == null) {
            return;
        }
        DatetimeValidator dataValidator = (DatetimeValidator) bdataValidator;

        if(val==null){
            if(dataValidator.isNullable()==false){
                IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATETIME_NULLABLE, bizName, attr);
            }
            return;
        }

        if (dataValidator.getMin() != null && val.compareTo(dataValidator.getMin()) < 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATETIME_MIN, bizName, attr, String.valueOf(dataValidator.getMin()));
        }
        if (dataValidator.getMax() != null && val.compareTo(dataValidator.getMax()) > 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATETIME_MAX, bizName, attr, String.valueOf(dataValidator.getMax()));
        }
        if (dataValidator.getLess() != null && val.compareTo(dataValidator.getLess()) <= 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATETIME_LESS, bizName, attr, String.valueOf(dataValidator.getLess()));
        }
        if (dataValidator.getMore() != null && val.compareTo(dataValidator.getMore()) >= 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATETIME_MORE, bizName, attr, String.valueOf(dataValidator.getMore()));
        }
        if (dataValidator.getEqual() != null && val.compareTo(dataValidator.getEqual()) != 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATETIME_EQUAL, bizName, attr, String.valueOf(dataValidator.getEqual()));
        }
        if (dataValidator.getUnequal() != null && val.compareTo(dataValidator.getUnequal()) == 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATETIME_UNEQUAL, bizName, attr, String.valueOf(dataValidator.getUnequal()));
        }
    }

    public static Boolean checkBoolean(Object value, String bizName, String attr) {
        if(!"true".equals(value)&&!"false".equals(value)&&!(value instanceof Boolean)){
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_BOOLEAN, bizName, attr);
        }
        return "true".equals(value);
    }

    public static void checkObject(Object value, String bizName, String attr) {
        if (!(value instanceof JSONObject)) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_OBJECT, bizName, attr); //需要想value展示到详细信息中心
        }
    }

    public static void checkArray(Object value, String bizName, String attr) {
        if (!(value instanceof JSONArray)) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_ARRAY, bizName, attr);  //需要想value展示到详细信息中心
        }
    }
}
