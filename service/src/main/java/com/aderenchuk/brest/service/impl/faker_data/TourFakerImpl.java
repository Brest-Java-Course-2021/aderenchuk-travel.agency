package com.aderenchuk.brest.service.impl.faker_data;

import com.aderenchuk.brest.model.Tour;
import com.aderenchuk.brest.service.TourFakerService;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Transactional
public class TourFakerImpl implements TourFakerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TourFakerImpl.class);


    private Faker faker;

    public TourFakerImpl(Faker faker) {
        this.faker = faker;
    }

    @Override
    public List<Tour> findAll() {
        LOGGER.trace("findAll()");
        List<Tour> fakerTours = IntStream.rangeClosed(1, 100).mapToObj(i -> new Tour(
                i,
                faker.country().name(),
                faker.date().birthday()
        )).collect(Collectors.toList());
        return fakerTours;
    }
}
