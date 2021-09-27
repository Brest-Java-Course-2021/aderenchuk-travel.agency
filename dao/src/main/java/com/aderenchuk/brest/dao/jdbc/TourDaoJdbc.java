package com.aderenchuk.brest.dao.jdbc;

import com.aderenchuk.brest.dao.TourDao;
import com.aderenchuk.brest.model.Tour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class TourDaoJdbc implements TourDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourDaoJdbc.class);

    @InjectSql("/sql/tour/findAll.sql")
    private String findAllSql;

    @InjectSql("/sql/tour/findById.sql")
    private String findByIdSql;

    @InjectSql("/sql/tour/create.sql")
    private String createSql;

    @InjectSql("/sql/tour/update.sql")
    private String updateSql;

    @InjectSql("/sql/tour/check.sql")
    private String checkSql;

    @InjectSql("/sql/tour/delete.sql")
    private String deleteSql;


    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TourDaoJdbc(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Tour> findAll() {
        LOGGER.debug("Find all tours");
        return namedParameterJdbcTemplate.query(findAllSql, new TourRowMapper());
    }

    @Override
    public Optional<Tour> findById(Integer tourId) {
        LOGGER.debug("Find tour by id: {}", tourId);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("TOUR_ID", tourId);
        List<Tour> results = namedParameterJdbcTemplate.query(findByIdSql, sqlParameterSource, new TourRowMapper());
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));

    }

    @Override
    public Integer create(Tour tour) {
        LOGGER.debug("Find tour by id: {}", tour);
        if (!isTourDirectionUnique(tour)) {
            throw new IllegalArgumentException("Tour with the same direction");
        }
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("DIRECTION", tour.getDirection())
                .addValue("DATE_TOUR", tour.getDateTour());
        namedParameterJdbcTemplate.update(createSql, sqlParameterSource, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey().intValue());
    }

    public boolean isTourDirectionUnique(Tour tour) {
        return namedParameterJdbcTemplate.queryForObject(checkSql,
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
        return namedParameterJdbcTemplate.update(updateSql, sqlParameterSource);
    }

    @Override
    public Integer delete(Integer tourId) {
        LOGGER.debug("Delete tour: {}", tourId);
        SqlParameterSource sqlParameterSource =
                new MapSqlParameterSource()
                        .addValue("TOUR_ID", tourId);
        return namedParameterJdbcTemplate.update(deleteSql, sqlParameterSource);
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
