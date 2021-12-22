package com.pawpaw.common.executor.call;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface ReturnableExecutorCall<T> extends ExecutorCall {

    /**
     * 拿到返回值
     *
     * @return
     */
    T getReturn();

}
