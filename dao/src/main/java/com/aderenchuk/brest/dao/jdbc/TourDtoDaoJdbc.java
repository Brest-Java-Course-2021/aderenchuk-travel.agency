package com.aderenchuk.brest.dao.jdbc;

import com.aderenchuk.brest.dao.TourDtoDao;
import com.aderenchuk.brest.model.dto.TourDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static com.aderenchuk.brest.constants.TourConstants.DATE_FROM;
import static com.aderenchuk.brest.constants.TourConstants.DATE_TO;

@Repository
public class TourDtoDaoJdbc implements TourDtoDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourDtoDaoJdbc.class);


    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TourDtoDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Value("${tourDto.findAllQuantityClients}")
    private String findAllQuantityClients;

    @Value("${tourDto.findAllQuantityClientsAndDateFilter}")
    private String findAllQuantityClientsAndDateFilter;

    @Override
    public List<TourDto> findAllQuantityClients() {
        LOGGER.debug("findAllQuantityClients()");
        return namedParameterJdbcTemplate.query(
                findAllQuantityClients, BeanPropertyRowMapper.newInstance(TourDto.class));
    }

    @Override
    public List<TourDto> findAllQuantityClientsAndDateFilter(Date dateFrom, Date dateTo) {
        LOGGER.debug("findAllQuantityClientsAndDateFilter(Date dateFrom, Date dateTo:{})");
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(DATE_FROM,dateFrom)
                .addValue(DATE_TO, dateTo);
        List<TourDto> tours = namedParameterJdbcTemplate.query
                (findAllQuantityClientsAndDateFilter, sqlParameterSource, BeanPropertyRowMapper.newInstance(TourDto.class));
        return tours;
    }

}
