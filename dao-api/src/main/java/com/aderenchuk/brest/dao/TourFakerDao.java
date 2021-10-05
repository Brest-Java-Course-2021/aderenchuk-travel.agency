package com.aderenchuk.brest.dao;

import com.aderenchuk.brest.model.Tour;

import java.util.Optional;

public interface TourFakerDao {

    /**
     * Find tour by id.
     * @param tourId Tour id.
     * @return Tour.
     */
    Optional<Tour> findTourById(Integer tourId);
}
