package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 多模块 Demo Controller 测试
 */
@MapperScan({"com.example.demo.dao"})
@SpringBootApplication
public class DemoControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoControllerApplication.class, args);
    }

}
