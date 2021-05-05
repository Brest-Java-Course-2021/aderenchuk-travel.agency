package com.aderenchuk.brest.dao.jdbc;

import com.aderenchuk.brest.model.constants.TourConstants;
import com.aderenchuk.brest.dao.TourDtoDao;
import com.aderenchuk.brest.model.dto.TourDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;

@Repository
public class TourDtoDaoJdbc implements TourDtoDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourDtoDaoJdbc.class);


    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TourDtoDaoJdbc(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
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
    public List<TourDto> findAllQuantityClientsAndDateFilter(LocalDate dateFrom, LocalDate dateTo) {
        LOGGER.debug("findAllQuantityClientsAndDateFilter(LocalDate dateFrom, LocalDate dateTo:{})");
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(TourConstants.DATE_FROM,dateFrom)
                .addValue(TourConstants.DATE_TO, dateTo);
        List<TourDto> tours = namedParameterJdbcTemplate.query
                (findAllQuantityClientsAndDateFilter, sqlParameterSource, BeanPropertyRowMapper.newInstance(TourDto.class));
        return tours;
    }

}
