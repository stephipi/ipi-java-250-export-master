package com.example.demo.service.export;

import com.example.demo.dto.ClientDTO;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
public class ExportXLSXService {

    public void export(OutputStream os, List<ClientDTO> clients) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("clients");

        XSSFRow headerRow = sheet.createRow(0);
        XSSFCell cellNom = headerRow.createCell(0);
        XSSFCell cellPrenom = headerRow.createCell(1);
        cellNom.setCellValue("Nom");
        cellPrenom.setCellValue("Prénom");

        for (int i = 0; i < clients.size(); i++) {
        	XSSFRow headerRowItem = sheet.createRow(i+1);
            XSSFCell cellNomItem = headerRowItem.createCell(0);
            XSSFCell cellPrenomItem = headerRowItem.createCell(1);
            cellNomItem.setCellValue(clients.get(i).getNom());
            cellPrenomItem.setCellValue(clients.get(i).getPrenom());
        }
        
        workbook.write(os);
        workbook.close();
    }
}
