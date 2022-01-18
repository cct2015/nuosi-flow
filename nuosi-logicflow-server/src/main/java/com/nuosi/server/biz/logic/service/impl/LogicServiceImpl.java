package com.nuosi.server.biz.logic.service.impl;

import com.ai.ipu.data.JMap;
import com.nuosi.flow.logic.LogicFlowEngine;
import com.nuosi.server.biz.logic.service.LogicService;
import org.springframework.stereotype.Service;

/**
 * <p>desc: 逻辑服务的实现 </p>
 * <p>date: 2021/12/4 1:07 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */

@Service
public class LogicServiceImpl implements LogicService {
    @Override
    public Object execute(String logicName, JMap param) throws Exception {
        Object result = LogicFlowEngine.execute(logicName, param);
        return result;
    }
}
