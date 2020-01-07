package com.example.demo.dao;

import com.example.demo.pojo.TestDO;

/**
 * @author zengweixiong
 * @date 2020/1/7
 */
public interface TestDao {
    void save(TestDO testDO);

    TestDO get(Long id);
}
