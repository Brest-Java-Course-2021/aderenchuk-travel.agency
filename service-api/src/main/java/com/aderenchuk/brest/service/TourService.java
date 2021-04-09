package com.aderenchuk.brest.service;

import com.aderenchuk.brest.model.Tour;

import java.util.List;
import java.util.Optional;

public interface TourService {

    List<Tour> findAll();

    Optional<Tour> findById(Integer tourId);

    Integer create(Tour tour);

    Integer update(Tour tour);

    Integer delete(Integer tourId);

}
