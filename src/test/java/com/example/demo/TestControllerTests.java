package com.example.demo;


import com.example.demo.web.TestController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class) //让测试运行于 Spring 测试环境
@SpringBootTest(classes = TestController.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT) //指定 Spring Boot 程序的测试引导入口

public class TestControllerTests {

    @Autowired
    private TestRestTemplate restTemplate; //用于测试 REST 接口的模板

    @Test
    public void testHome(){
        ResponseEntity<String> entity = this.restTemplate.getForEntity("/test/test", String.class);
        assert entity.getStatusCode().equals(HttpStatus.OK);
        System.out.printf(entity.toString());

    }
}
