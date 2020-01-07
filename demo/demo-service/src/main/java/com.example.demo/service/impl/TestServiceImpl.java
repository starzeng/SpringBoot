package com.example.demo.service.impl;

import com.example.demo.dao.TestDao;
import com.example.demo.pojo.TestDO;
import com.example.demo.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zengweixiong
 * @date 2020/1/7
 */
@Service
public class TestServiceImpl implements TestService {

    @Resource
    private TestDao testDao;

    @Override
    public void save(TestDO testDO) {
        testDao.save(testDO);
    }

    @Override
    public TestDO get(Long id) {
        return testDao.get(id);
    }
}
