package com.aderenchuk.brest.dao;

import com.aderenchuk.brest.model.dto.TourDto;

import java.util.List;

public interface TourDtoDao {
        /**
         * Get all tours with quantity clients.
         *
         * @return Tours list.
         */
        List<TourDto> findAllQuantityClients();


}
