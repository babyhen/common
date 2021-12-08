package com.pawpaw.common;

import com.pawpaw.common.executor.RetryExecutor;
import com.pawpaw.common.executor.call.ReturnableExecutorCall;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Callable;

public class RetryExecutorTest {
    ThreadLocal<Integer> tl = new ThreadLocal();

    @Before
    public void init() {
        tl.set(0);
    }


    ReturnableExecutorCall call = new ReturnableExecutorCall<Object>() {
        @Override
        public void call() {
            Integer num = tl.get();
            tl.set(++num);
            throw new RuntimeException("test");
        }

        @Override
        public Object getReturn() {
            return null;
        }
    };


    @Test
    public void execute() {
        RetryExecutor executor = new RetryExecutor(3);
        try {
            executor.execute(call);
        } catch (Exception e) {
            //不做任何操作，主要是防止最后一次运行call的时候test case 抛出异常被中断
        }
        Assert.assertTrue(tl.get() == 4);
    }


}




