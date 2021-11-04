package com.aderenchuk.brest.service.soap_app;

import com.aderenchuk.brest.GetAllToursResponse;
import com.aderenchuk.brest.GetTourByIdResponse;
import com.aderenchuk.brest.TourInfo;
import com.aderenchuk.brest.model.Tour;
import com.aderenchuk.brest.service.TourService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class TourControllerIT {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourController.class);

    private final String TOURS_ENDPOINT = "/tours";

    @Autowired
    private TourService tourService;

    @Test
    public void findAllTours() throws Exception {
        List<TourInfo> tourInfoList = new ArrayList<>();
        List<Tour> tourList = tourService.findAll();
        for (int i = 0; i < tourList.size(); i++) {
            TourInfo ob = new TourInfo();
            BeanUtils.copyProperties(tourList.get(i), ob);
            tourInfoList.add(ob);
        assertNotNull(tourList);
        assertTrue(tourList.size() > 0);
        }
    }

}