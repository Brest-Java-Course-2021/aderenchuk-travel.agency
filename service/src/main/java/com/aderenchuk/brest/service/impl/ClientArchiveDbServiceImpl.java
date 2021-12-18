package com.aderenchuk.brest.service.impl;

import com.aderenchuk.brest.model.Client;
import com.aderenchuk.brest.service.ClientArchiveDbService;
import com.aderenchuk.brest.service.impl.sax_parsing.SaxMyParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ClientArchiveDbServiceImpl implements ClientArchiveDbService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientExportServiceImpl.class);


    @Override
    public List<Client> saveArchiveDb() throws IOException {
        LOGGER.trace("saveArchiveDb()");
        SaxMyParser parser = new SaxMyParser();
        List<Client> clientList = parser.parseClient();
        return clientList;
    }
}
