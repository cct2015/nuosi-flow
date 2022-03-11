package com.nuosi.flow.data.impl;

import com.ai.ipu.basic.util.IpuUtility;
import com.ai.ipu.data.impl.JsonMap;
import com.nuosi.flow.data.BData;
import com.nuosi.flow.data.BDataDefine;
import com.nuosi.flow.data.BizDataManager;
import com.nuosi.flow.util.LogicFlowConstants;

/**
 * <p>desc: 业务传输对象的弱类型实现
 * 基于Map数据结构的Dto、JavaBean表达类</p>
 * <p>date: 2021/3/5 0:51</p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class BizData extends JsonMap implements BData {
    private final String bizName;
    private final BDataDefine dataDefine;

    public BizData(String bizName) {
        this.bizName = bizName;
        this.dataDefine = BizDataManager.getDataDefine(bizName);
    }

    /**
     * <p>desc: 存值时判断业务属性是否存在</p>
     * <p>date: 2021/3/6 0:30</p>
     * @author nuosi fsofs@163.com
     * @version v1.0.0
     * @param attr 1
     * @param value 2
     * @return com.nuosi.flow.data.impl.BizData
     */
    @Override
    public BizData put(String attr, Object value) {
        if (!dataDefine.containsAttr(attr)) {
            //业务属性不存在时抛出异常
            IpuUtility.errorCode(LogicFlowConstants.BDATA_ATTR_DEFINE_NO_EXISTS, bizName, attr);
        }
        if (!dataDefine.checkData(attr, value)) {
            //业务属性数据格式不正确时抛出异常
            IpuUtility.errorCode(LogicFlowConstants.BDATA_ATTR_FORMAT_ERROR, bizName, attr);
        }
        super.put(attr, value);
        return this;
    }

    @Override
    public Object get(Object attr) {
        if (!dataDefine.containsAttr((String) attr)) {
            //业务属性不存在时抛出异常
            IpuUtility.errorCode(LogicFlowConstants.BDATA_ATTR_DEFINE_NO_EXISTS, bizName, String.valueOf(attr));
        }
        return super.get(attr);
    }

    public String getBizName() {
        return bizName;
    }

    public BDataDefine getDataDefine() {
        return dataDefine;
    }
}
