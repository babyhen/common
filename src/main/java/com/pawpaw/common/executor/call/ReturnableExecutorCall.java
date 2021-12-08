package com.pawpaw.common.executor.call;

public interface ReturnableExecutorCall<T> extends ExecutorCall {
    /**
     * 拿到返回值
     *
     * @return
     */
    T getReturn();

}
