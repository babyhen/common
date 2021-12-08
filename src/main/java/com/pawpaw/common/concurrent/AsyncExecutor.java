package com.pawpaw.common.concurrent;

import com.pawpaw.common.concurrent.call.ExecutorCall;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 异步的执行任务
 *
 * @Auther: liujixin
 * @Date: 2018-12-25
 */
@Slf4j
public class AsyncExecutor {


    private final ExecutorService executor;

    public static AsyncExecutor ofSingleThreadExecutor() {
        return new AsyncExecutor(Executors.newSingleThreadExecutor());
    }

    public AsyncExecutor(ExecutorService executor) {
        this.executor = executor;
    }


    public void execute(ExecutorCall call) {
        this.executor.execute(call);
    }


}
