package com.pawpaw.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pawpaw.common.concurrent.SpeedLimitCall;
import com.pawpaw.common.concurrent.SpeedLimitExecutor;
import com.pawpaw.common.concurrent.WorkAndSleepCall;
import com.pawpaw.common.concurrent.WorkAndSleepExecutor;
import com.pawpaw.common.util.DateTimeUtil;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WorkAndSleepExecutorlTest {


    @Test
    public void execute() throws JsonProcessingException, InterruptedException {
        WorkAndSleepExecutor executor = new WorkAndSleepExecutor(3, 2);
        ExecutorService service = Executors.newFixedThreadPool(2);
        //添加任务
        for (int i = 0; i < 10; i++) {
            service.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    Object r = executor.execute(new WorkAndSleepCall<Object>() {
                        @Override
                        public Object call() {
                            System.out.println(Thread.currentThread().getName()+":"+DateTimeUtil.format19(new Date()));
                            doSomething();
                            return null;
                        }
                    });
                    return r;
                }
            });
        }
        service.shutdown();
        service.awaitTermination(100, TimeUnit.HOURS);
    }

    public void doSomething() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



