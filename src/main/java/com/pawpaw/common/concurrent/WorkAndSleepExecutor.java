package com.pawpaw.common.concurrent;

import com.google.common.util.concurrent.RateLimiter;
import com.pawpaw.common.domain.IEnumType;
import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

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

    public <T> T execute(WorkAndSleepCall<T> call) {
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
            return call.call();
        } else if (curr > workTimeEnd && curr < workAndSleepEnd) {  //休息时间段
            long sleepTime = workAndSleepEnd - curr;
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            lastWorkStartTime = System.currentTimeMillis();
            this.lastWorkStartTimeLocal.set(lastWorkStartTime);
            return call.call();
        } else {                                    //工作和休息都过了。那么继续下一个轮回
            lastWorkStartTime = System.currentTimeMillis();
            this.lastWorkStartTimeLocal.set(lastWorkStartTime);
            return call.call();
        }
    }


}
