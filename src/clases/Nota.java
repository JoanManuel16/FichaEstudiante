package clases;

public class Nota {

	private int idAsignatura;
	private int nota;
	private int lugar_examen_premio;
        private String nombreAsignatura;
	
	public Nota(int A, int N, int L, String NA) {
		idAsignatura = A;
		nota = N;
		lugar_examen_premio = L;
                nombreAsignatura = NA;
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

    public int getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(int idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }
	
        
	
}
