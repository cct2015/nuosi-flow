package com.nuosi.flow.logic.inject.common;

import com.ai.ipu.basic.util.IpuUtility;
import com.nuosi.flow.util.LogicFlowConstants;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>desc: 受保护的数据总线：传入上下文参数不可被修改
 * 参考：Collections.unmodifiableMap()</p>
 * <p>date: 2022/3/1 21:42 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class UnmodifiableDatabus implements Map<String, Object>{
    private Map<String, Object> databus = new HashMap<String, Object>();

    public UnmodifiableDatabus(Map<String, Object> databus){
        this.databus = databus;

    }

    @Override
    public int size() {
        return databus.size();
    }

    @Override
    public boolean isEmpty() {
        return databus.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return databus.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return databus.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return databus.get(key);
    }

    @Override
    public Set<String> keySet() {
        return databus.keySet();
    }

    @Override
    public Collection<Object> values() {
        return databus.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return databus.entrySet();
    }

    @Override
    public Object put(String key, Object value) {
        IpuUtility.errorCode(LogicFlowConstants.FLOW_DATABUS_CANNOT_OPERATED);
        return null;
    }

    @Override
    public Object remove(Object key) {
        IpuUtility.errorCode(LogicFlowConstants.FLOW_DATABUS_CANNOT_OPERATED);
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ?> m) {
        IpuUtility.errorCode(LogicFlowConstants.FLOW_DATABUS_CANNOT_OPERATED);
    }

    @Override
    public void clear() {
        IpuUtility.errorCode(LogicFlowConstants.FLOW_DATABUS_CANNOT_OPERATED);
    }
}
