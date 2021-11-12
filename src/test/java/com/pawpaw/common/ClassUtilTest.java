package com.pawpaw.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pawpaw.common.concurrent.WorkAndSleepCall;
import com.pawpaw.common.concurrent.WorkAndSleepExecutor;
import com.pawpaw.common.meta.ClassUtil;
import com.pawpaw.common.meta.Param;
import com.pawpaw.common.meta.ParamInfo;
import com.pawpaw.common.util.DateTimeUtil;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ClassUtilTest {


    @Test
    public void getParamInfo() throws JsonProcessingException, InterruptedException, NoSuchMethodException {
        Constructor[] cs = M.class.getConstructors();
        for (Constructor c : cs) {
            List<ParamInfo> list = ClassUtil.getParamInfo(c);
            for (ParamInfo pi : list) {
                System.out.println(pi);
            }
        }

        Method method = M.class.getMethod("h");
        List<ParamInfo> list = ClassUtil.getParamInfo(method);
        for (ParamInfo pi : list) {
            System.out.println(pi);
        }
    }
}


class M {
    @Param("x")
    @Param("y")
    public M(int x, String y) {
    }

    @Param("a")
    @Param(value = "b", defaultValue = "1111111111")
    public void h() {

    }
}



