package com.pawpaw.common.meta;

import java.lang.annotation.*;

@Inherited
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultValues {

    DefaultValue[] value() default {};


}

