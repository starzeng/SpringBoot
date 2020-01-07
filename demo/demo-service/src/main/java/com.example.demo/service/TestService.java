package com.example.demo.service;

import com.example.demo.pojo.TestDO;

/**
 * @author zengweixiong
 * @date 2020/1/7
 */
public interface TestService {
    void save(TestDO testDO);

    TestDO get(Long id);
}
