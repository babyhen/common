package com.pawpaw.common;

import com.pawpaw.common.sort.NumberAscComparator;
import com.pawpaw.common.sort.NumberDescComparator;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SortTest {


    @Test
    public void sortNumberDescComparator() {
        List<Double> r = new LinkedList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            r.add(random.nextDouble(1000d));
        }
        r.sort(new NumberDescComparator());
        System.out.println(r);
        r.sort(new NumberAscComparator());
        System.out.println(r);
    }
}



