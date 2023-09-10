package com.example.rentingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RentingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentingSystemApplication.class, args);
    }

}
