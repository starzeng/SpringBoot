package com.example.demo.aop;

import com.example.demo.LimitType;
import com.example.demo.annotation.LimiterBucket;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 限流令牌桶拦截器
 *
 * @author zengweixiong
 * @date 2020/1/2
 */
@Slf4j
@Aspect
@Component
public class LimitBucketInterceptor {

    private static final AtomicInteger ATOMIC_BUCKET_INTEGER = new AtomicInteger();

    private static final String UNKNOWN = "unknown";

    /**
     * 存放令牌桶 防止每次重新创建令牌桶
     */
    private Map<String, RateLimiter> limitMap = Maps.newConcurrentMap();

    @Around(value = "@annotation(com.example.demo.annotation.LimiterBucket)")
    public Object interceptor(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        LimiterBucket limitAnnotation = method.getAnnotation(LimiterBucket.class);
        LimitType limitType = limitAnnotation.limitType();
        double permitsPerSecond = limitAnnotation.permitsPerSecond();
        long timeout = limitAnnotation.timeout();
        TimeUnit timeunit = limitAnnotation.timeunit();
        String message = limitAnnotation.message();
        String key = getKey(limitAnnotation, limitType, method);
        String keys = StringUtils.join(limitAnnotation.prefix(), key);
        try {
            RateLimiter limiter = null;
            // 判断是否存在该限流的key值
            if (!limitMap.containsKey(keys)) {
                // 创建令牌桶
                limiter = RateLimiter.create(permitsPerSecond);
                limitMap.put(keys, limiter);
                log.error("<<================= {},创建令牌桶,容量{} 成功!!!", keys, permitsPerSecond);
            }
            // 获取令牌
            limiter = limitMap.get(keys);
            boolean acquire = limiter.tryAcquire(timeout, timeunit);
            if (!acquire) {
                System.out.println(ATOMIC_BUCKET_INTEGER.incrementAndGet() + "==" + message);
                return message;
            }
            System.out.println(ATOMIC_BUCKET_INTEGER.incrementAndGet() + "==" + "通过----");
            return pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return message;
    }


    /**
     * 获取IP地址
     *
     * @return IP
     */
    public String getIpAddress() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取key
     */
    private String getKey(LimiterBucket limitAnnotation, LimitType limitType, Method method) {
        String key;
        switch (limitType) {
            case IP:
                key = getIpAddress();
                break;
            case CUSTOMER:
                key = limitAnnotation.key();
                break;
            default:
                key = StringUtils.upperCase(method.getName());
        }
        return key;
    }

}
