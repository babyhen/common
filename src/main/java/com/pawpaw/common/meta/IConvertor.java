package com.pawpaw.common.meta;

import java.lang.annotation.*;

/**
 * 把字符串转换成目标类型的数据对象
 */
public interface IConvertor<R> {

    R convert(String raw);

}

