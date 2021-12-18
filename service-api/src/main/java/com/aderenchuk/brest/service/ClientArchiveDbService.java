package com.aderenchuk.brest.service;

import com.aderenchuk.brest.model.Client;

import java.io.IOException;
import java.util.List;

public interface ClientArchiveDbService {

    /**
     * Save archive db clients.
     *
     * @save archive client db.
     */
    List<Client> saveArchiveDb() throws IOException;

}
