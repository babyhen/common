package com.pawpaw.common.sort;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Sorter {

    public static final Comparator timeAscComparator = new Comparator<ITimeComparable>() {
        @Override
        public int compare(ITimeComparable o1, ITimeComparable o2) {
            Date d1 = o1.getCompareTime();
            Date d2 = o2.getCompareTime();
            return d1.compareTo(d2);
        }
    };

    public static final Comparator timeDescComparator = new Comparator<ITimeComparable>() {
        @Override
        public int compare(ITimeComparable o1, ITimeComparable o2) {
            Date d1 = o1.getCompareTime();
            Date d2 = o2.getCompareTime();
            return d2.compareTo(d1);
        }
    };

    /**
     * 正序
     *
     * @param list
     */
    public static void timeAsc(List<? extends ITimeComparable> list) {
        list.sort(timeAscComparator);
    }

    /**
     * 时间倒叙
     * @param list
     */
    public static void timeDesc(List<? extends ITimeComparable> list) {
        list.sort(timeDescComparator);
    }


}