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
@ToString
public class ComplexTypeParamInfo extends AbstractParamInfo {

    private List<AbstractParamInfo> fields;

    public ComplexTypeParamInfo(int position, String name, Class type, String desc) {
        super(position, name, type, desc);
        this.fields = new CopyOnWriteArrayList<>();
    }

    public void addField(AbstractParamInfo item) {
        this.fields.add(item);
    }
}
