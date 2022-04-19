package clases;

import java.util.Vector;

import utiles.Tupla;

public class Evento {

	private String nombre;
	private int dimension;
	private String anno;
	
	public Evento(String nombre, int dimension, String anno) {
		this.nombre = nombre;
		this.dimension = dimension;
		this.anno = anno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public String getAnno() {
		return anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}
	
	
}
