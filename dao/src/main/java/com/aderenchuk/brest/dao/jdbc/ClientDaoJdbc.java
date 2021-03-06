package com.aderenchuk.brest.dao.jdbc;

import com.aderenchuk.brest.dao.ClientDao;
import com.aderenchuk.brest.model.Client;
import com.aderenchuk.brest.model.constants.ClientConstants;
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
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ClientDaoJdbc implements ClientDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourDaoJdbc.class);

    @InjectSql("/sql/client/findAll.sql")
    private String findAllSql;

    @InjectSql("/sql/client/findById.sql")
    private String findByIdSql;

    @InjectSql("/sql/client/create.sql")
    private String createSql;

    @InjectSql("/sql/client/update.sql")
    private String updateSql;

    @InjectSql("/sql/client/check.sql")
    private String checkSql;

    @InjectSql("/sql/client/delete.sql")
    private String deleteSql;


    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ClientDaoJdbc(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Client> findAll() {
        LOGGER.debug("findAll()");
        return namedParameterJdbcTemplate.query(findAllSql, new ClientRowMapper());
    }

    @Override
    public Optional<Client> findById(Integer clientId) {
        LOGGER.debug("findById(client_id:{})", clientId);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(ClientConstants.CLIENT_ID, clientId);
        List<Client> results = namedParameterJdbcTemplate.query(findByIdSql, sqlParameterSource, new ClientRowMapper());
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    @Override
    public Integer create(Client client) {
        LOGGER.debug("create(client:{})", client);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
            .addValue(ClientConstants.FIRST_NAME, client.getFirstName())
            .addValue(ClientConstants.LAST_NAME, client.getLastName())
            .addValue(ClientConstants.TOUR_ID, client.getTourId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(createSql, sqlParameterSource, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey().intValue());
    }

    @Override
    public Integer update(Client client) {
        LOGGER.debug("update(client:{})", client);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
            .addValue(ClientConstants.FIRST_NAME, client.getFirstName())
            .addValue(ClientConstants.LAST_NAME, client.getLastName())
            .addValue(ClientConstants.TOUR_ID, client.getTourId())
            .addValue(ClientConstants.CLIENT_ID, client.getClientId());
        return namedParameterJdbcTemplate.update(updateSql, sqlParameterSource);
    }

    @Override
    public Integer delete(Integer clientId) {
        LOGGER.debug("delete(client_id:{})", clientId);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
        .addValue(ClientConstants.CLIENT_ID, clientId);
        return namedParameterJdbcTemplate.update(deleteSql, sqlParameterSource);
    }

    private class ClientRowMapper implements RowMapper<Client> {

        @Override
        public Client mapRow(ResultSet resultSet, int i) throws SQLException {
            Client client = new Client();
            client.setClientId(resultSet.getInt(ClientConstants.CLIENT_ID));
            client.setFirstName(resultSet.getString(ClientConstants.FIRST_NAME));
            client.setLastName(resultSet.getString(ClientConstants.LAST_NAME));
            client.setTourId(resultSet.getInt(ClientConstants.TOUR_ID));
            return client;
        }
    }
}
