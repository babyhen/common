package com.pawpaw.common.meta;

import java.lang.annotation.*;

@Inherited
@Target({ElementType.PARAMETER,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Param {

    String value();

    String defaultValue() default "";

    String desc() default "";

}

