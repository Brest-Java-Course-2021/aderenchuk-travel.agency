package com.aderenchuk.brest.service.web_app;

import com.aderenchuk.brest.service.ClientArchiveDbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
public class ClientArchiveDbController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientArchiveDbController.class);

    @Autowired
    private ClientArchiveDbService clientArchiveDbService;

    @GetMapping(value = "/parsing/clientDb")
    public void archiveDb(HttpServletResponse response) throws IOException {

        LOGGER.debug("saveArchiveDb()");
        String clientList = String.valueOf(clientArchiveDbService.saveArchiveDb());
        String fileName = "clientParsingDataDb.txt";
        String fileContent = clientList;

            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=ClientDb.zip");
            response.setStatus(HttpServletResponse.SC_OK);

            try (ZipOutputStream zippedOut = new ZipOutputStream(response.getOutputStream())) {
                if (!fileName.isEmpty()) {
                    FileSystemResource resource = new FileSystemResource(fileName);

                    ZipEntry e = new ZipEntry(resource.getFilename());
                    e.setSize(resource.contentLength());
                    zippedOut.putNextEntry(e);
                    StreamUtils.copy(resource.getInputStream(), zippedOut);
                    zippedOut.closeEntry();
                }
                zippedOut.finish();
            }
        }


    @GetMapping(value = "/rewriting/clientDb")
    public String rewritingDb(HttpServletResponse response) throws IOException {

        BufferedReader inputStream = new BufferedReader(new FileReader(
                "/home/artem/IdeaProjects/aderenchuk-travel.agency/clientParsingDataDb.txt"));

        String dirPath = System.getProperty("user.dir");
        File file = File.createTempFile("clientData", ".tmp", new File(dirPath));
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

        return "clients";
    }


}
