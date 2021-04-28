package com.aderenchuk.brest.dao;

import com.aderenchuk.brest.model.dto.TourDto;

import java.time.LocalDate;
import java.util.List;

public interface TourDtoDao {
        /**
         * Get all tours with quantity clients.
         *
         * @return Tours list.
         */
        List<TourDto> findAllQuantityClients();

        /**
         * Get all tours with quantity clients and Date Filter.
         *
         * @return Tours list.
         * @param dateFrom
         * @param dateTo
         */
        List<TourDto> findAllQuantityClientsAndDateFilter(LocalDate dateFrom, LocalDate dateTo);


}
