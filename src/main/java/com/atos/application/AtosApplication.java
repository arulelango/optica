package com.atos.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.atos")
        public class AtosApplication {
    public static void main(String[] args) {
        SpringApplication.run(AtosApplication.class);
    }
}