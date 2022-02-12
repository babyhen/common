package com.pawpaw.common.meta.convertor;

import com.pawpaw.common.meta.IConvertor;

public final class IntConvertor implements IConvertor<Integer> {


    @Override
    public Integer convert(String raw,Class type) {
        return Integer.parseInt(raw);
    }
}

