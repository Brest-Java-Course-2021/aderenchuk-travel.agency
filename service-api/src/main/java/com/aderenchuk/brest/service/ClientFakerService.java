package com.aderenchuk.brest.service;

import com.aderenchuk.brest.model.Client;

import java.util.List;

public interface ClientFakerService {

    /**
     * Find all clients.
     * @return clients list.
     */
    List<Client> findAll();
}
