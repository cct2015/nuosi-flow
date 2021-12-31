package com.nuosi.server;

import com.ai.ipu.basic.util.IpuBaseException;
import com.ai.ipu.restful.boot.IpuRestApplication;
import com.nuosi.server.util.LogicFlowConfig;

/**
 * <p>desc: 逻辑编排服务启动类 </p>
 * <p>date: 2021/11/25 14:42 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class LogicflowServerStart {

    public static void main(String[] args) {
        //初始化加载服务配置
        LogicFlowConfig.init();
        // 注册异常信息编码配置。
        registerExceptionCode();
        // 启动服务
        IpuRestApplication.start(args);
    }

    private static void registerExceptionCode(){
        // 热部署会多次加载，因此需要捕获并忽略异常
        String exceptionMessagesConfig = "exception_messages";
        IpuBaseException.registerCode(exceptionMessagesConfig);
    }
}
