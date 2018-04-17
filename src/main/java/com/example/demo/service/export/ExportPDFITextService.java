package com.example.demo.service.export;

import com.example.demo.dto.FactureDTO;
import com.example.demo.dto.LigneFactureDTO;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;

@Service
public class ExportPDFITextService {

    public void export(OutputStream os, FactureDTO facture) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, os);
        
        Double totaux = 0.0;
        
        Font font1 = new Font(Font.FontFamily.HELVETICA  , 12, Font.BOLD);
        Font font2 = new Font(Font.FontFamily.HELVETICA    , 8);
        Font font3 = new Font(Font.FontFamily.HELVETICA, 10);
        Font font4 = new Font(Font.FontFamily.HELVETICA, 18);
        
        BaseColor myColor = WebColors.getRGBColor("#9999DD");
        document.open();

        //INTITULE

        document.add(new Phrase("Client : "));
        document.add(new Phrase(facture.getClient().getNom()));
        document.add(new Phrase(" "));
        document.add(new Phrase(facture.getClient().getPrenom()));

        Paragraph titre = new Paragraph("FACTURE N°"+facture.getId(), font4);
        titre.setAlignment(Element.ALIGN_CENTER);
        document.add(titre);
        
        PdfPTable table = new PdfPTable(4); // 4 columns.

        PdfPCell cell1 = new PdfPCell(new Paragraph("DESCRIPTION", font1));
        PdfPCell cell2 = new PdfPCell(new Paragraph("PRIX UNITAIRE", font1));
        PdfPCell cell3 = new PdfPCell(new Paragraph("QUANTITE", font1));
        PdfPCell cell4 = new PdfPCell(new Paragraph("TOTAL", font1));

        cell1.setBackgroundColor(myColor);
        cell2.setBackgroundColor(myColor);
        cell3.setBackgroundColor(myColor);
        cell4.setBackgroundColor(myColor);
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);

        for (LigneFactureDTO ligneFactures : facture.getLigneFactures())
        {
            PdfPCell cell11 = new PdfPCell(new Paragraph(ligneFactures.getDesignation().toString(), font3));
            PdfPCell cell21 = new PdfPCell(new Paragraph(ligneFactures.getPrixUnitaire().toString(), font3));
            PdfPCell cell31 = new PdfPCell(new Paragraph(ligneFactures.getQuantite().toString(), font3));
            Double total = ligneFactures.getPrixUnitaire()*ligneFactures.getQuantite();
            PdfPCell cell41 = new PdfPCell(new Paragraph(total.toString(), font3));
            

            table.addCell(cell11);
            table.addCell(cell21);
            table.addCell(cell31);
            table.addCell(cell41);
            totaux += total;
        }

        PdfPCell cell111 = new PdfPCell(new Paragraph("", font3));
        PdfPCell cell211 = new PdfPCell(new Paragraph("", font3));
        PdfPCell cell311 = new PdfPCell(new Paragraph("TOTAL", font1));
        PdfPCell cell411 = new PdfPCell(new Paragraph(totaux.toString(), font1));
        
        cell111.setBorder(Rectangle.NO_BORDER);
        cell211.setBorder(Rectangle.NO_BORDER);
        cell311.setBackgroundColor(myColor);
        cell411.setBackgroundColor(myColor);
        table.addCell(cell111);
        table.addCell(cell211);
        table.addCell(cell311);
        table.addCell(cell411);
        
        document.add(table);
        
		document.add(new Paragraph("Pour toute réclamation, demande d'innformation, veuillez contacter le support du fournisseur", font2));

		Anchor anchor = new Anchor("http://monsite.com", font2);
		anchor.setReference("http://monsite.com");

		Paragraph paragraph = new Paragraph();
	    paragraph.add(anchor);
	    document.add(paragraph);
	    
		document.add(new Paragraph("En cas de retard de paiement, seront exigibles, conformément à l'article L. 441-6 du code de commerce, une indemnité calculée sur la base de trois fois le taux de l'intérêt légal en vigueur ainsi qu'une indemnité forfaitaire pour frais de recouvrement de 40 euros.", font2));

		document.add(new Paragraph("STEPHANE RIPE - 64 rue Paul Verlaine - 69005 LYON", font2));
		document.add(new Paragraph("STEPHANE RIPE : B 404 666 042 - SIRET : 835 356 564 69007", font2));
		
        document.close();
    }
}
