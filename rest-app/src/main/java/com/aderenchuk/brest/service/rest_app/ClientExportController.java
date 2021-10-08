package com.aderenchuk.brest.service.rest_app;

import com.aderenchuk.brest.service.impl.excelExport.ClientExcelExporter;
import com.aderenchuk.brest.model.Client;
import com.aderenchuk.brest.service.ClientExportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientExportController {

    @Autowired
    private ClientExportService clientExportService;

    @GetMapping("export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue="attachment; filename=Client_info.xlsx";

        response.setHeader(headerKey, headerValue);
        List<Client> clientList = clientExportService.findAll();
        ClientExcelExporter exp = new ClientExcelExporter(clientList);
        exp.export(response);
    }

}
