package com.west2.test6_4;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.west2.test6_4.mapper")
public class Test64Application {

    public static void main(String[] args) {
        SpringApplication.run(Test64Application.class, args);
    }

}
