package com.pawpaw.common.util;

import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

@Log
public class StringUtils {

    public static List<String> split(String s) {
        return split(s, ",");
    }


    public static List<String> split(String s, String sep) {
        if (s == null) {
            return new LinkedList<>();
        }
        String[] arr = org.apache.commons.lang3.StringUtils.split(s, sep);
        return CollectionConventer.array2List(arr);
    }

    public static List<String> split(String s, CharSequence sep) {
        return split(s, sep.toString());
    }


}
