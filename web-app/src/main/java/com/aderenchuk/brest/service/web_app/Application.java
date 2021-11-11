package com.aderenchuk.brest.service.web_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.aderenchuk.brest")
@PropertySource({"classpath:dao.properties"})
@EntityScan("com.aderenchuk.brest")
@EnableJpaRepositories("com.aderenchuk.brest")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}