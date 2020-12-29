package com.atguigu.edu_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = {"com.atguigu"})
@EnableSwagger2
public class EduSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduSpringApplication.class,args);
    }
}
