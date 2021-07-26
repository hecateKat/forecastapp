package com.kat.forecastapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ForecastappApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForecastappApplication.class, args);
    }

}
