package com.algebra.aspect;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCaching
@MapperScan("com.algebra.aspect.mapper")
@ComponentScan
public class AspectDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AspectDemoApplication.class, args);
    }

}
