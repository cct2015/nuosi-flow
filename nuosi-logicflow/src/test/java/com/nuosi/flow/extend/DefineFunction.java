package com.nuosi.flow.extend;

import com.nuosi.flow.logic.inject.function.AbstractDomainFunction;

/**
 * <p>desc: 在流程中新增的自定义方法类 </p>
 * <p>date: 2022/1/17 15:53 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class DefineFunction extends AbstractDomainFunction {

    public void printContext(Integer userId) throws Exception {
        System.out.println("input===" + getInput());
        System.out.println("databus===" + getDatabus());
        System.out.println("userId===" + userId);
    }

    public void printNullContext(Integer userId) throws Exception {
        System.out.println("input===" + getInput());
        System.out.println("databus===" + getDatabus());
        System.out.println("userId===" + userId);
    }
}
