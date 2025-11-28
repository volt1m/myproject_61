package com.cuit.house;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync//开启异步框架
public class HouseApplication {
    public static void main(String[] args) {
        SpringApplication.run(HouseApplication.class,args);
    }
}
