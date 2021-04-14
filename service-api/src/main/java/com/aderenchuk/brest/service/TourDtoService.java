package com.aderenchuk.brest.service;

import com.aderenchuk.brest.model.dto.TourDto;

import java.util.List;

public interface TourDtoService {

    List<TourDto> findAllQuantityClients();
}
