package com.aderenchuk.brest.service.impl;

import com.aderenchuk.brest.dao.jdbc.TourDaoJdbc;
import com.aderenchuk.brest.model.Tour;
import com.aderenchuk.brest.service.DummyService;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Locale;
import java.util.Optional;

@Service
public class DummyServiceImpl implements DummyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourDaoJdbc.class);

    private Faker faker;

    @PostConstruct
    private void fill() {
        this.faker = generateFaker();
    }

    private Faker generateFaker() {
        return new Faker(new Locale("en-US"));
    }

    @Override
    public Optional<Tour> getDummyTourFindById(Integer tourId) {
        return Optional.of(new Tour(tourId, faker.country().name(), faker.date().birthday()));
    }
}
