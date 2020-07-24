package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.example.demo.mapper")
//MapperScan 自动扫描所有 Mybatis Mapper 文件，在这里扫描的是无 XML 形式的， XML形式的扫描与其大致相同。
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
