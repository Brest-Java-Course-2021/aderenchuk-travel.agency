package com.aderenchuk.brest.service.rest_app;

import com.aderenchuk.brest.model.Tour;
import com.aderenchuk.brest.service.TourService;
import com.aderenchuk.brest.service.rest_app.exception.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static com.aderenchuk.brest.service.rest_app.constants.RestConstants.TOUR_NOT_FOUND;
import static com.aderenchuk.brest.service.rest_app.constants.RestConstants.TOUR_NOT_FOUND_BY_ID;

@RestController
public class TourController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourController.class);


    private TourService tourService;

    public TourController(TourService tourService) {
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
                        new ErrorResponse(TOUR_NOT_FOUND,
                                Arrays.asList(TOUR_NOT_FOUND_BY_ID + id)),
                HttpStatus.NOT_FOUND);
    }

    /**
     * create new tour
     * @param tour
     * @return id tour
     */
    @PostMapping(path = "/tours", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Integer> createTour(@RequestBody Tour tour) {
        LOGGER.debug("createTour({})", tour);
        Integer id = tourService.create(tour);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    /**
     * Update tour
     * @param tour
     * @return id tour
     */
    @PutMapping(value = "/tours", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Integer> updateTour(@RequestBody Tour tour) {
        LOGGER.debug("updateTour({})", tour);
        int result = tourService.update(tour);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * delete tour
     * @param id
     * @return result of delete
     */
    @DeleteMapping(value = "/tours/{id}", produces = {"application/json"})
    public ResponseEntity<Integer> deleteTour (@PathVariable Integer id) {
        int result = tourService.delete(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}