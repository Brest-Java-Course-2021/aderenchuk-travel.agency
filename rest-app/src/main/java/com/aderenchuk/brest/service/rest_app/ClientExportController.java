package com.aderenchuk.brest.service.rest_app;

import com.aderenchuk.brest.model.Client;
import com.aderenchuk.brest.service.ClientExportService;
import com.aderenchuk.brest.service.impl.excelExport.ClientExcelExporter;
import com.aderenchuk.brest.service.impl.excelImporter.ClientExcelImporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class ClientExportController {

    @Autowired
    private ClientExportService clientExportService;

    @RequestMapping("export/excel")
    @ResponseBody
    public String exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Client_info.xlsx";

        response.setHeader(headerKey, headerValue);
        List<Client> clientList = clientExportService.findAll();
        ClientExcelExporter exp = new ClientExcelExporter(clientList);
        exp.export(response);
        return "Export Successfully";
    }

    @RequestMapping("import/excel")
    @ResponseBody
    public String importFromExcel() {
        ClientExcelImporter clientExcelImporter = new ClientExcelImporter();
        List<Client> clientList = clientExcelImporter.excelImport();
        return "Import Successfully";
    }
}
