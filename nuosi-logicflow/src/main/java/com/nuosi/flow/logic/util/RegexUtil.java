package com.nuosi.flow.logic.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * <p>desc: 正则表达式工具类 </p>
 * <p>date: 2022/4/6 16:46 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class RegexUtil {
    private static Map<String, Pattern> patternCache = new ConcurrentHashMap<String, Pattern>();

    public static Pattern getPattern(String regex) {
        String key = String.valueOf(regex.hashCode());
        Pattern pattern = patternCache.get(key);
        if (pattern == null) {
            synchronized (key) {
                if (pattern == null) {
                    pattern = Pattern.compile(regex);
                    patternCache.put(key, pattern);
                }
            }
        }
        return pattern;
    }

}
