package com.pawpaw.common.meta;

import lombok.*;

@RequiredArgsConstructor
@Getter
@ToString
public class ParamInfo {
    //参数的位置，从0开始
    private final int position;
    private final String name;
    private final String defaultValue;
    private final Class type;
    private final String desc;
}
