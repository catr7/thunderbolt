package com.thunderbolt.android.vista.utils;

import android.graphics.Color;
import android.os.Environment;

import com.db.android.model.Proyecto;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Andres y Jess on 29/11/2016.
 */

public class CrearPDF {

    public static void crear(Proyecto proyecto){
        Document doc=new Document();
        String outpath= new StringBuilder().append(Environment.getExternalStorageDirectory()).append("/").append(proyecto.getNombrePDF()).append(".pdf").toString();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(outpath));
            doc.open();
            doc.add(obtenerEncabezado());
            doc.add(new Paragraph(" "));
            doc.add(agregarLineaCentrada("EVALUACIÓN DE NIVEL DE RIESGO", 14, new BaseColor(0,191,165)));
            doc.add(agregarLineaCentrada("(Informe Final de Resultados)", 11, new BaseColor(38,50,56)));
            doc.add(new Paragraph(" "));
            doc.add(datosBasicos(proyecto));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            doc.add(agregarLineaCentrada("- RIESGO: PÉRDIDA DE VIDAS HUMANAS", 12, BaseColor.DARK_GRAY));
            doc.add(tablaRiesgoPerdidaDeVidasHumanas(proyecto));
            doc.add(new Paragraph(" "));
            doc.add(agregarLineaCentrada("- RIESGO: PÉRDIDA DE VIDAS HUMANAS", 12, BaseColor.DARK_GRAY));
            doc.add(tablaRiesgoPerdidaDeVidasHumanas(proyecto));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            doc.add(agregarLineaCentrada("- RIESGO: PÉRDIDA DE VIDAS HUMANAS", 12, BaseColor.DARK_GRAY));
            doc.add(tablaRiesgoPerdidaDeVidasHumanas(proyecto));
            doc.add(new Paragraph(" "));
            doc.add(agregarLineaCentrada("- RIESGO: PÉRDIDA DE VIDAS HUMANAS", 12, BaseColor.DARK_GRAY));
            doc.add(tablaRiesgoPerdidaDeVidasHumanas(proyecto));
            doc.add(new Paragraph(" "));
            doc.close();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static PdfPTable tablaRiesgoPerdidaDeVidasHumanas(Proyecto proyecto) throws DocumentException {
        PdfPTable table = new PdfPTable(6);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);
        float[] columnWidths = {1f, 3f, 3f, 3f, 3f, 3f};
        table.setWidths(columnWidths);

        PdfPCell cell01 = new PdfPCell(agregarLineaCentrada("", 11, BaseColor.DARK_GRAY));
        PdfPCell cell02 = new PdfPCell(agregarLineaCentrada("FUENTE DE DAÑO", 11, new BaseColor(0,191,165)));
        cell02.setPadding(5);
        cell01.setRowspan(2);
        cell01.setColspan(2);
        cell01.setBorder(PdfPCell.NO_BORDER);
        cell02.setColspan(4);
        cell02.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell01);
        table.addCell(cell02);

        PdfPCell cell11 = new PdfPCell(agregarLineaCentrada("S1 - Descarga de rayo sobre la estructura", 11, BaseColor.DARK_GRAY));
        PdfPCell cell12 = new PdfPCell(agregarLineaCentrada("S2 - Descarga de rayo cerca de la estructura", 11, BaseColor.DARK_GRAY));
        PdfPCell cell13 = new PdfPCell(agregarLineaCentrada("S3 - Descarga de rayo sobre la acometida", 11, BaseColor.DARK_GRAY));
        PdfPCell cell14 = new PdfPCell(agregarLineaCentrada("S4 - Descarga de rayo cerca de la acometida", 11, BaseColor.DARK_GRAY));
        cell11.setPadding(2);
        cell12.setPadding(2);
        cell13.setPadding(2);
        cell14.setPadding(2);
        table.addCell(cell11);
        table.addCell(cell12);
        table.addCell(cell13);
        table.addCell(cell14);

        PdfPCell cell00 = new PdfPCell(agregarLineaCentrada("TIPO DE DAÑO", 11, new BaseColor(0,191,165)));
        cell00.setPadding(5);
        cell00.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell00.setRowspan(3);
        cell00.setRotation(90);
        table.addCell(cell00);

        PdfPCell cell20 = new PdfPCell(agregarLineaCentrada("D1 - Lesión a los seres vivos", 11, BaseColor.DARK_GRAY));
        PdfPCell cell21 = new PdfPCell(agregarLineaCentrada("Celda 1", 11, BaseColor.DARK_GRAY));
        PdfPCell cell22 = new PdfPCell(agregarLineaCentrada("Celda 2", 11, BaseColor.DARK_GRAY));
        PdfPCell cell23 = new PdfPCell(agregarLineaCentrada("Celda 3", 11, BaseColor.DARK_GRAY));
        PdfPCell cell24 = new PdfPCell(agregarLineaCentrada("Celda 4", 11, BaseColor.DARK_GRAY));
        cell20.setPadding(2);
        table.addCell(cell20);
        table.addCell(cell21);
        table.addCell(cell22);
        table.addCell(cell23);
        table.addCell(cell24);

        PdfPCell cell30 = new PdfPCell(agregarLineaCentrada("D2 - Daños físicos", 11, BaseColor.DARK_GRAY));
        PdfPCell cell31 = new PdfPCell(agregarLineaCentrada("Celda 1", 11, BaseColor.DARK_GRAY));
        PdfPCell cell32 = new PdfPCell(agregarLineaCentrada("Celda 2", 11, BaseColor.DARK_GRAY));
        PdfPCell cell33 = new PdfPCell(agregarLineaCentrada("Celda 3", 11, BaseColor.DARK_GRAY));
        PdfPCell cell34 = new PdfPCell(agregarLineaCentrada("Celda 4", 11, BaseColor.DARK_GRAY));
        cell30.setPadding(2);
        table.addCell(cell30);
        table.addCell(cell31);
        table.addCell(cell32);
        table.addCell(cell33);
        table.addCell(cell34);

        PdfPCell cell40 = new PdfPCell(agregarLineaCentrada("D3 - Fallas en sistemas eléctricos y electrónicos", 11, BaseColor.DARK_GRAY));
        PdfPCell cell41 = new PdfPCell(agregarLineaCentrada("Celda 1", 11, BaseColor.DARK_GRAY));
        PdfPCell cell42 = new PdfPCell(agregarLineaCentrada("Celda 2", 11, BaseColor.DARK_GRAY));
        PdfPCell cell43 = new PdfPCell(agregarLineaCentrada("Celda 3", 11, BaseColor.DARK_GRAY));
        PdfPCell cell44 = new PdfPCell(agregarLineaCentrada("Celda 4", 11, BaseColor.DARK_GRAY));
        cell40.setPadding(2);
        table.addCell(cell40);
        table.addCell(cell41);
        table.addCell(cell42);
        table.addCell(cell43);
        table.addCell(cell44);

        return table;
    }

    public static PdfPTable datosBasicos(Proyecto proyecto){
        PdfPTable table = new PdfPTable(2);
        PdfPCell cell1 = new PdfPCell(agregarLineaCentrada("Datos del Usuario", 11, BaseColor.DARK_GRAY));
        PdfPCell cell2 = new PdfPCell(agregarLineaCentrada("Datos del Proyecto", 11, BaseColor.DARK_GRAY));
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell1);
        table.addCell(cell2);
        PdfPCell cellDatosUsuarios = new PdfPCell();
        cellDatosUsuarios.addElement(listaDatosBasicosUsuarios(proyecto));
        cellDatosUsuarios.setPadding(5);
        PdfPCell cellDatosProyecto = new PdfPCell();
        cellDatosProyecto.addElement(listaDatosBasicosProyecto(proyecto));
        cellDatosProyecto.setPadding(5);
        table.addCell(cellDatosUsuarios);
        table.addCell(cellDatosProyecto);
        return table;
    }

    public static List listaDatosBasicosUsuarios(Proyecto proyecto){
        List unorderedList = new List(List.UNORDERED);
        unorderedList.add(new ListItem("Nombre: " + proyecto.getUsuario().getNombre()+ " " + proyecto.getUsuario().getApellido()));
        unorderedList.add(new ListItem("E-mail: " + proyecto.getUsuario().getCorreo()));
        unorderedList.add(new ListItem("Teléfono: " + proyecto.getUsuario().getTelefono()));
        unorderedList.add(new ListItem("Dirección: " + proyecto.getUsuario().getDireccion()));
        return unorderedList;
    }

    public static List listaDatosBasicosProyecto(Proyecto proyecto){
        List unorderedList = new List(List.UNORDERED);
        unorderedList.add(new ListItem("Nombre Estructura: " + proyecto.getNombreEstructura()));
        unorderedList.add(new ListItem("País: " + proyecto.getPais()));
        unorderedList.add(new ListItem("Estado: " + proyecto.getEstado()));
        unorderedList.add(new ListItem("Dirección: " + proyecto.getDireccion()));
        return unorderedList;
    }

    public static Paragraph obtenerEncabezado(){
        Paragraph encabezado = new Paragraph();
        encabezado.add(agregarLineaCentrada("REPÚBLICA BOLIVARIANA DE VENZUELA",11, BaseColor.BLACK));
        encabezado.add(agregarLineaCentrada("UNIVERSIDAD NACIONAL EXPERIMENTAL POLITÉCNICA",11, BaseColor.BLACK));
        encabezado.add(agregarLineaCentrada("ANTONIO JOSÉ DE SUCRE",11, BaseColor.BLACK));
        encabezado.add(agregarLineaCentrada("VICE-RECTORADO BARQUISIMETO",11, BaseColor.BLACK));
        encabezado.add(agregarLineaCentrada("DIRECCIÓN DE INVESTIGACIÓN Y POSTGRADO",11, BaseColor.BLACK));
        return encabezado;
    }

    private static Paragraph agregarLineaCentrada(String linea, int fontSize, BaseColor baseColor){
        Paragraph paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setIndentationLeft(50);
        paragraph.setIndentationRight(50);
        paragraph.add(new Chunk(linea, new Font(Font.FontFamily.TIMES_ROMAN, fontSize, Font.BOLD, baseColor)));
        return paragraph;
    }

    private static Paragraph agregarLineaJustificada(String linea, int fontSize, BaseColor baseColor){
        Paragraph paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
        paragraph.setIndentationLeft(50);
        paragraph.setIndentationRight(50);
        paragraph.add(new Chunk(linea, new Font(Font.FontFamily.TIMES_ROMAN, fontSize, Font.BOLD, baseColor)));
        return paragraph;
    }

}