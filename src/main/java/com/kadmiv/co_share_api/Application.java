package com.kadmiv.co_share_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

//@EnableAuthorizationServer
@SpringBootApplication
@ComponentScan(
        {
//                "com.kadmiv.co_share_api.configuration",
                "com.kadmiv.co_share_api.controllers",
//                "com.kadmiv.co_share_api.components",
//                "com.kadmiv.co_share_api.services",
                "com.kadmiv.co_share_api.repo",
        }
)
@EntityScan({"com.kadmiv.co_share_api.models"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
