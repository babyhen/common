package com.pawpaw.common;

import com.pawpaw.common.util.ByteUtil;
import org.junit.Test;

public class ByteUtilTest {


    @Test
    public void convert() {
        byte[] bytes = ByteUtil.hexToByte(Integer.toHexString(192));
        String m = ByteUtil.byteToHex(bytes);
        System.out.println(m);
    }
}



