package com.example.onlinestorebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.example.onlinestorebackend.controllers" + "com.example.onlinestorebackend.services")

public class OnlineStoreBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineStoreBackendApplication.class, args);
        System.out.println("It's working!");
    }

}
