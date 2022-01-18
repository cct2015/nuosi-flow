package com.nuosi.server.user;

import com.ai.ipu.common.http.UnirestUtil;
import com.ai.ipu.data.JMap;
import com.ai.ipu.data.impl.JsonMap;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>desc: 用户服务测试 </p>
 * <p>date: 2022/1/18 19:30 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class UserServiceTest {

    @Test
    public void testInsertUser() throws UnirestException {
        String url = "http://127.0.0.1:8080/ipu/logic/user_insert";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("user_id", System.currentTimeMillis());
        param.put("user_name", "张三");
        param.put("gender", "1");
        param.put("user_state", "1");
        param.put("email", "zhangsan@163.com");
        param.put("link_phone", "13788888888");
        String result = UnirestUtil.requestByPost(url, param);
        System.out.println("result==="+result);
        Assert.assertTrue(true);
    }

    @Test
    public void testSelectUser() throws Exception {
        String url = "http://127.0.0.1:8080/ipu/logic/user_select";
        JMap param = new JsonMap();
        param.put("user_id", "344571");

        String result = UnirestUtil.requestByPost(url, param);
        System.out.println("result==="+result);
        Assert.assertTrue(true);
    }

    @Test
    public void testSelectUserByName() throws Exception {
        String url = "http://127.0.0.1:8080/ipu/logic/user_select_by_name";
        JMap param = new JsonMap();
        param.put("user_name", "张三");

        String result = UnirestUtil.requestByPost(url, param);
        System.out.println("result==="+result);
        Assert.assertTrue(true);
    }
}
