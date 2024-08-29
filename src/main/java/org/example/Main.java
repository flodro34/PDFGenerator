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

        Table table = new Table(twocolumnWidth);
        table.addCell(new Cell().add("Facture").setFontSize(20f) .setBorder(Border.NO_BORDER).setBold());
        Table nestedtable = new Table(new float[]{twocol/2, twocol/2});
        nestedtable.addCell(new Cell().add("Facture No.").setBold().setBorder(Border.NO_BORDER));
        nestedtable.addCell(new Cell().add("PO123456").setBorder(Border.NO_BORDER));
        nestedtable.addCell(new Cell().add("Facture Date :").setBold().setBorder(Border.NO_BORDER));
        nestedtable.addCell(new Cell().add("15/07/2024").setBorder(Border.NO_BORDER));

        table.addCell(new Cell().add(nestedtable).setBorder(Border.NO_BORDER));

        Border gb  = new SolidBorder(Color.GRAY,2f);
        Table divider = new Table(fullwidth);
        divider.setBorder(gb);

        document.add(table);
        document.add(divider);

        document.close();
        System.out.println("pdf generated !");
    }
}