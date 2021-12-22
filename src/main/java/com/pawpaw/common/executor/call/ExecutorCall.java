package com.pawpaw.common.executor.call;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface ExecutorCall extends Runnable {
    /**
     * 执行主要的逻辑
     */
    void call();


    @Override
    default void run() {
        try {
            long start = System.currentTimeMillis();
            this.call();
            long spend = System.currentTimeMillis() - start;
            Logger log = LoggerFactory.getLogger(this.getClass());
            log.debug("async task spend time {}", spend);
        } catch (Exception e) {
            this.onException(e);
        }
    }

    default void onException(Exception e) {
        throw new RuntimeException(e);
    }

}
