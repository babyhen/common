package com.pawpaw.common.meta;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParamInfo {
    private String name;

    private String defaultValue;
    private Class type;

}
