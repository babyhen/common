package com.pawpaw.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pawpaw.common.util.BrowserUtil;
import com.pawpaw.common.util.CollectionUtil;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class CollectionUtilTest {
    List<String> l = new LinkedList<>();

    @Before
    public void init() {
        l.add("a");
        l.add("b");
        l.add("c");
        l.add("d");
    }

    @Test
    public void subList() throws JsonProcessingException {
        List<String> l2 = CollectionUtil.subList(this.l, 1, 0);
        System.out.println(l2);
    }

    @Test
    public void subLast() throws JsonProcessingException {
        List<String> l2 = CollectionUtil.subLast(this.l, 4);
        System.out.println(l2);
    }


    @Test
    public void subFromFirstOccurrence() throws JsonProcessingException {
        List<String> l2 = CollectionUtil.subFromFirstOccurrence(this.l, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.equals("a");
            }
        });
        System.out.println(l2);
    }


    @Test
    public void subListFrom() {
        List<String> l2 = CollectionUtil.subListFrom(this.l, 2);
        System.out.println(l2);
    }


    @Test
    public void toArray() throws JsonProcessingException {
        String[] a = CollectionUtil.toArray(this.l, String.class);
        System.out.println(a[0]);
    }

    @Test
    public void getFirstContinuousPiCi() {
        List<Integer> a = new LinkedList<>();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        a.add(5);
        a.add(6);
        a.add(7);
        a.add(8);
        a.add(9);
        a.add(1);
        a.add(2);
        a.add(3);
        Predicate<Integer> p = new Predicate<Integer>() {
            @Override
            public boolean test(Integer o) {
                return o >= 0&&o<3;
            }
        };
        Optional r = CollectionUtil.getFirstContinuousPiCi(a, p, 2);
        System.out.println(r);
    }


}




