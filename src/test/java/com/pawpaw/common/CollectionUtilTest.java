package com.pawpaw.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pawpaw.common.util.BrowserUtil;
import com.pawpaw.common.util.CollectionUtil;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class CollectionUtilTest {


    @Test
    public void subList() throws JsonProcessingException {
        List<String> l = new LinkedList<>();
        l.add("a");
        l.add("b");
        l.add("c");
        l.add("d");
        List<String> l2 = CollectionUtil.subList(l, 1,0);
        System.out.println(l2);
    }


}




