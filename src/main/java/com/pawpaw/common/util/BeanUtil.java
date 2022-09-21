package com.pawpaw.common.util;

import com.pawpaw.common.json.JsonUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanUtil {
    /**
     * 把一个字符串“折叠”成一个对象
     *
     * @param rawStr {"volParam.minVolRate":"1","volParam.maxVolRate":"2","totalDayNum":"30","daYangFlag":"5"}
     * @return
     */
    public static Map<String, Object> fold(String rawStr, CharSequence keySeparator) {
        Map<String, Object> r = new HashMap<>();
        Map<String, Object> strMap = JsonUtil.json2Object(rawStr, Map.class);
        for (String key : strMap.keySet()) {
            Object value = strMap.get(key);
            List<String> subKeys = StringUtils.split(key, keySeparator);
            //依次遍历，找到对应的节点设置进去
            Map<String, Object> curr = r;
            for (int i = 0; i < subKeys.size(); i++) {
                String subKey = subKeys.get(i);
                //如果是最末级的字段。那么直接设置成值
                if (i == subKeys.size() - 1) {
                    curr.put(subKey, value);
                } else {
                    Object isExist = curr.get(subKey);
                    if (isExist == null) {
                        isExist = new HashMap<String, Object>();
                        curr.put(subKey, isExist);
                    }
                    curr = (HashMap) isExist;
                }
            }
        }
        return r;
    }

}
