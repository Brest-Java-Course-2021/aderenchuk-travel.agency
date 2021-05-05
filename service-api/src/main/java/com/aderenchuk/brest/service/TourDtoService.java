package com.aderenchuk.brest.service;

import com.aderenchuk.brest.model.dto.TourDto;

import java.time.LocalDate;
import java.util.List;

public interface TourDtoService {

    /**
     * Get all tours with quantity clients.
     *
     * @return Tours list.
     */
    List<TourDto> findAllQuantityClients();

    /**
     * Get all tours with quantity clients and filter of date.
     *
     * @return Tours list.
     */
    List<TourDto> findAllQuantityClientsAndDateFilter(LocalDate dateFrom, LocalDate dateTo);
}