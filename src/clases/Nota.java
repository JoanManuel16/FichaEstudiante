package clases;

public class Nota {

	private int idAsignatura;
	private int nota;
        private String nombreAsignatura;
	
	public Nota(int A, int N, String NA) {
		idAsignatura = A;
		nota = N;
                nombreAsignatura = NA;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
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
