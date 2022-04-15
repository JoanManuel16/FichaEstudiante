/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

public class Estudiante {
    
    protected final String nombre_estudiante;
    protected final String CI;
    protected boolean activo;
    
    public Estudiante(String nombre, String CI){
        this.nombre_estudiante = nombre;
        this.CI = CI;
        activo = true;
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
    
    
    
}
