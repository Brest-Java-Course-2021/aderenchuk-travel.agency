package com.aderenchuk.brest.dao.jdbc;

import com.aderenchuk.brest.dao.TourDao;
import com.aderenchuk.brest.model.Tour;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class TourDaoJdbcTest {

    @Autowired
    private TourDao tourDao;

    @Test
    public void findAllTest() {
//        List<Tour> tours = tourDao.findAll();
//        Assert.assertNotNull(tours);
//        Assert.assertTrue(tours.size() > 0);
    }
}