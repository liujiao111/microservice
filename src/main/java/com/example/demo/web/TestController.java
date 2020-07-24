package com.example.demo.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(value = "/test")
    public String test() {
        System.out.printf("Hello Spring Boot");
        //throw new NullPointerException("控制组啦");
        return "Hello Spring Boot";
    }
}
