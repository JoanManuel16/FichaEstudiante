/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author joanmanuel
 */
public class Estudiante {
    
    private final String nombre_estudiante;
    private final String CI;
    private final String carrera;
    private boolean activo;
    
    public Estudiante(String nombre, String CI, String carrera){
        this.nombre_estudiante = nombre;
        this.CI = CI;
        this.carrera = carrera;
        activo = true;
    }

    public String getNombre_estudiante() {
        return nombre_estudiante;
    }

    public String getCI() {
        return CI;
    }

    public String getCarrera() {
        return carrera;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    
    
    
}
