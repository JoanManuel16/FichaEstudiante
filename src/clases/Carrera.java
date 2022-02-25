package clases;

import java.util.Vector;

import utiles.Tupla;

public class Carrera {

	
	private String nombre;
	private Vector<Vector<Tupla<Integer, String>>> Asignaturas;
	
	public Carrera(String nombre, Vector<Vector<Tupla<Integer, String>>> Asignaturas) {
		this.nombre = nombre;
		this.Asignaturas = Asignaturas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Vector<Vector<Tupla<Integer, String>>> getAsignaturas() {
		return Asignaturas;
	}

	public void setAsignaturas(Vector<Vector<Tupla<Integer, String>>> asignaturas) {
		Asignaturas = asignaturas;
	}
	
	
}
