package com.aderenchuk.brest.dao.jdbc;

import com.aderenchuk.brest.dao.TourFakerDao;
import com.aderenchuk.brest.model.Tour;
import com.aderenchuk.brest.service.DummyService;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TourFakerDaoJdbc implements TourFakerDao {

    private DummyService dummyService;

    public TourFakerDaoJdbc(DummyService dummyService) {
        this.dummyService = dummyService;
    }

    @Override
    public Optional<Tour> findTourById(Integer tourId) {
        return dummyService.getDummyTourFindById(tourId);
    }
}
