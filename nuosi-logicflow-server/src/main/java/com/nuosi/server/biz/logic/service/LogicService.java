package com.nuosi.server.biz.logic.service;

import com.ai.ipu.data.JMap;

/**
 * <p>desc: 逻辑服务的实现接口 </p>
 * <p>date: 2021/11/25 15:01 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public interface LogicService {

    public Object execute(String logicName, JMap param) throws Exception;
}
