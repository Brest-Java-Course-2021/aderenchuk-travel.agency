package com.aderenchuk.brest.service.impl;

import com.aderenchuk.brest.model.Tour;
import com.aderenchuk.brest.service.TourService;
import com.aderenchuk.brest.dao.TourDao;
import com.aderenchuk.brest.dao.jdbc.TourDaoJdbc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TourServiceImpl implements TourService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourDaoJdbc.class);

    private final TourDao tourDao;

    public TourServiceImpl(TourDao tourDao) {
        this.tourDao = tourDao;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Tour> findAll() {
        LOGGER.trace("findAll()");
        return tourDao.findAll();
    }

    @Override
    public Optional<Tour> findById(Integer tourId) {
        LOGGER.debug("findById(tourId:{})", tourId);
        return tourDao.findById(tourId);
    }

    @Override
    public Integer create(Tour tour) {
        LOGGER.debug("create(tour:{})", tour);
        return tourDao.create(tour);
    }

    @Override
    public Integer update(Tour tour) {
        LOGGER.debug("update(tour:{})", tour);
        return tourDao.update(tour);
    }

    @Override
    public Integer delete(Integer tourId) {
        LOGGER.debug("delete(tourId:{})", tourId);
        return tourDao.delete(tourId);
    }
}
