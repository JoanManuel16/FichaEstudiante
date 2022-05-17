/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import utiles.Tupla;

/**
 *
 * @author joanmanuel
 */
public class ReproteAlumnos {
   Vector<Tupla<DatosEstudiante,Vector<Nota>>> datosEstudiantes;
    Gestion g;
    Brigada b;
    public ReproteAlumnos(Vector<Tupla<DatosEstudiante,Vector<Nota>>> datosEstudiantes,Brigada b) {
        this.datosEstudiantes = datosEstudiantes;
        g = new Gestion();
       this.b=b;
    }
    public boolean GenerarReporte() throws DocumentException{
     try {
            Document pdf = new Document();
            String sol = "Resumen de los estudiantes de la brigada de la carrera " + b.getCarrera() + " del año " + b.getAnno_brigada() + " del " + b.getAnno();
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(pdf, new FileOutputStream(ruta + "/Desktop/" + sol + ".pdf"));
            pdf.open();
            for (int i = 0; i < datosEstudiantes.size(); i++) {
             
          Paragraph informacionExtra = new Paragraph("Nombre del Estduiante: "+datosEstudiantes.elementAt(i).getN1().getNombre_estudiante()+"\n Telefono movil= "+datosEstudiantes.elementAt(i).getN1().getTelefono_particular()+"\n Telefono Fijo: "+datosEstudiantes.elementAt(i).getN1().getTelefono_fijo()+"\n Correo: "+datosEstudiantes.elementAt(i).getN1().getEmail()+"\n Deseos Futuros: "+datosEstudiantes.elementAt(i).getN1().getDeseos_futuros());
          informacionExtra.setAlignment(Paragraph.ALIGN_JUSTIFIED);
          pdf.add(informacionExtra);
          Paragraph saltos = new Paragraph("\n\n");
          pdf.add(saltos);
            //crear la primera tabla
            PdfPTable tabla1 = new PdfPTable(2);
            tabla1.setHorizontalAlignment(Paragraph.ALIGN_JUSTIFIED);
            tabla1.addCell("Nombre De la Asignatura");
            tabla1.addCell("Nota");
                for (int j = 0; j < datosEstudiantes.elementAt(i).getN2().size(); j++) {
                    tabla1.addCell(datosEstudiantes.elementAt(i).getN2().elementAt(j).getNombreAsignatura());
                    tabla1.addCell(datosEstudiantes.elementAt(i).getN2().elementAt(j).getNota()+"");
                }
                pdf.add(tabla1);
                Paragraph p = new Paragraph("--------------------------------------------------------\n\n");
                pdf.add(p);
         }
              pdf.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GenerarReporteICI.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
