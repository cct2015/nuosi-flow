package com.nuosi.flow.logic.inject.function;

import com.ai.ipu.data.JMap;
import com.nuosi.flow.logic.model.action.Function;

import java.util.Map;

/**
 * <p>desc: 业务域的函数功能接口 </p>
 * <p>date: 2021/5/18 14:33 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public interface IDomainFunction {

    public Object invoke(Function function, JMap input, Map<String, Object> databus) throws Exception;
}
