package com.aderenchuk.brest.service.rest_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = "com.aderenchuk.brest")
@PropertySource({"classpath:dao.properties"})
@EntityScan("com.aderenchuk.brest")
@EnableJpaRepositories("com.aderenchuk.brest")
public class ApplicationRest extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRest.class, args);
    }

}
