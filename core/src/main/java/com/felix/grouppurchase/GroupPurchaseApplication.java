package com.felix.grouppurchase;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.felix.grouppurchase.mapper")
public class GroupPurchaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroupPurchaseApplication.class, args);
    }
}
