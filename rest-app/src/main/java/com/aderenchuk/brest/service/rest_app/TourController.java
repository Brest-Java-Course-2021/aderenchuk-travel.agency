package com.aderenchuk.brest.service.rest_app;

import com.aderenchuk.brest.model.Tour;
import com.aderenchuk.brest.service.TourService;
import com.aderenchuk.brest.service.rest_app.exception.TourNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

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

    @GetMapping(value = "/{tourId}")
    public Tour findById(@PathVariable Integer tourId) {
        LOGGER.debug("findById({})", tourId);
        return tourService.findById(tourId).orElseThrow(() -> new TourNotFoundException(tourId));
    }
}
