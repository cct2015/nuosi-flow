package com.nuosi.flow.logic.inject.calculate;

/**
 * <p>desc: Var标签数值计算方法管理类 </p>
 * <p>date: 2022/3/1 21:59 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class CalculateMethodManager {
    private static AbstractCalculateMethod calculateMethod;

    public static void init() {
        registerCalculateMethod(new CalculateMethod());
    }

    public static void registerCalculateMethod(AbstractCalculateMethod calculateMethod) {
        CalculateMethodManager.calculateMethod = calculateMethod;
    }

    public static AbstractCalculateMethod getCalculateMethod() {
        return calculateMethod;
    }
}
