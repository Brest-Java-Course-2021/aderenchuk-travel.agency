package com.aderenchuk.brest.dao;

import com.aderenchuk.brest.model.Client;
import com.aderenchuk.brest.model.Tour;

import java.util.List;
import java.util.Optional;

public interface ClientDao {

    /**
     * Find all clients.
     * @return clients list.
     */
    List<Client> findAll();

    /**
     * Find clients by id.
     * @param clientId Client id.
     * @return Client.
     */
    Optional<Client> findById(Integer clientId);

    /**
     * Create new client.
     * @param client clients.
     * @return persisted client id.
     */
    Integer create(Client client);

    /**
     * Update client.
     * @param client client.
     * @return number of updated record in the database.
     */
    Integer update(Client client);

    /**
     * Delete client.
     * @param clientId client id.
     * @return number of deleted record in the database.
     */
    Integer delete(Integer clientId);
}
