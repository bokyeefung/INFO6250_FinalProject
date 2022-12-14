package com.bokyeefung.webtools.webtoolsmainservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bokyeefung.webtools.webtoolsmainservice.dao.mapper")
public class WebToolsMainServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebToolsMainServiceApplication.class, args);
    }
}