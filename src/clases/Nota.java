package clases;

public class Nota {

	private int idAsignatura;
	private int nota;
	private int lugar_examen_premio;
	
	public Nota(int A, int N, int L) {
		idAsignatura = A;
		nota = N;
		lugar_examen_premio = L;
	}

	public int getAsignatura() {
		return idAsignatura;
	}

	public void setAsignatura(int asignatura) {
		this.idAsignatura = asignatura;
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
