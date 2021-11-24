package com.pawpaw.common.util;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Slf4j
public class ArrayConventer {


    public static int[] toInt(String[] source) {
        if (source == null) {
            return new int[0];
        }
        int[] r = new int[source.length];
        for (int i = 0; i < source.length; i++) {
            r[i] = Integer.parseInt(source[i]);
        }
        return r;
    }

    public static long[] toLong(String[] source) {
        if (source == null) {
            return new long[0];
        }
        long[] r = new long[source.length];
        for (int i = 0; i < source.length; i++) {
            r[i] = Long.parseLong(source[i]);
        }
        return r;
    }

}
