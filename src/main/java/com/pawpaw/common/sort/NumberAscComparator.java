package com.pawpaw.common.sort;

import java.util.Comparator;

public class NumberAscComparator implements Comparator<Number> {

    //公共的默认，攻其它的类调用，可以省去new一个新对象的开销
    public static final NumberAscComparator defaultInstance = new NumberAscComparator();

    @Override
    public int compare(Number o1, Number o2) {
        double r = o1.doubleValue() - o2.doubleValue();
        if (r == 0) {
            return 0;
        } else if (r < 0) {
            return -1;
        } else {
            return 1;
        }
    }
}
