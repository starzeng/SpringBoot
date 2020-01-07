package com.example.demo.redis.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.pojo.TestDO;
import com.example.demo.redis.TestRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author zengweixiong
 * @date 2020/1/7
 */
@Service
public class TestRedisImpl implements TestRedis {

    /**
     * redis key的前缀
     */
    private static final String TEST_KEY_PREFIX = "test:";

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public TestDO get(Long id) {
        JSONObject jsonObject = (JSONObject) redisTemplate.opsForValue().get(TEST_KEY_PREFIX + id);
        assert jsonObject != null;
        return jsonObject.toJavaObject(TestDO.class);
    }

    @Override
    public void save(TestDO testDO) {
        redisTemplate.opsForValue().set(TEST_KEY_PREFIX + testDO.getId(), testDO);
    }
}
