package com.aderenchuk.brest.dao.jdbc;

import com.aderenchuk.brest.dao.TourDao;
import com.aderenchuk.brest.model.Tour;

import java.util.List;
import java.util.Optional;

public class TourDaoJdbc implements TourDao {

    @Override
    public List<Tour> findAll() {
        return null;
    }

    @Override
    public Optional<Tour> findById(Integer tourId) {
        return Optional.empty();
    }

    @Override
    public Integer create(Tour tour) {
        return null;
    }

    @Override
    public Integer update(Tour tour) {
        return null;
    }

    @Override
    public Integer delete(Integer tourId) {
        return null;
    }
}
