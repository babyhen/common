package com.pawpaw.common.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Predicate;

@Slf4j
public class CollectionUtil {

    public static <T> T lastOne(List<T> source) {
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
        if (from < 0) {
            from = 0;
        }
        return source.subList(from, toIndex);
    }

    /**
     * 从第一个满足条件的位置开始往后截取
     *
     * @param source
     * @param <T>
     * @return
     */
    public static <T> List<T> subFromFirstOccurrence(List<T> source, Predicate<T> predicate) {
        List<T> r = new LinkedList<>();
        boolean isMatch = false;
        for (T t : source) {
            if (!isMatch) {
                isMatch = predicate.test(t);
            }
            if (isMatch) {
                r.add(t);
            }

        }
        return r;
    }

    public static <T> T findFirstMatch(List<T> source, Predicate<T> predicate) {
        for (T t : source) {
            if (predicate.test(t)) {
                return t;
            }

        }
        return null;
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


    /**
     * 得到满足指定条件的，有连续性的片段
     *
     * @param source
     * @param minContinuousSize 有连续性，顾名思义就是至少是2
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> getContinuous(List<T> source,
                                                  Predicate<? super T> predicate,
                                                  int minContinuousSize) {
        AssertUtil.assertTrue(minContinuousSize >= 1, "连续的周期数最小值为1");
        List<T> copy = new LinkedList<>(source);
        List<List<T>> r = new LinkedList<>();
        List<T> tmp = new LinkedList<>();
        //
        for (int i = 0; i < copy.size(); i++) {
            T item = copy.get(i);
            if (predicate.test(item)) {
                tmp.add(item);
                if (i == copy.size() - 1) {   //如果是最后一个元素，判断是否满足条件
                    if (tmp.size() >= minContinuousSize) {
                        r.add(tmp);
                    }
                }


            } else {
                //判断是否满足加入到返回列表的条件
                if (tmp.size() >= minContinuousSize) {
                    r.add(tmp);
                }
                //重置tmp对象
                tmp = new LinkedList<>();
            }
        }

        //
        return r;
    }


}
