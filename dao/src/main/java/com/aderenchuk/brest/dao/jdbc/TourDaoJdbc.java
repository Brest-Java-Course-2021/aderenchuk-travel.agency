package com.aderenchuk.brest.dao.jdbc;

import com.aderenchuk.brest.dao.TourDao;
import com.aderenchuk.brest.model.Tour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TourDaoJdbc implements TourDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourDaoJdbc.class);

    private static final String SQL_GET_ALL_TOUR = "SELECT * FROM TOUR AS T ORDER BY T.DIRECTION";

    private static final String SQL_GET_TOUR_BY_ID = "SELECT * FROM TOUR AS T WHERE T.TOUR_ID = :TOUR_ID";

    private static final String SQL_CREATE_TOUR = "INSERT INTO TOUR (DIRECTION,DATE_TOUR) VALUES (:DIRECTION, :DATE_TOUR);";

    private static final String SQL_CHECK_DIRECTION = "SELECT COUNT(TOUR_ID) FROM TOUR WHERE lower(DIRECTION) = lower(:DIRECTION)";

    private static final String SQL_UPDATE_TOUR = "UPDATE TOUR SET DIRECTION = :DIRECTION, DATE_TOUR =:DATE_TOUR WHERE TOUR_ID = :TOUR_ID;";
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TourDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

//    RowMapper<Tour> rowMapper = BeanPropertyRowMapper.newInstance(Tour.class);

    @Override
    public List<Tour> findAll() {
        LOGGER.debug("Find all tours");
        return namedParameterJdbcTemplate.query(SQL_GET_ALL_TOUR, new TourRowMapper());
    }

    @Override
    public Optional<Tour> findById(Integer tourId) {
        LOGGER.debug("Find tour by id: {}", tourId);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("TOUR_ID", tourId);
        return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(SQL_GET_TOUR_BY_ID, sqlParameterSource, new TourRowMapper()));

    }

    @Override
    public Integer create(Tour tour) {
        long startTime = System.nanoTime();
        LOGGER.debug("Find tour by id: {}", tour);
        if (!isTourDirectionUnique(tour)) {
            throw new IllegalArgumentException("Tour with the same direction");
        }
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("DIRECTION", tour.getDirection())
                .addValue("DATE_TOUR", tour.getDateTour());
        namedParameterJdbcTemplate.update(SQL_CREATE_TOUR, sqlParameterSource, keyHolder);
        long stopTime = System.nanoTime();
        LOGGER.debug("Execution time: {}", stopTime - startTime);
        return Objects.requireNonNull(keyHolder.getKey().intValue());
    }

    public boolean isTourDirectionUnique(Tour tour) {
        return namedParameterJdbcTemplate.queryForObject(SQL_CHECK_DIRECTION,
                new MapSqlParameterSource("DIRECTION", tour.getDirection()), Integer.class) == 0;
    }

    @Override
    public Integer update(Tour tour) {
        LOGGER.debug("Update tour: {}", tour);
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource()
                        .addValue("TOUR_ID", tour.getTourId())
                        .addValue("DIRECTION", tour.getDirection())
                        .addValue("DATE_TOUR", tour.getDateTour());
        return namedParameterJdbcTemplate.update(SQL_UPDATE_TOUR, sqlParameterSource);
    }

    @Override
    public Integer delete(Integer tourId) {
        LOGGER.debug("Delete tour: {}", tourId);
        return null;
    }

    private class TourRowMapper implements RowMapper<Tour> {
        @Override
        public Tour mapRow(ResultSet resultSet, int i) throws SQLException {
            Tour tour = new Tour();
            tour.setTourId(resultSet.getInt("TOUR_ID"));
            tour.setDirection(resultSet.getString("DIRECTION"));
            tour.setDateTour(resultSet.getDate("DATE_TOUR").toLocalDate());
            return tour;
        }
    }
}
