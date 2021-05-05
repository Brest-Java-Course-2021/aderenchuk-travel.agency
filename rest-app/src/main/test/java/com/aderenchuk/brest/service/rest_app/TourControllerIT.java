package com.aderenchuk.brest.service.rest_app;

import com.aderenchuk.brest.service.TourService;
import com.aderenchuk.brest.model.Tour;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class TourControllerIT {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourController.class);

    private final String TOURS_ENDPOINT = "/tours";

    @Autowired
    private TourService tourService;

    @Test
    public void findAllTours() throws Exception {

        List<Tour> tours = tourService.findAll();
        assertNotNull(tours);
        assertTrue(tours.size() > 0);
    }

    @Test
    public void findById() {
        List<Tour> tours = tourService.findAll();
        assertNotNull(tours);
        assertTrue(tours.size() > 0);

        Integer id = tours.get(0).getTourId();
        Tour expTour = tourService.findById(id).get();
        assertEquals(id, expTour.getTourId());
        assertEquals(tours.get(0).getDirection(), expTour.getDirection());
        assertEquals(tours.get(0), expTour);
    }

    @Test
    public void findByIdExceptional() {
        Optional<Tour> optionalTour = tourService.findById(999);
        assertTrue(optionalTour.isEmpty());
    }

    @Test
     public void createTour() {
        List<Tour> tours = tourService.findAll();
        assertNotNull(tours);
        assertTrue(tours.size() > 0);

        tourService.create(new Tour(101,"BREST-MINSK", LocalDate.of(2021, 2, 12)));

        List<Tour> realTour = tourService.findAll();
        assertEquals(tours.size() + 1, realTour.size());
    }

    @Test
    public void createTourWithSameName() {
        List<Tour> tours = tourService.findAll();
        assertNotNull(tours);
        assertTrue(tours.size() > 0);
        assertThrows(IllegalArgumentException.class, () -> {
            tourService.create(new Tour(101, "BREST-MINSK", LocalDate.of(2015, 2, 15)));
            tourService.create(new Tour(101, "BREST-MINSK", LocalDate.of(2015, 2, 15)));
        });
    }

    @Test
    public void createTourWithSameNameDifferentCaseTest() {
        List<Tour> tours = tourService.findAll();
        assertNotNull(tours);
        assertTrue(tours.size() > 0);

        assertThrows(IllegalArgumentException.class, () -> {
            tourService.create(new Tour(101, "BREST-MINSK", LocalDate.of(2015, 2, 15)));
            tourService.create(new Tour(101, "BREST-minsk", LocalDate.of(2015, 2, 15)));
        });
    }

    @Test
    public void updateTour() {
        List<Tour> tours = tourService.findAll();
        assertNotNull(tours);
        assertTrue(tours.size() > 0);

        Tour tour = tours.get(0);
        tour.setDirection("MOSCOW-BERLIN");
        tourService.update(tour);

        Optional<Tour> realTour = tourService.findById(tour.getTourId());
        assertEquals(realTour.get().getDirection(), "MOSCOW-BERLIN");
    }

    @Test
    public void deleteTour() {
        List<Tour> tours = tourService.findAll();
        assertNotNull(tours);
        assertTrue(tours.size() > 0);

        Integer id = tours.get(0).getTourId();
        tourService.delete(id);
    }

}
