package com.pawpaw.common.meta;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 最基础的参数信息
 */
@RequiredArgsConstructor
@Getter
@ToString
public abstract class AbstractParamInfo {

    protected final String name;
    protected final Class type;
    protected final String desc;

    /**
     * @return
     */
    public abstract String getDefaultValue();

    /**
     * @return
     */
    public abstract List<AbstractParamInfo> getFields();
}
