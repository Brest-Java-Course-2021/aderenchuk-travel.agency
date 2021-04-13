package com.aderenchuk.brest.dao;

import com.aderenchuk.brest.model.Client;
import com.aderenchuk.brest.model.Tour;

import java.util.List;
import java.util.Optional;

public interface ClientDao {

    List<Client> findAll();

    Optional<Client> findById(Integer clientId);

    Integer create(Client client);

    Integer update(Client client);

    Integer delete(Integer clientId);
}
