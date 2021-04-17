package com.aderenchuk.brest.service;

import com.aderenchuk.brest.model.Tour;

import java.util.List;
import java.util.Optional;

public interface TourService {

    /**
     * Find all tours.
     * @return tours list.
     */
    List<Tour> findAll();

    /**
     * Find tours by id.
     * @param tourId Tour id.
     * @return Tour.
     */
    Optional<Tour> findById(Integer tourId);

    /**
     * Create new tour.
     * @param tour tours.
     * @return persisted tour id.
     */
    Integer create(Tour tour);

    /**
     * Update tour.
     * @param tour tour.
     * @return number of updated record in the database.
     */
    Integer update(Tour tour);

    /**
     * Delete tour.
     * @param tourId tour id.
     * @return number of deleted record in the database.
     */
    Integer delete(Integer tourId);

}
