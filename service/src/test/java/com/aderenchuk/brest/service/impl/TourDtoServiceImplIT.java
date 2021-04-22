package com.aderenchuk.brest.service.impl;

import com.aderenchuk.brest.service.TourDtoService;
import com.aderenchuk.brest.model.dto.TourDto;
import com.aderenchuk.brest.testdb.SpringJdbcConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@JdbcTest
@ContextConfiguration(classes = SpringJdbcConfig.class)
@PropertySource({"classpath:dao.properties"})
@Transactional
public class TourDtoServiceImplIT {

    @Autowired
          TourDtoService tourDtoService;

    @Test
    public void shouldFindAllWithQuantityClients() {
        List<TourDto> tours = tourDtoService.findAllQuantityClients();
        assertNotNull(tours);
        assertTrue(tours.size() > 0);
        assertTrue(tours.get(0).getQuantityClients() > 0);
    }

    @Test
    public void findAllWithQuantityPassengersAndDateFilter() {
        Date dateFrom = new Date();
        Date dateTo = new Date(130,05,12);
        assertTrue(dateFrom.compareTo(dateTo)<0);
        List<TourDto> tours = tourDtoService.findAllQuantityClientsAndDateFilter(dateFrom,dateTo);
        assertNotNull(tours);
        assertTrue(tours.size() > 0);
    }
}
