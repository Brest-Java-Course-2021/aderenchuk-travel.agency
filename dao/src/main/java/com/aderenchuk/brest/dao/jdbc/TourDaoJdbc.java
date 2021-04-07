package com.aderenchuk.brest.dao.jdbc;

import com.aderenchuk.brest.dao.TourDao;
import com.aderenchuk.brest.model.Tour;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSourceExtensionsKt;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TourDaoJdbc implements TourDao {

    private static final String SQL_GET_ALL_TOUR = "SELECT T.TOUR_ID, T.TOUR_DIRECTION FROM TOUR AS T ORDER BY T.TOUR_DIRECTION";

    private static final String SQL_GET_TOUR_BY_ID = "SELECT T.TOUR_ID, T.TOUR_DIRECTION FROM TOUR AS T WHERE T.TOUR_ID = :TOUR_ID";

    private static final String SQL_CREATE_TOUR = "INSERT INTO TOUR (TOUR_DIRECTION) VALUES (:TOUR_DIRECTION);";

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TourDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    RowMapper rowMapper = BeanPropertyRowMapper.newInstance(Tour.class);

    @Override
    public List<Tour> findAll() {
        return namedParameterJdbcTemplate.query(SQL_GET_ALL_TOUR, rowMapper);
    }

    @Override
    public Optional<Tour> findById(Integer tourId) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("TOUR_ID", tourId);
        return Optional.ofNullable((Tour) namedParameterJdbcTemplate.queryForObject(SQL_GET_TOUR_BY_ID, sqlParameterSource, rowMapper));

    }

    @Override
    public Integer create(Tour tour) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("TOUR_DIRECTION", tour.getDirection());
        namedParameterJdbcTemplate.update(SQL_CREATE_TOUR, sqlParameterSource, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey().intValue());
    }

    @Override
    public Integer update(Tour tour) {
        return null;
    }

    @Override
    public Integer delete(Integer tourId) {
        return null;
    }


}
