package com.aderenchuk.brest.service.rest_app;

import com.aderenchuk.brest.model.dto.TourDto;
import com.aderenchuk.brest.service.TourDtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collection;

/**
 * TourDto rest Controller
 */
@RestController
public class TourDtoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourController.class);

    private final TourDtoService tourDtoService;

    public TourDtoController(TourDtoService tourDtoService) {
        this.tourDtoService = tourDtoService;
    }

    /**
     * Get tour with quantity clients.
     *
     * @return Tours Dtos collection.
     */
    @GetMapping(value = "/tours_quantity")
    public final Collection<TourDto> tourWithQuantityClients() {
        LOGGER.debug("tours find all with quantity clients()");
        return tourDtoService.findAllQuantityClients();
    }

    /**
     * Find tours with date Filter
     * @param dateFrom
     * @param dateTo
     * @return Collection of tours with date filter
     */
    @GetMapping(value = "/toursDto")
    public final Collection<TourDto> tourWithDateFilter
    (@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate dateFrom,
     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo) {

        LOGGER.debug("find tours with date filter({}, {})", dateFrom, dateTo);
        return tourDtoService.findAllQuantityClientsAndDateFilter(dateFrom, dateTo);
    }
}
