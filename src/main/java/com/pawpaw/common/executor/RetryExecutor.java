package com.pawpaw.common.executor;

import com.pawpaw.common.executor.call.ReturnableExecutorCall;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * 重试的执行器
 * 重试maxTryTime次。一共执行maxTryTime+1次
 */
@AllArgsConstructor
@Slf4j
public class RetryExecutor {
    private final long maxTryTime;

    public <T> T execute(ReturnableExecutorCall<T> call) {

        for (int retryTime = 0; retryTime <= this.maxTryTime; retryTime++) {
            try {
                call.run();
                T t = call.getReturn();
                return t;
            } catch (Throwable e) {
                if (retryTime >= this.maxTryTime) {
                    throw new RuntimeException(e);
                } else {
                    log.error("跳过此异常:{},当前重试次数:{},最大重试次数:{}", e.getMessage(), retryTime, maxTryTime);
                    continue;
                }


            }

        }
        //never reach here
        //never reach here
        throw new RuntimeException();
    }


}
