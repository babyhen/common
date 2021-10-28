package com.pawpaw.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pawpaw.common.concurrent.SpeedLimitCall;
import com.pawpaw.common.concurrent.SpeedLimitExecutor;
import com.pawpaw.common.util.BrowserUtil;
import com.pawpaw.common.util.DateTimeUtil;
import org.junit.Test;

import java.util.Date;

public class SpeedLimitExecutorlTest {


    @Test
    public void execute() throws JsonProcessingException {
        SpeedLimitExecutor executor = new SpeedLimitExecutor(5f);
        for (int i = 0; i < 10; i++) {
            executor.execute(new SpeedLimitCall<Object>() {
                @Override
                public Object call() {
                    System.out.println(DateTimeUtil.format19(new Date()));
                    return null;
                }
            });
        }
    }
}



