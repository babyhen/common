package com.pawpaw.common.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Slf4j
public class CollectionUtil {

    public static <T> List<T> subList(List<T> source, int size) {
        return subList(source, 0, size);
    }


    public static <T> List<T> subList(List<T> source, int from, int size) {
        int tmpToIndex = from + size;
        int toIndex = source.size() >= tmpToIndex ? tmpToIndex : source.size();
        return source.subList(from, toIndex);
    }

}
