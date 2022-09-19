package com.pawpaw.common.meta;

import lombok.*;

/**
 * 基础的数据类型对应的类
 */
@Getter
@ToString(callSuper = true)
public class PrimaryTypeParamInfo extends ParamInfo {

    private final String defaultValue;

    public PrimaryTypeParamInfo(int position, String name, Class type, String desc, String defaultValue) {
        super(name, type, desc);
        this.defaultValue = defaultValue;
    }
}
