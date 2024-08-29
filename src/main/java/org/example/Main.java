package org.example;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

import java.io.FileNotFoundException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "invoice.pdf";
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument  = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfDocument);
        //document.add(new Paragraph("Hello Floflo !"));
        float threecol = 190f;
        float twocol = 285f;
        float twocol150 = twocol + 150f;
        float[] twocolumnWidth = {twocol150, twocol};
        float[] fullwidth ={threecol*3};
        Paragraph onesp = new Paragraph("\n");

        Table table = new Table(twocolumnWidth);
        table.addCell(new Cell().add("Facture").setFontSize(20f) .setBorder(Border.NO_BORDER).setBold());
        Table nestedtable = new Table(new float[]{twocol/2, twocol/2});
        nestedtable.addCell(getHeaderTextCell("Facture No."));
        nestedtable.addCell(getHeaderTextCellValue("PO123456"));
        nestedtable.addCell(getHeaderTextCell("Facture Date :"));
        nestedtable.addCell(getHeaderTextCellValue("15/07/2024"));

        table.addCell(new Cell().add(nestedtable).setBorder(Border.NO_BORDER));

        Border gb  = new SolidBorder(Color.GRAY,2f);
        Table divider = new Table(fullwidth);
        divider.setBorder(gb);

        document.add(table);
        document.add(onesp);
        document.add(divider);
        document.add(onesp);

        Table twoColTable = new Table(twocolumnWidth);
        twoColTable.addCell(getBillingAndShippingCell("Informations de Facturation"));
        twoColTable.addCell(getBillingAndShippingCell("Informations d'envoi"));
        document.add(twoColTable.setMarginBottom(12f));

        Table twoColTable2 = new Table(twocolumnWidth);
        twoColTable2.addCell(getCell10Left("Entreprise", true));
        twoColTable2.addCell(getCell10Left("Nom", true));
        twoColTable2.addCell(getCell10Left("factice Ets", false));
        twoColTable2.addCell(getCell10Left("Toto", false));
        document.add(twoColTable2);

        Table twoColTable3 = new Table(twocolumnWidth);
        twoColTable3.addCell(getCell10Left("Nom", true));
        twoColTable3.addCell(getCell10Left("Adresse", true));
        twoColTable3.addCell(getCell10Left(" Toto", false));
        twoColTable3.addCell(getCell10Left(" 5 impasse des joncs 34000 Montpellier", false));
        document.add(twoColTable3);

        document.close();
        System.out.println("pdf generated !");

    }
    static Cell getHeaderTextCell(String textValue){
        return new Cell().add(textValue).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);
    }
    static Cell getHeaderTextCellValue(String textValue){
        return new Cell().add(textValue).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }
    static Cell getBillingAndShippingCell(String textValue){
        return new Cell().add(textValue).setFontSize(12f).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }

    static Cell getCell10Left(String textValue, Boolean isBold){
        Cell myCell = new Cell().add(textValue).setFontSize(10f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
        return isBold ?myCell.setBold():myCell;
    }
}