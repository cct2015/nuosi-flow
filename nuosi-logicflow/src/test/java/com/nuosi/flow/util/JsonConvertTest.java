package com.nuosi.flow.util;

import com.ai.ipu.data.JMap;
import com.ai.ipu.data.impl.JsonMap;
import org.junit.Assert;
import org.junit.Test;

/**
 * <p>desc: Json数据格式转换功能测试类 </p>
 * <p>date: 2022/1/11 17:15 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class JsonConvertTest {

    @Test
    public void testMapConvert() throws Exception {
        JMap jmap = new JsonMap();
        jmap.put("name", "张三");
        jmap.put("money", String.format("%.2f", 10.155));
        jmap.put("point", 10);
        String jsonFormat = "{'name':'Name','money':'Money'}";
        String result = JsonConvert.convert(jsonFormat, jmap);
        System.out.println(result);
        Assert.assertTrue(result.contains("Money")); //包含转化后的Key则测试正确
    }
}
