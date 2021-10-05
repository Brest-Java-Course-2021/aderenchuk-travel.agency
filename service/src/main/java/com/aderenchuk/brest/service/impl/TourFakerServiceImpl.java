package com.aderenchuk.brest.service.impl;

import com.aderenchuk.brest.dao.TourFakerDao;
import com.aderenchuk.brest.model.Tour;
import com.aderenchuk.brest.service.TourFakerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TourFakerServiceImpl implements TourFakerService {

    private TourFakerDao tourFakerDao;

    public TourFakerServiceImpl(TourFakerDao tourFakerDao) {
        this.tourFakerDao = tourFakerDao;
    }

    @Override
    public Optional<Tour> findTourById(Integer tourId) {
        return tourFakerDao.findTourById(tourId);
    }
}
