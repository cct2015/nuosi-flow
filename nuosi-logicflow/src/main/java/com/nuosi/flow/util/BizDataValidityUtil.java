package com.nuosi.flow.util;

import com.ai.ipu.basic.string.StringUtil;
import com.ai.ipu.basic.util.IpuUtility;
import com.nuosi.flow.data.BDataDefine;
import com.nuosi.flow.data.BDataLimit;
import com.nuosi.flow.data.BizDataManager;
import com.nuosi.flow.data.limit.*;

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
        BDataLimit dataLimit = dataDefine.getDataLimit(attr);
        switch (dataLimit.getDataType()) {
            /*校验整数类型*/
            case INT:
                Integer intValue = checkInt(value, bizName, attr);
                checkIntLimit(intValue, dataLimit, bizName, attr);
                break;
            /*校验字符类型*/
            case STRING:
                String stringValue = checkString(value, bizName, attr);
                checkStringLimit(stringValue, dataLimit, bizName, attr);
                break;
            /*校验小数类型*/
            case DECIMAL:
                BigDecimal decimalValue = checkDecimal(value, bizName, attr);
                checkDecimalLimit(decimalValue, dataLimit, bizName, attr);
                break;
            /*校验日期类型*/
            case DATE:
                java.sql.Date dateValue = checkDate(value, bizName, attr);
                checkDateLimit(dateValue, dataLimit, bizName, attr);
                break;
            /*校验日期时间类型*/
            case DATETIME:
                Timestamp timestampValue = checkDatetime(value, bizName, attr);
                checkDatetimeLimit(timestampValue, dataLimit, bizName, attr);
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

    public static void checkIntLimit(Integer val, BDataLimit bdataLimit, String bizName, String attr) {
        if (bdataLimit == null) {
            return;
        }
        IntegerLimit dataLimit = (IntegerLimit) bdataLimit;
        if (dataLimit.getMax() != null && val > dataLimit.getMax()) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_INT_MAX, bizName, attr, String.valueOf(dataLimit.getMax()));
        }
        if (dataLimit.getMin() != null && val < dataLimit.getMin()) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_INT_MIN, bizName, attr, String.valueOf(dataLimit.getMin()));
        }
        if (dataLimit.getMore() != null && val >= dataLimit.getMore()) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_INT_MORE, bizName, attr, String.valueOf(dataLimit.getMore()));
        }
        if (dataLimit.getLess() != null && val <= dataLimit.getLess()) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_INT_LESS, bizName, attr, String.valueOf(dataLimit.getLess()));
        }
        if (dataLimit.getEqual() != null && val >= dataLimit.getEqual()) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_INT_EQUAL, bizName, attr, String.valueOf(dataLimit.getEqual()));
        }
        if (dataLimit.getUnequal() != null && val <= dataLimit.getUnequal()) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_INT_UNEQUAL, bizName, attr, String.valueOf(dataLimit.getUnequal()));
        }
    }

    public static String checkString(Object value, String bizName, String attr) {
        String val = (String)value;
        return val;
    }

    public static void checkStringLimit(String val, BDataLimit bdataLimit, String bizName, String attr) {
        if (bdataLimit == null) {
            return;
        }
        StringLimit dataLimit = (StringLimit) bdataLimit;

        if(StringUtil.isEmpty(val)){
            if(dataLimit.isNullable()==false){
                IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_STRING_NULLABLE, bizName, attr);
            }
            return;
        }

        if (dataLimit.getMin() != null && val.length() < dataLimit.getMin()) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_STRING_LENGTH_MIN, bizName, attr, String.valueOf(dataLimit.getMin()));
        }
        if (dataLimit.getMax() != null && val.length() > dataLimit.getMax()) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_STRING_LENGTH_MAX, bizName, attr, String.valueOf(dataLimit.getMax()));
        }
        if (dataLimit.getLess() != null && val.length() <= dataLimit.getLess()) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_STRING_LENGTH_LESS, bizName, attr, String.valueOf(dataLimit.getLess()));
        }
        if (dataLimit.getMore() != null && val.length() >= dataLimit.getMore()) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_STRING_LENGTH_MORE, bizName, attr, String.valueOf(dataLimit.getMore()));
        }
        if (dataLimit.getEqual() != null && val.length()!=dataLimit.getEqual()) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_STRING_LENGTH_EQUAL, bizName, attr, String.valueOf(dataLimit.getEqual()));
        }
        if (dataLimit.getUnequal() != null && val.length()==dataLimit.getUnequal()) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_STRING_LENGTH_UNEQUAL, bizName, attr, String.valueOf(dataLimit.getUnequal()));
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

    public static void checkDecimalLimit(BigDecimal val, BDataLimit bdataLimit, String bizName, String attr) {
        if (bdataLimit == null) {
            return;
        }
        DecimalLimit dataLimit = (DecimalLimit) bdataLimit;

        if (dataLimit.getPrecision() != null && val.precision() > dataLimit.getPrecision()) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DECIMAL_PRECISION, bizName, attr, String.valueOf(dataLimit.getPrecision()));
        }
        if (dataLimit.getScale() != null && val.scale() > dataLimit.getScale()) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DECIMAL_SCALE, bizName, attr, String.valueOf(dataLimit.getScale()));
        }
        if (dataLimit.getMin() != null && val.compareTo(dataLimit.getMin()) < 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DECIMAL_MIN, bizName, attr, String.valueOf(dataLimit.getMin()));
        }
        if (dataLimit.getMax() != null && val.compareTo(dataLimit.getMax()) > 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DECIMAL_MAX, bizName, attr, String.valueOf(dataLimit.getMax()));
        }
        if (dataLimit.getLess() != null && val.compareTo(dataLimit.getLess()) <= 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DECIMAL_LESS, bizName, attr, String.valueOf(dataLimit.getLess()));
        }
        if (dataLimit.getMore() != null && val.compareTo(dataLimit.getMore()) >= 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DECIMAL_MORE, bizName, attr, String.valueOf(dataLimit.getMore()));
        }
        if (dataLimit.getEqual() != null && val.compareTo(dataLimit.getEqual()) != 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DECIMAL_EQUAL, bizName, attr, String.valueOf(dataLimit.getEqual()));
        }
        if (dataLimit.getUnequal() != null && val.compareTo(dataLimit.getUnequal()) == 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DECIMAL_UNEQUAL, bizName, attr, String.valueOf(dataLimit.getUnequal()));
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

    public static void checkDateLimit(java.sql.Date val, BDataLimit bdataLimit, String bizName, String attr) {
        if (bdataLimit == null) {
            return;
        }
        DateLimit dataLimit = (DateLimit) bdataLimit;

        if(val==null){
            if(dataLimit.isNullable()==false){
                IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATE_NULLABLE, bizName, attr);
            }
            return;
        }

        if (dataLimit.getMin() != null && val.compareTo(dataLimit.getMin()) < 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATE_MIN, bizName, attr, String.valueOf(dataLimit.getMin()));
        }
        if (dataLimit.getMax() != null && val.compareTo(dataLimit.getMax()) > 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATE_MAX, bizName, attr, String.valueOf(dataLimit.getMax()));
        }
        if (dataLimit.getLess() != null && val.compareTo(dataLimit.getLess()) <= 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATE_LESS, bizName, attr, String.valueOf(dataLimit.getLess()));
        }
        if (dataLimit.getMore() != null && val.compareTo(dataLimit.getMore()) >= 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATE_MORE, bizName, attr, String.valueOf(dataLimit.getMore()));
        }
        if (dataLimit.getEqual() != null && val.compareTo(dataLimit.getEqual()) != 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATE_EQUAL, bizName, attr, String.valueOf(dataLimit.getEqual()));
        }
        if (dataLimit.getUnequal() != null && val.compareTo(dataLimit.getUnequal()) == 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATE_UNEQUAL, bizName, attr, String.valueOf(dataLimit.getUnequal()));
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

    public static void checkDatetimeLimit(Timestamp val, BDataLimit bdataLimit, String bizName, String attr) {
        if (bdataLimit == null) {
            return;
        }
        DatetimeLimit dataLimit = (DatetimeLimit) bdataLimit;

        if(val==null){
            if(dataLimit.isNullable()==false){
                IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATETIME_NULLABLE, bizName, attr);
            }
            return;
        }

        if (dataLimit.getMin() != null && val.compareTo(dataLimit.getMin()) < 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATETIME_MIN, bizName, attr, String.valueOf(dataLimit.getMin()));
        }
        if (dataLimit.getMax() != null && val.compareTo(dataLimit.getMax()) > 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATETIME_MAX, bizName, attr, String.valueOf(dataLimit.getMax()));
        }
        if (dataLimit.getLess() != null && val.compareTo(dataLimit.getLess()) <= 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATETIME_LESS, bizName, attr, String.valueOf(dataLimit.getLess()));
        }
        if (dataLimit.getMore() != null && val.compareTo(dataLimit.getMore()) >= 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATETIME_MORE, bizName, attr, String.valueOf(dataLimit.getMore()));
        }
        if (dataLimit.getEqual() != null && val.compareTo(dataLimit.getEqual()) != 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATETIME_EQUAL, bizName, attr, String.valueOf(dataLimit.getEqual()));
        }
        if (dataLimit.getUnequal() != null && val.compareTo(dataLimit.getUnequal()) == 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATETIME_UNEQUAL, bizName, attr, String.valueOf(dataLimit.getUnequal()));
        }
    }

    public static Boolean checkBoolean(Object value, String bizName, String attr) {
        if(!"true".equals(value)&&!"false".equals(value)&&!(value instanceof Boolean)){
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_BOOLEAN, bizName, attr);
        }
        return "true".equals(value);
    }
}
