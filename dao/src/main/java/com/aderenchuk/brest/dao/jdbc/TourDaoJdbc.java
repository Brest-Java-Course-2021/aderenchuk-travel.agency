package com.aderenchuk.brest.dao.jdbc;

import com.aderenchuk.brest.dao.TourDao;
import com.aderenchuk.brest.model.Tour;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TourDaoJdbc implements TourDao {

    private static final String SQL_GET_ALL_TOUR = "SELECT T.TOUR_ID, T.TOUR_DIRECTION FROM TOUR AS T ORDER BY T.TOUR_DIRECTION";

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TourDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Tour> findAll() {
        return namedParameterJdbcTemplate.query(SQL_GET_ALL_TOUR, new TourRowMapper());
    }

    @Override
    public Optional<Tour> findById(Integer tourId) {
        return Optional.empty();
    }

    @Override
    public Integer create(Tour tour) {
        return null;
    }

    @Override
    public Integer update(Tour tour) {
        return null;
    }

    @Override
    public Integer delete(Integer tourId) {
        return null;
    }

    private class TourRowMapper implements RowMapper<Tour> {

        @Override
        public Tour mapRow(ResultSet resultSet, int i) throws SQLException {
                Tour tour = new Tour();
                tour.setTourId(resultSet.getInt("TOUR_ID"));
                tour.setDirection(resultSet.getString("TOUR_DIRECTION"));
                return tour;
        }
    }

}
