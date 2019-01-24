package com.axp.action.permission.permissionAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2016/7/12 0012.
 * <p>
 * 此标签表示贴有此标签的方法是菜单项；
 * 规则：
 * 1，二级菜单一定要有，没有报错，并忽略此标签；
 * 2，一级菜单一定要有，没有报错，并忽略此标签，如果有，但是数据库中没有，就创建一个；
 * 3，二级菜单的链接名称，如果有，就以此为准，如果没有，就从方法和类上的requestMapping标签值中拼凑出来；
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IsItem {

    //二级菜单的名称；
    String secondItemName();

    //二级菜单对应的一级菜单的名称；
    String firstItemName();

    //此二级菜单对应的链接名称；
    String linkName() default "";
}
