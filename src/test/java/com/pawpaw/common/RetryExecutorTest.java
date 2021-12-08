package com.pawpaw.common;

import com.pawpaw.common.concurrent.RetryExecutor;
import com.pawpaw.common.util.ArrayUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Callable;

public class RetryExecutorTest {


    @Test
    public void execute() {
        RetryExecutor executor = new RetryExecutor(3);
        ThreadLocal<Integer> tl = new ThreadLocal();
        tl.set(0);
        try {
            executor.execute(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    Integer num = tl.get();
                    tl.set(++num);
                    throw new RuntimeException("test");
                }
            });
        } catch (Exception e) {

        }
        Assert.assertTrue(tl.get() == 4);
    }


}




