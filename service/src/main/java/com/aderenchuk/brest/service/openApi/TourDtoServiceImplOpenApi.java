package com.aderenchuk.brest.service.openApi;

import com.aderenchuk.brest.api.ToursDtoApi;
import com.aderenchuk.brest.api.ToursQuantityApi;
import com.aderenchuk.brest.models.TourDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class TourDtoServiceImplOpenApi implements ToursDtoApi, ToursQuantityApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(ToursQuantityApi.class);

    @Override
    public ResponseEntity<List<TourDto>> tourWithDateFilterUsingGET(String dateFrom, String dateTo) {
        LOGGER.debug("tourWithDateFilterUsingGET(dateFrom, dateTo)");
        return tourWithDateFilterUsingGET(dateFrom, dateTo);
    }

    @Override
    public ResponseEntity<List<TourDto>> tourWithQuantityClientsUsingGET() {
        LOGGER.debug("tourWithQuantityClientsUsingGET()");
        return tourWithQuantityClientsUsingGET();
    }
}
