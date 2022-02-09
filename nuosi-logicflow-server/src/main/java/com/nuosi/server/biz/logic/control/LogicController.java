package com.nuosi.server.biz.logic.control;

import com.ai.ipu.data.JMap;
import com.ai.ipu.data.impl.JsonMap;
import com.nuosi.server.biz.logic.service.LogicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * <p>desc: 逻辑服务调用的入口 </p>
 * <p>date: 2021/11/25 15:00 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
@Controller
public class LogicController {
    private static final Logger log = LoggerFactory.getLogger(LogicController.class);

    @Autowired
    private LogicService logicService;

    @ResponseBody
    @RequestMapping("/logic/{logicId}")
    public Object logic(@PathVariable("logicId") String logicId, @RequestParam Map input) throws Exception {
        System.out.println("logicId="+logicId);
        System.out.println("input="+input);
        JMap param = new JsonMap(input);
        Object result = logicService.execute(logicId, param);
        return result;
    }

}
