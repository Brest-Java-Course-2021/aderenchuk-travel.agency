package com.aderenchuk.brest.service.rest_app;

import com.aderenchuk.brest.model.Tour;
import com.aderenchuk.brest.service.TourFakerService;
import com.aderenchuk.brest.service.rest_app.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.aderenchuk.brest.service.rest_app.exception.ControllerAdvisor.LOGGER;

@RestController
@RequestMapping("/tourFaker")
public class TourFakerController {

    private final TourFakerService tourFakerService;

    public TourFakerController(TourFakerService tourFakerService) {
        this.tourFakerService = tourFakerService;
    }

    @GetMapping(value = "tourId/{tourId}")
    @ResponseBody
    public ResponseEntity<Tour> tourById(@PathVariable Integer id) {
        LOGGER.debug("find tour by id({})", id);

        Optional<Tour> optionalTour = tourFakerService.findTourById(id);
        return optionalTour.isPresent()
                ? new ResponseEntity<>(optionalTour.get(), HttpStatus.OK)
                : new ResponseEntity(
                new ErrorResponse(List.of("Can't find Tour with such id")),
                HttpStatus.NOT_FOUND);
    }
}
