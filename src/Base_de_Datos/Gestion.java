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
import clases.Nota;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
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
    
    
    public boolean actualizarBrigada(Brigada B){
        
        C.conectar();
    	
    	String stat = "select * from brigada where ano_brigada = " + B.getAnno_brigada() + " and id_carrera = (select id_carrera from carrera where nombre_carrera = '" + B.getCarrera() + "') and anno = " + B.getAnno();
    	try
        {
			ResultSet RS = C.getConsulta().executeQuery(stat);
			int idB = RS.getInt("id_brigada");
        
        stat = "select * from EstudianteSencillo where id_brigada = " + idB;
        RS = C.getConsulta().executeQuery(stat);
        Vector<String> CIS = new Vector<>();
        if(RS.next()){
            do{
                CIS.add(RS.getString("CI"));
                
            }while(RS.next());
        }
        for(int i = 0; i < B.getEstudiantes().size(); i++){
            String ci_estudiante = B.getEstudiantes().elementAt(i).getCI();
            if(!CIS.contains(ci_estudiante)){
            stat = "update EstudianteSencillo set id_brigada = " + idB + " where CI = '" + ci_estudiante + "'";
            C.getConsulta().execute(stat);
            }
            else{
                CIS.remove(ci_estudiante);
            }
        }
        
        for(int i = 0; i < CIS.size(); i++){
             stat = "update EstudianteSencillo set id_brigada = null where CI = '" + CIS.elementAt(i) + "'";
            C.getConsulta().execute(stat);
        }
        
        
    	} catch (SQLException e) {
    		C.desconectar();
			return false;
		}
       
    	
    	C.desconectar();
    	return true;
        
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
            String ci_estudiante = B.getEstudiantes().elementAt(i).getCI();
            stat = "update EstudianteSencillo set id_brigada = " + idB + " where CI = '" + ci_estudiante + "'";
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
    
    
    public boolean agregar_estudiante(Estudiante E, String Carrera){
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
            
            stat = "select id_estudiante from EstudianteSencillo where CI = '" + E.getCI() + "'";
            RS = C.getConsulta().executeQuery(stat);
            int idE = RS.getInt("id_estudiante");
            
            String stat2 = "select asignaturas_semestre.id_asignatura, asignaturas.nombre_asignatura from asignaturas_semestre join carrera join asignaturas on asignaturas_semestre.id_carrera = carrera.id_carrera and asignaturas.id_asignatura = asignaturas_semestre.id_asignatura where carrera.id_carrera = (select id_carrera from carrera where nombre_carrera = '" + Carrera + "')";
           
            RS = C.getConsulta().executeQuery(stat2);
            //Crear un vector y hacer el ciclo debajo
            do{
            
                int idA = RS.getInt("id_asignatura");
                String NA = RS.getString("nombre_asignatura");
                String execute = "insert into notas_estudiante values(" + idE + ", " + idA + ", 0, 0, '" + NA +"')";
                C.getConsulta().execute(execute);
                
            }while(RS.next());
            
        } catch (SQLException ex) {
            System.err.println(ex);
            C.desconectar();
            return false;
        }
        
        C.desconectar();
        return true;
    }
    
    
    public boolean editar_estudiante(Brigada B, DatosEstudiante DE) {
    	int becado=0;
        int militante=0;
        int hijos=0;
        int bebidas=0;
        int fumador=0;
        int [] familiares = new int [10];
        int [] electronics = new int [4];
        int feliz =0;
        int gusto_estudio=0;
        int gusto_carrera=0;
        Vector<Integer> deportes = new Vector<>();
        Vector<Integer> enfermedades = new Vector<>();
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
			if(DE.isBecado()){
                        becado=1;
                        }
                        if(DE.isMilitante()){
                            militante=1;
                        }
                        if(DE.isHijos()){
                            hijos=1;
                        }
                        if(DE.isBebidas_alcoholicas()){
                        bebidas=1;
                        }
                        if(DE.isFumador()){
                            fumador=1;
                        }
                        if(DE.isFeliz()){
                            feliz=1;
                        }
                        if(DE.isGusta_carrera()){
                            gusto_carrera=1;
                        }
                        if(DE.isGusta_estudio()){
                            gusto_estudio=1;
                        }
                        for (int i = 0; i < DE.getConvivencia().length; i++) {
                                if(DE.getConvivencia()[i] == true){
                                    familiares[i]=1;
                                }
                                else {
                                    familiares[i]=0;
                                }
            }
                        for (int i = 0; i < DE.getElectronicos().length; i++) {
                            if(DE.getElectronicos()[i]==true){
                                electronics[i]=1;
                            }
                            else {
                                electronics [i]=0;
                            }
            }
			stat = "insert into Estudiante values(null, '" + DE.getNombre_estudiante() + "', '" + DE.getCI() + "', " + DE.getTelefono_particular() + ", " + DE.getTelefono_fijo() + ", " + DE.isDatos_moviles() + ", '" + DE.getEmail() + "', "
					+ "'"+ DE.getSexo() + "', " + DE.getEdad() + ", " + becado + ", (select id_color_piel from color_piel where color_piel = '" + DE.getColor_de_piel() + "'), " + militante + ", " +  
					"(select id_estado_civil from estado_civil where estado_civil = '" + DE.getEstado_civil() + "'), " + hijos+ ", '" + DE.getDireccion_particular() + "', (select id_zona from zona where zona = '" + DE.getZona() + 
					"'), (select id_religion from religion where religion = '" + DE.getReligion() + "'), " + bebidas + ", "  + fumador + ", " + DE.getParticipacion_brigada() + ", " + idB + 
					", (select id_nivel_ingles from nivel_ingles where nivel_ingles = '" + DE.getNivel_ingles() + "'), " + DE.isActivo() + ")";
			C.getConsulta().execute(stat);
			stat = "select id_estudiante from Estudiante where CI = '" + DE.getCI() + "'";
			RS = C.getConsulta().executeQuery(stat);
			int idE = RS.getInt("id_estudiante");	
			
			for(int i = 0; i < DE.getManifestaciones_artisticas().size(); i++) {
				stat = "insert into artes_estudiante values(" + idE + ", " + DE.getManifestaciones_artisticas().elementAt(i) + ")";
				C.getConsulta().execute(stat);
			}
			
			stat = "insert into convivencia values(" + idE + ", " + familiares[0] + ", " + familiares[1] + ", " + familiares[2] + ", " + familiares[3] + ", " + familiares[4] + 
					", " + familiares[5] + ", " + familiares[6] + ", " + familiares[7] + ", " + familiares[8] + ", " + familiares[9] + ", " + DE.getTotal_familiares() + ", " + DE.getIngreso_total() + 
					", (select id_relaciones from relaciones convivencia where relaciones = '" + DE.getRelaciones() + "') )";
			C.getConsulta().execute(stat);
			         for (int i = 0; i < DE.getDeportes().size(); i++) {
                                     stat = "slect id_deporte from deporte where deporte = '"+DE.getDeportes().elementAt(1)+"'";
                                     RS=C.getConsulta().executeQuery(stat);
                                     deportes.add(RS.getInt("id_deporte"));
                                     
            }
			for(int i = 0; i < deportes.size(); i++) {
				stat = "insert into deporte_estudiante values(" + idE + ", " + deportes.elementAt(i) + ")";
				C.getConsulta().execute(stat);
			}
			
			stat = "insert into Electronics values(null, " + idE + ", " + DE.getElectronicos()[0] + ", " + DE.getElectronicos()[1] + ", " + DE.getElectronicos()[2] + ", " + DE.getElectronicos()[3] + ")";
			C.getConsulta().execute(stat);
			
                        for (int i = 0; i < DE.getEnfermedades().size(); i++) {
                            stat= "slect id_enfermedad from enfermedad where enfermedad = '"+DE.getEnfermedades().elementAt(i)+"'";
                            RS= C.getConsulta().executeQuery(stat);
                            enfermedades.add(RS.getInt("id_enfermedad"));
                        }
			for(int i = 0; i < enfermedades.size(); i++) {
				stat = "insert into enfermedades_estudiante values(" + idE + ", " + enfermedades.elementAt(i) + ")";
				C.getConsulta().execute(stat);
			}
			
			for(int i = 0; i < DE.getMedicamentos().size(); i++) {
				stat = "insert into medicamentos_estudiante values(" + idE + ", " + DE.getMedicamentos().elementAt(i) + ")";
				C.getConsulta().execute(stat);
			}
			
			stat = "insert into psiquis_estudiante values(" + idE + ", '" + DE.getDeseos_futuros() + "', '" + DE.getActividades_tiempo_libre() + "', '" + DE.getProyectos_vida() + "', '"
					 + DE.getRasgos_habitos() + "', "  + feliz + ", " + gusto_estudio + ", " + gusto_carrera + ")";
			
			C.getConsulta().execute(stat);
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
    	
            try{
			
			String stat = "insert into eventos values(null, (select id_nombre_evento from nombre_evento where nombre_evento = '" + E.getNombre() + "'), " + E.getDimension() + ", '" + E.getAnno() + "')";
			C.getConsulta().execute(stat);
			
		} catch (SQLException e) {
			C.desconectar();
			return false;
		}
    	
    	C.desconectar();
    	return true;
    }
    
    public boolean agregar_evento_a_estudiante(Evento E, Estudiante EST, Tupla<String, Integer> logro) {
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
			
			stat = "insert into eventos_estudiante values(" + idEST + ", " + idEV + ", (select id_logro from logros_evento where id_evento = " + idEV + " and logro_evento = '" + logro.getN1() + "))";
			C.getConsulta().execute(stat);
			
		} catch (SQLException e) {
			C.desconectar();
			return false;		
			}
    	C.desconectar();
    	return true;
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
            
            Vector<Integer> brigadasID = new Vector<>();
            if(!RS.next()){
                throw new SQLException();
            }
            do{
                
                int anno = RS.getInt("anno");
                int annoB = RS.getInt("ano_brigada");
                String nombreCarrera = RS.getString("nombre_carrera");
                brigadasID.add(RS.getInt("id_brigada"));
                V.add(new Brigada(nombreCarrera, anno, annoB, new Vector<>()));
                
            }while(RS.next());
            
            for(int i = 0; i < brigadasID.size(); i++){
                stat = "select * from EstudianteSencillo where id_brigada = " + brigadasID.elementAt(i);
                RS = C.getConsulta().executeQuery(stat);
                if(RS.next()){
                    do{
                        V.elementAt(i).addStudent(new Estudiante(RS.getString("nombre_estudiante"), RS.getString("CI")));
                        
                    }while(RS.next());
                }
 
            }
            
            
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
            stat ="select * from brigada where id_carrera="+id_carrera;
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
            String stat = "select * from dimensiones";
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
        
       C.conectar();
        Vector<String> manifestaciones = new Vector<>();
        
        try {
            
            String stat = "select manifestacion from manifestacion_artistica";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            if(RS.next()){
                do{
                 manifestaciones.add(RS.getString("manifestacion"));

                }while(RS.next());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        C.desconectar();
        return manifestaciones;
        
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

    public void agregarManifestacionArtistica(String temp) {

         C.conectar();
        
        try {
            
            
            String stat = "select manifestacion from manifestacion_artistica where manifestacion = '" + temp + "'";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            if(RS.next()){
                throw new SQLException();
            }
            stat = "insert into manifestacion_artistica values (null, '" + temp + "')";
        } catch (SQLException ex) {
            C.desconectar();
        }
        
        C.desconectar();

    }

    public Vector<String> obtenerDeportes() {

         C.conectar();
        Vector<String> deportes = new Vector<>();
        
        try {
            
            String stat = "select deporte from deporte";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            if(RS.next()){
                do{
                 deportes.add(RS.getString("deporte"));

                }while(RS.next());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        C.desconectar();
        return deportes;
    }

    public void agregarDeporte(String temp) {

   C.conectar();
        
        try {
            
            
            String stat = "select deporte from deporte where deporte = '" + temp + "'";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            if(RS.next()){
                throw new SQLException();
            }
            stat = "insert into deporte values (null, '" + temp + "')";
        } catch (SQLException ex) {
            C.desconectar();
        }
        
        C.desconectar();

    }

    public Vector<String> obtenerEnfermedades() {

 C.conectar();
        Vector<String> enfermedades = new Vector<>();
        
        try {
            
            String stat = "select enfermedad from enfermedad";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            if(RS.next()){
                do{
                 enfermedades.add(RS.getString("enfermedad"));

                }while(RS.next());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        C.desconectar();
        return enfermedades;
    }

    public void agregarEnfermedad(String temp) {

        C.conectar();
        
        try {
            
            
            String stat = "select enfermedad from enfermedad where enfermedad = '" + temp + "'";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            if(RS.next()){
                throw new SQLException();
            }
            stat = "insert into enfermedad values (null, '" + temp + "')";
        } catch (SQLException ex) {
            C.desconectar();
        }
        
        C.desconectar();

        
    }
    
    
    public Vector<String> obtenerMedicamentos() {

 C.conectar();
        Vector<String> medicamentos = new Vector<>();
        
        try {
            
            String stat = "select medicamento from medicamento";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            if(RS.next()){
                do{
                 medicamentos.add(RS.getString("medicamento"));

                }while(RS.next());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        C.desconectar();
        return medicamentos;
    }

    public void agregarMedicamento(String temp) {

        C.conectar();
        
        try {
            
            
            String stat = "select medicamento from medicamento where medicamento = '" + temp + "'";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            if(RS.next()){
                throw new SQLException();
            }
            stat = "insert into medicamento values (null, '" + temp + "')";
        } catch (SQLException ex) {
            C.desconectar();
        }
        
        C.desconectar();

        
    }

    public Vector<String> obtenerConvivencia() {

        C.conectar();
        Vector<String> convivencia = new Vector<>();
        
        try {
            
            String stat = "select relaciones from relaciones_convivencia";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            while(RS.next()){
                convivencia.add(RS.getString("relaciones"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        C.desconectar();
        return convivencia;
        
    }

    public Vector<Nota> obtenerNotas(Carrera carr, Estudiante E,int anno_brugada) {

             C.conectar();
             Vector<Nota> notas = new Vector<>();
        
        try {
       
            
            String stat = "select id_estudiante from EstudianteSencillo where CI = '" + E.getCI() + "'";
            
            ResultSet RS = C.getConsulta().executeQuery(stat);
            
            int idE = RS.getInt("id_estudiante");
            
             stat = "select id_asignatura, nombre_asignatura from asignaturas_semestre join carrera on asignaturas_semestre.id_carrera = carrera.id_carrera where carrera.id_carrera = (select id_carrera from carrera where nombre_carrera = '" + carr + "') and anno_brigada="+anno_brugada;
           
            RS = C.getConsulta().executeQuery(stat);
            while(RS.next()){
                
                int idA = RS.getInt("id_asignatura");
                String NA = RS.getString("nombre_asignatura");
                notas.add(new Nota(idA, 0, 0, NA));
            
            }
            
            for(int i = 0; i < notas.size(); i++){
                stat = "select nota_asignatura, nota_examen_premio from notas_estudiante where id_asignatura = " + notas.elementAt(i).getIdAsignatura() + " and id_estudiante = " + idE;
                RS = C.getConsulta().executeQuery(stat);
                
                int notaAsig = RS.getInt("nota_asignatura");
                int notaPremio = RS.getInt("nota_examen_premio");
                
                notas.elementAt(i).setLugar_examen_premio(notaPremio);
                notas.elementAt(i).setNota(notaAsig);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        C.desconectar();
        return notas;
    }

    public boolean esPrimerSemestre(int idAsignatura) {
        C.conectar();
        
        try {
            
            String stat = "select semestre from asignaturas_semestre where id_asignatura = " + idAsignatura;
            ResultSet RS = C.getConsulta().executeQuery(stat);
            if(RS.getInt("semestre") == 1){
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
    public boolean esSegundoSemestre(int idAsignatura) {
        C.conectar();
        
        try {
            
            String stat = "select semestre from asignaturas_semestre where id_asignatura = " + idAsignatura;
            ResultSet RS = C.getConsulta().executeQuery(stat);
            if(RS.getInt("semestre") == 2){
                C.desconectar();
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        C.desconectar();
        return false;
    }

    public void actualizarNota(Nota nota, Estudiante E) {
        
        try {
            C.conectar();
            
            String stat = "update notas_estudiante put nota_asignatura = " + nota.getNota() + " where id_asignatura = " + nota.getIdAsignatura() + " and id_estudiante = (select id_estudiante from EstudianteSencillo where CI = '" + E.getCI() + "')";
            
            boolean RS = C.getConsulta().execute(stat);
            C.desconectar();
            
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Vector<Tupla<Integer, String>> obtenerEventos() {
        
                    C.conectar();

                    Vector<Tupla<Integer, String>> eventos = new Vector<>();
        try {
            
            String stat = "select * from eventos";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            
            if(RS.next()){
                do{
                    eventos.add(new Tupla<Integer,String>(RS.getInt("id_evento"), RS.getString("fecha_evento")));
                    
                }while(RS.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        C.desconectar();
        return eventos;
    }

    public Vector<Evento> obtenerEventosBrigada(Brigada B) {
        
        C.conectar();
        Vector<Evento> eventos = new Vector<>();
        
        try {
            
            String stat = "select id_brigada from brigada where ano_brigada = " + B.getAnno_brigada() +" and id_carrera = (select id_carrera from carrera where nombre_carrera = '" + B.getCarrera() + "') and anno = " + B.getAnno();
            ResultSet RS = C.getConsulta().executeQuery(stat);
            int idB = RS.getInt("id_brigada");
            
            
            stat = "select * from eventos_brigada join eventos join nombre_evento on eventos_brigada.id_evento = eventos.id_evento and eventos.id_nombre_evento = nombre_evento.id_nombre_evento where eventos_brigada.id_brigada = " + idB;
             RS = C.getConsulta().executeQuery(stat);
            
            if(RS.next()){
                do{
                    eventos.add(new Evento(RS.getString("nombre_evento"), RS.getInt("id_dimension"), RS.getString("fecha_evento")));
                    
                }while(RS.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        C.desconectar();
        return eventos;
    }
    
    public Vector<Tupla<Integer,String>> obtenerBrigadaEventos(Brigada B) {
        
        C.conectar();
        Vector<Tupla<Integer,String>> eventos = new Vector<>();
        
        try {
            
            String stat = "select id_brigada from brigada where ano_brigada = " + B.getAnno_brigada() +" and id_carrera = (select id_carrera from carrera where nombre_carrera = '" + B.getCarrera() + "') and anno = " + B.getAnno();
            ResultSet RS = C.getConsulta().executeQuery(stat);
            int idB = RS.getInt("id_brigada");
            
            
            stat = "select * from eventos_brigada join eventos join nombre_evento on eventos_brigada.id_evento = eventos.id_evento and eventos.id_nombre_evento = nombre_evento.id_nombre_evento where eventos_brigada.id_brigada = " + idB;
             RS = C.getConsulta().executeQuery(stat);
            
            if(RS.next()){
                do{
                    eventos.add(new Tupla<>(RS.getInt("id_evento"), RS.getString("fecha_evento")));
                    
                }while(RS.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        C.desconectar();
        return eventos;
    }
    
    

    public Object obtenerNombreEvento(Integer id) {

        String nombreEvento="";
        try {
            C.conectar();
            
            String stat = "select nombre_evento.nombre_evento from eventos join nombre_evento on nombre_evento.id_nombre_evento = eventos.id_nombre_evento where eventos.id_evento = " + id;
            ResultSet RS = C.getConsulta().executeQuery(stat);
            nombreEvento = RS.getString("nombre_evento");
            C.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nombreEvento;
    }

    public void actualizarEventosBrigada(Brigada B, Vector<Tupla<Integer, String>> eventosBrigada, Vector<Tupla<Integer, String>> eventosEliminados) {
        
        C.conectar();

        try {
            String stat = "select id_brigada from brigada where ano_brigada = " + B.getAnno_brigada() +" and id_carrera = (select id_carrera from carrera where nombre_carrera = '" + B.getCarrera() + "') and anno = " + B.getAnno();
            ResultSet RS = C.getConsulta().executeQuery(stat);
            int idB = RS.getInt("id_brigada");
            
            for(int i = 0; i < eventosBrigada.size(); i++){
                stat = "select * from eventos_brigada where id_brigada = " + idB + " and id_evento = " + eventosBrigada.elementAt(i).getN1();
                RS = C.getConsulta().executeQuery(stat);
                if(!RS.next()){
                    stat = "insert into eventos_brigada values(" + idB + ", " + eventosBrigada.elementAt(i).getN1() + ")";
                    C.getConsulta().execute(stat);
                }
            }
            
            for(int i = 0; i < eventosEliminados.size(); i++){
                stat = "select * from eventos_brigada where id_brigada = " + idB + " and id_evento = " + eventosEliminados.elementAt(i).getN1();
                RS = C.getConsulta().executeQuery(stat);
                if(RS.next()){
                    stat = "delete from eventos_brigada where id_brigada = " + idB + " and id_evento = " + eventosEliminados.elementAt(i).getN1();
                    C.getConsulta().execute(stat);
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        C.desconectar();

    }

    public int obtenerSumaValoresEventos(Brigada brigada) {

        C.conectar();
        int sol =0;
        
        try {
            
            String stat = "select id_brigada from brigada where ano_brigada = " + brigada.getAnno_brigada() +" and id_carrera = (select id_carrera from carrera where nombre_carrera = '" + brigada.getCarrera() + "') and anno = " + brigada.getAnno();
            ResultSet RS = C.getConsulta().executeQuery(stat);
            int idB = RS.getInt("id_brigada");
            
            stat = "select sum (max(logros_evento.valor_logro)) from logros_evento join eventos_brigada on logros_evento.id_evento = eventos_brigada.id_evento where eventos_brigada.id_brigada = " + idB;
            RS = C.getConsulta().executeQuery(stat);
            sol = Integer.parseInt(RS.getString(stat));
            
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }

        C.desconectar();
        return sol;
    }

    public double obtenerPromedio(Estudiante est) {
        C.conectar();
        double promedio = 0.0;
        try {
            String stat = "select sum (nota_asignatura) from notas_estudiante where id_estudiante = (select id_estudiante from EstudianteSencillo where CI = '" + est.getCI() + "')";
            
            ResultSet RS = C.getConsulta().executeQuery(stat);
            int suma = RS.getInt(stat);
            
            stat = "select count from notas_estudiante where id_estudiante = (select id_estudiante from EstudianteSencillo where CI = '" + est.getCI() + "') and nota_asignatura != 0";
            int cantidad = RS.getInt(stat);
            
            promedio = suma/cantidad;
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        C.desconectar();
        return promedio;
    }

    public int obtenerValoresEventosEstudiante(Estudiante est) {

        C.conectar();
        int sol = 0;
        try {
            String stat = "select sum logros_evento.valor_logro from logros_evento join eventos_estudiante on logros_evento.id_logro = eventos_estudiante.id_logro where eventos_estudiante.id_estudiante = (select id_estudiante from EstudianteSencillo where CI = '" + est.getCI() + "')";
            
            ResultSet RS = C.getConsulta().executeQuery(stat);
            sol = RS.getInt(stat);
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        C.desconectar();
        return sol;
    }

    public boolean existeEventoFecha(int anno, String nombreEvento) {

            C.conectar();
        try {
            
            String stat = "select * from eventos where id_nombre_evento = (select id_nombre_evento from nombre_evento where nombre_evento = '" + nombreEvento + "')";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            
            
            if(RS.next()){
                do{
            String fecha = RS.getString("fecha_evento");
                int nuevoAnno = Integer.parseInt(fecha.substring(fecha.length()-4));

                if(anno == nuevoAnno){
                    C.desconectar();
                    return true;
                }
                }while(RS.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        C.desconectar();
        return false;
    }

    public boolean existeEstudianteEvento(Evento E, Estudiante est) {
            C.conectar();

        try {
            
            String stat = "select * from eventos_estudiante where id_estudiante = (select id_estudiante from EstudianteSencillo where CI = '" + est.getCI() + "' and id_evento = (select id_evento from eventos where id_dimension = " + E.getDimension() + " and fecha_evento = '" + E.getAnno() + "' and id_nombre_evento = (select id_nombre_evento from nombre_evento where nombre_evento = '" + E.getNombre() + "'))";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            
            if(RS.next()){
                
                return true;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    public Object obtenerLogroEstudiante(Evento E, Estudiante est) {

        C.conectar();

        String logro = "";
        
        try {
            
            String stat = "select * from eventos_estudiante where id_estudiante = (select id_estudiante from EstudianteSencillo where CI = '" + est.getCI() + "' and id_evento = (select id_evento from eventos where id_dimension = " + E.getDimension() + " and fecha_evento = '" + E.getAnno() + "' and id_nombre_evento = (select id_nombre_evento from nombre_evento where nombre_evento = '" + E.getNombre() + "'))";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            
            int idL = RS.getInt("id_logro");
            
            stat = "select logro_evento from logros_evento where id_logro = " + idL;
            
            RS = C.getConsulta().executeQuery(stat);
            
            logro = RS.getString("logro_evento");
            
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        C.desconectar();
        return logro;

    }

    public void agregarLogro(Tupla<String, Integer> logro, Evento eventoSeleccionado) {

        C.conectar();
        try {
            String stat = "select id_evento from eventos where id_evento = (select id_evento from eventos where id_dimension = " + eventoSeleccionado.getDimension() + " and fecha_evento = '" + eventoSeleccionado.getAnno() + "' and id_nombre_evento = (select id_nombre_evento from nombre_evento where nombre_evento = '" + eventoSeleccionado.getNombre() + "'))";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            
            int idE = RS.getInt("id_evento");
            
            stat = "select * from logros_evento where id_evento = " + idE + " and logro_evento = '" + logro.getN1() + "'";
            RS = C.getConsulta().executeQuery(stat);
            
            if(RS.next()){
                int idL = RS.getInt("id_logro");
                stat = "update logros_evento set valor_logro = " + logro.getN2() + " where id_logro = " + idL;
                C.getConsulta().execute(stat);
            }
            else{
                stat = "insert into logros_evento values(null, " + idE + ", '" + logro.getN1() + "', " + logro.getN2() + ")";
                C.getConsulta().execute(stat);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        C.desconectar();
    }

    public Vector<Tupla<String, Integer>> obtenerLogrosEvento(Evento eventoSeleccionado) {

        Vector<Tupla<String, Integer>> logros = new Vector<>();
        
         C.conectar();
        try {
            String stat = "select id_evento from eventos where id_evento = (select id_evento from eventos where id_dimension = " + eventoSeleccionado.getDimension() + " and fecha_evento = '" + eventoSeleccionado.getAnno() + "' and id_nombre_evento = (select id_nombre_evento from nombre_evento where nombre_evento = '" + eventoSeleccionado.getNombre() + "'))";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            
            int idE = RS.getInt("id_evento");
            
            stat = "select * from logros_evento where id_evento = " + idE;
            RS = C.getConsulta().executeQuery(stat);
            
            if(RS.next()){
                do{
                    logros.add(new Tupla<>(RS.getString("logro_evento"), RS.getInt("valor_logro")));
                    
                }while(RS.next());
            }

            
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        C.desconectar();
        return logros;
    }

    public void eliminarEstudianteEvento(Estudiante est, Evento eventoSeleccionado) {
            
        C.conectar();
    	String stat = "select id_evento from eventos where id_nombre_evento = (select id_nombre_evento from nombre_evento where nombre_evento = '" + eventoSeleccionado.getNombre() + "') and anno_evento = " + eventoSeleccionado.getAnno() + ")";
    	
    	try {
			ResultSet RS = C.getConsulta().executeQuery(stat);
			int idEV = RS.getInt("id_evento");
			
			stat = "select id_estudiante from EstudianteSencillo where CI = '" + est.getCI() + "'";
			RS = C.getConsulta().executeQuery(stat);
			int idEST = RS.getInt("id_estudiante");
			
			stat = "select * from eventos_estudiante where id_evento = " + idEV + " and id_estudiante = " + idEST;
			RS = C.getConsulta().executeQuery(stat);
			if(RS.next()) {
                            
                            stat = "delete from eventos_estudiante where id_evento = " + idEV + " and id_estudiante = " + idEST;
                            C.getConsulta().execute(stat);
                        }
			
			
		} catch (SQLException e) {
			C.desconectar();
			}
    	C.desconectar();
    }
   
}
