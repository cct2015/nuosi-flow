package com.nuosi.flow.logic.model;

import com.nuosi.flow.logic.model.body.Action;
import com.nuosi.flow.logic.model.body.End;
import com.nuosi.flow.logic.model.body.Start;
import com.nuosi.flow.logic.model.global.Declare;

import java.util.List;

/**
 * <p>desc: 逻辑流</p>
 * <p>date: 2021/3/6 12:12</p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class LogicFlow {
    private String id;
    private String name;
    private String desc;
    private List<Declare> declares;
    private List<Start> starts;
    private List<End> ends;
    private List<Action> actions;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Declare> getDeclares() {
        return declares;
    }

    public void setDeclares(List<Declare> declares) {
        this.declares = declares;
    }

    public List<Start> getStarts() {
        return starts;
    }

    public void setStarts(List<Start> starts) {
        this.starts = starts;
    }

    public List<End> getEnds() {
        return ends;
    }

    public void setEnds(List<End> ends) {
        this.ends = ends;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }
}
