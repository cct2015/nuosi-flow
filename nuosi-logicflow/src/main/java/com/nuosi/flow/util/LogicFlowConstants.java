package com.nuosi.flow.util;

/**
 * <p>desc: 逻辑流程引擎使用的常量</p>
 * <p>date: 2021/3/6 12:00</p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class LogicFlowConstants {
    public static final String PACKAGE = "com.nuosi.flow";
    public static final String PACKAGE_PATH = "com/nuosi/flow/";

    /*表达式内置变量*/
    public static final String DATABUS = "DATABUS";
    public static final String INPUT = "INPUT";
    public static final String QB = "QB";

    /*默认值常量*/
    public static class Default {
        public static final int CONN_TIMEOUT = 5000;
        public static final int READ_TIMEOUT = 10000;
    }

    /*LogicFlow异常编码*/
    public static final String FLOW_NO_EXISTS = "FLOW_NO_EXISTS";
    public static final String FLOW_NOT_SUPPORT_MULTIPLE_KEY = "FLOW_NOT_SUPPORT_MULTIPLE_KEY";
    public static final String FLOW_NO_MATCH_DATA_TYPE = "FLOW_NO_MATCH_DATA_TYPE";

    /*逻辑节点统一执行异常*/
    public static final String FLOW_ACTION_ERROR = "FLOW_ACTION_ERROR";
    public static final String FLOW_FOREACH_ITERATOR_TYPE_ERROR = "FLOW_FOREACH_ITERATOR_TYPE_ERROR";
    public static final String FLOW_FOREACH_ITERATOR_NULL = "FLOW_FOREACH_ITERATOR_NULL";
    public static final String FLOW_IF_ERROR = "FLOW_IF_ERROR";
    public static final String FLOW_BEHAVIOR_NOT_EXIST = "FLOW_BEHAVIOR_NOT_EXIST";
    public static final String FLOW_FUNCTION_EXECUTE_EXCEPTION = "FLOW_FUNCTION_EXECUTE_EXCEPTION";

    /*BData异常编码*/
    public static final String BDATA_DEFINE_NO_EXISTS = "BDATA_DEFINE_NO_EXISTS";
    public static final String BDATA_STRUCTURE_EXISTS = "BDATA_STRUCTURE_EXISTS";
    public static final String BDATA_ATTR_DEFINE_NO_EXISTS = "BDATA_ATTR_DEFINE_NO_EXISTS";
    public static final String BDATA_ATTR_FORMAT_ERROR = "BDATA_ATTR_FORMAT_ERROR";
    /**整数校验提示信息*/
    public static final String BDATA_CHECK_INT = "BDATA_CHECK_INT";
    public static final String BDATA_CHECK_INT_MAX = "BDATA_CHECK_INT_MAX";
    public static final String BDATA_CHECK_INT_MIN = "BDATA_CHECK_INT_MIN";
    public static final String BDATA_CHECK_INT_MORE = "BDATA_CHECK_INT_MORE";
    public static final String BDATA_CHECK_INT_LESS = "BDATA_CHECK_INT_LESS";
    public static final String BDATA_CHECK_INT_EQUAL = "BDATA_CHECK_INT_EQUAL";
    public static final String BDATA_CHECK_INT_UNEQUAL = "BDATA_CHECK_INT_UNEQUAL";
    /**字符串校验提示信息*/
    public static final String BDATA_CHECK_STRING_LENGTH_MIN = "BDATA_CHECK_STRING_LENGTH_MIN";
    public static final String BDATA_CHECK_STRING_LENGTH_MAX = "BDATA_CHECK_STRING_LENGTH_MAX";
    public static final String BDATA_CHECK_STRING_LENGTH_LESS = "BDATA_CHECK_STRING_LENGTH_LESS";
    public static final String BDATA_CHECK_STRING_LENGTH_MORE = "BDATA_CHECK_STRING_LENGTH_MORE";
    public static final String BDATA_CHECK_STRING_LENGTH_EQUAL = "BDATA_CHECK_STRING_LENGTH_EQUAL";
    public static final String BDATA_CHECK_STRING_LENGTH_UNEQUAL = "BDATA_CHECK_STRING_LENGTH_UNEQUAL";
    public static final String BDATA_CHECK_STRING_NULLABLE = "BDATA_CHECK_STRING_NULLABLE";
    /**小数校验提示信息*/
    public static final String BDATA_CHECK_DECIMAL = "BDATA_CHECK_DECIMAL";
    public static final String BDATA_CHECK_DECIMAL_PRECISION = "BDATA_CHECK_DECIMAL_PRECISION";
    public static final String BDATA_CHECK_DECIMAL_SCALE = "BDATA_CHECK_DECIMAL_SCALE";
    public static final String BDATA_CHECK_DECIMAL_MAX = "BDATA_CHECK_DECIMAL_MAX";
    public static final String BDATA_CHECK_DECIMAL_MIN = "BDATA_CHECK_DECIMAL_MIN";
    public static final String BDATA_CHECK_DECIMAL_LESS = "BDATA_CHECK_DECIMAL_LESS";
    public static final String BDATA_CHECK_DECIMAL_MORE = "BDATA_CHECK_DECIMAL_MORE";
    public static final String BDATA_CHECK_DECIMAL_EQUAL = "BDATA_CHECK_DECIMAL_EQUAL";
    public static final String BDATA_CHECK_DECIMAL_UNEQUAL = "BDATA_CHECK_DECIMAL_UNEQUAL";
    /**日期校验提示信息*/
    public static final String BDATA_CHECK_DATE = "BDATA_CHECK_DATE";
    public static final String BDATA_CHECK_DATE_MIN = "BDATA_CHECK_DATE_MIN";
    public static final String BDATA_CHECK_DATE_MAX = "BDATA_CHECK_DATE_MAX";
    public static final String BDATA_CHECK_DATE_LESS = "BDATA_CHECK_DATE_LESS";
    public static final String BDATA_CHECK_DATE_MORE = "BDATA_CHECK_DATE_MORE";
    public static final String BDATA_CHECK_DATE_EQUAL = "BDATA_CHECK_DATE_EQUAL";
    public static final String BDATA_CHECK_DATE_UNEQUAL = "BDATA_CHECK_DATE_UNEQUAL";
    public static final String BDATA_CHECK_DATE_NULLABLE = "BDATA_CHECK_DATE_NULLABLE";
    /**日期时间校验提示信息*/
    public static final String BDATA_CHECK_DATETIME = "BDATA_CHECK_DATETIME";
    public static final String BDATA_CHECK_DATETIME_MIN = "BDATA_CHECK_DATETIME_MIN";
    public static final String BDATA_CHECK_DATETIME_MAX = "BDATA_CHECK_DATETIME_MAX";
    public static final String BDATA_CHECK_DATETIME_LESS = "BDATA_CHECK_DATETIME_LESS";
    public static final String BDATA_CHECK_DATETIME_MORE = "BDATA_CHECK_DATETIME_MORE";
    public static final String BDATA_CHECK_DATETIME_EQUAL = "BDATA_CHECK_DATETIME_EQUAL";
    public static final String BDATA_CHECK_DATETIME_UNEQUAL = "BDATA_CHECK_DATETIME_UNEQUAL";
    public static final String BDATA_CHECK_DATETIME_NULLABLE = "BDATA_CHECK_DATETIME_NULLABLE";
    /**布尔校验提示信息*/
    public static final String BDATA_CHECK_BOOLEAN = "BDATA_CHECK_BOOLEAN";
    /**对象校验提示信息*/
    public static final String BDATA_CHECK_OBJECT = "BDATA_CHECK_OBJECT";
    public static final String BDATA_CHECK_ARRAY = "BDATA_CHECK_ARRAY";
    /**正则表达式校验提示信息*/
    public static final String BDATA_CHECK_REGEX = "BDATA_CHECK_REGEX";

    public static final String VALIDATE_ACTION_NULL = "VALIDATE_ACTION_NULL";
    public static final String VALIDATE_START_VAR_ATTR_UNDEFINE = "VALIDATE_START_VAR_ATTR_UNDEFINE";
    public static final String VALIDATE_START_VAR_MODEL_UNDEFINE = "VALIDATE_START_VAR_MODEL_UNDEFINE";
    public static final String VALIDATE_ACTION_NEXT_NOT_EXIST = "VALIDATE_ACTION_NEXT_NOT_EXIST";
    public static final String VALIDATE_INPUT_VAR_ATTR_UNDEFINE = "VALIDATE_INPUT_VAR_ATTR_UNDEFINE";
    public static final String VALIDATE_INPUT_VAR_MODEL_UNDEFINE = "VALIDATE_INPUT_VAR_MODEL_UNDEFINE";
    public static final String VALIDATE_END_VAR_NULL = "VALIDATE_END_VAR_NULL";
    public static final String VALIDATE_START_SINGLE = "VALIDATE_START_SINGLE";
    public static final String VALIDATE_END_SINGLE = "VALIDATE_END_SINGLE";

}
