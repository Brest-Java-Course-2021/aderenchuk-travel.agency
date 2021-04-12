package com.aderenchuk.brest.dao.jdbc;

import com.aderenchuk.brest.dao.TourDao;
import com.aderenchuk.brest.model.Tour;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
public class TourDaoJdbcTest {

    @Autowired
    private TourDao tourDao;

    @Test
    public void findAllTest() {
        List<Tour> tours = tourDao.findAll();
        Assert.assertNotNull(tours);
        Assert.assertTrue(tours.size() > 0);
    }

    @Test
    public void findByIdTest() {
        List<Tour> tours = tourDao.findAll();
        Assert.assertNotNull(tours);
        Assert.assertTrue(tours.size() > 0);

        Integer tourId = tours.get(0).getTourId();
        Tour expTour = tourDao.findById(tourId).get();
        Assert.assertEquals(tourId, expTour.getTourId());
        Assert.assertEquals(tours.get(0).getDirection(), expTour.getDirection());
        Assert.assertEquals(tours.get(0), expTour);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void findByIdExceptionalTest() {
        Tour expTour = tourDao.findById(999).get();
    }

    @Test(expected = IllegalArgumentException.class)
    public void createTourTest() {
        List<Tour> tours = tourDao.findAll();
        Assert.assertNotNull(tours);
        Assert.assertTrue(tours.size() > 0);

        tourDao.create(new Tour("BREST-MINSK", LocalDate.of(2015, 2, 15)));
        tourDao.create(new Tour("BREST-MINSK", LocalDate.of(2015, 2, 15)));

        List<Tour> realTour = tourDao.findAll();
        Assert.assertEquals(tours.size() + 1, realTour.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createTourWithSameNameTest() {
        List<Tour> tours = tourDao.findAll();
        Assert.assertNotNull(tours);
        Assert.assertTrue(tours.size() > 0);

        tourDao.create(new Tour("BREST-MINSK", LocalDate.of(2015, 2 ,15)));
        tourDao.create(new Tour("BREST-MINSK", LocalDate.of(2015, 2, 15)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void createTourWithSameNameDifferentCaseTest() {
        List<Tour> tours = tourDao.findAll();
        Assert.assertNotNull(tours);
        Assert.assertTrue(tours.size() > 0);

        tourDao.create(new Tour("BREST-MINSK", LocalDate.of(2015, 2, 15)));
        tourDao.create(new Tour("BREST-minsk", LocalDate.of(2015, 2, 15)));
    }

    @Test
    public void updateTourTest() {
        List<Tour> tours = tourDao.findAll();
        Assert.assertNotNull(tours);
        Assert.assertTrue(tours.size() > 0);

        Tour tour = tours.get(0);
        tour.setDirection("MOSCOW-BERLIN");
        tourDao.update(tour);

        Optional<Tour> realTour = tourDao.findById(tour.getTourId());
        Assert.assertEquals(realTour.get().getDirection(), "MOSCOW-BERLIN");
    }

}