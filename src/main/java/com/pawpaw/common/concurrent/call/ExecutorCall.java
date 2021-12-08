package com.pawpaw.common.concurrent.call;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public interface ExecutorCall<T> extends Runnable {

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
