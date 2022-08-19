/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import Base_de_Datos.Gestion;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joan Manuel
 */
public class GenerarReporteICI {

    private double M;
    private int m;
    private Brigada b;
    private Gestion g;
    private int PA;
    private Queue e;
    public GenerarReporteICI(double M, int m,int PA, Brigada b, Queue e) {
        this.M = M;
        this.m = m;
        this.b = b;
        this.PA=PA;
        this.e=e;
        g = new Gestion();
    }

    public boolean generarReporte() throws DocumentException {
        try {
            Document pdf = new Document();
            String sol = "Resumen del ICI de la brigada de la carrera " + b.getCarrera() + " del año " + b.getAnno_brigada() + " del " + b.getAnno();
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(pdf, new FileOutputStream(ruta + "/Desktop/" + sol + ".pdf"));
            pdf.open();
            Paragraph informacionExtra = new Paragraph("Valor de las actividades extracurriculares: "+m +"\n Relación académico-curricular: "+M+"\n Se tomó en cuenta el ICI con un PA de: "+PA);
          informacionExtra.setAlignment(Paragraph.ALIGN_JUSTIFIED);
          pdf.add(informacionExtra);
          Paragraph saltos = new Paragraph("\n\n");
          pdf.add(saltos);
            //crear la primera tabla
            PdfPTable tabla1 = new PdfPTable(4);
            tabla1.setHorizontalAlignment(Paragraph.ALIGN_JUSTIFIED);
            tabla1.addCell("Estudiante");
            tabla1.addCell("Promedio");
            tabla1.addCell("Valor de las actividades extracurriculares");
            tabla1.addCell("ICI");
            
             do {
                 Estudiante estudiante = (Estudiante) e.poll();
            String nombreEstudiante = estudiante.getNombre_estudiante();
            tabla1.addCell(nombreEstudiante);
            double PromedioEstudiante = calcularPrmedio(estudiante);
            tabla1.addCell(PromedioEstudiante+"");
            int mi = calcularMi(estudiante);
            tabla1.addCell(mi+"");
            
            tabla1.addCell(utiles.ICI.ICI(m, M, PromedioEstudiante, mi)+"");
        }while(!e.isEmpty());
             pdf.add(tabla1);
              pdf.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GenerarReporteICI.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    private int calcularMi(Estudiante e) {
        return g.obtenerValoresEventosEstudiante(e, b);
    }

    private double calcularPrmedio(Estudiante e) {
        return g.obtenerPromedio(e);
    }
}
