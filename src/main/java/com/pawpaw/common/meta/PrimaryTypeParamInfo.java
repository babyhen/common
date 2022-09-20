package com.pawpaw.common.meta;

import lombok.*;

import java.util.Collections;
import java.util.List;

/**
 * 基础的数据类型对应的类
 */
@ToString(callSuper = true)
public class PrimaryTypeParamInfo extends AbstractParamInfo {

    private final String defaultValue;

    public PrimaryTypeParamInfo(String name, Class type, String desc, String defaultValue) {
        super(name, type, desc);
        this.defaultValue = defaultValue;
    }

    @Override
    public String getDefaultValue() {
        if (this.defaultValue == null) {
            return "";
        }
        return this.defaultValue;
    }

    @Override
    public List<AbstractParamInfo> getFields() {
        return Collections.emptyList();
    }
}
