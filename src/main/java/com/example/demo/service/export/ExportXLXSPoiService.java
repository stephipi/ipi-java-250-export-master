package com.example.demo.service.export;

import java.io.IOException;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

//import com.example.demo.dto.ClientDTO;
import com.example.demo.dto.FactureDTO;
//import com.example.demo.service.FactureService;

import java.util.List;

import javax.servlet.ServletOutputStream;

@Service
public class ExportXLXSPoiService {

	public void export(ServletOutputStream os, List<FactureDTO> factures, Long clientId)  throws IOException {
        Double totaux = 0.0;
        int totalLignes = 0;
        
		XSSFWorkbook workbook = new XSSFWorkbook();
		for (FactureDTO facture : factures) 
		{
			if (facture.getClient().getId() == clientId)
			{
				XSSFSheet sheet = workbook.createSheet("facture_"+facture.getId());
				XSSFRow headerRow = sheet.createRow(0);
		        XSSFCell cellDesignation = headerRow.createCell(0);
		        XSSFCell cellQuantite = headerRow.createCell(1);
		        XSSFCell cellPrixUnitaire = headerRow.createCell(2);
		        XSSFCell cellPrixLigne = headerRow.createCell(3);
		        
		        cellDesignation.setCellValue("Désignation");
		        cellQuantite.setCellValue("Quantité");
		        cellPrixUnitaire.setCellValue("PrixUnitaire");
		        cellPrixLigne.setCellValue("PrixLigne");
		        
		        totalLignes = facture.getLigneFactures().size();
		        totaux = 0.0;
		        for (int i = 0; i < totalLignes; i++)
		        {
					XSSFRow headerRowItem = sheet.createRow(i+1);
			        XSSFCell cellDesignationItem = headerRowItem.createCell(0);
			        XSSFCell cellQuantiteItem = headerRowItem.createCell(1);
			        XSSFCell cellPrixUnitaireItem = headerRowItem.createCell(2);
			        XSSFCell cellPrixLigneItem = headerRowItem.createCell(3);

			        cellDesignationItem.setCellValue(facture.getLigneFactures().get(i).getDesignation());
			        cellQuantiteItem.setCellValue(facture.getLigneFactures().get(i).getQuantite());
			        cellPrixUnitaireItem.setCellValue(facture.getLigneFactures().get(i).getPrixUnitaire());
			        cellPrixLigneItem.setCellValue(cellQuantiteItem.getNumericCellValue()*cellPrixUnitaireItem.getNumericCellValue());
			        totaux += cellQuantiteItem.getNumericCellValue()*cellPrixUnitaireItem.getNumericCellValue();
		        }
		        
		        XSSFRow headerRowTotaux = sheet.createRow(totalLignes+1);

		        XSSFCell cellTotauxLabel = (XSSFCell) headerRowTotaux.createCell(0);
		        cellTotauxLabel.setCellValue("TOTAUX");
		        
		        XSSFCell cellTotauxValue = headerRowTotaux.createCell(3);
		        cellTotauxValue.setCellValue(totaux);

		        sheet.addMergedRegion(new CellRangeAddress(totalLignes+1, totalLignes+1, 0,2));
			}
		}

		workbook.write(os);
		workbook.close();
				
	}
}