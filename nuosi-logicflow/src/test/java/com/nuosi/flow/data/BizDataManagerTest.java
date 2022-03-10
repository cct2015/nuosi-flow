package com.nuosi.flow.data;

import com.nuosi.flow.data.impl.BizData;
import com.nuosi.flow.data.impl.BizDataDefine;
import com.nuosi.flow.logic.LogicFlowEngine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>desc: 业务传输对象管理类常用方法的单元测试</p>
 * <p>date: 2021/3/16 17:27</p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class BizDataManagerTest {
    private String bizName;

    @Before
    public void before(){
        LogicFlowEngine.init();
        bizName = "User";
        // 注册User结构的业务对象
        BDataDefine dataDefine = new BizDataDefine(bizName);
        dataDefine.defineValidator("name", BizDataValidatorManager.createStringValidator());
        dataDefine.defineValidator("age", BizDataValidatorManager.createIntegerValidator());
        BizDataManager.registerDto(dataDefine, true);
    }

    @Test
    public void testNewInstanceWithRegister(){
        try{
            BData user = new BizData(bizName);
            user.put("name","reynolds");
            System.out.println(bizName + ":" + user);
            Assert.assertTrue(true);
        }catch (Exception e){
            System.out.println("Ex:" + e.getMessage());
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testNewInstanceWithNoRegister(){
        try{
            BizDataManager.unregisterDto(bizName); //全量测试的时候提前删除
            BData user = new BizData(bizName);
            user.put("name","reynolds");
            System.out.println(bizName+ ":" + user);
            Assert.assertTrue(false);
        }catch (Exception e){
            System.out.println("Ex:" + e.getMessage());
            Assert.assertTrue(true);
        }
    }
}
