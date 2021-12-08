package com.pawpaw.common.executor.call;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface ExecutorCall extends ReturnableExecutorCall {
    /**
     * 简单的，不反悔任何值
     *
     * @return
     */
    @Override
    default Object getReturn() {
        return null;
    }

}
