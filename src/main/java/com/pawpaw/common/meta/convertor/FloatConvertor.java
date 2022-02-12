package com.pawpaw.common.meta.convertor;

import com.pawpaw.common.meta.IConvertor;

public final class FloatConvertor implements IConvertor<Float> {


    @Override
    public Float convert(String raw) {
        return Float.parseFloat(raw);
    }
}

