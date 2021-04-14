package com.aderenchuk.brest.dao.jdbc;

import com.aderenchuk.brest.dao.TourDao;
import com.aderenchuk.brest.model.Tour;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml", "classpath:dao.xml"})
public class TourDaoJdbcTest {

    @Autowired
    private TourDao tourDao;

    @Test
    public void findAllTest() {
        List<Tour> tours = tourDao.findAll();
        Assertions.assertNotNull(tours);
        Assertions.assertTrue(tours.size() > 0);
    }

    @Test
    public void findByIdTest() {
        List<Tour> tours = tourDao.findAll();
        Assertions.assertNotNull(tours);
        Assertions.assertTrue(tours.size() > 0);

        Integer tourId = tours.get(0).getTourId();
        Tour expTour = tourDao.findById(tourId).get();
        Assertions.assertEquals(tourId, expTour.getTourId());
        Assertions.assertEquals(tours.get(0).getDirection(), expTour.getDirection());
        Assertions.assertEquals(tours.get(0), expTour);
    }

    @Test
    public void findByIdExceptionalTest() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, ()-> {
            tourDao.findById(999).get();
        });
    }

    @Test
    public void createTourTest() {
        List<Tour> tours = tourDao.findAll();
        Assertions.assertNotNull(tours);
        Assertions.assertTrue(tours.size() > 0);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tourDao.create(new Tour(101, "BREST-MINSK", LocalDate.of(2015, 2, 15)));
            tourDao.create(new Tour(101, "BREST-MINSK", LocalDate.of(2015, 2, 15)));
        });
        List<Tour> realTour = tourDao.findAll();
        Assertions.assertEquals(tours.size() + 1, realTour.size());
    }

    @Test
    public void createTourWithSameNameTest() {
        List<Tour> tours = tourDao.findAll();
        Assertions.assertNotNull(tours);
        Assertions.assertTrue(tours.size() > 0);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tourDao.create(new Tour(101, "BREST-MINSK", LocalDate.of(2015, 2, 15)));
            tourDao.create(new Tour(101, "BREST-MINSK", LocalDate.of(2015, 2, 15)));
        });
    }

    @Test
    public void createTourWithSameNameDifferentCaseTest() {
        List<Tour> tours = tourDao.findAll();
        Assertions.assertNotNull(tours);
        Assertions.assertTrue(tours.size() > 0);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tourDao.create(new Tour(101, "BREST-MINSK", LocalDate.of(2015, 2, 15)));
            tourDao.create(new Tour(101, "BREST-minsk", LocalDate.of(2015, 2, 15)));
        });
    }

    @Test
    public void updateTourTest() {
        List<Tour> tours = tourDao.findAll();
        Assertions.assertNotNull(tours);
        Assertions.assertTrue(tours.size() > 0);

        Tour tour = tours.get(0);
        tour.setDirection("MOSCOW-BERLIN");
        tourDao.update(tour);

        Optional<Tour> realTour = tourDao.findById(tour.getTourId());
        Assertions.assertEquals(realTour.get().getDirection(), "MOSCOW-BERLIN");
    }

}