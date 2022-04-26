package com.nuosi.flow.logic.invoke.processer;

import com.ai.ipu.common.http.UnirestUtil;
import com.ai.ipu.data.JMap;
import com.nuosi.flow.logic.model.action.Http;
import com.nuosi.flow.logic.model.action.sub.Header;
import com.nuosi.flow.logic.model.body.Action;
import com.nuosi.flow.util.LogicFlowConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>desc: 节点执行处理器的http请求类型实现 </p>
 * <p>date: 2021/10/27 14:48 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class HttpProcesser implements IActionProcesser {
    private static String REQUEST_TYPE_GET = "get";

    @Override
    public Object execute(Map<String, Object> databus, Action action, JMap input, Object... param) throws Exception {
        List<Http> https = action.getHttps();
        Http http = https.get(0);
        String url = http.getUrl();

        List<Header> headers = http.getHeaders();
        Map<String, String> headerParam = null;
        if (headers != null) {
            headerParam = new HashMap<String, String>();
            for (Header header : headers) {
                if (header.getValue() != null) {   //请求头内容以常用为使用优先级
                    headerParam.put(header.getKey(), header.getValue());
                } else {
                    String value = (String) databus.get(header.getKey());
                    if (value != null) {
                        headerParam.put(header.getKey(), value);
                    }
                }
            }
        }

        int connTimeout = http.getConnTimeout();
        int readTimeout = http.getReadTimeout();
        if (connTimeout >= LogicFlowConstants.Default.CONN_TIMEOUT
                && readTimeout >= LogicFlowConstants.Default.READ_TIMEOUT) {
            UnirestUtil.setTimeout(connTimeout, readTimeout);
        }

        String result;
        if (REQUEST_TYPE_GET.equals(http.getType())) {
            result = UnirestUtil.requestByGet(url, input, headerParam);
        } else {
            result = UnirestUtil.requestByPost(url, input, headerParam);
        }
        return result;
    }
}
