package com.nuosi.flow.logic.model.body;

import com.nuosi.flow.logic.model.action.*;
import com.nuosi.flow.logic.model.domain.Behavior;
import com.nuosi.flow.logic.model.element.Input;
import com.nuosi.flow.logic.model.element.Output;

import java.util.List;

/**
 * <p>desc: 逻辑流元素：行为节点</p>
 * <p>date: 2021/3/6 12:16</p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class Action {

    public static enum ActionType{
        SQL,EXPRESSION,IF,FOREACH,BEHAVIOR,SUBFLOW,FUNCTION,HTTP,AGGREGATE,
        SERVICE,RULE,MAPPING,EVENT
    }

    private ActionType actionType;

    private String id;
    private String name;
    private String next;
    private List<Input> inputs;
    private List<Output> outputs;
    private List<Sql> sqls;
    private List<Expression> expressions;
    private List<If> ifs;
    private List<Foreach> foreachs;
    private List<Behavior> behaviors;
    private List<Subflow> subflows;
    private List<Function> functions;
    private List<Http> https;
    private List<Aggregate> aggregates;

    public ActionType getActionType() {
        return actionType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<Input> getInputs() {
        return inputs;
    }

    public Action setInputs(List<Input> inputs) {
        this.inputs = inputs;
        return this;
    }

    public List<Output> getOutputs() {
        return outputs;
    }

    public Action setOutputs(List<Output> outputs) {
        this.outputs = outputs;
        return this;
    }

    public List<Sql> getSqls() {
        return sqls;
    }

    public Action setSqls(List<Sql> sqls) {
        this.sqls = sqls;
        this.actionType = ActionType.SQL;
        return this;
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

    public Action setExpressions(List<Expression> expressions) {
        this.expressions = expressions;
        this.actionType = ActionType.EXPRESSION;
        return this;
    }

    public List<If> getIfs() {
        return ifs;
    }

    public Action setIfs(List<If> ifs) {
        this.ifs = ifs;
        this.actionType = ActionType.IF;
        return this;
    }

    public List<Foreach> getForeachs() {
        return foreachs;
    }

    public Action setForeachs(List<Foreach> foreachs) {
        this.foreachs = foreachs;
        this.actionType = ActionType.FOREACH;
        return this;
    }

    public List<Behavior> getBehaviors() {
        return behaviors;
    }

    public Action setBehaviors(List<Behavior> behaviors) {
        this.behaviors = behaviors;
        this.actionType = ActionType.BEHAVIOR;
        return this;
    }

    public List<Subflow> getSubflows() {
        return subflows;
    }

    public Action setSubflows(List<Subflow> subflows) {
        this.subflows = subflows;
        this.actionType = ActionType.SUBFLOW;
        return this;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    public Action setFunctions(List<Function> functions) {
        this.functions = functions;
        this.actionType = ActionType.FUNCTION;
        return this;
    }

    public List<Http> getHttps() {
        return https;
    }

    public Action setHttps(List<Http> https) {
        this.https = https;
        this.actionType = ActionType.HTTP;
        return this;
    }

    public List<Aggregate> getAggregates() {
        return aggregates;
    }

    public Action setAggregates(List<Aggregate> aggregates) {
        this.aggregates = aggregates;
        this.actionType = ActionType.AGGREGATE;
        return this;
    }
}
