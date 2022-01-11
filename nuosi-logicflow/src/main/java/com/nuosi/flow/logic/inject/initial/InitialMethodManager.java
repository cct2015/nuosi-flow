package com.nuosi.flow.logic.inject.initial;

/**
 * <p>desc: Var标签初始化方法管理类 </p>
 * <p>date: 2022/1/11 15:26 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class InitialMethodManager {
    private static AbstractInitialMethod initialMethod;

    public static void init() {
        registerInitialMethod(new InitialMethod());
    }

    public static void registerInitialMethod(AbstractInitialMethod initialMethod) {
        InitialMethodManager.initialMethod = initialMethod;
    }

    public static AbstractInitialMethod getInitialMethod() {
        return initialMethod;
    }
}
