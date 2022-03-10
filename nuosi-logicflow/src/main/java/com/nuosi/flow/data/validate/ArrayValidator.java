package com.nuosi.flow.data.validate;

import com.nuosi.flow.data.BDataDefine;
import com.nuosi.flow.util.BizDataValidityUtil;

/**
 * <p>desc: 对象列表结构数据限制和校验 </p>
 * <p>date: 2022/3/9 0:37 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class ArrayValidator extends AbstractDataValidator {

    public ArrayValidator() {
        super(BDataDefine.BDataType.ARRAY);
    }

    @Override
    public void checkValidity(String bizName, String attr, Object value) {
        BizDataValidityUtil.checkArray(value, bizName, attr);
    }
}
