package com.nuosi.flow.logic.inject.common;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>desc: 受保护的数据总线：传入上下文参数不可被修改
 * 参考：Collections.unmodifiableMap()</p>
 * <p>date: 2022/3/1 21:42 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class ProtectedDatabus {
    private Map<String, Object> databus = new HashMap<String, Object>();

    public ProtectedDatabus(Map<String, Object> databus){
        this.databus = databus;

    }

    public Object get(String key){
        return databus.get(key);
    }
}
