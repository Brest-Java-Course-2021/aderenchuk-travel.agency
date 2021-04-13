package com.aderenchuk.brest.dao.jdbc;

import com.aderenchuk.brest.dao.ClientDao;
import com.aderenchuk.brest.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Optional;

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


    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ClientDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Client> findAll() {
        return null;
    }

    @Override
    public Optional<Client> findById(Integer clientId) {
        return Optional.empty();
    }

    @Override
    public Integer create(Client client) {
        return null;
    }

    @Override
    public Integer update(Client client) {
        return null;
    }

    @Override
    public Integer delete(Integer clientId) {
        return null;
    }
}
