package com.aderenchuk.brest.service.rest.config;

import com.aderenchuk.brest.service.rest.TourDtoServiceRest;
import com.aderenchuk.brest.service.rest.TourServiceRest;
import com.aderenchuk.brest.service.TourDtoService;
import com.aderenchuk.brest.service.TourService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TestConfig {

    public static final String TOUR_DTOS_URL = "http://localhost:8088/tour-dtos";
    public static final String TOURS_URL = "http://localhost:8088/tours";

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate(new SimpleClientHttpRequestFactory());
    }

    @Bean
    TourDtoService tourDtoService() {
        return new TourDtoServiceRest(TOUR_DTOS_URL, restTemplate());
    }

    @Bean
    TourService tourService() {
        return new TourServiceRest(TOURS_URL, restTemplate());
    }
}
