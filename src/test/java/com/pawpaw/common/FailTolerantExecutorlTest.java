package com.pawpaw.common;

import com.pawpaw.common.concurrent.FailTolerantExecutor;
import com.pawpaw.common.test.ITest;
import com.pawpaw.common.util.DateTimeUtil;
import org.apache.commons.collections.functors.ExceptionPredicate;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FailTolerantExecutorlTest implements ITest {
    Callable call = new Callable<Object>() {
        @Override
        public Object call() throws Exception {
            throw new RuntimeException("发生异常");
        }
    };

    @Test
    public void singleExecute() {
        FailTolerantExecutor executor = new FailTolerantExecutor(3);
        for (int i = 0; i < 4; i++) {
            try {
                Object o = executor.execute(call);
                System.out.println(Thread.currentThread().getName() + ":" + o);
            } catch (Exception e) {
                System.out.println(Thread.currentThread().getName() + ":" + e.getMessage());
            }

        }

    }


    @Test
    public void concurrentExecute() throws InterruptedException {
        FailTolerantExecutor executor = new FailTolerantExecutor(3);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 40; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        doSomeThing(1);
                        Object o = executor.execute(call);
                        System.out.println(Thread.currentThread().getName() + ":" + o);
                    } catch (Exception e) {
                        System.out.println(Thread.currentThread().getName() + ":" + e.getMessage());
                    }

                }
            });
        }
        service.shutdown();
        service.awaitTermination(10, TimeUnit.MINUTES);
    }
}



