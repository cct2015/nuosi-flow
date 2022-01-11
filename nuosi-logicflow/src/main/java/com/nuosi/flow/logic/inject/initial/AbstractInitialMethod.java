package com.nuosi.flow.logic.inject.initial;

import com.ai.ipu.basic.reflect.ReflectUtil;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>desc: Var标签初始化方法抽象类 </p>
 * <p>date: 2022/1/11 15:27 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public abstract class AbstractInitialMethod {
    private Map<String, Method> methodMap = new ConcurrentHashMap<String, Method>();

    public Object invoke(String methodName) throws Exception {
        Method method = getMethod(methodName);
        return method.invoke(this);
    }

    private Method getMethod(String methodName){
        String key = methodName;
        Method method = methodMap.get(key);
        if(method==null){
            synchronized (methodName){
                if(method==null){
                    method = ReflectUtil.getMethod(this.getClass(), methodName, null);
                    methodMap.put(key, method);
                }
            }
        }
        return method;
    }
}
