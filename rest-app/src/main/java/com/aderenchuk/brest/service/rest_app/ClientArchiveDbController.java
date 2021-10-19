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
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@RestController
public class ClientArchiveDbController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientArchiveDbController.class);

    @Autowired
    private ClientArchiveDbService clientArchiveDbService;

    @RequestMapping("parsing/clientDb")
    public List<Client> archiveDb(HttpServletResponse response) throws IOException {

        LOGGER.debug("saveArchiveDb()");
        List<Client> clientList = clientArchiveDbService.saveArchiveDb();


        File file = new File("/home/artem/IdeaProjects/aderenchuk-travel.agency/clientParsingDataDb.txt");

//        ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("/home/artem/IdeaProjects/aderenchuk-travel.agency/dataBase.zip"));
//        ZipEntry entry = new ZipEntry("clientParsingDataDb.txt");
//        zout.putNextEntry(entry);

//        PrintWriter pw = new PrintWriter(file);
//        pw.println(clientList);
//        pw.close();
//        File file1 = new File("/home/artem/IdeaProjects/aderenchuk-travel.agency/DataBase.zip");


        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=clientParsingDataDb.txt";

        response.setHeader(headerKey, headerValue);

//        ServletOutputStream outputStream = response.getOutputStream();
//
//        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file);
//
//        byte[] buffer = new byte[8192];
//
//        int bytesRead = -1;
//
//        while ((bytesRead = inputStream.read(buffer)) != -1) {
//            outputStream.write(buffer, 0, bytesRead);
//        }
//
//
//        inputStream.close();
//        outputStream.close();
        return clientList;

    }

    @RequestMapping("rewriting/clientDb")
    public String rewritingDb(HttpServletResponse response) throws IOException {

//        ZipInputStream zin = new ZipInputStream(new FileInputStream("/home/artem/IdeaProjects/aderenchuk-travel.agency/clientParsingDataDb.txt"));
//        ZipEntry entry;
//        while ((entry = zin.getNextEntry()) != null) {
//            System.out.println(entry.getName());
//            System.out.println(entry.getSize());
//        }
//        zin.close();

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
