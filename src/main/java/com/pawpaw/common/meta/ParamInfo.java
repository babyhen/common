package com.pawpaw.common.meta;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * 最基础的参数信息
 */
@RequiredArgsConstructor
@Getter
@ToString
public class ParamInfo {

    protected final String name;
    protected final Class type;
    protected final String desc;
}
