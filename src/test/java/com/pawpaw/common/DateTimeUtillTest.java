package com.pawpaw.common;

import com.pawpaw.common.util.DateTimeUtil;
import org.junit.Test;

import java.util.Date;

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


    @Test
    public void truncateTime() {
        Date d = DateTimeUtil.truncateTime(new Date());
        System.out.println(d);
    }


    @Test
    public void truncateSecond() {
        Date d = DateTimeUtil.truncateSecond(new Date());
        System.out.println(d);
    }

    @Test
    public void plusMinute() {
        Date d = DateTimeUtil.plusMinute(new Date(), 5);
        System.out.println(d);
    }
}



