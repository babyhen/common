package com.pawpaw.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pawpaw.common.concurrent.WorkAndSleepCall;
import com.pawpaw.common.concurrent.WorkAndSleepExecutor;
import com.pawpaw.common.util.DateTimeUtil;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DateTimeUtillTest {


    @Test
    public void dayInterval() {
        int period = DateTimeUtil.dayInterval(new Date(), DateTimeUtil.minusDay(new Date(), 1));
        System.out.println(period);
    }


    @Test
    public void ofDate() {
        Date d = DateTimeUtil.ofDate("2021", "01", "02");
        System.out.println(d);
    }
}



