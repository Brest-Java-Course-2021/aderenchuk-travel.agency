package com.aderenchuk.brest.dao.jdbc;

import com.aderenchuk.brest.dao.TourDao;
import com.aderenchuk.brest.model.Tour;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TourDaoJdbcMockTest {

    @InjectMocks
    private TourDaoJdbc tourDao;

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Test
    public void findAllTest() {
        List<Tour> result = tourDao.findAll();

        Assert.assertNotNull(result);
    }
}
