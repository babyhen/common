package com.pawpaw.common.concurrent;

import lombok.AllArgsConstructor;

import java.util.concurrent.Callable;

/**
 * 重试的执行器
 * 重试maxTryTime次。一共执行maxTryTime+1次
 */
@AllArgsConstructor
public class RetryExecutor {
    private final long maxTryTime;

    public <T> T execute(Callable<T> call) {

        for (int retryTime = 0; retryTime <= this.maxTryTime; retryTime++) {
            try {
                T t = call.call();
                return t;
            } catch (Throwable e) {
                if (retryTime >= this.maxTryTime) {
                    throw new RuntimeException(e);
                }
                continue;

            }

        }
        throw new RuntimeException();
    }


}
