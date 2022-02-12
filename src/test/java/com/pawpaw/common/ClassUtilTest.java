package com.pawpaw.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pawpaw.common.meta.ClassUtil;
import com.pawpaw.common.meta.MetaInfo;
import com.pawpaw.common.meta.Param;
import com.pawpaw.common.meta.ParamInfo;
import com.pawpaw.common.meta.convertor.DefaultConvertor;
import com.pawpaw.common.meta.convertor.IntConvertor;
import com.pawpaw.common.util.JsonUtil;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @Test
    public void deserializeconstructArgs() throws Exception {
        Constructor c = M.class.getConstructor(Integer.class, String.class, Integer.TYPE);
        MetaInfo mi = new MetaInfo<>(c);
        Map<String, Object> pm = new HashMap<>();
        pm.put("z", 99);

        Object[] realParam = mi.deserializeconstructArgs(JsonUtil.object2Json(pm));
        for (Object o : realParam) {
            System.out.println(o);
        }
    }
}


class M {
    public M() {
    }

    public M(Integer x,
             String y,
             @Param(value = "z", convertor = IntConvertor.class) int z) {
    }

    public M(@Param(value = "oneParam", defaultValue = "222") String oneParam) {
    }

    public void h() {

    }


    public void oneParam(@Param(value = "oneParam", defaultValue = "1111111111") String one) {

    }

}



