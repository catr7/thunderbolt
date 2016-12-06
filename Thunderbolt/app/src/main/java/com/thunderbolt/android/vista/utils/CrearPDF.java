
package com.thunderbolt.android.vista.utils;

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
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            doc.add(agregarLinea("EVALUACIÓN DE NIVEL DE RIESGO", 14, BaseColor.BLUE));
            doc.add(agregarLinea("(Informe Final de Resultados)", 11, BaseColor.DARK_GRAY));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            doc.add(datosBasicos(proyecto));
            doc.close();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static PdfPTable datosBasicos(Proyecto proyecto){
        PdfPTable table = new PdfPTable(2);
        PdfPCell cell1 = new PdfPCell(agregarLinea("Datos del Usuario", 11, BaseColor.DARK_GRAY));
        PdfPCell cell2 = new PdfPCell(agregarLinea("Datos del Proyecto", 11, BaseColor.DARK_GRAY));
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
        encabezado.add(agregarLinea("REPÚBLICA BOLIVARIANA DE VENZUELA",11, BaseColor.BLACK));
        encabezado.add(agregarLinea("UNIVERSIDAD NACIONAL EXPERIMENTAL POLITÉCNICA",11, BaseColor.BLACK));
        encabezado.add(agregarLinea("ANTONIO JOSÉ DE SUCRE",11, BaseColor.BLACK));
        encabezado.add(agregarLinea("VICE-RECTORADO BARQUISIMETO",11, BaseColor.BLACK));
        encabezado.add(agregarLinea("DIRECCIÓN DE INVESTIGACIÓN Y POSTGRADO",11, BaseColor.BLACK));
        return encabezado;
    }

    private static Paragraph agregarLinea(String linea, int fontSize, BaseColor baseColor){
        Paragraph paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setIndentationLeft(50);
        paragraph.setIndentationRight(50);
        paragraph.add(new Chunk(linea, new Font(Font.FontFamily.TIMES_ROMAN, fontSize, Font.BOLD, baseColor)));
        return paragraph;
    }

}