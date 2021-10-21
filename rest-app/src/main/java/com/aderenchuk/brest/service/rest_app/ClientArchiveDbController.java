package com.aderenchuk.brest.service.rest_app;

import com.aderenchuk.brest.model.Client;
import com.aderenchuk.brest.service.ClientArchiveDbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
@RestController
public class ClientArchiveDbController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientArchiveDbController.class);

    @Autowired
    private ClientArchiveDbService clientArchiveDbService;

    @RequestMapping("parsing/clientDb")
    public List<Client> archiveDb(HttpServletResponse response) throws IOException {

        LOGGER.debug("saveArchiveDb()");
        List<Client> clientList = clientArchiveDbService.saveArchiveDb();


        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=clientParsingDataDb.txt";

        response.setHeader(headerKey, headerValue);

        return clientList;

    }

    @RequestMapping("rewriting/clientDb")
    public String rewritingDb(HttpServletResponse response) throws IOException {

        BufferedReader inputStream = new BufferedReader(new FileReader(
                "/home/artem/IdeaProjects/aderenchuk-travel.agency/clientParsingDataDb.txt"));

        File file = new File("/home/artem/IdeaProjects/aderenchuk-travel.agency/test-db/src/main/resources/clientData.sql");
        BufferedWriter outputStream = new BufferedWriter(new FileWriter(
                file));

        // if File doesnt exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }
        PrintWriter writer = new PrintWriter(file);
        writer.print("");
        writer.close();
        String count;
        while ((count = inputStream.readLine()) != null) {
            outputStream.write(count);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();

        return "all data is rewriting";
    }


}
