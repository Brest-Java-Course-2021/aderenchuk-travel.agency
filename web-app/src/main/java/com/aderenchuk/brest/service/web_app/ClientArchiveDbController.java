package com.aderenchuk.brest.service.web_app;

import com.aderenchuk.brest.service.ClientArchiveDbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Controller
public class ClientArchiveDbController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientArchiveDbController.class);

    @Autowired
    private ClientArchiveDbService clientArchiveDbService;

    @GetMapping(value = "/parsing/clientDb")
    public ResponseEntity<Resource> archiveDb(HttpServletResponse response) throws IOException {

        LOGGER.debug("saveArchiveDb()");
        String clientList = String.valueOf(clientArchiveDbService.saveArchiveDb());
        String fileName = "clientParsingDataDb.txt";
        String fileContent = clientList;

        final String EXPORT_DIRECTORY = "/home/artem/IdeaProjects/aderenchuk-travel.agency";

        Path filePath = Paths.get(EXPORT_DIRECTORY, fileName);
        Path exportedFilePath = Files.write(filePath, fileContent.getBytes(), StandardOpenOption.CREATE);

        URI exportedFileUri = exportedFilePath.toUri();
        Resource resource = new UrlResource(exportedFileUri);


        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
                .contentLength(resource.contentLength())
                .body(resource);

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
