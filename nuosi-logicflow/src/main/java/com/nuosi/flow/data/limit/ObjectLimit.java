package com.nuosi.flow.data.limit;

import com.nuosi.flow.data.BDataDefine;
import com.nuosi.flow.util.BizDataValidityUtil;

/**
 * <p>desc: 对象结构数据限制和校验 </p>
 * <p>date: 2022/3/9 0:37 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class ObjectLimit extends AbstractDataLimit {

    public ObjectLimit() {
        super(BDataDefine.BDataType.OBJECT);
    }

    @Override
    public void checkValidity(String bizName, String attr, Object value) {
        BizDataValidityUtil.checkObject(value, bizName, attr);
    }
}
