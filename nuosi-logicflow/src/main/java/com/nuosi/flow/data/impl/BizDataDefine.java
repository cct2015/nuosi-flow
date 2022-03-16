package com.nuosi.flow.data.impl;

import com.ai.ipu.basic.util.IpuUtility;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nuosi.flow.data.BDataDefine;
import com.nuosi.flow.data.BDataValidator;
import com.nuosi.flow.util.LogicFlowConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>desc: 业务传输对象的数据类型和格式定义</p>
 * <p>date: 2021/3/16 14:36</p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class BizDataDefine implements BDataDefine {
    private final String bizName;
    private final Map<String, BDataValidator> dataValidators;

    public BizDataDefine(String bizName) {
        this.bizName = bizName;
        this.dataValidators = new HashMap<String, BDataValidator>();
    }

    @Override
    public String getBizName() {
        return bizName;
    }

    @Override
    public BizDataDefine defineValidator(String attr, BDataValidator dataValidator) {
        dataValidators.put(attr, dataValidator);
        return this;
    }

    @Override
    public BDataValidator getDataValidator(String bizName) {
        return dataValidators.get(bizName);
    }

    @Override
    public Map<String, BDataValidator> getDataValidators() {
        return dataValidators;
    }

    @Override
    public boolean containsAttr(String attr) {
        return dataValidators.containsKey(attr);
    }

    @Override
    public boolean checkData(String attr, Object value) {
        BDataValidator dataValidator = this.getDataValidator(attr);
        if(dataValidator!=null){
            /*1.进行指定规则校验*/
            dataValidator.checkValidity(bizName, attr, value);
            /*2.进行正则表达式规则校验*/
            dataValidator.checkRegex(bizName, attr, value);
        }
        return true;
    }

    @Override
    public boolean checkData(JSONObject value, boolean isAttrExists) {
        BDataValidator dataValidator;
        String attr;
        Object val;

        if(isAttrExists){
            for (Map.Entry<String, BDataValidator> entry : dataValidators.entrySet()) {
                dataValidator = entry.getValue();
                attr = entry.getKey();
                if(!value.containsKey(attr)){
                    if(dataValidator.isExists()){
                        IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_ATTR_NOT_EXISTS, bizName, attr); //入参不包含模型属性则报错
                    }else{
                        continue; //入参不包含模型则跳过校验
                    }
                }

                val = value.get(attr);
                /*1.进行指定规则校验*/
                dataValidator.checkValidity(bizName, attr, val);
                /*2.进行正则表达式规则校验*/
                dataValidator.checkRegex(bizName, attr, val);
            }
        }else{
            for (Map.Entry<String, BDataValidator> entry : dataValidators.entrySet()) {
                dataValidator = entry.getValue();
                attr = entry.getKey();
                if(!value.containsKey(attr)){   //入参不包含模型属性则跳过校验
                    continue;
                }

                val = value.get(attr);
                /*1.进行指定规则校验*/
                dataValidator.checkValidity(bizName, attr, val);
                /*2.进行正则表达式规则校验*/
                dataValidator.checkRegex(bizName, attr, val);
            }
        }

        return true;
    }

    @Override
    public boolean checkData(JSONArray value, boolean isAttrExists) {
        for(Object model : value){
            checkData((JSONObject)model, isAttrExists);
        }
        return true;
    }
}
