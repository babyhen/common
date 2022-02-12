package com.pawpaw.common.meta.convertor;

import com.pawpaw.common.meta.IConvertor;

public final class LongConvertor implements IConvertor<Long> {


    @Override
    public Long convert(String raw) {
        return Long.parseLong(raw);
    }
}

