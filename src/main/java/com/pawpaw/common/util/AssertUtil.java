package com.pawpaw.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Date;

public class AssertUtil {

    public static void notBlank(String str, String exceptionMessage) {
        if (StringUtils.isBlank(str)) {
            throw new RuntimeException(exceptionMessage);
        }
    }

    public static void notNull(Object obj, String exceptionMessage) {
        if (obj == null) {
            throw new RuntimeException(exceptionMessage);
        }
    }

    public static void length(String str, int length, String exceptionMessage) {
        notNull(str, "字符串不能为空");
        if (str.length() != length) {
            throw new RuntimeException(exceptionMessage);
        }
    }

    public static void notEmpty(Collection<?> collection, String exceptionMessage) {
        if (collection == null || collection.isEmpty()) {
            throw new RuntimeException(exceptionMessage);
        }
    }

    /**
     * 断言，from是不是早于to
     *
     * @param from
     * @param to
     */
    public static void before(Date from, Date to, String exceptionMessage) {
        if (from.before(to)) {
            return;
        }
        throw new RuntimeException(exceptionMessage);
    }


    /**
     * 断言，from是不是早于to
     *
     * @param from
     * @param to
     */
    public static void before(Comparable from, Comparable to, String exceptionMessage) {
        int r = from.compareTo(to);
        if (r < 0) {
            return;
        }
        throw new RuntimeException(exceptionMessage);
    }


    public static void assertTrue(boolean expression, String exceptionMessage) {
        if (!expression) {
            throw new RuntimeException(exceptionMessage);
        }
    }

}
