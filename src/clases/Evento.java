package clases;

import java.util.Vector;

import utiles.Tupla;

public class Evento {

	private String nombre;
	private int dimension;
	private int anno;
	private Vector<Tupla<String, Integer>> logros;
	
	public Evento(String nombre, int dimension, int anno, Vector<Tupla<String, Integer>> logros) {
		this.nombre = nombre;
		this.dimension = dimension;
		this.anno = anno;
		this.logros = logros;
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

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public Vector<Tupla<String, Integer>> getLogros() {
		return logros;
	}

	public void setLogros(Vector<Tupla<String, Integer>> logros) {
		this.logros = logros;
	}
	
	
}
