/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.util.Vector;

public class Estudiante {
    
    protected final String nombre_estudiante;
    protected final String CI;
    protected boolean activo;
    protected Vector<Nota> notas;
    
    public Estudiante(String nombre, String CI){
        this.nombre_estudiante = nombre;
        this.CI = CI;
        activo = true;
        notas = new Vector<Nota>();
    }
    
    public Vector<Nota> getNotas(){
    	return notas;
    }

    public String getNombre_estudiante() {
        return nombre_estudiante;
    }

    public String getCI() {
        return CI;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setNotas(Vector<Nota> notas) {
        this.notas = notas;
    }
    
    
    
    
}
