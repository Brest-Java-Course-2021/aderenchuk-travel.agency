package com.aderenchuk.brest.dao.jpa;

import com.aderenchuk.brest.model.Tour;
import com.aderenchuk.brest.model.dto.TourDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TourDaoJPA extends JpaRepository<Tour, Integer > {
//    /**
//     * Get all tours with quantity clients.
//     *
//     * @return Tours list.
//     */
//    @Query("SELECT TOUR_ID, DIRECTION, DATE_TOUR (SELECT COUNT(TOUR_ID ) FROM CLIENT AS C WHERE C.TOUR_ID = T.TOUR_ID) AS QUANTITY_CLIENTS, FROM TOUR AS T")
//    List<TourDto> findAllQuantityClients();
//
//    /**
//     * Get all tours with quantity clients and Date Filter.
//     *
//     * @return Tours list.
//     * @param dateFrom
//     * @param dateTo
//     */
//    @Query("SELECT TOUR_ID, DIRECTION,DATE_TOUR, (SELECT COUNT(TOUR_ID ) FROM CLIENT AS C WHERE C.TOUR_ID = T.TOUR_ID) AS QUANTITY_CLIENTS, FROM TOUR AS T WHERE DATE_TOUR BETWEEN :dateFrom AND :dateTo")
//    List<TourDto> findAllQuantityClientsAndDateFilter(LocalDate dateFrom, LocalDate dateTo);
}
