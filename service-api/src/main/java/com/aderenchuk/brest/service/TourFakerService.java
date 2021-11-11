package com.aderenchuk.brest.service;

import com.aderenchuk.brest.model.Tour;

import java.util.List;

public interface TourFakerService {

    /**
     * Find all tours.
     * @return tours list.
     */
    List<Tour> findAll();
}
