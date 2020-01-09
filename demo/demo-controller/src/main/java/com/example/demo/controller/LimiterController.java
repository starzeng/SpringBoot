package com.example.demo.controller;

import com.example.demo.LimitType;
import com.example.demo.annotation.Limit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 限流测试:
 * <p>
 * 最大请求数10次/分钟, 每分钟恢复10次请求数
 *
 * @author zengweixiong
 * @date 2020/1/2
 */
@RestController
@RequestMapping("/limiter")
public class LimiterController {

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger();

    /**
     * 自定义key, 60S内最多可以访问10次
     */
    @Limit(key = "test", period = 60, count = 10)
    @GetMapping("/test")
    public Integer test() {
        int i = ATOMIC_INTEGER.incrementAndGet();
        System.err.println(i);
        return i;
    }

    /**
     * ip限流, 60秒10次访问
     */
    @Limit(limitType = LimitType.IP, period = 60, count = 10)
    @GetMapping("/test1")
    public Integer test1() {
        return ATOMIC_INTEGER.incrementAndGet();
    }

}
