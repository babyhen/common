package com.pawpaw.common.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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


    public void execute(AsyncCal call) {

        this.executor.execute(new Runnable() {

            @Override
            public void run() {
                if (call == null) {
                    return;
                }
                try {
                    long start = System.currentTimeMillis();
                    call.call();
                    long spend = System.currentTimeMillis() - start;
                    log.debug("async task spend time {}", spend);
                } catch (Exception e) {
                    call.onException(e);
                }

            }
        });

    }

    public interface AsyncCal {

        public void call();

        public void onException(Exception e);
    }

}
