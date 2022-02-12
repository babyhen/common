package com.pawpaw.common.meta;

import com.pawpaw.common.meta.convertor.AutoConvertor;

import java.lang.annotation.*;

@Inherited
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Param {

    String value();

    String defaultValue() default "";

    String desc() default "";

    Class<? extends IConvertor> convertor() default AutoConvertor.class;
}

