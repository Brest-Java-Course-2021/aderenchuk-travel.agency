package com.aderenchuk.brest.service.rest_app;

import com.aderenchuk.brest.model.Tour;
import com.aderenchuk.brest.service.TourService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class TourController {

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
        return tourService.findAll();
    }
}