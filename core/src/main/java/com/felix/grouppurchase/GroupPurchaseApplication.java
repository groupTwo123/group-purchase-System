package com.felix.grouppurchase;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(value = "com.felix.grouppurchase.mapper")
//开启事务管理
@EnableTransactionManagement
public class GroupPurchaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroupPurchaseApplication.class, args);
    }
}
