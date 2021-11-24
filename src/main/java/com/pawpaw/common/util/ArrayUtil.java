package com.pawpaw.common.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
public class ArrayUtil {

    public static <T> T[] subArray(T[] source, int from, int size) {
        if (from >= source.length) {
            return Arrays.copyOf(source, 0);
        }
        if ((from + size) > source.length) {
            size = source.length - from;
        }
        int to = from + size;
        T[] r = Arrays.copyOfRange(source, from, to);
        return r;
    }

    public static int[] subArray(int[] source, int from, int size) {
        Integer[] pt = new Integer[source.length];
        for (int i = 0; i < source.length; i++) {
            pt[i] = source[i];
        }
        Integer[] spt = subArray(pt, from, size);
        int[] r = new int[spt.length];
        for (int i = 0; i < spt.length; i++) {
            r[i] = spt[i];
        }
        return r;
    }

}
