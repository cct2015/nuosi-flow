package com.nuosi.flow.logic.invoke.check;

import com.ai.ipu.basic.util.IpuBaseException;
import com.ai.ipu.basic.util.IpuUtility;
import com.nuosi.flow.data.BDataLimit;
import com.nuosi.flow.data.impl.BizDataDefine;

/**
 * <p>desc: 逻辑流中的数据类型和格式定义 </p>
 * <p>date: 2022/3/5 15:52 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class FlowDataDefine extends BizDataDefine {

    public FlowDataDefine(String bizName) {
        super(bizName);
    }

    @Override
    public boolean checkData(String attr, Object value) {
        try {
            BDataLimit dataLimit = this.getDataLimit(attr);
            if (dataLimit != null) {
                /*1.进行指定规则校验*/
                dataLimit.checkValidity(getBizName(), attr, value);
                /*2.进行正则表达式规则校验*/
                dataLimit.checkRegex(getBizName(), attr, value);
            }
        } catch (Exception e) {
            if (e instanceof IpuBaseException) {
                IpuUtility.error("服务逻辑" + e.getMessage());
            }
            throw e;
        }
        return true;
    }
}
