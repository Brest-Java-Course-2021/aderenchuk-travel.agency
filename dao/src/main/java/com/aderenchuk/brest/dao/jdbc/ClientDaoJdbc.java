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

import javax.sql.DataSource;
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


//    private final ClientRowMapper clientRowMapper = new ClientRowMapper();

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ClientDaoJdbc(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Client> findAll() {
        LOGGER.debug("findAll()");
        return namedParameterJdbcTemplate.query(selectSql, new ClientRowMapper());
    }

    @Override
    public Optional<Client> findById(Integer clientId) {
        LOGGER.debug("findById(client_id:{})", clientId);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(CLIENT_ID, clientId);
        List<Client> results = namedParameterJdbcTemplate.query(findByIdSql, sqlParameterSource, new ClientRowMapper());
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    @Override
    public Integer create(Client client) {
        LOGGER.debug("create(client:{})", client);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
            .addValue(FIRST_NAME, client.getFirstName())
            .addValue(LAST_NAME, client.getLastName())
            .addValue(TOUR_ID, client.getTourId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        //sadasdasasd
        namedParameterJdbcTemplate.update(createSql, sqlParameterSource, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey().intValue());
    }

    @Override
    public Integer update(Client client) {
        LOGGER.debug("update(client:{})", client);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
            .addValue(FIRST_NAME, client.getFirstName())
            .addValue(LAST_NAME, client.getLastName())
            .addValue(TOUR_ID, client.getTourId())
            .addValue(CLIENT_ID, client.getClientId());
        //dsfsdfsdfds
        return namedParameterJdbcTemplate.update(updateSql, sqlParameterSource);
    }

    @Override
    public Integer delete(Integer clientId) {
        LOGGER.debug("delete(client_id:{})");
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
        .addValue(CLIENT_ID, clientId);
        return namedParameterJdbcTemplate.update(deleteSql, sqlParameterSource);
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
