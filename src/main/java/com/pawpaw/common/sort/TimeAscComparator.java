package com.pawpaw.common.sort;

import java.util.Comparator;
import java.util.Date;

public class TimeAscComparator implements Comparator<ITimeComparable> {

    public static final TimeAscComparator defaultInstance = new TimeAscComparator();

    @Override
    public int compare(ITimeComparable o1, ITimeComparable o2) {
        Date d1 = o1.getTime();
        Date d2 = o2.getTime();
        return d1.compareTo(d2);
    }
}
