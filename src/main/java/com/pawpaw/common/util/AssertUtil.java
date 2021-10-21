package com.pawpaw.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

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

    public static void assertTrue(boolean expression, String exceptionMessage) {
        if (!expression) {
            throw new RuntimeException(exceptionMessage);
        }
    }

}
