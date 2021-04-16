package com.aderenchuk.brest.dao.jdbc;

import com.aderenchuk.brest.dao.ClientDao;
import com.aderenchuk.brest.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.aderenchuk.brest.constants.ClientConstants.*;

@Repository
public class ClientDaoJdbc implements ClientDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourDaoJdbc.class);

    @Value("${client.select}")
    private String selectSql;

    @Value("${client.findById}")
    private String findByIdSql;

    @Value("${client.create}")
    private String createSql;

    @Value("${client.update}")
    private String updateSql;

    @Value("${client.check}")
    private String checkSql;

    @Value("${client.delete}")
    private String deleteSql;


    private final ClientRowMapper clientRowMapper = new ClientRowMapper();

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ClientDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Client> findAll() {
        LOGGER.debug("findAll()");
        return namedParameterJdbcTemplate.query(selectSql, clientRowMapper);
    }

    @Override
    public Optional<Client> findById(Integer clientId) {
        LOGGER.debug("findById(client_id:{})", clientId);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(CLIENT_ID, clientId);
        List<Client> results = namedParameterJdbcTemplate.query(findByIdSql, sqlParameterSource, clientRowMapper);
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    @Override
    public Integer create(Client client) {
        LOGGER.debug("create(client:{})", client);
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(FIRST_NAME, client.getFirstName());
        params.addValue(LAST_NAME, client.getLastName());
        params.addValue(TOUR_ID, client.getTourId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(createSql, params, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public Integer update(Client client) {
        LOGGER.debug("update(client:{})", client);
        Map<String, Object> params = new HashMap<>();
        params.put(FIRST_NAME, client.getFirstName());
        params.put(LAST_NAME, client.getLastName());
        params.put(TOUR_ID, client.getTourId());
        params.put(CLIENT_ID, client.getClientId());
        return namedParameterJdbcTemplate.update(updateSql, params);
    }

    @Override
    public Integer delete(Integer clientId) {
        LOGGER.debug("delete(client_id:{})");
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(CLIENT_ID, clientId);
        return namedParameterJdbcTemplate.update(updateSql, mapSqlParameterSource);
    }

    private class ClientRowMapper implements RowMapper<Client> {

        @Override
        public Client mapRow(ResultSet resultSet, int i) throws SQLException {
            Client client = new Client();
            client.setClientId(resultSet.getInt(CLIENT_ID));
            client.setFirstName(resultSet.getString(FIRST_NAME));
            client.setLastName(resultSet.getString(LAST_NAME));
            client.setTourId(resultSet.getInt(TOUR_ID));
            return client;
        }
    }
}
