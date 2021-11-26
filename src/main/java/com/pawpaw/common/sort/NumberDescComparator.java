package com.pawpaw.common.sort;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.Comparator;
import java.util.Date;

public class NumberDescComparator implements Comparator<Number> {

    //公共的默认，攻其它的类调用，可以省去new一个新对象的开销
    public static final NumberDescComparator defaultInstance = new NumberDescComparator();

    @Override
    public int compare(Number o1, Number o2) {
        double r = o2.doubleValue() - o1.doubleValue();
        if (r == 0) {
            return 0;
        } else if (r < 0) {
            return -1;
        } else {
            return 1;
        }
    }
}
