package com.aderenchuk.brest.service;

import com.aderenchuk.brest.model.Tour;

import java.util.Optional;

public interface DummyService {

    /**
     * Find tours by id.
     * @param tourId Tour id.
     * @return Tour.
     */
    Optional<Tour> getDummyTourFindById(Integer tourId);
}
