package com.pawpaw.common.concurrent;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

@Slf4j
public abstract class AbsConcurrentCall<V> implements  Callable<V> {

    private CountDownLatch cdl;

    protected void setCdl(CountDownLatch cdl) {
        this.cdl = cdl;
    }

    @Override
    public V call() throws Exception {
        try {
            V v = this.doCall();
            return v;
        } catch (Throwable t) {
            log.error("exception happends,{}", t.getMessage());
            throw new RuntimeException(t);
        } finally {
            this.cdl.countDown();
        }

    }

    public abstract V doCall();

}