package com.aderenchuk.brest.service.rest_app.faker_data;

import com.aderenchuk.brest.model.Tour;
import com.aderenchuk.brest.service.TourFakerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.aderenchuk.brest.service.rest_app.exception.ControllerAdvisor.LOGGER;

@RestController
@RequestMapping("/fakerTours")
public class TourFakerController {

    private TourFakerService tourFakerService;

    public TourFakerController(TourFakerService tourFakerService) {
        this.tourFakerService = tourFakerService;
    }

    @GetMapping()
    public final Iterable<Tour> fakeTours() {
        LOGGER.debug("fakeTour()");

        return tourFakerService.findAll();
    }
}
