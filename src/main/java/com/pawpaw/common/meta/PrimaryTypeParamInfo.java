package com.pawpaw.common.meta;

import lombok.*;

import java.util.Collections;
import java.util.List;

/**
 * 基础的数据类型对应的类
 */
@ToString(callSuper = true)
public class PrimaryTypeParamInfo extends AbstractParamInfo {


    public PrimaryTypeParamInfo(String name, Class type, String desc, AbstractParamInfo parent, DefaultValue dfv) {
        super(name, type, desc, parent, dfv);
    }

    @Override
    public List<AbstractParamInfo> getFields() {
        return Collections.emptyList();
    }
}
