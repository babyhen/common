package com.pawpaw.common.meta;

import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
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
    public AbstractParamInfo getField(String name) {
        for (AbstractParamInfo pi : this.fields) {
            if (StringUtils.equalsIgnoreCase(pi.name, name)) {
                return pi;
            }
        }
        return null;
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
        //如果带有DefaultValue(s)的注解，那么设置的路径应该是正确可达的;
        List<DefaultValue> allDFVConfig = this.allDefaultValueConfig();
        allDFVConfig.forEach(dv -> {
            String specifiedPath = dv.path();
            List<String> separatedPath = com.pawpaw.common.util.StringUtils.split(specifiedPath, PATH_SEPARATOR);
            //循环一层一层检查是否存在对应名字的字段
            AbstractParamInfo curr = this;
            for (String segment : separatedPath) {
                AbstractParamInfo isExist = curr.getField(segment);
                if (isExist == null) {
                    throw new MetaException("sub child \"" + segment + "\" is missing !");
                }
                curr = isExist;
            }
        });
        //子字段依次进行检查
        for (AbstractParamInfo field : this.fields) {
            field.check();
        }
    }
}
