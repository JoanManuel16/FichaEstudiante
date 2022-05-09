package clases;

import java.util.Vector;

import utiles.Tupla;

public class DatosEstudiante extends Estudiante{

	
	private String telefono_particular;
	private String telefono_fijo;
	private boolean datos_moviles;
	private String email;
	private String sexo;
	private int edad;
	private boolean becado;
	private int color_de_piel;
	private boolean militante;
	private int estado_civil;
	private boolean hijos;
	private String direccion_particular;
	private int zona;
	private String religion; 
	private boolean bebidas_alcoholicas;
	private boolean fumador;
	private int participacion_brigada;
	private Vector<String> manifestaciones_artisticas;
	private boolean[] convivencia;
	private int total_familiares;
	private int ingreso_total;
	private int relaciones;
        private boolean padres_divorciados;
	private Vector<String> deportes; 
	private boolean[] electronicos;
	private Vector<String> enfermedades;
	private boolean activo;
	private Vector<Tupla<Integer, Integer>> eventos;
	private Vector<String> medicamentos;
	private String deseos_futuros;
	private String actividades_tiempo_libre;
	private String proyectos_vida;
	private String rasgos_habitos;
	private boolean feliz;
	private boolean gusta_carrera;
	private boolean gusta_estudio;
	private int nivel_ingles;
	public DatosEstudiante(String nombre, String CI, String telefono_particular, String telefono_fijo, boolean datos_moviles,
			String email, String sexo, int edad, boolean becado, int color_de_piel, boolean militante,
			int estado_civil, boolean hijos, String direccion_particular, int zona, String religion,
			boolean bebidas_alcoholicas, boolean fumador, int participacion_brigada,
			Vector<String> manifestaciones_artisticas, boolean[] convivencia, int total_familiares, int ingreso_total,
			int relaciones, Vector<String> deportes, boolean[] electronicos, Vector<String> enfermedades,
			boolean activo, Vector<Tupla<Integer, Integer>> eventos, Vector<String> medicamentos,
			String deseos_futuros, String actividades_tiempo_libre, String proyectos_vida, String rasgos_habitos,
			boolean feliz, boolean gusta_carrera, boolean gusta_estudio, int nivel_ingles) {
		super(nombre, CI);
		this.telefono_particular = telefono_particular;
		this.telefono_fijo = telefono_fijo;
		this.datos_moviles = datos_moviles;
		this.email = email;
		this.sexo = sexo;
		this.edad = edad;
		this.becado = becado;
		this.color_de_piel = color_de_piel;
		this.militante = militante;
		this.estado_civil = estado_civil;
		this.hijos = hijos;
		this.direccion_particular = direccion_particular;
		this.zona = zona;
		this.religion = religion;
		this.bebidas_alcoholicas = bebidas_alcoholicas;
		this.fumador = fumador;
		this.participacion_brigada = participacion_brigada;
		this.manifestaciones_artisticas = manifestaciones_artisticas;
		this.convivencia = convivencia;
		this.total_familiares = total_familiares;
		this.ingreso_total = ingreso_total;
		this.relaciones = relaciones;
		this.deportes = deportes;
		this.electronicos = electronicos;
		this.enfermedades = enfermedades;
		this.activo = activo;
		this.eventos = eventos;
		this.medicamentos = medicamentos;
		this.deseos_futuros = deseos_futuros;
		this.actividades_tiempo_libre = actividades_tiempo_libre;
		this.proyectos_vida = proyectos_vida;
		this.rasgos_habitos = rasgos_habitos;
		this.feliz = feliz;
		this.gusta_carrera = gusta_carrera;
		this.gusta_estudio = gusta_estudio;
		this.nivel_ingles = nivel_ingles;
	}
	
	public String getTelefono_particular() {
		return telefono_particular;
	}
	public String getTelefono_fijo() {
		return telefono_fijo;
	}
	public boolean isDatos_moviles() {
		return datos_moviles;
	}
	public String getEmail() {
		return email;
	}
	public String getSexo() {
		return sexo;
	}
	public int getEdad() {
		return edad;
	}
	public boolean isBecado() {
		return becado;
	}
	public int getColor_de_piel() {
		return color_de_piel;
	}
	public boolean isMilitante() {
		return militante;
	}
	public int getEstado_civil() {
		return estado_civil;
	}
	public boolean isHijos() {
		return hijos;
	}
	public String getDireccion_particular() {
		return direccion_particular;
	}
	public String getReligion() {
		return religion;
	}
	public boolean isBebidas_alcoholicas() {
		return bebidas_alcoholicas;
	}
	public boolean isFumador() {
		return fumador;
	}
	public int getParticipacion_brigada() {
		return participacion_brigada;
	}
	public Vector<String> getManifestaciones_artisticas() {
		return manifestaciones_artisticas;
	}
	public boolean[] getConvivencia() {
		return convivencia;
	}
	public int getTotal_familiares() {
		return total_familiares;
	}
	public int getIngreso_total() {
		return ingreso_total;
	}
	public int getRelaciones() {
		return relaciones;
	}
	public Vector<String> getDeportes() {
		return deportes;
	}
	public boolean[] getElectronicos() {
		return electronicos;
	}
	public Vector<String> getEnfermedades() {
		return enfermedades;
	}
	public boolean isActivo() {
		return activo;
	}
	public Vector<Tupla<Integer, Integer>> getEventos() {
		return eventos;
	}
	public Vector<String> getMedicamentos() {
		return medicamentos;
	}
	public String getDeseos_futuros() {
		return deseos_futuros;
	}
	public String getActividades_tiempo_libre() {
		return actividades_tiempo_libre;
	}
	public String getProyectos_vida() {
		return proyectos_vida;
	}
	public String getRasgos_habitos() {
		return rasgos_habitos;
	}
	public boolean isFeliz() {
		return feliz;
	}
	public boolean isGusta_carrera() {
		return gusta_carrera;
	}
	public boolean isGusta_estudio() {
		return gusta_estudio;
	}
	public int getZona() {
		return zona;
	}
	public void setZona(int zona) {
		this.zona = zona;
	}
	public int getNivel_ingles() {
		return nivel_ingles;
	}
	public void setNivel_ingles(int nivel_ingles) {
		this.nivel_ingles = nivel_ingles;
	}

    public boolean isPadres_divorciados() {
        return padres_divorciados;
    }

    public void setPadres_divorciados(boolean padres_divorciados) {
        this.padres_divorciados = padres_divorciados;
    }
        
	
	
	
	

}
