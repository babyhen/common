package com.pawpaw.common;

import com.pawpaw.common.util.ArrayUtil;
import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest {
    private int[] a = new int[0];

    @Before
    public void init() {
        a = new int[7];
        for (int i = 0; i < a.length; i++) {
            a[i] =  i;
        }
    }

    @Test
    public void subArray() {
        int[] s = ArrayUtil.subArray(a, 2, 0);
        for (int i : s) {
            System.out.println(i);
        }
    }


}




