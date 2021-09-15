package com.nuosi.flow.data;

import com.nuosi.flow.data.impl.BizData;
import com.nuosi.flow.data.impl.BizDataDefine;
import com.nuosi.flow.data.limit.*;
import com.nuosi.flow.logic.LogicFlowEngine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * <p>desc: 业务传输对象的数据限制和校验单元测试</p>
 * <p>date: 2021/3/19 23:06</p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class BizDataLimitTest {
    private String bizName;

    @Before
    public void before() throws ParseException {
        LogicFlowEngine.init();
        bizName = "User";

        BDataLimit stringLimit = new StringLimit().setMax(10);
        BDataLimit intLimit = new IntegerLimit().setMax(200).setMin(0);
        BDataLimit decimalLimit = new DecimalLimit().setPrecision(4).setScale(2)
                .setMax(new BigDecimal("1111.11")).setMin(new BigDecimal("1.11"));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = new Date(dateFormat.parse("1982-1-1").getTime());
        Date endDate = new Date(dateFormat.parse("1984-12-30").getTime());
        BDataLimit dateLimit = new DateLimit().setMin(startDate).setMax(endDate);

        SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp startDatetime = new Timestamp(dateFormat.parse("2021-3-19 00:00:00").getTime());
        Timestamp endDatetime = new Timestamp(dateFormat.parse("2021-3-20 23:59:59").getTime());
        BDataLimit datetimeLimit = new DatetimeLimit().setMin(startDatetime).setMax(endDatetime);

        // 注册User结构的业务对象
        BDataDefine userDefine = new BizDataDefine(bizName);
        userDefine.defineLimit("name", stringLimit);
        userDefine.defineLimit("age", intLimit);
        userDefine.defineLimit("height", decimalLimit);
        userDefine.defineLimit("birthday", dateLimit);
        userDefine.defineLimit("create_date", datetimeLimit);
        BizDataManager.registerDto(userDefine, true);
    }

    @Test
    public void testStringSizeError(){
        try{
            BData user = new BizData(bizName);
            user.put("name", "reynolds1234");
            System.out.println(bizName + ":" + user);
            Assert.assertTrue(false);
        }catch (Exception e){
            System.out.println("Ex:" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testIntMaxError(){
        try{
            BData user = new BizData(bizName);
            user.put("age", 201);
            System.out.println(bizName + ":" + user);
            Assert.assertTrue(false);
        }catch (Exception e){
            System.out.println("Ex:" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testIntMinError(){
        try{
            BData user = new BizData(bizName);
            user.put("age", -1);
            System.out.println(bizName + ":" + user);
            Assert.assertTrue(false);
        }catch (Exception e){
            System.out.println("Ex:" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDecimalPrecisionError(){
        try{
            BData user = new BizData(bizName);
            user.put("height", 999.99);
            System.out.println(bizName + ":" + user);
            Assert.assertTrue(false);
        }catch (Exception e){
            System.out.println("Ex:" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDecimalScaleError(){
        try{
            BData user = new BizData(bizName);
            user.put("height", 1.781);
            System.out.println(bizName + ":" + user);
            Assert.assertTrue(false);
        }catch (Exception e){
            System.out.println("Ex:" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDecimalMaxError(){
        try{
            BData user = new BizData(bizName);
            user.put("height", 9999);
            System.out.println(bizName + ":" + user);
            Assert.assertTrue(false);
        }catch (Exception e){
            System.out.println("Ex:" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testDecimalMinError(){
        try{
            BData user = new BizData(bizName);
            user.put("height", 0.99);
            System.out.println(bizName + ":" + user);
            Assert.assertTrue(false);
        }catch (Exception e){
            System.out.println("Ex:" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testStartDateError(){
        try{
            BData user = new BizData(bizName);
            user.put("birthday", "1981-8-5");
            System.out.println(bizName + ":" + user);
            Assert.assertTrue(false);
        }catch (Exception e){
            System.out.println("Ex:" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testEndDateError(){
        try{
            BData user = new BizData(bizName);
            user.put("birthday", "1985-8-5");
            System.out.println(bizName + ":" + user);
            Assert.assertTrue(false);
        }catch (Exception e){
            System.out.println("Ex:" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testStartDatetime(){
        try{
            BData user = new BizData(bizName);
            user.put("create_date", "2021-3-1 23:50:11");
            System.out.println(bizName + ":" + user);
            Assert.assertTrue(false);
        }catch (Exception e){
            System.out.println("Ex:" + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testEndDatetime(){
        try{
            BData user = new BizData(bizName);
            user.put("create_date", "2021-4-1 23:50:11");
            System.out.println(bizName + ":" + user);
            Assert.assertTrue(false);
        }catch (Exception e){
            System.out.println("Ex:" + e.getMessage());
            Assert.assertTrue(true);
        }
    }
}
