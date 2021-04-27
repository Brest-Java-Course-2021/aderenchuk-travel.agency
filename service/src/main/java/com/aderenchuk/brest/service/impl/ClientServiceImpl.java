package com.aderenchuk.brest.service.impl;

import com.aderenchuk.brest.dao.ClientDao;
import com.aderenchuk.brest.dao.jdbc.TourDaoJdbc;
import com.aderenchuk.brest.model.Client;
import com.aderenchuk.brest.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourDaoJdbc.class);

    private final ClientDao clientDao;

    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }


    @Override
    public List<Client> findAll() {
        return clientDao.findAll();
    }

    @Override
    public Optional<Client> findById(Integer clientId) {
        return clientDao.findById(clientId);
    }

    @Override
    public Integer create(Client client) {
        return clientDao.create(client);
    }

    @Override
    public Integer update(Client client) {
        return clientDao.update(client);
    }

    @Override
    public Integer delete(Integer clientId) {
        return clientDao.delete(clientId);
    }
}