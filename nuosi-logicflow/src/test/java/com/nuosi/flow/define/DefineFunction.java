package com.nuosi.flow.define;

import com.ai.ipu.data.JMap;
import com.nuosi.flow.logic.inject.function.AbstractDomainFunction;

import java.util.Map;

/**
 * <p>desc: 在流程中新增的自定义方法类 </p>
 * <p>date: 2022/1/17 15:53 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class DefineFunction extends AbstractDomainFunction {

    public void print(JMap input, Map<String, Object> databus, Integer userId) throws Exception {
        System.out.println("input===" + input);
        System.out.println("databus===" + databus);
        System.out.println("userId===" + userId);
    }
}
