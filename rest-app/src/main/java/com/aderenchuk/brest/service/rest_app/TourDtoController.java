package com.aderenchuk.brest.service.rest_app;

import com.aderenchuk.brest.model.dto.TourDto;
import com.aderenchuk.brest.service.TourDtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class TourDtoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourController.class);

    private final TourDtoService tourDtoService;


    public TourDtoController(TourDtoService tourDtoService) {
        this.tourDtoService = tourDtoService;
    }

    @GetMapping(value = "/tours_quantity")
    public final Collection<TourDto> tourDto() {
        LOGGER.debug("tourDto()");
        return tourDtoService.findAllQuantityClients();
    }
}
