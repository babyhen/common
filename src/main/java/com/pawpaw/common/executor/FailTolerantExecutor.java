package com.pawpaw.common.executor;

import com.google.common.util.concurrent.RateLimiter;
import com.pawpaw.common.executor.call.ExecutorCall;
import com.pawpaw.common.executor.call.ReturnableExecutorCall;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 连续失败N次就会拒绝执行未来的任何任务。失败的次数可能会大于N次。
 * 特别的：只要达到了N次,就会拒绝执行未来的任何任务。即使执行已经提交的任务成功，把连续失败次数设置成为了0。以后新提交的任务也不会继续执行了。
 */
@Slf4j
public class FailTolerantExecutor {

    private final int maxFailTimes;
    private final AtomicInteger failTimes;
    private final AtomicBoolean rejectTask;

    public FailTolerantExecutor(int maxFailTimes) {
        this.maxFailTimes = maxFailTimes;
        this.failTimes = new AtomicInteger(0);
        this.rejectTask = new AtomicBoolean(false);
    }

    /**
     * @param call 因为支持失败的容错。所以参数使用 没有返回值的ExecutorCall。否则如果带返回值。当失败的时候其实也没啥可返回的
     */
    public void execute(ExecutorCall call) {
        //如果已经到了最大值。则拒绝所有的任务
        boolean reject = this.rejectTask.get();
        if (reject) {
            throw new RuntimeException("reach max fail times " + this.maxFailTimes + ". all tasks commited will be rejected");
        }
        //
        try {
            call.run();
            this.failTimes.set(0);  //成功，则把连续失败数重置
        } catch (Exception e) {
            int currFail = this.failTimes.incrementAndGet();
            log.error("连续第{}次失败，原因:{}", currFail, e.getMessage());
            if (currFail >= this.maxFailTimes) {
                this.rejectTask.set(true);
                throw new RuntimeException(e); //只有达到最大次数才会抛出异常。单线程情况下，防止当前线程抛出异常
            }
        }

    }
}
