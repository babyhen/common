package com.pawpaw.common.meta.convertor;

import com.pawpaw.common.meta.IConvertor;

public final class DefaultConvertor implements IConvertor<String> {


    @Override
    public String convert(String raw) {
        if (raw == null) {
            return "";
        }
        return raw;
    }
}

