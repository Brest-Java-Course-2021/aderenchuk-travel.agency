package com.aderenchuk.brest.dao.jdbc;

import com.aderenchuk.brest.dao.TourDao;
import com.aderenchuk.brest.model.Tour;
import com.aderenchuk.brest.model.constants.TourConstants;
import com.aderenchuk.brest.testdb.SpringJdbcConfig;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.support.hierarchical.ThrowableCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@PropertySource({"classpath:/sql"})
@ContextConfiguration(classes = SpringJdbcConfig.class)
@Transactional
public class TourDaoJdbcIT {

    @Autowired
    private TourDao tourDao;

    @Test
    public void findAllTest() {
        List<Tour> tours = tourDao.findAll();
        assertNotNull(tours);
        assertTrue(tours.size() > 0);
    }

    @Test
    public void findByIdTest() {
        List<Tour> tours = tourDao.findAll();
        assertNotNull(tours);
        assertTrue(tours.size() > 0);

        Integer tourId = tours.get(0).getTourId();
        Tour expTour = tourDao.findById(tourId).get();
        assertEquals(tourId, expTour.getTourId());
        assertEquals(tours.get(0).getDirection(), expTour.getDirection());
        assertEquals(tours.get(0), expTour);
    }

    @Test
    public void findByIdExceptionalTest() {
        Optional<Tour> optionalTour = tourDao.findById(999);
        assertTrue(optionalTour.isEmpty());
    }

    @Test
    public void createTourTest() {
        List<Tour> tours = tourDao.findAll();
        assertNotNull(tours);
        assertTrue(tours.size() > 0);

            tourDao.create(new Tour(101,"BREST-MINSK", LocalDate.of(2021, 2, 12)));

        List<Tour> realTour = tourDao.findAll();
        assertEquals(tours.size() + 1, realTour.size());
    }

    @Test
    public void createTourWithSameNameTest() {
        List<Tour> tours = tourDao.findAll();
        assertNotNull(tours);
        assertTrue(tours.size() > 0);
        assertThrows(IllegalArgumentException.class, () -> {
            tourDao.create(new Tour(101, "BREST-MINSK", LocalDate.of(2015, 2, 15)));
            tourDao.create(new Tour(101, "BREST-MINSK", LocalDate.of(2015, 2, 15)));
        });
    }

    @Test
    public void createTourWithSameNameDifferentCaseTest() {
        List<Tour> tours = tourDao.findAll();
        assertNotNull(tours);
        assertTrue(tours.size() > 0);

        assertThrows(IllegalArgumentException.class, () -> {
            tourDao.create(new Tour(101, "BREST-MINSK", LocalDate.of(2015, 2, 15)));
            tourDao.create(new Tour(101, "BREST-minsk", LocalDate.of(2015, 2, 15)));
        });
    }

    @Test
    public void updateTourTest() {
        List<Tour> tours = tourDao.findAll();
        assertNotNull(tours);
        assertTrue(tours.size() > 0);

        Tour tour = tours.get(0);
        tour.setDirection("MOSCOW-BERLIN");
        tourDao.update(tour);

        Optional<Tour> realTour = tourDao.findById(tour.getTourId());
        assertEquals(realTour.get().getDirection(), "MOSCOW-BERLIN");
    }

    @Test
    public void deleteTour() {
        Tour tour = new Tour();
        tour.setDirection(RandomStringUtils.randomAlphabetic(TourConstants.DIRECTION_MAX_SIZE));
        tour.setDateTour(LocalDate.of(2015, 2, 15));
        Integer id = tourDao.create(tour);

        List<Tour> tours = tourDao.findAll();
        assertNotNull(tours);

        int result = tourDao.delete(id);

        assertTrue(1 == result);

        List<Tour> realTours = tourDao.findAll();
        assertNotNull(realTours);

        assertTrue(tours.size()-1 == realTours.size());
    }

    @Test
    public void fakeTour() {
        Faker faker = new Faker(Locale.ENGLISH);
        System.out.println(faker.address().fullAddress());
        System.out.println(faker.country().name());
        System.out.println(faker.date().birthday());
    }

}