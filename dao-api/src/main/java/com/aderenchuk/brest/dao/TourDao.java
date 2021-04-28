package com.aderenchuk.brest.dao;

import com.aderenchuk.brest.model.Tour;

import java.util.List;
import java.util.Optional;

public interface TourDao {

    /**
     * Find all tours.
     * @return tours list.
     */
    List<Tour> findAll();

    /**
     * Find tour by id.
     * @param tourId Tour id.
     * @return Tour.
     */
    Optional<Tour> findById(Integer tourId);

    /**
     * Create new Tour.
     * @param tour Tour.
     * @return persisted Tour id.
     */
    Integer create(Tour tour);

    /**
     * Update Tour.
     * @param tour Tour.
     * @return number of updated record in the database.
     */
    Integer update(Tour tour);

    /**
     * Delete Tour.
     * @param tourId Tour id.
     * @return number of deleted record in the database.
     */
    Integer delete(Integer tourId);


}
