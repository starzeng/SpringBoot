package com.example.demo.controller;

import com.example.demo.pojo.TestDO;
import com.example.demo.redis.TestRedis;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zengweixiong
 * @date 2020/1/7
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private TestRedis testRedis;

    /**
     * 测试是否搭建成功
     *
     * @return String
     */
    @GetMapping("/hello")
    public String hello() {
        return "Hello World!!";
    }

    /**
     * 测试数据库存储
     *
     * @return String
     */
    @GetMapping("/save")
    public String save() {
        TestDO testDO = new TestDO();
        testDO.setName("test001");
        testDO.setAge(25);
        testService.save(testDO);
        return "success";
    }

    /**
     * 测试数据库获取
     *
     * @param id Long
     * @return {@link TestDO}
     */
    @GetMapping("/get/{id}")
    public TestDO get(@PathVariable("id") Long id) {
        return testService.get(id);
    }

    /**
     * 测试redis存储
     *
     * @return String
     */
    @GetMapping("/redis/save")
    public String redisSave() {
        TestDO testDO = new TestDO();
        testDO.setId(3L);
        testDO.setName("test003");
        testDO.setAge(88);
        testRedis.save(testDO);
        return "success";
    }

    /**
     * 测试获取redis存储的值
     *
     * @param id test对象id
     * @return test对象
     */
    @GetMapping("/redis/get/{id}")
    public TestDO redisGet(@PathVariable("id") Long id) {
        return testRedis.get(id);
    }


}
