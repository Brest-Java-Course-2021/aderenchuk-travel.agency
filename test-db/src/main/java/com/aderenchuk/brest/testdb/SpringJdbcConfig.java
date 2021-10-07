package com.aderenchuk.brest.testdb;


import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("com.aderenchuk")
public class SpringJdbcConfig {

    @Bean
    Faker faker() {
        return new Faker();
    }
}
