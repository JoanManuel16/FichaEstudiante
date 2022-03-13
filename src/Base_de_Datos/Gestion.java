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

public class Gestion {
    private final Conexion C;
    
    public Gestion(){
        C = new Conexion();
    }

    public boolean agregar_brigada(Brigada B) {
    	
    	C.conectar();
    	
    	String stat = "select * from brigada where ano_brigada = " + B.getAnno_brigada() + " and id_carrera = (select id_carrera from carrera where nombre_carrera = '" + B.getCarrera() + "') and anno = " + B.getAnno();
    	try {
			ResultSet RS = C.getConsulta().executeQuery(stat);
			if(RS.next()) {
				throw new SQLException();
			}
		
    	stat = "insert into brigada values(null, " + B.getAnno_brigada() + ", (select id_carrera from carrera where nombre_carrera = '" + B.getCarrera() + "'), " + B.getAnno() + ")";
    	C.getConsulta().execute(stat);
    	
    	} catch (SQLException e) {
    		C.desconectar();
			return false;
		}
    	
    	C.desconectar();
    	return true;
    	}
    
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
    
    
    public boolean agregar_estudiante(Brigada B, DatosEstudiante DE) {
    	
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
    	Vector<String> nombres = new Vector<String>();
    	String stat = "select nombre_evento from nombre_evento";
    	try {
			ResultSet RS = C.getConsulta().executeQuery(stat);
			
			while(RS.next()) {
				nombres.add(RS.getString("nombre_evento"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
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
			e.printStackTrace();
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
			
			stat = "select id_estudiante from Estudiante where CI = '" + EST.getCI() + "'";
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
    	
    	Vector<Boolean> Mapa = new Vector<Boolean>();
    	
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
            while(RS.next()){
                carreras.add(RS.getString("nombre_carrera"));
            }
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
            
            stat = "select id_estudiante Estudiante where CI = '" + ci  + "'";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            
            if(RS.next()){
                stat = "update Estudiante set id_brigada = 0 where CI = '" + ci + "'";
                C.getConsulta().execute(stat);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void editar_carrera(Carrera Carr) {


    }
   
}
