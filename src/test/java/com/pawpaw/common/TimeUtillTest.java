package com.pawpaw.common;

import com.pawpaw.common.util.TimeUtil;
import org.junit.Test;

import java.time.LocalTime;

public class TimeUtillTest {


    @Test
    public void parse8() {
        LocalTime t = TimeUtil.parse8("09:25:10");
        System.out.println(t);
    }


}



