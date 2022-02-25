package clases;

public class Nota {

	private int asignatura;
	private int nota;
	private int lugar_examen_premio;
	
	public Nota(int A, int N, int L) {
		asignatura = A;
		nota = N;
		lugar_examen_premio = L;
	}

	public int getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(int asignatura) {
		this.asignatura = asignatura;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public int getLugar_examen_premio() {
		return lugar_examen_premio;
	}

	public void setLugar_examen_premio(int lugar_examen_premio) {
		this.lugar_examen_premio = lugar_examen_premio;
	}
	
	
}
