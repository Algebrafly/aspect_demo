package com.algebra.aspect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author al
 * @date 2019/12/6 9:10
 * @description 自定义注解，拦截请求接口，限制请求次数
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AccessLimit {
    /**
     * 请求限制失效时间
     */
    int seconds();

    /**
     * 请求限制最大次数
     */
    int maxCount();

    /**
     * 是否需要登录限制
     */
    boolean needLogin() default true;
}
