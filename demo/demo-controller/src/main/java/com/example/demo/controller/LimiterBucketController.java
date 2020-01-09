package com.example.demo.controller;

import com.example.demo.annotation.LimiterBucket;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 令牌桶限流
 *
 * @author zengweixiong
 * @date 2020/1/2
 */
@RestController
@RequestMapping("/limiter/bucket")
public class LimiterBucketController {
    private static final AtomicInteger ATOMIC_BUCKET_INTEGER = new AtomicInteger();

    /**
     * 令牌桶测试
     *
     * 每秒添加10个令牌, 令牌获取时间为100毫秒
     */
    @LimiterBucket(permitsPerSecond = 10, timeout = 100)
    @GetMapping("/test")
    public String test() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "" + ATOMIC_BUCKET_INTEGER.incrementAndGet();
    }

}
