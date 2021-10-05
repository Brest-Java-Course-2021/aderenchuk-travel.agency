package com.aderenchuk.brest.service;

import com.aderenchuk.brest.model.Tour;

import java.util.Optional;

public interface TourFakerService {

    /**
     * Find tour by id.
     * @param tourId Tour id.
     * @return Tour.
     */
    Optional<Tour> findTourById(Integer tourId);
}
