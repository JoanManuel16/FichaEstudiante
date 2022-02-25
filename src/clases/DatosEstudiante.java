package clases;

import java.util.Vector;

import utiles.Tupla;

public class DatosEstudiante extends Estudiante{

	
	private int telefono_particular;
	private int telefono_fijo;
	private boolean datos_moviles;
	private String email;
	private String sexo;
	private int edad;
	private boolean becado;
	private String color_de_piel;
	private boolean militante;
	private String estado_civil;
	private boolean hijos;
	private String direccion_particular;
	private String zona;
	private String religion; // Preguntar como se organiza esto de las religiones, si solo se ponen las mas importantes, o todas
	private boolean bebidas_alcoholicas;
	private boolean fumador;
	private int participacion_brigada;
	private Vector<Integer> manifestaciones_artisticas;
	private boolean[] convivencia;
	private int total_familiares;
	private int ingreso_total;
	private String relaciones;
	private Vector<Integer> deportes; //preguntar lo mismo de arriba
	private boolean[] electronicos;
	private Vector<Integer> enfermedades;
	private boolean activo;
	private Vector<Tupla<Integer, Integer>> eventos;
	private Vector<Integer> medicamentos;
	private String deseos_futuros;
	private String actividades_tiempo_libre;
	private String proyectos_vida;
	private String rasgos_habitos;
	private boolean feliz;
	private boolean gusta_carrera;
	private boolean gusta_estudio;
	private String nivel_ingles;
	public DatosEstudiante(String nombre, String CI, int telefono_particular, int telefono_fijo, boolean datos_moviles,
			String email, String sexo, int edad, boolean becado, String color_de_piel, boolean militante,
			String estado_civil, boolean hijos, String direccion_particular, String zona, String religion,
			boolean bebidas_alcoholicas, boolean fumador, int participacion_brigada,
			Vector<Integer> manifestaciones_artisticas, boolean[] convivencia, int total_familiares, int ingreso_total,
			String relaciones, Vector<Integer> deportes, boolean[] electronicos, Vector<Integer> enfermedades,
			boolean activo, Vector<Tupla<Integer, Integer>> eventos, Vector<Integer> medicamentos,
			String deseos_futuros, String actividades_tiempo_libre, String proyectos_vida, String rasgos_habitos,
			boolean feliz, boolean gusta_carrera, boolean gusta_estudio, String nivel_ingles) {
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
	
	public int getTelefono_particular() {
		return telefono_particular;
	}
	public int getTelefono_fijo() {
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
	public String getColor_de_piel() {
		return color_de_piel;
	}
	public boolean isMilitante() {
		return militante;
	}
	public String getEstado_civil() {
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
	public Vector<Integer> getManifestaciones_artisticas() {
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
	public String getRelaciones() {
		return relaciones;
	}
	public Vector<Integer> getDeportes() {
		return deportes;
	}
	public boolean[] getElectronicos() {
		return electronicos;
	}
	public Vector<Integer> getEnfermedades() {
		return enfermedades;
	}
	public boolean isActivo() {
		return activo;
	}
	public Vector<Tupla<Integer, Integer>> getEventos() {
		return eventos;
	}
	public Vector<Integer> getMedicamentos() {
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
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	public String getNivel_ingles() {
		return nivel_ingles;
	}
	public void setNivel_ingles(String nivel_ingles) {
		this.nivel_ingles = nivel_ingles;
	}
	
	
	
	

}
