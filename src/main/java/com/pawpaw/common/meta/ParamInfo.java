package com.pawpaw.common.meta;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParamInfo {
    //参数的位置，从0开始
    private int position;
    private String name;
    private String defaultValue;
    private Class type;
    private String desc;

}
