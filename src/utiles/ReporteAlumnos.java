/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utiles;

import Base_de_Datos.Gestion;
import clases.Brigada;
import clases.DatosEstudiante;
import clases.Evento;
import clases.Nota;
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
public class ReporteAlumnos {

    Vector<Tupla<DatosEstudiante, Vector<Nota>>> datosEstudiantes;
    Gestion g;
    Brigada b;

    public ReporteAlumnos(Vector<Tupla<DatosEstudiante, Vector<Nota>>> datosEstudiantes, Brigada b) {
        this.datosEstudiantes = datosEstudiantes;
        g = new Gestion();
        this.b = b;
    }

    public boolean GenerarReporte() throws DocumentException {
        try {
            Document pdf = new Document();
            String sol = "Resumen de los estudiantes de la brigada de la carrera " + b.getCarrera() + " del año " + b.getAnno_brigada() + " del " + b.getAnno();
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(pdf, new FileOutputStream(ruta + "/Desktop/" + sol + ".pdf"));
            pdf.open();
            Paragraph separador = new Paragraph("-----------------------------------------------");
            Paragraph datos_de_la_brigada = new Paragraph("Brigada de la carrra: " + b.getCarrera() + "\n Anno de la brigada: " + b.getAnno_brigada() + " Anno escolar: " + b.getAnno() + "\n Total de alumnos de la brigada: " + b.getEstudiantes().size() + "\n");
            pdf.add(separador);
            pdf.add(datos_de_la_brigada);
            pdf.add(new Paragraph("Eventos a de la brigada\n\n"));
            PdfPTable TablaEventosBrigada = new PdfPTable(3);

            Vector<Evento> eventos_de_la_brigada = g.obtenerEventosBrigada(b);
            Vector <String>dimensiones = g.obtenerDimensiones();

            TablaEventosBrigada.setHorizontalAlignment(Paragraph.ALIGN_JUSTIFIED);
            TablaEventosBrigada.addCell("Nombre del  evento");
            TablaEventosBrigada.addCell("Diemsion");
            TablaEventosBrigada.addCell("Anno");
            for (Evento evento: eventos_de_la_brigada) {
                TablaEventosBrigada.addCell(evento.getNombre());
                TablaEventosBrigada.addCell(dimensiones.elementAt(evento.getDimension()-1));
                TablaEventosBrigada.addCell(""+evento.getAnno());
            }
            pdf.add(TablaEventosBrigada);
            Paragraph separador2 = new Paragraph("-----------------------------------------------");
            for (int i = 0; i < datosEstudiantes.size(); i++) {

                Paragraph informacionExtra = new Paragraph("Nombre del Estudiante: " + datosEstudiantes.elementAt(i).getN1().getNombre_estudiante() + "\n");
                informacionExtra.setAlignment(Paragraph.ALIGN_JUSTIFIED);
                pdf.add(informacionExtra);
                if (!datosEstudiantes.elementAt(i).getN1().getTelefono_particular().equals("")) {
                    Paragraph telefono_particular = new Paragraph("Telefono movil: " + datosEstudiantes.elementAt(i).getN1().getTelefono_particular() + "\n");
                    telefono_particular.setAlignment(Paragraph.ALIGN_JUSTIFIED);
                    pdf.add(telefono_particular);
                }
                if (!datosEstudiantes.elementAt(i).getN1().getTelefono_fijo().equals("")) {
                    Paragraph telefono_fijo = new Paragraph("Telefono Fijo:" + datosEstudiantes.elementAt(i).getN1().getTelefono_fijo() + "\n");
                    telefono_fijo.setAlignment(Paragraph.ALIGN_JUSTIFIED);
                    pdf.add(telefono_fijo);
                }
                if (!datosEstudiantes.elementAt(i).getN1().getEmail().equals("")) {
                    Paragraph email = new Paragraph("Correo: " + datosEstudiantes.elementAt(i).getN1().getEmail() + "\n");
                    email.setAlignment(Paragraph.ALIGN_JUSTIFIED);
                    pdf.add(email);
                }
                if (!datosEstudiantes.elementAt(i).getN1().getDeseos_futuros().equals("")) {
                    Paragraph deseos = new Paragraph("Deseos Futuros: " + datosEstudiantes.elementAt(i).getN1().getDeseos_futuros() + "\n");
                    deseos.setAlignment(Paragraph.ALIGN_JUSTIFIED);
                    pdf.add(deseos);
                }

                Paragraph saltos = new Paragraph("\n\n");
                pdf.add(saltos);
                //crear la primera tabla
                PdfPTable tabla1 = new PdfPTable(2);
                tabla1.setHorizontalAlignment(Paragraph.ALIGN_JUSTIFIED);
                tabla1.addCell("Nombre De la Asignatura");
                tabla1.addCell("Nota");
                for (int j = 0; j < datosEstudiantes.elementAt(i).getN2().size(); j++) {
                    tabla1.addCell(datosEstudiantes.elementAt(i).getN2().elementAt(j).getNombreAsignatura());
                    tabla1.addCell(datosEstudiantes.elementAt(i).getN2().elementAt(j).getNota() + "");
                }
                pdf.add(tabla1);

                //llenado la tabla de los eventos;
                PdfPTable tablaEventos = new PdfPTable(3);
                
                        var eventosEstudiante = g.obtenerEventoEstudiante(datosEstudiantes.elementAt(i).getN1().getCI(), b);

                        if (!eventosEstudiante.isEmpty()) {

                            tablaEventos.setHorizontalAlignment(Paragraph.ALIGN_JUSTIFIED);
                            tablaEventos.addCell("Nombre del Evento");
                            tablaEventos.addCell("Logro en el evento");
                            tablaEventos.addCell("Valor del logro");

                            for (int j = 0; j < eventosEstudiante.size(); j++) {
                                tablaEventos.addCell(eventosEstudiante.elementAt(j).getN1());
                                tablaEventos.addCell(eventosEstudiante.elementAt(j).getN2().getN1());
                                tablaEventos.addCell(eventosEstudiante.elementAt(j).getN2().getN2() + "");
                            }

                        }
                       
                    
                    pdf.add(saltos);
                    Paragraph p2 = new Paragraph("Eventos en los que participó el estudiante\n\n");
                    p2.setAlignment(Paragraph.ALIGN_CENTER);
                    pdf.add(p2);
                    
                    pdf.add(tablaEventos);
                    Paragraph p = new Paragraph("--------------------------------------------------------\n\n");
                    pdf.add(p);
                
            }
            pdf.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReporteICI.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
