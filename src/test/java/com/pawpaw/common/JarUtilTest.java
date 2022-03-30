package com.pawpaw.common;

import com.pawpaw.common.util.JarUtil;
import org.junit.Test;

import java.io.IOException;

public class JarUtilTest {

    @Test
    public   void getManiFestField() throws IOException {
        String value = JarUtil.getManiFestField(JarUtilTest.class, "a");
        System.out.println(value);
    }


}



