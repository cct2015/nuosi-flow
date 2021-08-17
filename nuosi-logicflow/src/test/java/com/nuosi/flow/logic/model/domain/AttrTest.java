package com.nuosi.flow.logic.model.domain;

import com.alibaba.fastjson.JSON;
import com.nuosi.flow.data.BizDataLimitManager;
import com.nuosi.flow.data.BizDataManager;
import com.nuosi.flow.data.impl.BizData;
import com.nuosi.flow.data.impl.BizDataDefine;
import com.nuosi.flow.logic.util.DtoUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>desc: Attr标签测试</p>
 * <p>date: 2021/3/6 16:33</p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class AttrTest {

    @Before
    public void before(){
        BizDataDefine bizDataDefine = new BizDataDefine("Attr");
        bizDataDefine.defineLimit("id", BizDataLimitManager.createStringLimit());
        bizDataDefine.defineLimit("type",BizDataLimitManager.createStringLimit());
        bizDataDefine.defineLimit("name",BizDataLimitManager.createStringLimit());
        BizDataManager.registerDto(bizDataDefine);
    }

    @Test
    public void testJsonToAttr(){
        BizData varData = new BizData("Attr");
        varData.put("id","goods_name").put("type","string").put("name","货物名称");
        // 从获取Json开始验证
        String varJson = varData.toJSONString();
        Attr attr = JSON.parseObject(varJson, Attr.class);
        System.out.println(attr);
        System.out.println(JSON.toJSONString(attr));
        System.out.println(DtoUtil.toString(attr));
        Assert.assertTrue(true);
    }

    @After
    public void after(){

    }
}
