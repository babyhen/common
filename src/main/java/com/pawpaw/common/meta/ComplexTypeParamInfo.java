package com.pawpaw.common.meta;

import lombok.ToString;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    public void addField(Collection<AbstractParamInfo> fields) {
        this.fields.addAll(fields);
    }


    @Override
    public List<AbstractParamInfo> getFields() {
        return this.fields;
    }

    @Override
    public void check() {
        //所有的field不能重名
        Set<String> existNames = new HashSet<>();
        for (AbstractParamInfo pi : this.fields) {
            if (existNames.contains(pi.name)) {
                throw new MetaException("name \"" + pi.name + "\" already exist !");
            }
            existNames.add(pi.name);
        }

    }
}
