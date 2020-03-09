package com.aynu.data.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.aynu.data.web.core.adminDAO")
@ComponentScan(basePackages = {"com.aynu.data.web","com.aynu.data.common"})
public class WebApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("安阳师范学院-软件学院-数据库一班-张玥-104804109-毕业设计启动成功");
    }
}
