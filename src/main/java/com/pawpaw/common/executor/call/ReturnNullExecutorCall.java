package com.pawpaw.common.executor.call;

public interface ReturnNullExecutorCall extends ReturnableExecutorCall<Object> {

    @Override
    default Object getReturn() {
        return null;
    }

}
