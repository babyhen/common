package com.pawpaw.common.sort;

import com.pawpaw.common.util.DateTimeUtil;
import lombok.AllArgsConstructor;

import java.time.LocalTime;
import java.util.function.Predicate;

@AllArgsConstructor
public class BetweenSpecifiedTimePredicate<T extends ITimeComparable> implements Predicate<T> {

    private LocalTime beginTime;
    private LocalTime endTime;

    @Override
    public boolean test(T t) {
        LocalTime time = DateTimeUtil.toLocalTime(t.getCompareTime());
        if (time.isBefore(beginTime)) {
            return false;
        }
        if (time.isAfter(endTime)) {
            return false;
        }
        return true;
    }
};