package com.pawpaw.common.meta;

import java.lang.annotation.*;

@Inherited
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Params {

    public Param[] value();

}
