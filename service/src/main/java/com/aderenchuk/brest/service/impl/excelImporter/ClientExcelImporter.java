package com.aderenchuk.brest.service.impl.excelImporter;

import com.aderenchuk.brest.model.Client;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClientExcelImporter {
    public List<Client> excelImport() {
        List<Client> clientList = new ArrayList<>();
        String firstName = "";
        String lastName = "";
        int tourId = 0;

        String excelFilePath = "/home/artem/IdeaProjects/aderenchuk-travel.agency/client.xlsx";

        try {
            FileInputStream inputStream = new FileInputStream(excelFilePath);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = firstSheet.iterator();
            rowIterator.next();

            while (rowIterator.hasNext()) {
                Row nextRow = rowIterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell nextCell = cellIterator.next();
                    int columnIndex = nextCell.getColumnIndex();
                    switch (columnIndex) {
                        case 0:
                            firstName = nextCell.getStringCellValue();
                            System.out.println(firstName);
                            break;
                        case 1:
                            lastName = nextCell.getStringCellValue();
                            System.out.println(lastName);
                            break;
                        case 2:
                            tourId = (int) nextCell.getNumericCellValue();
                            System.out.println(tourId);
                            break;
                    }
                    clientList.add(new Client(firstName, lastName, tourId));
                }
            }
            workbook.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
        return clientList;
    }
}
