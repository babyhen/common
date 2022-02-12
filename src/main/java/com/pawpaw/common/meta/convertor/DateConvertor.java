package com.pawpaw.common.meta.convertor;

import com.pawpaw.common.meta.IConvertor;
import com.pawpaw.common.util.DateTimeUtil;

import java.util.Date;

public final class DateConvertor implements IConvertor<Date> {


    @Override
    public Date convert(String raw) {
        int l = raw.length();
        if (l == 10) {
            return DateTimeUtil.parse10(raw);
        }
        if (l == 8) {
            return DateTimeUtil.parse8(raw);
        }
        if (l == 14) {
            return DateTimeUtil.parse14(raw);
        }
        if (l == 19) {
            return DateTimeUtil.parse19(raw);
        }
        throw new RuntimeException("不支持的日期格式:" + raw);
    }
}

