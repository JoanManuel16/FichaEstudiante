/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.util.Vector;

/**
 *
 * @author joanmanuel
 */
public class Brigada {
 
    private String carrera;
    private int anno;
    private int anno_brigada;
    private Vector<Estudiante> estudiantes;

    public Brigada(String carrera, int anno, int anno_brigada, Vector<Estudiante> estudiantes) {
        this.carrera = carrera;
        this.anno = anno;
        this.anno_brigada = anno_brigada;
        this.estudiantes = estudiantes;
    }
    
    

    public String getCarrera() {
        return carrera;
    }

    public int getAnno() {
        return anno;
    }

    public int getAnno_brigada() {
        return anno_brigada;
    }

    public Vector<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void addStudent(Estudiante E) {
        estudiantes.add(E);
    }
    
    
    
}
