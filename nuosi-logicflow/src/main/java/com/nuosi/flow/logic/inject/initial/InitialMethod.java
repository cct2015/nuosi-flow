package com.nuosi.flow.logic.inject.initial;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>desc: Var标签初始化方法默认类 </p>
 * <p>date: 2022/1/11 10:30 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class InitialMethod extends AbstractInitialMethod{

    public String getDatetime(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatDate = format.format(date);
        return formatDate;
    }

}
