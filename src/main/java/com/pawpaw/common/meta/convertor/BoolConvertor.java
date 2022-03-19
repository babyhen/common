package com.pawpaw.common.meta.convertor;

import com.pawpaw.common.meta.IConvertor;

public final class BoolConvertor implements IConvertor<Boolean> {


    @Override
    public Boolean convert(String raw,Class type) {
       if("true".equalsIgnoreCase(raw)){
           return  true ;
       }
       if("false".equalsIgnoreCase(raw)){
           return  false;
       }
       throw new IllegalArgumentException("无法转换成布尔类型:"+raw);
    }
}

