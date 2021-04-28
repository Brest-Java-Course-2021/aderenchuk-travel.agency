package com.aderenchuk.brest.dao.jdbc;

import com.aderenchuk.brest.dao.TourDtoDao;
import com.aderenchuk.brest.model.dto.TourDto;
import com.aderenchuk.brest.testdb.SpringJdbcConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJdbcTest
@Import({TourDaoJdbc.class})
@PropertySource({"classpath:dao.properties"})
@ContextConfiguration(classes = SpringJdbcConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
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
        LocalDate dateFrom = LocalDate.now().minusMonths(1);
        LocalDate dateTo = LocalDate.now();
        assertTrue(dateFrom.compareTo(dateTo)<0);
        List<TourDto> tours = tourDtoDao.findAllQuantityClientsAndDateFilter(dateFrom,dateTo);
        assertNotNull(tours);
//        List<TourDto> tourList = tourDtoDao.findAllQuantityClientsAndDateFilter(dateFrom,dateTo);
//        assertTrue(tourList.size() == 0);
    }
}
