package com.example.demo.service.export;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.stereotype.Service;

import com.example.demo.dto.FactureDTO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


@Service
public class ExportPDFITextService {

	public void export(OutputStream os, FactureDTO facture) throws IOException, DocumentException
	{
		Document document = new Document();
		PdfWriter.getInstance(document, os);
		document.open();

		//NOM
		document.add(new Paragraph(facture.getClient().getNom()));
		
		//PRENOM
		document.add(new Paragraph(facture.getClient().getPrenom()));
		
		//LISTE ARTICLES
		//document.add(new Paragraph(facture.getLigneFactures().get(0)));
		
		//PRIX TOTAL
		
		document.close();
	}
}