package com.example.demo.controller;

import com.example.demo.pojo.TestDO;
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

    @GetMapping("/hello")
    public String hello(){
        return "Hello World!!";
    }

    @GetMapping("/save")
    public String save(){
        TestDO testDO = new TestDO();
        testDO.setName("test001");
        testDO.setAge(25);
        testService.save(testDO);
        return "success";
    }

    @GetMapping("/get/{id}")
    public TestDO get(@PathVariable("id") Long id){
        return testService.get(id);
    }

}
