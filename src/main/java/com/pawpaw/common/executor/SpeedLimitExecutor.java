package com.pawpaw.common.executor;

import com.google.common.util.concurrent.RateLimiter;
import com.pawpaw.common.executor.call.ExecutorCall;
import com.pawpaw.common.executor.call.ReturnNullExecutorCall;
import com.pawpaw.common.executor.call.ReturnableExecutorCall;

public class SpeedLimitExecutor {

    RateLimiter rateLimiter;

    public SpeedLimitExecutor(float rate) {
        //新建一个每秒限制N个的令牌桶
        this.rateLimiter = RateLimiter.create(rate);
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
        //获取令牌，被阻塞住。直到获取令牌
        this.rateLimiter.acquire();
        call.run();
        T t = call.getReturn();
        return t;
    }


}
