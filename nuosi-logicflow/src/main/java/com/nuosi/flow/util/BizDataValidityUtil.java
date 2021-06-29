package com.nuosi.flow.util;

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
    }

    public static String checkString(Object value, String bizName, String attr) {
        String val = String.valueOf(value);
        return val;
    }

    public static void checkStringLimit(String val, BDataLimit bdataLimit, String bizName, String attr) {
        if (bdataLimit == null) {
            return;
        }
        StringLimit dataLimit = (StringLimit) bdataLimit;

        if (dataLimit.getSize() != -1 && val.length() > dataLimit.getSize()) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_STRING_LENGTH, bizName, attr, String.valueOf(dataLimit.getSize()));
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

        if (dataLimit.getPrecision() != -1 && val.precision() > dataLimit.getPrecision()) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DECIMAL_PRECISION, bizName, attr, String.valueOf(dataLimit.getPrecision()));
        }
        if (dataLimit.getScale() != -1 && val.scale() > dataLimit.getScale()) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DECIMAL_SCALE, bizName, attr, String.valueOf(dataLimit.getScale()));
        }
        if (dataLimit.getMaxDecimal() != null && val.compareTo(dataLimit.getMaxDecimal()) > 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DECIMAL_MAX, bizName, attr, String.valueOf(dataLimit.getMaxDecimal()));
        }
        if (dataLimit.getMinDecimal() != null && val.compareTo(dataLimit.getMinDecimal()) < 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DECIMAL_MIN, bizName, attr, String.valueOf(dataLimit.getMinDecimal()));
        }
    }

    public static java.sql.Date checkDate(Object value, String bizName, String attr) {
        java.sql.Date val = null;
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

        if (dataLimit.getStartDate() != null && val.compareTo(dataLimit.getStartDate()) < 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATE_START, bizName, attr, String.valueOf(dataLimit.getStartDate()));
        }
        if (dataLimit.getEndDate() != null && val.compareTo(dataLimit.getEndDate()) > 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATE_END, bizName, attr, String.valueOf(dataLimit.getEndDate()));
        }
    }


    public static Timestamp checkDatetime(Object value, String bizName, String attr) {
        Timestamp val = null;
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

        if (dataLimit.getStartDatetime() != null && val.compareTo(dataLimit.getStartDatetime()) < 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATETIME_START, bizName, attr, String.valueOf(dataLimit.getStartDatetime()));
        }
        if (dataLimit.getEndDatetime() != null && val.compareTo(dataLimit.getEndDatetime()) > 0) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_DATETIME_END, bizName, attr, String.valueOf(dataLimit.getEndDatetime()));
        }
    }
}
