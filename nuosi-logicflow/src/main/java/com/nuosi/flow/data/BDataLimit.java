package com.nuosi.flow.data;

/**
 * <p>desc: 业务传输对象数据限制和校验的接口抽象</p>
 * <p>date: 2021/3/16 23:40</p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public interface BDataLimit {

    public void checkValidity(String bizName, String attr, Object value);

    public BDataDefine.BDataType getDataType();

    public String getRegex();

    public void setRegex(String regex);

    public void checkRegex(String bizName, String attr, Object value);
}
