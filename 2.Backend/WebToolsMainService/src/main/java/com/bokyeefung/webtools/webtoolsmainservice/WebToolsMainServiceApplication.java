package com.bokyeefung.webtools.webtoolsmainservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages ={"com.bokyeefung.webtools"})
@MapperScan("com.bokyeefung.webtools.webtoolsmainservice.common.dao.mapper")
public class WebToolsMainServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebToolsMainServiceApplication.class, args);
    }
}