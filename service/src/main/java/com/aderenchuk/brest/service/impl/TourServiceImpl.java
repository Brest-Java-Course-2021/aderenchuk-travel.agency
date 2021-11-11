package com.aderenchuk.brest.service.impl;

import com.aderenchuk.brest.dao.jpa.TourDaoJPA;
import com.aderenchuk.brest.model.Tour;
import com.aderenchuk.brest.dao.jdbc.TourDaoJdbc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TourServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourServiceImpl.class);

    @Autowired
    private final TourDaoJPA tourDaoJPA;

    public TourServiceImpl(TourDaoJPA tourDaoJPA) {
        this.tourDaoJPA = tourDaoJPA;
    }


    @Transactional(readOnly = true)
    public Page<Tour> findAll(Pageable page) {
        LOGGER.trace("findAll()");
        return tourDaoJPA.findAll(page);
    }


    public Optional<Tour> findById(Integer tourId) {
        LOGGER.debug("findById(tourId:{})", tourId);
        return tourDaoJPA.findById(tourId);
    }


    public Tour create(Tour tour) {
        LOGGER.debug("create(tour:{})", tour);
        tourDaoJPA.save(tour);
        return tour;
    }


    public boolean update(Tour tour) {
        LOGGER.debug("update(tour:{})", tour);
        if (tourDaoJPA.existsById(tour.getTourId())) {
            tourDaoJPA.save(tour);
            return true;
        } else return false;
    }


    public void delete(Integer tourId) {
        LOGGER.debug("delete(tourId:{})", tourId);
         tourDaoJPA.deleteById(tourId);
    }
}
