package com.aderenchuk.brest.dao.jdbc;

import com.aderenchuk.brest.dao.TourDtoDao;
import com.aderenchuk.brest.model.dto.TourDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
public class TourDtoDaoJdbcIT {

    @Autowired
    TourDtoDao tourDtoDao;

    @Test
    public void shouldFindAllQuantityClients() {
        List<TourDto> tours = tourDtoDao.findAllQuantityClients();
        assertNotNull(tours);
        assertTrue(tours.size() > 0);
        assertTrue(tours.get(0).getQuantityClients().intValue() > 0);
    }

    @Test
    public void shouldFindAllQuantityClientsAndDateFilter() {
        Date dateFrom = new Date();
        Date dateTo = new Date(130, 05, 12);
        assertTrue(dateFrom.compareTo(dateTo) < 0);
        List<TourDto> tours = tourDtoDao.findAllQuantityClientsAndDateFilter(dateFrom, dateTo);
        assertNotNull(tours);
    }
}
