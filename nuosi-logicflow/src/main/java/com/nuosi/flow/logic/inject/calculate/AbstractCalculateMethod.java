package com.nuosi.flow.logic.inject.calculate;

import com.ai.ipu.basic.reflect.ReflectUtil;
import com.nuosi.flow.logic.inject.common.ProtectedDatabus;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>desc: Var标签数值计算方法抽象类 </p>
 * <p>date: 2022/3/1 21:58 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class AbstractCalculateMethod {
    private Map<String, Method> methodMap = new ConcurrentHashMap<String, Method>();

    public Object invoke(String methodName, Object value, ProtectedDatabus databus) throws Exception {
        Method method = getMethod(methodName);
        return method.invoke(this, value, databus);
    }

    private Method getMethod(String methodName){
        String key = methodName;
        Method method = methodMap.get(key);
        if(method==null){
            synchronized (methodName){
                if(method==null){
                    method = ReflectUtil.getMethod(this.getClass(), methodName, new Class[]{Object.class, ProtectedDatabus.class});
                    methodMap.put(key, method);
                }
            }
        }
        return method;
    }
}
