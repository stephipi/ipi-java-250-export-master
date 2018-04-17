package com.example.demo.service.export;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ClientDTO;
import java.io.OutputStream;

@Service
public class ExportXLXSPoiService {
	
	public void export(OutputStream os, ClientDTO client) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("client_"+client.getNom()+"_"+client.getPrenom());
		
        XSSFRow headerRow = sheet.createRow(0);
        XSSFCell cellDesignation = headerRow.createCell(0);
        XSSFCell cellQuantite = headerRow.createCell(1);
        XSSFCell cellPrixUnitaire = headerRow.createCell(2);
        XSSFCell cellPrixLigne = headerRow.createCell(3);
        cellDesignation.setCellValue("Désignation");
        cellQuantite.setCellValue("Quantité");
        cellPrixUnitaire.setCellValue("PrixUnitaire");
        cellPrixLigne.setCellValue("PrixLigne");
        
		workbook.write(os);
		workbook.close();
	}
}