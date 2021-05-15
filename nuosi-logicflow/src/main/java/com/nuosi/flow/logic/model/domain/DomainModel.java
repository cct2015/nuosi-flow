package com.nuosi.flow.logic.model.domain;

import java.util.List;

/**
 * <p>desc: 逻辑流元素：领域模型</p>
 * <p>date: 2021/3/25 18:08</p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class DomainModel {
    private String id;
    private String name;
    private String desc;
    private List<Attr> attrs;
    private List<Function> functions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        initFunctions(id, functions);
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

    public List<Attr> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<Attr> attrs) {
        this.attrs = attrs;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    public void setFunctions(List<Function> functions) {
        this.functions = functions;
        initFunctions(id, functions);
    }

    /**
     * <p>desc: JSON转Model时，无先后顺序，需要id和function同时不为空时方可初始化 </p>
     * <p>date: 2021/5/15 10:44</p>
     * @author nuosi fsofs@163.com
     * @version v1.0.0
     * @param id 1
     * @param functions 2
     * @return void
     */
    private void initFunctions(String id, List<Function> functions){
        if(id==null||functions==null){
            return;
        }

        if(functions!=null){
            FunctionManager.initFunctions(id, functions);
        }
    }
}
