package com.aderenchuk.brest.service.soap_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan(basePackages = "com.aderenchuk.brest")
@PropertySource({"classpath:dao.properties"})
public class ApplicationSoap {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationSoap.class, args);
    }

}