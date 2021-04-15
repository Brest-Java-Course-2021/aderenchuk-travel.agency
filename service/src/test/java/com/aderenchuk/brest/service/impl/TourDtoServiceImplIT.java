package com.aderenchuk.brest.service.impl;

import com.aderenchuk.brest.model.dto.TourDto;
import com.aderenchuk.brest.service.TourDtoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:service_test.xml"})
@PropertySource("classpath*:dao.properties")
@Transactional
public class TourDtoServiceImplIT {

    @Autowired
    TourDtoService tourDtoService;

//    @Test
//    public void shouldFindAllWithQuantityClients() {
//        List<TourDto> tours = tourDtoService.findAllQuantityClients();
//        assertNotNull(tours);
//        assertTrue(tours.size() > 0);
//        assertTrue(tours.get(0).getQuantityClients() > 0);
//    }
}
