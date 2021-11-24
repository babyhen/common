package com.pawpaw.common.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.util.*;

@Slf4j
public class CollectionUtil {

    public static <T> List<T> subList(List<T> source, int size) {
        return subList(source, 0, size);
    }


    public static <T> List<T> subListFrom(List<T> source, int from) {
        int size = source.size() - from;
        return subList(source, from, size);
    }


    public static <T> List<T> subList(List<T> source, int from, int size) {
        int tmpToIndex = from + size;
        int toIndex = source.size() >= tmpToIndex ? tmpToIndex : source.size();
        if (from >= toIndex) {
            return Collections.emptyList();
        }
        return source.subList(from, toIndex);
    }


    public static <T> T[] toArray(List<T> l, Class<T> tClass) {
        T[] a = (T[]) Array.newInstance(tClass, l.size());
        for (int i = 0; i < l.size(); i++) {
            a[i] = l.get(i);
        }
        return a;
    }


}
