package org.example.bstest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("org.example.bstest.demos.web.mapper.mysql")
@EnableAsync
public class BsTestApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(BsTestApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
