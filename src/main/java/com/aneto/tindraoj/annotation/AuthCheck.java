package com.aneto.tindraoj.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * AuthCheck
 * 权限检查注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {

    /**N
     * 必须有某个角色
     *
     * @return
     */
    String mustRole() default "";

}

