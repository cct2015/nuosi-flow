package com.nuosi.flow.data;

import com.nuosi.flow.data.impl.BizData;
import com.nuosi.flow.data.impl.BizDataDefine;
import com.nuosi.flow.logic.LogicFlowEngine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>desc: 业务传输对象的数据类型和格式定义的单元测试</p>
 * <p>date: 2021/3/19 23:07</p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class BizDataDefineTest {
    private String bizName;

    @Before
    public void before(){
        LogicFlowEngine.init();
        bizName = "User";
        // 注册User结构的业务对象
        BDataDefine userDefine = new BizDataDefine(bizName);
        userDefine.defineValidator("name", BizDataValidatorManager.createStringValidator());
        userDefine.defineValidator("age", BizDataValidatorManager.createIntegerValidator());
        userDefine.defineValidator("height", BizDataValidatorManager.createDecimalValidator());
        userDefine.defineValidator("birthday", BizDataValidatorManager.createDateValidator());
        userDefine.defineValidator("create_date", BizDataValidatorManager.createDatetimeValidator());
        BizDataManager.registerDto(userDefine, true);
    }

    @Test
    public void testStringBDataType(){
        try{
            BData user = new BizData(bizName);
            user.put("name", "reynolds");
            System.out.println(bizName + ":" + user);
            Assert.assertTrue(true);
        }catch (Exception e){
            System.out.println("Ex信息:" + e.getMessage());
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testIntBDataType(){
        try{
            BData user = new BizData(bizName);
            user.put("age", 20);
            System.out.println(bizName + ":" + user);
            Assert.assertTrue(true);
        }catch (Exception e){
            System.out.println("Ex信息:" + e.getMessage());
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testIntBDataTypeError(){
        try{
            BData user = new BizData(bizName);
            user.put("age", "abc");
            System.out.println(bizName + ":" + user);
            Assert.assertTrue(false);
        }catch (Exception e){
            System.out.println("Ex信息:" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDecimalBDataType(){
        try{
            BData user = new BizData(bizName);
            user.put("height", 1.78);
            System.out.println(bizName + ":" + user);
            Assert.assertTrue(true);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Ex信息:" + e.getMessage());
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testDecimalBDataTypeError(){
        try{
            BData user = new BizData(bizName);
            user.put("height", "abc");
            System.out.println(bizName + ":" + user);
            Assert.assertTrue(false);
        }catch (Exception e){
            System.out.println("Ex信息:" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDateBDataType(){
        try{
            BData user = new BizData(bizName);
            user.put("birthday", "1983-8-5");
            System.out.println(bizName + ":" + user);
            Assert.assertTrue(true);
        }catch (Exception e){
            System.out.println("Ex信息:" + e.getMessage());
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testDateBDataTypeError(){
        try{
            BData user = new BizData(bizName);
            user.put("birthday", "abc");
            System.out.println(bizName + ":" + user);
            Assert.assertTrue(false);
        }catch (Exception e){
            System.out.println("Ex信息:" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDatetimeBDataType(){
        try{
            BData user = new BizData(bizName);
            user.put("create_date", "2021-3-19 23:50:11");
            System.out.println(bizName + ":" + user);
            Assert.assertTrue(true);
        }catch (Exception e){
            System.out.println("Ex信息:" + e.getMessage());
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testDatetimeBDataTypeError(){
        try{
            BData user = new BizData(bizName);
            user.put("create_date", "abc");
            System.out.println(bizName + ":" + user);
            Assert.assertTrue(false);
        }catch (Exception e){
            System.out.println("Ex信息:" + e.getMessage());
            Assert.assertTrue(true);
        }
    }
}
