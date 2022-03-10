package com.nuosi.flow.data.validate;

import com.nuosi.flow.data.BDataDefine;
import com.nuosi.flow.util.BizDataValidityUtil;

/**
 * <p>desc: 布尔数据限制和校验 </p>
 * <p>date: 2022/3/2 16:47 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class BooleanValidator extends AbstractDataValidator {

    public BooleanValidator() {
        super(BDataDefine.BDataType.BOOLEAN);
    }

    @Override
    public void checkValidity(String bizName, String attr, Object value) {
        Boolean booleanValue = BizDataValidityUtil.checkBoolean(value, bizName, attr);
    }
}
