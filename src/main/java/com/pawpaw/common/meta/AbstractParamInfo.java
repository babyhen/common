package com.pawpaw.common.meta;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

/**
 * 最基础的参数信息
 */
@RequiredArgsConstructor
@Getter
@ToString
public abstract class AbstractParamInfo {

    protected final String name;
    protected final Class type;
    protected final String desc;

    /**
     * @return
     */
    public abstract String getDefaultValue();

    /**
     * 得到直接的下一级带注解的field
     *
     * @return
     */
    public abstract List<AbstractParamInfo> getFields();

    /**
     * 递归得到当前对象下的所有的带注解的最终field
     *
     * @return
     */
    public List<AbstractParamInfo> getRecursionPrimaryFields() {
        List<AbstractParamInfo> r = new LinkedList<>();
        List<AbstractParamInfo> fields = this.getFields();
        for (AbstractParamInfo pi : fields) {
            List<AbstractParamInfo> child = pi.getFields();
            //说明已经是最底层的基础类型了
            if (child == null || child.isEmpty()) {
                r.add(pi);
            } else {
                //说明该字段是个符合字段，还没到根（基础类型）
                r.addAll(pi.getRecursionPrimaryFields());
            }

        }

        return r;
    }
}
