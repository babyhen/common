package com.pawpaw.common.concurrent;

import java.util.concurrent.Callable;

public interface SpeedLimitCall<T> {

    public T call();


}
