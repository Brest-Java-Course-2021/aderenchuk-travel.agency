package com.aderenchuk.brest.dao;

import com.aderenchuk.brest.model.Tour;

import java.util.List;
import java.util.Optional;

public interface TourDao {

    List<Tour> findAll();

    Optional<Tour> findById(Integer tourId);

    Integer create(Tour tour);

    Integer update(Tour tour);

    Integer delete(Integer tourId);


}
