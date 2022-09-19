package com.pawpaw.common.meta;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 复合的数据类型对应的类
 */
@Getter
@ToString(callSuper = true)
public class ComplexTypeParamInfo extends ParamInfo {

    private List<ParamInfo> fields;

    public ComplexTypeParamInfo(String name, Class type, String desc) {
        super(name, type, desc);
        this.fields = new CopyOnWriteArrayList<>();
    }

    public void addField(ParamInfo field) {
        this.fields.add(field);
    }
}
