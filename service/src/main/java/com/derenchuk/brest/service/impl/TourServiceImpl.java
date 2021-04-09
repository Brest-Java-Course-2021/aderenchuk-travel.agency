package com.derenchuk.brest.service.impl;

import com.aderenchuk.brest.dao.TourDao;
import com.aderenchuk.brest.model.Tour;
import com.aderenchuk.brest.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TourServiceImpl implements TourService {

    //private static final Logger LOGGER = Logger

    private final TourDao tourDao;

    @Autowired
    public TourServiceImpl(TourDao tourDao) {
        this.tourDao = tourDao;
    }


    @Override
    public List<Tour> findAll() {
        return tourDao.findAll();
    }

    @Override
    public Optional<Tour> findById(Integer tourId) {
        return tourDao.findById(tourId);
    }

    @Override
    public Integer create(Tour tour) {
        return tourDao.create(tour);
    }

    @Override
    public Integer update(Tour tour) {
        return tourDao.update(tour);
    }

    @Override
    public Integer delete(Integer tourId) {
        return tourDao.delete(tourId);
    }
}
