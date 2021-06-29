package com.nuosi.flow.data.limit;

import com.nuosi.flow.data.BDataDefine;
import com.nuosi.flow.data.BDataLimit;
import com.nuosi.flow.util.BizDataValidityUtil;

import java.sql.Timestamp;

/**
 * <p>desc: 时间戳数据限制和校验 </p>
 * <p>date: 2021/4/8 15:31 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class DatetimeLimit extends AbstractDataLimit {
    private Timestamp startDatetime = null;
    private Timestamp endDatetime = null;

    public DatetimeLimit() {
        super(BDataDefine.BDataType.DATETIME);
    }

    public Timestamp getStartDatetime() {
        return startDatetime;
    }

    public DatetimeLimit setStartDatetime(Timestamp startDatetime) {
        this.startDatetime = startDatetime;
        return this;
    }

    public Timestamp getEndDatetime() {
        return endDatetime;
    }

    public DatetimeLimit setEndDatetime(Timestamp endDatetime) {
        this.endDatetime = endDatetime;
        return this;
    }

    @Override
    public void checkValidity(String bizName, String attr, Object value) {
        Timestamp timestampValue = BizDataValidityUtil.checkDatetime(value, bizName, attr);
        BizDataValidityUtil.checkDatetimeLimit(timestampValue, this, bizName, attr);
    }
}
