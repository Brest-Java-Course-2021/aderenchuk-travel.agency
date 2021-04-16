package com.aderenchuk.brest.dao.jdbc;

import com.aderenchuk.brest.dao.TourDtoDao;
import com.aderenchuk.brest.model.dto.TourDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TourDtoDaoJdbc implements TourDtoDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourDtoDaoJdbc.class);

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TourDtoDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Value("${tourDto.findAllQuantityClients}")
    private String findAllQuantityClients;

    @Override
    public List<TourDto> findAllQuantityClients() {
        LOGGER.debug("findAllQuantityClients()");
        return namedParameterJdbcTemplate.query(
                findAllQuantityClients, BeanPropertyRowMapper.newInstance(TourDto.class));
    }
}
