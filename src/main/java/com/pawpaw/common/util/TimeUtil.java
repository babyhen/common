package com.pawpaw.common.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class TimeUtil {

    public static DateTimeFormatter TIME_FORMAT_8 = new DateTimeFormatterBuilder().appendPattern("HH:mm:ss").toFormatter();

    /**
     * @param str
     * @param format
     * @return
     */
    public static LocalTime parseTime(String str, DateTimeFormatter format) {
        LocalTime r = LocalTime.parse(str, format);
        return r;
    }

    /**
     * @param str
     * @return
     */
    public static LocalTime parse8(String str) {
        return parseTime(str, TIME_FORMAT_8);
    }
}
