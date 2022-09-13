package com.pawpaw.common.meta;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class AbstractParamInfo {

    //参数的位置，从0开始
    protected final int position;
    protected final String name;
    protected final Class type;
    protected final String desc;
}
