package com.nuosi.flow.data.validate;

import com.ai.ipu.basic.util.IpuUtility;
import com.nuosi.flow.data.BDataDefine;
import com.nuosi.flow.data.BDataValidator;
import com.nuosi.flow.util.LogicFlowConstants;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>desc: 业务传输对象数据限制和校验抽象类 </p>
 * <p>date: 2021/6/29 21:58 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public abstract class AbstractDataValidator implements BDataValidator {
    private BDataDefine.BDataType dataType;
    private String regex;
    private String escapeRegex;

    public AbstractDataValidator(BDataDefine.BDataType dataType) {
        this.dataType = dataType;
    }

    public BDataDefine.BDataType getDataType() {
        return dataType;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
        this.escapeRegex = escapeExprSpecialWord(regex);
    }

    public void checkRegex(String bizName, String attr, Object value) {
        if (regex == null) {
            return;
        }

        Pattern pattern = Pattern.compile(regex);
        String val = String.valueOf(value);
        Matcher matcher = pattern.matcher(val);
        if (!matcher.matches()) {
            IpuUtility.errorCode(LogicFlowConstants.BDATA_CHECK_REGEX, bizName, attr, escapeRegex, val);
        }
    }

    public static String escapeExprSpecialWord(String keyword) {
        if (StringUtils.isNotBlank(keyword)) {
            String[] fbsArr = {"\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|"};
            for (String key : fbsArr) {
                if (keyword.contains(key)) {
                    keyword = keyword.replace(key, "\\" + key);
                }
            }
        }
        return keyword;
    }
}
