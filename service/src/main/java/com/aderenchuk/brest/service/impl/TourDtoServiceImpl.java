package com.aderenchuk.brest.service.impl;


import com.aderenchuk.brest.dao.TourDtoDao;
import com.aderenchuk.brest.model.dto.TourDto;
import com.aderenchuk.brest.service.TourDtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class TourDtoServiceImpl implements TourDtoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourDtoServiceImpl.class);

    private final TourDtoDao tourDtoDao;

    public TourDtoServiceImpl(TourDtoDao tourDtoDao) {
        this.tourDtoDao = tourDtoDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TourDto> findAllQuantityClients() {
        LOGGER.debug("findAllQuantityClients()");
        return tourDtoDao.findAllQuantityClients();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TourDto> findAllQuantityClientsAndDateFilter(LocalDate dateFrom, LocalDate dateTo) {
        LOGGER.debug("findAllQuantityClientsAndDateFilter()");
        return tourDtoDao.findAllQuantityClientsAndDateFilter(dateFrom, dateTo);
    }
}