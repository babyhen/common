package com.pawpaw.common.meta;

import java.lang.annotation.*;

@Inherited
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Param {

    String value();

    String defaultValue() default "";

    String desc() default "";

}

