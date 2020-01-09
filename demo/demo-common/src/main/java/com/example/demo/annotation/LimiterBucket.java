package com.example.demo.annotation;

import com.example.demo.LimitType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

import static com.example.demo.LimitType.IP;

/**
 * @author zengweixiong
 * @date 2020/1/2
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LimiterBucket {

    /**
     * 以固定数值往令牌桶添加令牌
     */
    double permitsPerSecond();

    /**
     * 获取令牌最大等待时间(timeunit时间单位, 默认毫秒)
     */
    long timeout();

    /**
     * 单位(例:分钟/秒/毫秒) 默认:毫秒
     */
    TimeUnit timeunit() default TimeUnit.MILLISECONDS;

    /**
     * 无法获取令牌返回提示信息 默认值可以自行修改
     */
    String message() default "系统繁忙,请稍后再试.";

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
     * 类型:  CUSTOMER 自定义key; IP 根据请求者IP
     *
     * @return LimitType {@link LimitType}
     */
    LimitType limitType() default IP;

}
