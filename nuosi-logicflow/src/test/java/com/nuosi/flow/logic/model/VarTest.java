package com.nuosi.flow.logic.model;

import com.alibaba.fastjson.JSON;
import com.nuosi.flow.data.BizDataLimitManager;
import com.nuosi.flow.data.BizDataManager;
import com.nuosi.flow.data.impl.BizData;
import com.nuosi.flow.data.impl.BizDataDefine;
import com.nuosi.flow.logic.model.element.Var;
import com.nuosi.flow.logic.util.DtoUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>desc: Var标签测试</p>
 * <p>date: 2021/3/6 16:33</p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class VarTest {

    @Before
    public void before(){
        BizDataDefine bizDataDefine = new BizDataDefine("Var");
        bizDataDefine.defineLimit("id", BizDataLimitManager.createStringLimit());
        bizDataDefine.defineLimit("type",BizDataLimitManager.createStringLimit());
        bizDataDefine.defineLimit("name",BizDataLimitManager.createStringLimit());
        bizDataDefine.defineLimit("final",BizDataLimitManager.createStringLimit());
        bizDataDefine.defineLimit("default",BizDataLimitManager.createStringLimit());
        bizDataDefine.defineLimit("initial",BizDataLimitManager.createStringLimit());
        BizDataManager.registerDto(bizDataDefine);
    }

    @Test
    public void testJsonToVar(){
        BizData varData = new BizData("Var");
        varData.put("id","goods_name").put("type","string").put("name","货物名称");
        // 从获取Json开始验证
        String varJson = varData.toJSONString();
        Var var = JSON.parseObject(varJson, Var.class);
        System.out.println(var);
        System.out.println(JSON.toJSONString(var));
        System.out.println(DtoUtil.toString(var));
        Assert.assertTrue(true);
    }

    @After
    public void after(){

    }
}
