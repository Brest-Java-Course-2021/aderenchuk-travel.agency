package com.aderenchuk.brest.service.openApi;

import com.aderenchuk.brest.api.ClientsApi;
import com.aderenchuk.brest.models.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ClientServiceImplOpenApi implements ClientsApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientsApi.class);


    @Override
    public ResponseEntity<List<Client>> clientsUsingGET() {
        LOGGER.trace("clientsUsingGET()");
        return clientsUsingGET();
    }

    @Override
    public ResponseEntity<Integer> createClientUsingPOST(Client body) {
        LOGGER.debug("createTourUsingPOST(body:{})", body);
        return createClientUsingPOST(body);
    }

    @Override
    public ResponseEntity<Integer> deleteClientUsingDELETE(Integer id) {
        LOGGER.debug("deleteTourUsingDELETE(id:{})", id);
        return deleteClientUsingDELETE(id);
    }

    @Override
    public ResponseEntity<Client> findByIdUsingGET(Integer id) {
        LOGGER.debug("tourByIdUsingGET(id:{})", id);
        return findByIdUsingGET(id);
    }

    @Override
    public ResponseEntity<Integer> updateClientUsingPUT(Client body) {
        LOGGER.debug("updateTourUsingPUT(body:{})", body);
        return updateClientUsingPUT(body);
    }
}
