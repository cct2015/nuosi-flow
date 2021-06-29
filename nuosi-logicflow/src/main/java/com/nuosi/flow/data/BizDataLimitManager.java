package com.nuosi.flow.data;

import com.nuosi.flow.data.limit.*;

/**
 * <p>desc: 业务传输对象数据限制和校验的管理类 </p>
 * <p>date: 2021/6/29 22:18 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class BizDataLimitManager {

    public static BDataLimit create(BDataDefine.BDataType dataType){
        BDataLimit dataLimit = null;
        switch (dataType) {
            /*校验整数类型*/
            case INT:
                dataLimit = new IntegerLimit();
                break;
            /*校验字符类型*/
            case STRING:
                dataLimit = new StringLimit();
                break;
            /*校验小数类型*/
            case DECIMAL:
                dataLimit = new DecimalLimit();
                break;
            /*校验日期类型*/
            case DATE:
                dataLimit = new DateLimit();
                break;
            /*校验日期时间类型*/
            case DATETIME:
                dataLimit = new DatetimeLimit();
                break;
            default:
                break;
        }
        return dataLimit;
    }

    public static BDataLimit createIntegerLimit(){
        return create(BDataDefine.BDataType.INT);
    }

    public static BDataLimit createStringLimit(){
        return create(BDataDefine.BDataType.STRING);
    }

    public static BDataLimit createDecimalLimit(){
        return create(BDataDefine.BDataType.DECIMAL);
    }

    public static BDataLimit createDateLimit(){
        return create(BDataDefine.BDataType.DATE);
    }

    public static BDataLimit createDatetimeLimit(){
        return create(BDataDefine.BDataType.DATETIME);
    }
}
