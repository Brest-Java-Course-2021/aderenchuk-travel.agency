package com.aderenchuk.brest.service.impl;

import com.aderenchuk.brest.dao.jpa.ClientDaoJPA;
import com.aderenchuk.brest.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ClientServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Autowired
    private final ClientDaoJPA clientDaoJPA;

    public ClientServiceImpl(ClientDaoJPA clientDaoJPA) {
        this.clientDaoJPA = clientDaoJPA;
    }


    @Transactional(readOnly = true)
    public Page<Client> findAll(Pageable page) {
        LOGGER.trace("findAll()");
        return clientDaoJPA.findAll(page);
    }


    public Optional<Client> findById(Integer clientId) {
        LOGGER.debug("findById(clientId:{})", clientId);
        return clientDaoJPA.findById(clientId);
    }


    public Client create(Client client) {
        LOGGER.debug("create(client:{})", client);
        return clientDaoJPA.save(client);
    }


    public boolean update(Client client) {
        LOGGER.debug("update(client:{})", client);
        if (clientDaoJPA.existsById(client.getClientId())) {
            clientDaoJPA.save(client);
            return true;
        } else return false;
    }


    public void delete(Integer clientId) {
        LOGGER.debug("delete(clientId:{})", clientId);
        clientDaoJPA.deleteById(clientId);
    }
}