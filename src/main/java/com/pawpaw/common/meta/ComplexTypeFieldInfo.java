package com.pawpaw.common.meta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 复合的数据类型对应的类
 */
@Getter
@RequiredArgsConstructor
public class ComplexTypeFieldInfo {

    protected final String name;
    protected final Class type;
    protected final String desc;
    private final String defaultValue;
}
