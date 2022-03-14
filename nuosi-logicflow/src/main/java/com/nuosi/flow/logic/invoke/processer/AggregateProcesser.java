package com.nuosi.flow.logic.invoke.processer;

import com.ai.ipu.data.JMap;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nuosi.flow.logic.model.action.Aggregate;
import com.nuosi.flow.logic.model.body.Action;
import com.nuosi.flow.util.JsonConvert;

import java.util.Map;

/**
 * <p>desc: 节点执行处理器的数据聚合类型实现  </p>
 * <p>date: 2022/1/11 16:51 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class AggregateProcesser implements IActionProcesser {

    @Override
    public Object execute(Map<String, Object> databus, Action action, JMap input, Object... param) throws Exception {
        Aggregate aggregate = action.getAggregates().get(0);
        String convert = aggregate.getAggregate();
        Object result = null;
        if(convert!=null){
            String json = JsonConvert.convert(convert, input);
            result = JSON.parse(json);
        }else{
            result = new JSONObject(input); //不转换时直接聚合为JMap
        }
        /*if("map".equals(aggregate.getType())){
        }*/
        return result;
    }

}
