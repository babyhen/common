package com.pawpaw.common.meta;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
    @ToString.Exclude
    protected final AbstractParamInfo parent;
    @Nullable
    protected final DefaultValue defaultValueAnnotation;
    @Nullable
    protected final DefaultValues defaultValues;

    /**
     * 得到这个字段的默认值
     * 从父节点依次往下查找，找到设置的默认值
     *
     * @return
     */
    public String getDefaultValue() {
        List<AbstractParamInfo> parents = this.getParentChain();
        //从最顶级的节点开始查找，有没有设置（重写）该对象的默认值，如果有。那么则获取该值，然后返回
        while (!parents.isEmpty()) {
            AbstractParamInfo curr = parents.get(0);
            //把所有默认值的注解都找出来
            DefaultValue dfv = curr.getDefaultValueAnnotation();
            DefaultValues dfvs = curr.getDefaultValues();
            List<DefaultValue> allDefaultValue = new LinkedList<>();
            if (dfv != null) {
                allDefaultValue.add(dfv);
            }
            if (dfvs != null) {
                allDefaultValue.addAll(Arrays.asList(dfvs.value()));
            }
            //计算好目标要匹配的path
            List<String> path = parents.stream().map(AbstractParamInfo::getName).collect(Collectors.toList());
            //去掉父节点当前的名字,并且加上自己的名字
            path.remove(0);
            path.add(this.name);
            String toMatchPath = StringUtils.join(path, ".");
            //遍历所有的指定的设置，如果满足，则直接返回了。
            for (DefaultValue tmpDV : allDefaultValue) {
                String specifiedPath = tmpDV.path();
                if (StringUtils.equalsIgnoreCase(specifiedPath, toMatchPath)) {
                    //找到了，则返回该值
                    return tmpDV.value();
                }
            }


            //把第一个节点删掉，从下一级继续
            parents.remove(0);
        }
        //如果父节点没有。那么查看自己有没有
        if (this.defaultValueAnnotation != null) {
            return this.defaultValueAnnotation.value();
        }
        //都没有则返回空字符串
        return "";
    }

    /**
     * 得到直接的下一级带注解的field
     *
     * @return
     */
    public abstract List<AbstractParamInfo> getFields();

    /**
     * 得到当前这个对象的 parent引用链（由上往下的引用，即   grandpa->father->son）
     *
     * @return 由上往下的引用
     */
    public List<AbstractParamInfo> getParentChain() {
        List<AbstractParamInfo> r = new LinkedList<>();
        AbstractParamInfo curr = this;
        while (curr.parent != null) {
            r.add(curr.parent);
            curr = curr.parent;
        }
        //重新按照由上往下的顺序排列
        Collections.reverse(r);
        return r;
    }

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
