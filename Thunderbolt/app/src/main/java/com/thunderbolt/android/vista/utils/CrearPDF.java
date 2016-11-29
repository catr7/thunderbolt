package com.thunderbolt.android.vista.utils;

import android.os.Environment;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Andres y Jess on 29/11/2016.
 */

public class CrearPDF {

    public static void crear(String nombrePDF){
        Document doc=new Document();
        String outpath= new StringBuilder().append(Environment.getExternalStorageDirectory()).append("/").append(nombrePDF).toString();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(outpath));
            doc.open();
            Paragraph preface = new Paragraph();
            preface.add(new Paragraph("Datos Del Proyecto",new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD)));
            doc.add(preface);
            doc.close();
        } catch (DocumentException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
