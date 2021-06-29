package com.nuosi.flow.data.limit;

import com.nuosi.flow.data.BDataDefine;
import com.nuosi.flow.data.BDataLimit;

/**
 * <p>desc: 业务传输对象数据限制和校验抽象类 </p>
 * <p>date: 2021/6/29 21:58 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public abstract class AbstractDataLimit implements BDataLimit {
    private BDataDefine.BDataType dataType;

    public AbstractDataLimit(BDataDefine.BDataType dataType){
        this.dataType = dataType;
    }

    public BDataDefine.BDataType getDataType(){
        return dataType;
    }
}
