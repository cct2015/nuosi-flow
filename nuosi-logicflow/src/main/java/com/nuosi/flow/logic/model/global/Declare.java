package com.nuosi.flow.logic.model.global;

import com.nuosi.flow.logic.model.domain.Attr;

import java.util.List;

/**
 * <p>desc: 逻辑流元素：全局定义</p>
 * <p>date: 2021/3/6 12:13</p>
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class Declare {
    private List<Import> imports;
    private List<Attr> attrs;

    public List<Import> getImports() {
        return imports;
    }

    public void setImports(List<Import> imports) {
        this.imports = imports;
    }

    public List<Attr> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<Attr> attrs) {
        this.attrs = attrs;
    }
}
