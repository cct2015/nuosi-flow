package com.nuosi.flow.logic.model.action;

import com.nuosi.flow.logic.model.action.sub.Header;
import com.nuosi.flow.util.LogicFlowConstants;

import java.util.List;

/**
 * <p>desc: 逻辑流元素：http接口请求 </p>
 * <p>date: 2021/10/27 14:36 </p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class Http {
    private String url;
    private String type = "post";
    private int connTimeout;
    private int readTimeout;
    private List<Header> headers;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getConnTimeout() {
        return connTimeout;
    }

    public void setConnTimeout(int connTimeout) {
        this.connTimeout = connTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }
}
