package com.pawpaw.common.executor;

import com.pawpaw.common.executor.call.ExecutorCall;
import com.pawpaw.common.executor.call.ReturnNullExecutorCall;
import com.pawpaw.common.executor.call.ReturnableExecutorCall;

/**
 * 工作一会，然后再休息一会
 */
public class WorkAndSleepExecutor {
    private final long workInMillionSecond;
    private final long sleepInMillionSecond;

    private volatile ThreadLocal<Long> lastWorkStartTimeLocal;


    public WorkAndSleepExecutor(int workInSecond, int sleepInSecond) {
        this(workInSecond * 1000L, sleepInSecond * 1000L);
    }

    public WorkAndSleepExecutor(long workInMillionSecond, long sleepInMillionSecond) {
        this.workInMillionSecond = workInMillionSecond;
        this.sleepInMillionSecond = sleepInMillionSecond;
        this.lastWorkStartTimeLocal = new ThreadLocal();
    }


    public void execute(ExecutorCall call) {
        this.execute(new ReturnNullExecutorCall() {
            @Override
            public void call() {
                call.call();
            }
        });
    }

    public <T> T execute(ReturnableExecutorCall<T> call) {
        //如果是刚创建。那么初始化为工作状态
        Long lastWorkStartTime = this.lastWorkStartTimeLocal.get();
        if (lastWorkStartTime == null) {
            lastWorkStartTime = System.currentTimeMillis();
            this.lastWorkStartTimeLocal.set(lastWorkStartTime);
        }
        long workTimeEnd = lastWorkStartTime + this.workInMillionSecond;
        long workAndSleepEnd = lastWorkStartTime + this.workInMillionSecond + this.sleepInMillionSecond;
        //
        long curr = System.currentTimeMillis();
        //检查工作时长
        if (curr >= lastWorkStartTime && curr <= workTimeEnd) {   //工作时间段
            call.run();
            T t = call.getReturn();
            return t;
        } else if (curr > workTimeEnd && curr < workAndSleepEnd) {  //休息时间段
            long sleepTime = workAndSleepEnd - curr;
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            lastWorkStartTime = System.currentTimeMillis();
            this.lastWorkStartTimeLocal.set(lastWorkStartTime);
            call.run();
            T t = call.getReturn();
            return t;
        } else {                                    //工作和休息都过了。那么继续下一个轮回
            lastWorkStartTime = System.currentTimeMillis();
            this.lastWorkStartTimeLocal.set(lastWorkStartTime);
            call.run();
            T t = call.getReturn();
            return t;
        }
    }


}
