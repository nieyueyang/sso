package com.deyun.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.deyun.**"})
@SpringBootApplication
public class SsoApplication{

    public static void main(String[] args) {
        SpringApplication.run(SsoApplication.class, args);
    }

}
