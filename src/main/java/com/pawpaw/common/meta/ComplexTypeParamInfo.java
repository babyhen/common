package com.pawpaw.common.meta;

import lombok.ToString;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 复合的数据类型对应的类
 */

@ToString(callSuper = true)
public class ComplexTypeParamInfo extends AbstractParamInfo {

    private List<AbstractParamInfo> fields;

    public ComplexTypeParamInfo(String name, Class type, String desc, AbstractParamInfo parent, DefaultValue dfv, DefaultValues dfvs) {
        super(name, type, desc, parent, dfv, dfvs);
        this.fields = new CopyOnWriteArrayList<>();
    }

    public void addField(AbstractParamInfo field) {
        this.fields.add(field);
    }


    @Override
    public List<AbstractParamInfo> getFields() {
        return this.fields;
    }
}
