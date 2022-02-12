package com.pawpaw.common.meta.convertor;

import com.pawpaw.common.meta.IConvertor;

public final class StringConvertor implements IConvertor<String> {


    @Override
    public String convert(String raw,Class type) {
        if (raw == null) {
            return "";
        }
        return raw;
    }
}

