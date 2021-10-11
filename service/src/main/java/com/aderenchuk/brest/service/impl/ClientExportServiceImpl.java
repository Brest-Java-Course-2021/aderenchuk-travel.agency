package com.aderenchuk.brest.service.impl;

import com.aderenchuk.brest.model.Client;
import com.aderenchuk.brest.service.ClientExportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ClientExportServiceImpl implements ClientExportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientExportServiceImpl.class);


    @Override
    public List<Client> findAll() {
        LOGGER.trace("findAll()");

        Client client = new Client("Ihor", "Dmitriev", 101);
        Client client1 = new Client("Alex", "Volkanovski", 103);
        Client client2 = new Client("Irina", "Sheyk", 103);
        Client client3 = new Client("Leonel", "Messi", 104);
        Client client4 = new Client("Polina", "Chistyakova", 104);
        Client client5 = new Client("Anna", "Sedakova", 102);
        Client client6 = new Client("Gareth", "Bale", 102);
        Client client7 = new Client("Toni", "Kross", 102);
        List<Client> clientList = new ArrayList<>();
        clientList.add(client);
        clientList.add(client1);
        clientList.add(client2);
        clientList.add(client3);
        clientList.add(client4);
        clientList.add(client5);
        clientList.add(client6);
        clientList.add(client7);
        return clientList;
    }
}
