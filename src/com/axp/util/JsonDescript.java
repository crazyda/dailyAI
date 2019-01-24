/**
 * 
 */
package com.axp.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* 
 * 定义注解 Test 
 * 首先使用ElementType.TYPE
 * 运行级别定为 运行时，以便后面测试解析
 */ 
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonDescript {
	boolean isShow() default  false;//是否显示
	String dateFormat() default "";//时间格式
}
