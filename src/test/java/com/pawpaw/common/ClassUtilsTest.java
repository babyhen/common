package com.pawpaw.common;

import com.pawpaw.common.util.ClassUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class ClassUtilsTest {


    @Test
    public void getDeclaredFields() {
        List<Field> fs = ClassUtils.getDeclaredFields(TChild.class);
        for (Field f : fs) {
            System.out.println(f.getName());
        }
    }


}


class T {

    private int a;
    protected String b;
    public Date c;
    Collection d;
}

class TChild extends T {

    private long e;
}

