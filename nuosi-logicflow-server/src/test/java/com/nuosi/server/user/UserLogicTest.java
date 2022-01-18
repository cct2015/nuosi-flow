package com.nuosi.server.user;

import com.ai.ipu.data.JMap;
import com.ai.ipu.data.impl.JsonMap;
import com.nuosi.flow.logic.LogicFlowEngine;
import com.nuosi.flow.util.LogicFlowUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>desc: 用户对象逻辑的测试 </p>
 * <p>date: 2022/1/18 19:43 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class UserLogicTest {

    @Test
    public void testInsertUser() throws Exception {
        JMap param = new JsonMap();
        param.put("user_id", String.valueOf(System.currentTimeMillis()).substring(7));
        param.put("user_name", "张三");
        param.put("gender", "1");
        param.put("user_state", "1");
        param.put("email", "zhangsan@163.com");
        param.put("link_phone", "13788888888");

        JMap result = LogicFlowEngine.execute("user_insert", param);
        System.out.println("执行结果：" + result);
        Assert.assertEquals("新增用户信息失败!", result.getInt("insert_num"), 1);
    }

    @Test
    public void testSelectUser() throws Exception {
        JMap param = new JsonMap();
        param.put("user_id", "344571");

        JMap result = LogicFlowEngine.execute("user_select", param);
        System.out.println("执行结果：" + result);
        Assert.assertEquals("查询用户信息失败!", ((Map)result.get("user_info")).size(), 7);
    }

    @Test
    public void testSelectUserByName() throws Exception {
        JMap param = new JsonMap();
        param.put("user_name", "张三");

        JMap result = LogicFlowEngine.execute("user_select_by_name", param);
        System.out.println("执行结果：" + result);
        Assert.assertTrue("查询用户信息失败!", ((List)result.get("user_infos")).size()>=1);
    }

    @Before
    public void before() throws IOException {
        String[] models = {
                "flow/user/model/tab_user.xml"
        };
        LogicFlowUtil.loadLogicModels(models);

        String[] flows = {
                "flow/user/user_insert.xml",
                "flow/user/user_select.xml",
                "flow/user/user_select_by_name.xml"
        };

        LogicFlowUtil.loadLogicFlows(flows);
    }
}
