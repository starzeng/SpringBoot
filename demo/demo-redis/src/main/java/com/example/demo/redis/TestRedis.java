package com.example.demo.redis;

import com.example.demo.pojo.TestDO;

/**
 * @author zengweixiong
 * @date 2020/1/7
 */
public interface TestRedis {

    /**
     * 获取redis中的值
     * @param id test对象ID
     * @return test对想
     */
    TestDO get(Long id);

    /**
     * 存储test对象
     *
     * @param testDO test对象
     */
    void save(TestDO testDO);
}
