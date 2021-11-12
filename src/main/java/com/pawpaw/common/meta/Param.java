package com.pawpaw.common.meta;

import java.lang.annotation.*;

@Inherited
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Params.class)
public @interface Param {

    String value();

    String defaultValue() default "";

    Class type() default String.class;
}

