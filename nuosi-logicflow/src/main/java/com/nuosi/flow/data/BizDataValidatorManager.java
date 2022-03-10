package com.nuosi.flow.data;

import com.nuosi.flow.data.validate.*;

/**
 * <p>desc: 业务传输对象数据限制和校验的管理类 </p>
 * <p>date: 2021/6/29 22:18 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class BizDataValidatorManager {

    public static BDataValidator create(BDataDefine.BDataType dataType){
        BDataValidator dataValidator = null;
        switch (dataType) {
            /*校验整数类型*/
            case INT:
                dataValidator = createIntegerValidator();
                break;
            /*校验字符类型*/
            case STRING:
                dataValidator = createStringValidator();
                break;
            /*校验小数类型*/
            case DECIMAL:
                dataValidator = createDecimalValidator();
                break;
            /*校验日期类型*/
            case DATE:
                dataValidator = createDateValidator();
                break;
            /*校验日期时间类型*/
            case DATETIME:
                dataValidator = createDatetimeValidator();
                break;
            default:
                break;
        }
        return dataValidator;
    }

    public static BDataValidator createIntegerValidator(){
        return new IntegerValidator();
    }

    public static BDataValidator createStringValidator(){
        return new StringValidator();
    }

    public static BDataValidator createDecimalValidator(){
        return new DecimalValidator();
    }

    public static BDataValidator createDateValidator(){
        return new DateValidator();
    }

    public static BDataValidator createDatetimeValidator(){
        return new DatetimeValidator();
    }
}
