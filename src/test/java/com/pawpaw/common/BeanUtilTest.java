package com.pawpaw.common;

import com.pawpaw.common.json.JsonUtil;
import com.pawpaw.common.util.BeanUtil;
import com.pawpaw.common.util.JarUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BeanUtilTest {

    @Test
    public void fold() throws IOException {
        Map<String, String> raw = new HashMap<>();
        raw.put("volParam.minVolRate", "1");
        raw.put("volParam.maxVolRate", "2");
        raw.put("totalDayNum", "30");
        raw.put("daYangFlag", "5");
        String rawStr = JsonUtil.object2Json(raw);
        System.out.println(rawStr);
        Map<String, Object> folded = BeanUtil.fold(rawStr,".");
        System.out.println(folded);
    }


}



