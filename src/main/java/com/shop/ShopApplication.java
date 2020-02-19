package com.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;

@SpringBootApplication
@MapperScan("com.shop.mapper")
public class ShopApplication {

    public static void main(String[] args) throws ParseException {
        SpringApplication.run(ShopApplication.class, args);
    }

}
