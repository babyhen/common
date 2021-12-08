package com.pawpaw.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pawpaw.common.executor.WorkAndSleepExecutor;
import com.pawpaw.common.executor.call.ReturnableExecutorCall;
import com.pawpaw.common.test.ITest;
import com.pawpaw.common.util.DateTimeUtil;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WorkAndSleepExecutorlTest implements ITest {
    ReturnableExecutorCall call = new ReturnableExecutorCall<Integer>() {
        @Override
        public void call() {
            doSomeThing(1);
            System.out.println(Thread.currentThread().getName() + ":" + DateTimeUtil.format19(new Date()));
        }

        @Override
        public Integer getReturn() {
            return 1;
        }
    };

    @Test
    public void execute() throws JsonProcessingException, InterruptedException {
        WorkAndSleepExecutor executor = new WorkAndSleepExecutor(3, 2);
        ExecutorService service = Executors.newFixedThreadPool(2);
        //添加任务
        for (int i = 0; i < 10; i++) {
            service.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    Object r = executor.execute(call);
                    return r;
                }
            });
        }
        service.shutdown();
        service.awaitTermination(100, TimeUnit.HOURS);
    }


}



