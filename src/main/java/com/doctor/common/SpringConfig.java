package com.doctor.common;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author doctor
 *
 * @time 2015年5月8日 下午4:52:00
 * 
 *       用于jstorm结合spring，注解jstorm组件，提供spring配置文件
 * 
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SpringConfig {
	Class<?> value();
}
