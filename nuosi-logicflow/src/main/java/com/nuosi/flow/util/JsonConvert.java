package com.nuosi.flow.util;

import com.ai.ipu.data.JMap;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * <p>desc: Json数据格式转换功能 </p>
 * <p>date: 2022/1/11 17:08 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class JsonConvert {

    private final static String SUFFIX_MAP = "_map";
    private final static String SUFFIX_LIST = "_list";

    public static String convert(String jsonFormat, String json) throws Exception {
        Object value = JSON.parse(json);
        return convert(jsonFormat, value);
    }

    public static String convert(String jsonFormat, JMap jmap) throws Exception {
        Object value = new JSONObject(jmap);
        return convert(jsonFormat, value);
    }

    /**
     * <p>desc: Json转Json方法</p>
     * <p>date: 2022/1/11 17:12</p>
     * @author nuosi fsofs@163.com
     * @version v1.0.0
     * @param jsonFormat 1
     * @param value 2
     * @return java.lang.String
     */
    public static String convert(String jsonFormat, Object value) throws Exception {
        if(value instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject)value;
            /*通过name得到转换配置*/
            JSONObject jsonConfig = JSONObject.parseObject(jsonFormat);
            JSONObject targetJsonObject = convertMap(jsonObject, jsonConfig);
            return targetJsonObject.toJSONString();
        }else if (value instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray)value;
            /*通过name得到转换配置*/
            JSONArray jsonConfig = JSONArray.parseArray(jsonFormat);
            JSONArray targetJsonObject = convertList(jsonArray, jsonConfig);
            return targetJsonObject.toJSONString();
        }else{
            return null;
        }
    }

    /**
     * <p>desc: 递归转换Map结构 </p>
     * <p>date: 2022/1/11 17:13</p>
     * @author nuosi fsofs@163.com
     * @version v1.0.0
     * @param jsonObject 1
     * @param jsonConfig 2
     * @return com.alibaba.fastjson.JSONObject
     */
    private static JSONObject convertMap(JSONObject jsonObject, JSONObject jsonConfig){
        String key;
        String newKey;
        Object value;
        Object config;
        JSONObject jo;
        JSONArray ja;
        for(Object k : jsonObject.keySet().toArray()){
            key = (String)k;
            value = jsonObject.get(key);
            config = jsonConfig.get(key);
            /*增加Number类型的转义*/
            if ((value instanceof String)||value instanceof Number) {
                newKey = (String)config; //存在key转换才做处理
                if(newKey!=null){
                    jsonObject.put((String)config, value);
                    jsonObject.remove(key);
                }
            }else if(value instanceof JSONObject) {
                jo = convertMap((JSONObject) value, (JSONObject)config);
                newKey = key + SUFFIX_MAP;
                jsonObject.put(jsonConfig.getString(newKey), jo);
                jsonObject.remove(key);
            }else if (value instanceof JSONArray) {
                ja = convertList((JSONArray) value, (JSONArray)config);
                newKey = key + SUFFIX_LIST;
                jsonObject.put(jsonConfig.getString(newKey), ja);
                jsonObject.remove(key);
            }
        }
        return jsonObject;
    }

    /**
     * <p>desc: 递归转换List结构 </p>
     * <p>date: 2022/1/11 17:13</p>
     * @author nuosi fsofs@163.com
     * @version v1.0.0
     * @param jsonArray 1
     * @param jsonConfig 2
     * @return com.alibaba.fastjson.JSONArray
     */
    private static JSONArray convertList(JSONArray jsonArray, JSONArray jsonConfig){
        Object value;
        Object config;
        JSONObject jo;
        JSONArray ja;
        for(int i=0,len=jsonArray.size();i<len;i++){
            value = jsonArray.get(i);
            config = jsonConfig.get(0);    //多条数据只对应第一条配置
            if (value instanceof JSONObject) {
                jo = convertMap((JSONObject)value, (JSONObject)config);
                jsonArray.remove(i);
                jsonArray.add(i, jo);
            }else if (value instanceof JSONArray) {
                ja = convertList((JSONArray)value, (JSONArray)config);
                jsonArray.remove(i);
                jsonArray.add(i, ja);
            }
        }
        return jsonArray;
    }

    /**
     * @author nuosi fsofs@163.com
     * @title: checkConvertConfig
     * @desc: 校验转换配置文件是否正确
     * @param name
     * @param json
     */
    public static boolean checkConvertConfig(String name, String json) {
        // TODO Auto-generated method stub
        return false;
    }
}
