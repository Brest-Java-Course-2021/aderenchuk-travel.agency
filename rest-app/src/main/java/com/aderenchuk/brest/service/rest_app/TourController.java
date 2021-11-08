package com.aderenchuk.brest.service.rest_app;

import com.aderenchuk.brest.model.Tour;
import com.aderenchuk.brest.service.impl.TourServiceImpl;
import com.aderenchuk.brest.service.rest_app.exception.ErrorResponse;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class TourController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourController.class);

    @Autowired
    private TourServiceImpl tourService;

    public TourController(TourServiceImpl tourService) {
        this.tourService = tourService;
    }

    /**
     * Go to tours list page
     *
     * @return view name
     */
    @GetMapping(value = "/tours")
    public final Collection<Tour> tours() {
        LOGGER.debug("tours()");
        return tourService.findAll();
    }

    /**
     * find tour by id
     * @param id
     * @return tour
     */
    @GetMapping(value = "tours/{id}")
    public ResponseEntity<Tour> tourById(@PathVariable Integer id) {
        LOGGER.debug("find tour by id({})", id);

        Optional<Tour> optionalTour = tourService.findById(id);
        return optionalTour.isPresent()
                ? new ResponseEntity<>(optionalTour.get(), HttpStatus.OK)
                : new ResponseEntity(
                        new ErrorResponse(List.of("Can't find Tour with such id")),
                HttpStatus.NOT_FOUND);
    }

    /**
     * create new tour
     * @param tour
     * @return id tour
     */
    @PostMapping(path = "/tours", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Tour> createTour(@RequestBody Tour tour) {
        LOGGER.debug("createTour({})", tour);
        tourService.create(tour);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Update tour
     * @param tourId
     * @param tourDetails
     * @return id tour
     */
    @PutMapping(value = "/tours", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Boolean> updateTour(@PathVariable Integer tourId, @RequestBody Tour tourDetails) throws NotFoundException {
        LOGGER.debug("updateTour({})", tourDetails);
        Tour tour = tourService.findById(tourId)
                .orElseThrow(() -> new NotFoundException("Tour not found for this id :: " + tourId));

        tour.setTourId(tourDetails.getTourId());
        tour.setDirection(tourDetails.getDirection());
        tour.setDateTour(tourDetails.getDateTour());
        final boolean updatedTour = tourService.update(tour);
        return ResponseEntity.ok(updatedTour);
    }

    /**
     * delete tour
     * @param id
     * @return result of delete
     */
    @DeleteMapping(value = "/tours/{id}", produces = {"application/json"})
    public ResponseEntity<Integer> deleteTour (@PathVariable Integer id) throws NotFoundException {
        Tour tour = tourService.findById(id)
                .orElseThrow(() -> new NotFoundException("Tour not found for this id :: " + id));

         tourService.delete(id);
         List<Tour> list = new ArrayList<>();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}