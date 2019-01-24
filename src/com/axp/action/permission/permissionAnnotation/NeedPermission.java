package com.axp.action.permission.permissionAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2016/7/12 0012.
 * <p>
 * 此标签用在方法上，表示当前方法的访问需要权限；
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedPermission {

    //权限本身的名称；
    String permissionName();

    //权限分类的名称；
    String classifyName();
}
