package com.aderenchuk.brest.service.openApi;

import com.aderenchuk.brest.api.ToursApi;
import com.aderenchuk.brest.models.Tour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TourServiceImplOpenApi implements ToursApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(ToursApi.class);


    @Override
    public ResponseEntity<Integer> createTourUsingPOST(Tour body) {
        LOGGER.debug("createTourUsingPOST(body:{})", body);
        return createTourUsingPOST(body);
    }

    @Override
    public ResponseEntity<Integer> deleteTourUsingDELETE(Integer id) {
        LOGGER.debug("deleteTourUsingDELETE(id:{})", id);
        return deleteTourUsingDELETE(id);
    }

    @Override
    public ResponseEntity<Tour> tourByIdUsingGET(Integer id) {
        LOGGER.debug("tourByIdUsingGET(id:{})", id);
        return tourByIdUsingGET(id);
    }

    @Override
    public ResponseEntity<List<Tour>> toursUsingGET() {
        LOGGER.trace("toursUsingGET()");
        return toursUsingGET();
    }

    @Override
    public ResponseEntity<Integer> updateTourUsingPUT(Tour body) {
        LOGGER.debug("updateTourUsingPUT(body:{})", body);
        return updateTourUsingPUT(body);
    }
}
