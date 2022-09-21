package com.pawpaw.common.meta;

import java.lang.annotation.*;

@Inherited
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultValue {

    String value();

    /**
     * 属性的路径。例如  注解在a的上面 a.b.c
     * 那么就是a下面的b，b下面的c的默认值。假如c里面有了自己的注解，那么会重写c的默认值
     * @return
     */
    String path() default  "";
}

