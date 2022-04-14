/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base_de_Datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import clases.Brigada;
import clases.Carrera;
import clases.DatosEstudiante;
import clases.Estudiante;
import clases.Evento;
import java.util.logging.Level;
import java.util.logging.Logger;
import utiles.Tupla;


//UPDATEAR los datos del estudiante solo cuando se vaya a cargar. Nunca antes.
public class Gestion
{
    private final Conexion C;
    
    public Gestion()
    {
        C = new Conexion();
    }

    //Agrega la brigada a la base de datos
    public boolean agregar_brigada(Brigada B) 
    {
    	
    	C.conectar();
    	
    	String stat = "select * from brigada where ano_brigada = " + B.getAnno_brigada() + " and id_carrera = (select id_carrera from carrera where nombre_carrera = '" + B.getCarrera() + "') and anno = " + B.getAnno();
    	try
        {
			ResultSet RS = C.getConsulta().executeQuery(stat);
			if(RS.next())
                        {
				throw new SQLException();
			}
		
    	stat = "insert into brigada values(null, " + B.getAnno_brigada() + ", (select id_carrera from carrera where nombre_carrera = '" + B.getCarrera() + "'), " + B.getAnno() + ")";
    	C.getConsulta().execute(stat);
    	
        stat = "select id_brigada from brigada where ano_brigada = " + B.getAnno_brigada() + " and id_carrera = (select id_carrera from carrera where nombre_carrera = '" + B.getCarrera() + "') and anno = " + B.getAnno();
        RS = C.getConsulta().executeQuery(stat);
        int idB = RS.getInt("id_brigada");
        
        
        
        for(int i = 0; i < B.getEstudiantes().size(); i++){
            String id_estudiante = B.getEstudiantes().elementAt(i).getCI();
            stat = "update EstudianteSencillo set id_brigada = " + idB + " where CI = '" + id_estudiante + "'";
            C.getConsulta().execute(stat);
        }
        
        
    	} catch (SQLException e) {
    		C.desconectar();
			return false;
		}
       
    	
    	C.desconectar();
    	return true;
    	}
    
    //Agrega la carrera a la base de datos
    public boolean agregar_carrera(Carrera Carr) {  	
    	C.conectar();
    	
    	String stat = "select * from carrera where nombre_carrera = '" + Carr.getNombre() + "'";
    	try {
			ResultSet RS = C.getConsulta().executeQuery(stat);
			if(RS.next()) {
				throw new SQLException();
			}
			
			stat = "insert into carrera values(null, '" + Carr.getNombre() + "')";	
			C.getConsulta().execute(stat);
			
			stat = "select * from carrera where nombre_carrera = '" + Carr.getNombre() + "'";
			RS = C.getConsulta().executeQuery(stat);
			int idC = RS.getInt("id_carrera");
			
			
			for(int i = 0; i < Carr.getAsignaturas().size(); i++) {
				for(int j = 0; j < Carr.getAsignaturas().elementAt(i).size(); j++) {
					Tupla<Integer, String> T = Carr.getAsignaturas().elementAt(i).elementAt(j);
					int idA= 0;
					
					stat = "select * from asignaturas where nombre_asignatura = '" + T.getN2() + "'";
					RS = C.getConsulta().executeQuery(stat);
					if(RS.next()) {
						idA = RS.getInt("id_asignatura");
					}
					else {
						stat = "insert into asignaturas values(null, '" + T.getN2() + "')";
						C.getConsulta().execute(stat);
						stat = "select * from asignaturas where nombre_asignatura = '" + T.getN2() + "'";
						RS = C.getConsulta().executeQuery(stat);
						idA = RS.getInt("id_asignatura");
					}
					
					stat = "insert into asignaturas_semestre values(" + idC + ", " + idA + ", " + (i+1) + ", " + T.getN1() + ")";
					C.getConsulta().execute(stat);		
					
				}
			}
			
			
		} catch (SQLException e) {
			C.desconectar();
			return false;
		}
    	
    	C.desconectar();
    	return true;
    }
    
    
    public boolean agregar_estudiante(Estudiante E){
         C.conectar();
            
            String stat = "select * from EstudianteSencillo where CI = '" + E.getCI() + "'";
            
        try {
           
            ResultSet RS = C.getConsulta().executeQuery(stat);
            if(RS.next()){
                
                int idB = RS.getInt("id_brigada");
                
                if(idB != 0){
                    throw new SQLException();
                }
                
                C.desconectar();
                return true;
                
            }
            
            stat = "insert into EstudianteSencillo values(null, '" + E.getNombre_estudiante() + "', '" + E.getCI() + "', 0)";
            C.getConsulta().execute(stat);
            
        } catch (SQLException ex) {
            
            C.desconectar();
            return false;
        }
        
        C.desconectar();
        return true;
    }
    
    
    public boolean editar_estudiante(Brigada B, DatosEstudiante DE) {
    	
    	C.conectar();
    	
    	String stat = "select id_brigada from brigada where ano_brigada = " + B.getAnno_brigada() +" and id_carrera = (select id_carrera from carrera where nombre_carrera = '" + B.getCarrera() + "') and anno = " + B.getAnno();
    	
    	try {
			ResultSet RS = C.getConsulta().executeQuery(stat);
			int idB = RS.getInt("id_brigada");
			
			stat = "select id_estudiante from Estudiante where CI = '" + DE.getCI() + "'";
			RS = C.getConsulta().executeQuery(stat);
			if(RS.next()) {
				throw new SQLException();
			}
			
			stat = "insert into Estudiante values(null, '" + DE.getNombre_estudiante() + "', '" + DE.getCI() + "', " + DE.getTelefono_particular() + ", " + DE.getTelefono_fijo() + ", " + DE.isDatos_moviles() + ", '" + DE.getEmail() + "', "
					+ "'"+ DE.getSexo() + "', " + DE.getEdad() + ", " + DE.isBecado() + ", (select id_color_piel from color_piel where color_piel = '" + DE.getColor_de_piel() + "'), " + DE.isMilitante() + ", " +  
					"(select id_estado_civil from estado_civil where estado_civil = '" + DE.getEstado_civil() + "'), " + DE.isHijos() + ", '" + DE.getDireccion_particular() + "', (select id_zona from zona where zona = '" + DE.getZona() + 
					"'), (select id_religion from religion where religion = '" + DE.getReligion() + "'), " + DE.isBebidas_alcoholicas() + ", "  + DE.isFumador() + ", " + DE.getParticipacion_brigada() + ", " + idB + 
					", (select id_nivel_ingles from nivel_ingles where nivel_ingles = '" + DE.getNivel_ingles() + "'), " + DE.isActivo() + ")";
			C.getConsulta().execute(stat);
			stat = "select id_estudiante from Estudiante where CI = '" + DE.getCI() + "'";
			RS = C.getConsulta().executeQuery(stat);
			int idE = RS.getInt("id_estudiante");	
			
			for(int i = 0; i < DE.getManifestaciones_artisticas().size(); i++) {
				stat = "insert into artes_estudiante values(" + idE + ", " + DE.getManifestaciones_artisticas().elementAt(i) + ")";
				C.getConsulta().execute(stat);
			}
			
			stat = "insert into convivencia values(" + idE + ", " + DE.getConvivencia()[0] + ", " + DE.getConvivencia()[1] + ", " + DE.getConvivencia()[2] + ", " + DE.getConvivencia()[3] + ", " + DE.getConvivencia()[4] + 
					", " + DE.getConvivencia()[5] + ", " + DE.getConvivencia()[6] + ", " + DE.getConvivencia()[7] + ", " + DE.getConvivencia()[8] + ", " + DE.getConvivencia()[9] + ", " + DE.getTotal_familiares() + ", " + DE.getIngreso_total() + 
					", (select id_relaciones from relaciones convivencia where relaciones = '" + DE.getRelaciones() + "') )";
			C.getConsulta().execute(stat);
			
			for(int i = 0; i < DE.getDeportes().size(); i++) {
				stat = "insert into deporte_estudiante values(" + idE + ", " + DE.getDeportes().elementAt(i) + ")";
				C.getConsulta().execute(stat);
			}
			
			stat = "insert into Electronics values(null, " + idE + ", " + DE.getElectronicos()[0] + ", " + DE.getElectronicos()[1] + ", " + DE.getElectronicos()[2] + ", " + DE.getElectronicos()[3] + ")";
			C.getConsulta().execute(stat);
			
			for(int i = 0; i < DE.getEnfermedades().size(); i++) {
				stat = "insert into enfermedades_estudiante values(" + idE + ", " + DE.getEnfermedades().elementAt(i) + ")";
				C.getConsulta().execute(stat);
			}
			
			for(int i = 0; i < DE.getMedicamentos().size(); i++) {
				stat = "insert into medicamentos_estudiante values(" + idE + ", " + DE.getMedicamentos().elementAt(i) + ")";
				C.getConsulta().execute(stat);
			}
			
			stat = "insert into psiquis_estudiante values(" + idE + ", '" + DE.getDeseos_futuros() + "', '" + DE.getActividades_tiempo_libre() + "', '" + DE.getProyectos_vida() + "', '"
					 + DE.getRasgos_habitos() + "', "  + DE.isFeliz() + ", " + DE.isGusta_estudio() + ", " + DE.isGusta_carrera() + ")";
			
			
		} catch (SQLException e) {
			C.desconectar();
			return false;
		}
    	
    	C.desconectar();
    	return true;
    }
    
    
    public Vector<String> obtener_nombres_eventos(){
    	C.conectar();
    	Vector<String> nombres = new Vector<>();
    	String stat = "select nombre_evento from nombre_evento";
    	try {
			ResultSet RS = C.getConsulta().executeQuery(stat);
			
			while(RS.next()) {
				nombres.add(RS.getString("nombre_evento"));
			}
			
		} catch (SQLException e) {
		}
    	C.desconectar();
    	return nombres;
    }
    
    public void agregar_nombre_evento(String evento) {
    	C.conectar();
    	String stat = "insert into nombre_evento values(null, '" + evento + "')";
    	try {
			C.getConsulta().execute(stat);
		} catch (SQLException e) {
		}
    	C.desconectar();
    }
    
    public boolean agregar_evento(Evento E) {
    	C.conectar();
    	
    	String stat = "select * from eventos where id_nombre_evento = (select id_nombre_evento from nombre_evento where nombre_evento = '" + E.getNombre() + "') and anno_evento = " + E.getAnno() + ")";
    	try {
			ResultSet RS = C.getConsulta().executeQuery(stat);
			
			if(RS.next()) {
				throw new SQLException();
			}
			
			stat = "insert into eventos values(null, (select id_nombre_evento from nombre_evento where nombre_evento = '" + E.getNombre() + "'), " + E.getDimension() + ", " + E.getAnno() + ")";
			C.getConsulta().execute(stat);
			
			stat = "select * from eventos where id_nombre_evento = (select id_nombre_evento from nombre_evento where nombre_evento = '" + E.getNombre() + "') and anno_evento = " + E.getAnno() + ")";
			RS = C.getConsulta().executeQuery(stat);
			int idE = RS.getInt("id_evento");
			
			for(int i = 0; i < E.getLogros().size(); i++) {
				stat = "insert into logros_evento values(null, " + idE + ", '" + E.getLogros().elementAt(i).getN1() + "', " + E.getLogros().elementAt(i).getN2() + ")";
			}
			
		} catch (SQLException e) {
			C.desconectar();
			return false;
		}
    	
    	C.desconectar();
    	return true;
    }
    
    public boolean agregar_evento_a_estudiante(Evento E, Estudiante EST, String logro) {
    	C.conectar();
    	String stat = "select id_evento from eventos where id_nombre_evento = (select id_nombre_evento from nombre_evento where nombre_evento = '" + E.getNombre() + "') and anno_evento = " + E.getAnno() + ")";
    	
    	try {
			ResultSet RS = C.getConsulta().executeQuery(stat);
			int idEV = RS.getInt("id_evento");
			
			stat = "select id_estudiante from EstudianteSencillo where CI = '" + EST.getCI() + "'";
			RS = C.getConsulta().executeQuery(stat);
			int idEST = RS.getInt("id_estudiante");
			
			stat = "select * from eventos_estudiante where id_evento = " + idEV + " and id_estudiante = " + idEST;
			RS = C.getConsulta().executeQuery(stat);
			if(RS.next()) {
				throw new SQLException();
			}
			
			stat = "insert into eventos_estudiante values(" + idEST + ", " + idEV + ", (select id_logro from logros_evento where id_evento = " + idEV + " and logro_evento = '" + logro + "))";
			C.getConsulta().execute(stat);
			
		} catch (SQLException e) {
			C.desconectar();
			return false;		
			}
    	C.desconectar();
    	return true;
    }
    
    
    public Vector<Boolean> agregar_estudiantes_a_evento(Vector<Tupla<Estudiante, String>> EST, Evento EV) {
    	
    	Vector<Boolean> Mapa = new Vector<>();
    	
    	for(int i = 0; i < EST.size(); i++) {
    		Mapa.add(agregar_evento_a_estudiante(EV, EST.elementAt(i).getN1(), EST.elementAt(i).getN2()));
    	}
    	
    	return Mapa;
    }
    
    public Vector<String> obtener_asignaturas(){
            Vector<String> asig = new Vector<>();
            C.conectar();
            
        try {

            String stat = "select * from asignaturas order by nombre_asignatura";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            while(RS.next()){
                asig.add(RS.getString("nombre_asignatura"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return asig;
    }
    
    public boolean agregar_asignatura(String X){
        
        try {
            C.conectar();
            String stat = "select * from asignaturas where nombre_asignatura = '" + X + "'";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            if(RS.next()){
                throw new SQLException();
            }
            
            stat = "insert into asignaturas values(null, '" + X  +"')";
            C.getConsulta().execute(stat);
            
        } catch (SQLException ex) {
            C.desconectar();
            return false;
        }
        
        C.desconectar();
        return true;
    }
    
    public Vector<String> obtener_carreras(){
        Vector<String> carreras = new Vector<>();
        C.conectar();    
            String stat = "select * from carrera";
        try {
         
            ResultSet RS = C.getConsulta().executeQuery(stat);
            if(!RS.next()){
                throw new SQLException();
            }
            do{
                carreras.add(RS.getString("nombre_carrera"));
            }while(RS.next());
            
        } catch (SQLException ex) {
                     C.desconectar();
                    return carreras;
        }
        
                    C.desconectar();
                    return carreras;
    }

    public Carrera obtener_carrera(String carr) {
         Vector<Vector<Tupla<Integer, String>>> Asignaturas = new Vector<>();
            
            C.conectar();
        
        try {
           
            String stat = "select id_carrera from carrera where nombre_carrera = '" + carr + "'";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            
            int idC = RS.getInt("id_carrera");
            
            stat = "select * from asignaturas_semestre join asignaturas on asignaturas_semestre.id_asignatura = asignaturas.id_asignatura where id_carrera = " + idC;
            RS = C.getConsulta().executeQuery(stat);
            
            while(RS.next()){
                int anno = RS.getInt("anno_brigada");
                int semestre = RS.getInt("semestre");
                String nombre_asig = RS.getString("nombre_asignatura");
                
                while(anno > Asignaturas.size()){
                    Asignaturas.add(new Vector<>());
                }
                
                Asignaturas.elementAt(anno-1).add(new Tupla<>(semestre, nombre_asig));
                
            }
         
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
           C.desconectar();
           return new Carrera(carr, Asignaturas);
    }

    public void eliminar_brigada_a_estudiante(String ci) {

        C.conectar();
            
        try {
            
            String stat = "update EstudianteSencillo set id_brigada = 0 where CI = '" + ci + "'";
            C.getConsulta().execute(stat);
            
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
        public int obtener_annos_carrera(String Carr) {
            int max_anno = 0;
        try {
            C.conectar();
            
            String stat = "select max (anno_brigada) from carrera join asignaturas_semestre on carrera.id_carrera = asignaturas_semestre.id_carrera where carrera.nombre_carrera = '" + Carr + "'";
            
            ResultSet RS = C.getConsulta().executeQuery(stat);
            max_anno = RS.getInt("anno_brigada");
            
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        C.desconectar();
        return max_anno;
        }

    public void editar_carrera(Carrera Carr) {
        
        

    }
    
    //ERIC DE AQUI PA ARRIBA

    public Vector<String> getDimensiones() {
       C.conectar();
        Vector <String> v = new Vector<>();        
        try {
            String stat = "select nombre_dimension from dimensiones ";
            ResultSet rs = C.getConsulta().executeQuery(stat);
            while (rs.next()) {
                v.add(rs.getString("nombre_dimension"));
            }
            C.desconectar();
        } catch (SQLException ex) {
            C.desconectar();
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
       return v;
    }
    
    public Vector<Brigada> obtenerBrigadas(){
       Vector<Brigada> V = new Vector<>();
         C.conectar();
        try {

            String stat = "select * from brigada join carrera on brigada.id_carrera = carrera.id_carrera";
            
            ResultSet RS = C.getConsulta().executeQuery(stat);
            if(!RS.next()){
                throw new SQLException();
            }
            do{
                
                int anno = RS.getInt("anno");
                int annoB = RS.getInt("ano_brigada");
                String nombreCarrera = RS.getString("nombre_carrera");
                V.add(new Brigada(nombreCarrera, anno, annoB, new Vector<>()));
                
            }while(RS.next());
            
        } catch (SQLException ex) {
            C.desconectar();
            return V;
        }
        
        C.desconectar();
        return V;
    }

    public Vector<Brigada> obtenerBrigadasDeUnaCarrera(String carreraSeleccionada) {
            //No estoy seguro si sacar las notas aqui tambien no lo creo necesario
        Vector<Brigada> brigasdasDeLacarrera = new Vector<>();
        Vector<Tupla<Integer,Integer>> anno_annoBrigada = new Vector<>();
        Vector<Integer>Id_brigadas = new Vector<>();
        Vector<Estudiante> estudiantes= new Vector<>();
        try {
            C.conectar();
            String stat ="select id_carrera from carrera where nombre_carrera= '"+carreraSeleccionada+"'";
            ResultSet rs = C.getConsulta().executeQuery(stat);
            int id_carrera = rs.getInt("id_carrera");
            stat ="slect * from brigada where id_carrera="+id_carrera;
            rs=C.getConsulta().executeQuery(stat);
            while (rs.next()) {
                anno_annoBrigada.add(new Tupla<>(rs.getInt("ano_brigada"),rs.getInt("anno")));
                Id_brigadas.add(rs.getInt("id_brigada"));
            }
            for (int i = 0; i < Id_brigadas.size(); i++) {
                stat ="slect * from estudiante where id_brigada="+Id_brigadas.elementAt(i);
                   rs=C.getConsulta().executeQuery(stat);
                   while (rs.next()) {                    
                    String nombreEstudiante = rs.getString("nombre_estudiante");
                    String CI = rs.getString("CI");
                    Estudiante e = new Estudiante(nombreEstudiante, CI);
                    estudiantes.add(e);
                }
                       Brigada b = new Brigada(carreraSeleccionada, anno_annoBrigada.elementAt(i).getN1(), anno_annoBrigada.elementAt(i).getN2(), estudiantes);
                       brigasdasDeLacarrera.add(b);
            }
            C.desconectar();
        } catch (SQLException ex) {
            C.desconectar();
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return brigasdasDeLacarrera;
    }

    public void agregarEventoEstudiantePorParticipacion(String fecha, int dimension,String evento, int valorDelLogro,Vector<Estudiante> estudiantesSeleccionados) {
       Vector<Integer> id_estudiantes = new Vector<>();
        try {
            C.conectar();
            String stat="Select id_nombre_evento from nombre_evento where nombre_evento='"+evento+"'";
            ResultSet rs = C.getConsulta().executeQuery(stat);
            int id_nombre_evento= rs.getInt("id_nombre_evento");
            stat ="insert into eventos values(null,"+id_nombre_evento+","+dimension+",'"+fecha+"')";
            C.getConsulta().execute(stat);
            stat =" SELECT * FROM eventos ORDER BY id_evento DESC LIMIT 1";
            rs = C.getConsulta().executeQuery(stat);
            int id_evento = rs.getInt("id_evento");
            stat="insert into logros_evento values(null,"+id_evento+",Participacion,"+valorDelLogro+") ";
            C.getConsulta().execute(stat);
            stat =" SELECT * FROM logros_evento ORDER BY id_logro DESC LIMIT 1";
            rs= C.getConsulta().executeQuery(stat);
            int id_logro=rs.getInt("id_logro");
            for (int i = 0; i < estudiantesSeleccionados.size(); i++) {
                stat="slect id_estudiante from EstudianteSencillo where CI='"+estudiantesSeleccionados.elementAt(i).getCI()+"'";
                rs=C.getConsulta().executeQuery(stat);
                id_estudiantes.add(rs.getInt("id_estdiante"));
            }
            for (int i = 0; i < id_estudiantes.size(); i++) {
                    stat ="insert into eventos_estudiante values("+id_estudiantes.elementAt(i)+","+id_evento+","+id_logro+")";
                    C.getConsulta().execute(stat);
            }
            C.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

    public Vector<String> obtenerDimensiones() {
         Vector<String> dimensiones = new Vector<>();
        try {
            C.conectar();
            String stat = " select * from dimensiones ";
            ResultSet rs =C.getConsulta().executeQuery(stat);            
            while (rs.next()) {
                dimensiones.add(rs.getString("nombre_dimension"));
            }
            
            C.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dimensiones;
    }

    public Vector<String> obtenerManifestaciones() {
        //mir ppai has esot cojone
        return null;
    }

    public Vector<String> obtenerZonas() {

        C.conectar();
        Vector<String> zonas = new Vector<>();
        
        try {
            
            String stat = "select zona from zona";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            while(RS.next()){
                zonas.add(RS.getString("zona"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        C.desconectar();
        return zonas;
    }
    
     public Vector<String> obtenerColorPiel() {

        C.conectar();
        Vector<String> color_piel = new Vector<>();
        
        try {
            
            String stat = "select color_piel from color_piel";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            while(RS.next()){
                color_piel.add(RS.getString("color_piel"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        C.desconectar();
        return color_piel;
    }
     
     public Vector<String> obtenerEstadoCivil() {

        C.conectar();
        Vector<String> estadoCivil = new Vector<>();
        
        try {
            
            String stat = "select estado_civil from estado_civil";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            while(RS.next()){
                estadoCivil.add(RS.getString("estado_civil"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        C.desconectar();
        return estadoCivil;
    }
     
      public Vector<String> obtenerReligiones() {

        C.conectar();
        Vector<String> Religiones = new Vector<>();
        
        try {
            
            String stat = "select religion from religion";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            if(RS.next()){
                do{
                 Religiones.add(RS.getString("religion"));

                }while(RS.next());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        C.desconectar();
        return Religiones;
    }

    public void agregarReligion(String religion) {

        C.conectar();
        
        try {
            
            
            String stat = "select religion from religion where religion = '" + religion + "'";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            if(RS.next()){
                throw new SQLException();
            }
            stat = "insert into religion values (null, '" + religion + "')";
        } catch (SQLException ex) {
            C.desconectar();
        }
        
        C.desconectar();
    }
    
    
     public Vector<String> obtenerNivelIngles() {

        C.conectar();
        Vector<String> nivelIngles = new Vector<>();
        
        try {
            
            String stat = "select nivel_ingles from nivel_ingles";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            while(RS.next()){
                nivelIngles.add(RS.getString("nivel_ingles"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        C.desconectar();
        return nivelIngles;
    }
   
}
