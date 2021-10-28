package com.pawpaw.common.concurrent;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SpeedLimitExecutor {

    RateLimiter rateLimiter;

    public SpeedLimitExecutor(float rate) {
        //新建一个每秒限制N个的令牌桶
        this.rateLimiter = RateLimiter.create(rate);
    }


    public <T> T execute(SpeedLimitCall<T> call) {
        //获取令牌，被阻塞住。直到获取令牌
        this.rateLimiter.acquire();
        T t = call.call();
        return t;
    }
}
