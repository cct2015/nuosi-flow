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
    private String version;
    private List<Attr> attrs;
    private List<Behavior> behaviors;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        initBehaviors(id, behaviors);
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Attr> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<Attr> attrs) {
        this.attrs = attrs;
    }

    public List<Behavior> getBehaviors() {
        return behaviors;
    }

    public void setBehaviors(List<Behavior> behaviors) {
        this.behaviors = behaviors;
        initBehaviors(id, behaviors);
    }

    /**
     * <p>desc: JSON转Model时，无先后顺序，需要id和behavior同时不为空时方可初始化 </p>
     * <p>date: 2021/5/15 10:44</p>
     * @author nuosi fsofs@163.com
     * @version v1.0.0
     * @param id 1
     * @param behaviors 2
     * @return void
     */
    private void initBehaviors(String id, List<Behavior> behaviors){
        if(id==null|| behaviors ==null){
            return;
        }

        if(behaviors !=null){
            BehaviorManager.initBehaviors(id, behaviors);
        }
    }
}
