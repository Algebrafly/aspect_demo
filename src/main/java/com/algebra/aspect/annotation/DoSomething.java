package com.algebra.aspect.annotation;

import java.lang.annotation.*;

/**
 * @author al
 * @date 2019/6/10 20:11
 * @description
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DoSomething {

    String key() default "";
    String cacheName() default "";
    boolean needLog() default true;

}
