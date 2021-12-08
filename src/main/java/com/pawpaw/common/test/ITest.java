package com.pawpaw.common.test;

import com.pawpaw.common.util.ArrayUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public interface ITest {


    default void doSomeThing(int executeTimeInSecond) {
        try {
            Thread.sleep(executeTimeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 概率性的失败
     *
     * @param rate 概率， 0.4代表  40%的概率
     */
    default void randomFail(float rate) {
        int flag = (int) (rate * 100);
        Random random = new Random();
        int v = random.nextInt(100);
        if (v < flag) {
            throw new RuntimeException("触发随机异常" + v);
        }
    }


}




