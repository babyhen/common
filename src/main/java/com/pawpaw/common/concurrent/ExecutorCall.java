package com.pawpaw.common.concurrent;

import java.util.concurrent.Callable;

public interface ExecutorCall<T> {

    void call();

    default void onException(Exception e) {
        throw new RuntimeException(e);
    }

}
