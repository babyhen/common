package com.pawpaw.common.meta.convertor;

import com.pawpaw.common.meta.IConvertor;

import java.util.Date;

public final class AutoConvertor implements IConvertor<Object> {

    @Override
    public Object convert(String raw, Class type) {
        if (type == String.class) {
            return new StringConvertor().convert(raw, type);
        }
        if (type == Boolean.class || type == Boolean.TYPE) {
            return new BoolConvertor().convert(raw, type);
        }
        if (type == Date.class) {
            return new DateConvertor().convert(raw, type);
        }
        if (type == Integer.class || type == Integer.TYPE) {
            return new IntConvertor().convert(raw, type);
        }
        if (type == Long.class || type == Long.TYPE) {
            return new LongConvertor().convert(raw, type);
        }
        if (type == Float.class || type == Float.TYPE) {
            return new FloatConvertor().convert(raw, type);
        }
        throw new RuntimeException("无法识别的类型:" + type);
    }
}

