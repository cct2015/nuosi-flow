package com.nuosi.flow.logic.inject.calculate;

import java.util.Map;

/**
 * <p>desc: Var标签数值计算方法默认类 </p>
 * <p>date: 2022/3/1 21:59 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class CalculateMethod extends AbstractCalculateMethod{

    public Object executeOffset(Object value, Map<String, Object> databus){
        return Integer.valueOf(value.toString())+1; //判null
    }
}
