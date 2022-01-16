package com.pawpaw.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pawpaw.common.meta.ClassUtil;
import com.pawpaw.common.meta.Param;
import com.pawpaw.common.meta.ParamInfo;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

public class ClassUtilTest {


    @Test
    public void getParamInfo() throws Exception {
        Constructor[] cs = M.class.getConstructors();
        for (Constructor c : cs) {
            List<ParamInfo> list = ClassUtil.getParamInfo(c);
            for (ParamInfo pi : list) {
                System.out.println(pi);
            }
        }

        Method method = M.class.getMethod("oneParam", String.class);
        List<ParamInfo> list = ClassUtil.getParamInfo(method);
        for (ParamInfo pi : list) {
            System.out.println(pi);
        }
    }
}


class M {
    public M() {
    }

    public M(int x, String y, @Param("z") int z) {
    }

    public M(@Param(value = "oneParam", defaultValue = "222") String oneParam) {
    }

    public void h() {

    }


    public void oneParam(@Param(value = "oneParam", defaultValue = "1111111111") String one) {

    }

}



