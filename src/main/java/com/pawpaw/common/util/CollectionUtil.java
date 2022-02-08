package com.pawpaw.common.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.util.*;

@Slf4j
public class CollectionUtil {

    public static <T> T last(List<T> source) {
        if (source == null || source.isEmpty()) {
            return null;
        }
        return source.get(source.size() - 1);
    }

    public static <T> List<T> subList(List<T> source, int size) {
        return subList(source, 0, size);
    }


    public static <T> List<T> subListFrom(List<T> source, int from) {
        int size = source.size() - from;
        return subList(source, from, size);
    }

    /**
     * 截取最后N个元素
     *
     * @param source
     * @param size
     * @param <T>
     * @return
     */
    public static <T> List<T> subLast(List<T> source, int size) {
        int from = source.size() - size;
        return subList(source, from, size);
    }


    public static <T> List<T> subList(List<T> source, int from, int size) {
        int tmpToIndex = from + size;
        int toIndex = source.size() >= tmpToIndex ? tmpToIndex : source.size();
        if (from >= toIndex) {
            return Collections.emptyList();
        }
        if(from<0){
            from=0;
        }
        return source.subList(from, toIndex);
    }


    public static <T> List<T> minus(List<T> source, Collection<T> toMinus) {
        List<T> copy = new ArrayList<>(source);
        copy.removeAll(toMinus);
        return copy;
    }


    public static <T> T[] toArray(List<T> l, Class<T> tClass) {
        T[] a = (T[]) Array.newInstance(tClass, l.size());
        for (int i = 0; i < l.size(); i++) {
            a[i] = l.get(i);
        }
        return a;
    }


}
