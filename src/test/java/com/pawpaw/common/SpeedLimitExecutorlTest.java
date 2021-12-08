package com.pawpaw.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pawpaw.common.executor.SpeedLimitExecutor;
import com.pawpaw.common.executor.call.ExecutorCall;
import com.pawpaw.common.util.DateTimeUtil;
import org.junit.Test;

import java.util.Date;

public class SpeedLimitExecutorlTest {
    ExecutorCall call = new ExecutorCall() {
        @Override
        public void call() {
            System.out.println(DateTimeUtil.format19(new Date()));
        }
    };

    @Test
    public void execute() throws JsonProcessingException {
        SpeedLimitExecutor executor = new SpeedLimitExecutor(5f);
        for (int i = 0; i < 10; i++) {
            executor.execute(call);
        }
    }
}



