package com.example.demo.annotation;

/**
 * @author zengweixiong
 * @date 2020/1/2
 */

import com.example.demo.LimitType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.example.demo.LimitType.IP;

/**
 * 限流
 *
 * @author zengweixiong
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Limit {

    /**
     * Key的prefix
     *
     * @return String
     */
    String prefix() default "limiter:";

    /**
     * 资源的key
     *
     * @return String
     */
    String key() default "";

    /**
     * 给定的时间段单位(秒), 默认60秒
     *
     * @return int
     */
    int period() default 60;

    /**
     * 最多的访问限制次数, 默认10
     *
     * @return int 
     */
    int count() default 10;

    /**
     * 类型:  CUSTOMER 自定义key; IP 根据请求者IP
     *
     * @return LimitType {@link LimitType}
     */
    LimitType limitType() default IP;

}
