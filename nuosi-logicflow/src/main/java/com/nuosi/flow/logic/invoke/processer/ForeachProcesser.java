package com.nuosi.flow.logic.invoke.processer;

import com.ai.ipu.basic.util.IpuUtility;
import com.ai.ipu.data.JMap;
import com.nuosi.flow.logic.inject.QuickBuild;
import com.nuosi.flow.logic.model.action.Foreach;
import com.nuosi.flow.logic.model.body.Action;
import com.nuosi.flow.util.LogicFlowConstants;
import org.mvel2.MVEL;
import org.mvel2.PropertyAccessException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>desc: 节点执行处理器的循环类型实现
 * foreach提供index、iterable、result三个常量，简化循环的使用</p>
 * <p>date: 2021/4/16 9:47 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class ForeachProcesser implements IActionProcesser{
    private static final String DATABUS = LogicFlowConstants.DATABUS;
    private static final String QB = LogicFlowConstants.QB;
    private static final String ITERATORS = "ITERATORS";
    private static final String ITERATOR = "ITERATOR";
    private static final String INDEX = "INDEX";
    private static final String RESULT = "RESULT";
    private static final String KEYS = "KEYS";

    public enum ResultType {
        INT, STRING, MAP, LIST;
    }


    @Override
    public Object execute(Map<String, Object> databus, Action action, JMap input, Object ... param) throws Exception {
        List<Foreach> foreachs = action.getForeachs();
        Foreach foreach = foreachs.get(0);

        Object iterator = databus.get(foreach.getIterator());
        if(iterator==null){
            return null;
        }

        Map<String, Object> vars = new HashMap<String, Object>();

        String foreachExpression = null;
        if(iterator instanceof Iterable){
            vars.put(ITERATORS, iterator);
            foreachExpression = createForeachExpressionWithIterator(foreach);
        }else if(iterator instanceof Map){
            Map iteratorMap = (Map) iterator;
            vars.put(ITERATORS, iteratorMap);
            vars.put(KEYS, iteratorMap.keySet());
            foreachExpression = createForeachExpressionWithMap(foreach);
        }else{
            IpuUtility.errorCode(LogicFlowConstants.FLOW_FOREACH_ITERATOR_TYPE_ERROR);
        }
        vars.put(DATABUS, databus);
        vars.put(QB, QuickBuild.getInstance());

        try{
            Object result = MVEL.eval(foreachExpression, vars);
            return result;
        }catch (Exception e){
            if(e instanceof PropertyAccessException){
                Throwable tr = IpuUtility.getBottomException(e);
                IpuUtility.error(tr);
            }
            throw e;
        }
    }

    private String createForeachExpressionWithIterator(Foreach foreach){
        StringBuilder expr = new StringBuilder();
        expr.append("int INDEX = 0; \n");
        if(foreach.getResultType()!=null){
            appendResult(foreach, expr);
        }
        expr.append("foreach(ITERATOR : ITERATORS){ \r");
        expr.append("INDEX++; \r");
        expr.append(foreach.getForeach()==null?"":foreach.getForeach()).append("\r");
        expr.append("} \r");
        if(foreach.getResultType()!=null){
            expr.append("return RESULT; \r");
        }
        return expr.toString();
    }

    private String createForeachExpressionWithMap(Foreach foreach){
        StringBuilder expr = new StringBuilder();
        expr.append("int INDEX = 0; \n");
        if(foreach.getResultType()!=null){
            appendResult(foreach, expr);
        }
        expr.append("foreach(KEY : KEYS){ \r");
        expr.append("INDEX++; \r");
        expr.append("VALUE=ITERATORS.get(KEY); \r");
        expr.append(foreach.getForeach()==null?"":foreach.getForeach()).append("\r");
        expr.append("} \r");
        if(foreach.getResultType()!=null){
            expr.append("return RESULT; \r");
        }
        return expr.toString();
    }

    private void appendResult(Foreach foreach, StringBuilder expr){
        ResultType resultType = ResultType.valueOf(foreach.getResultType().toUpperCase());

        switch (resultType) {
            case INT:
                expr.append("var RESULT = 0; \r");
                break;
            case STRING:
                expr.append("var RESULT = \"\"; \r");
                break;
            case MAP:
                expr.append("var RESULT = new java.util.HashMap(); \r");
                break;
            case LIST:
                expr.append("var RESULT = new java.util.ArrayList(); \r");
                break;
            default:
                break;
        }
    }
}
