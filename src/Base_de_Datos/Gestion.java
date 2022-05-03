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
import java.util.logging.Level;
import java.util.logging.Logger;
import utiles.Tupla;

//UPDATEAR los datos del estudiante solo cuando se vaya a cargar. Nunca antes.
public class Gestion {

    private final Conexion C;

    public Gestion() {
        C = new Conexion();
    }

    public boolean actualizarBrigada(Brigada B) {

        C.conectar();

        String stat = "select * from brigada where ano_brigada = " + B.getAnno_brigada() + " and id_carrera = (select id_carrera from carrera where nombre_carrera = '" + B.getCarrera() + "') and anno = " + B.getAnno();
        try {
            ResultSet RS = C.getConsulta().executeQuery(stat);
            int idB = RS.getInt("id_brigada");

            stat = "select * from EstudianteSencillo where id_brigada = " + idB;
            RS = C.getConsulta().executeQuery(stat);
            Vector<String> CIS = new Vector<>();
            if (RS.next()) {
                do {
                    CIS.add(RS.getString("CI"));

                } while (RS.next());
            }
            for (int i = 0; i < B.getEstudiantes().size(); i++) {
                String ci_estudiante = B.getEstudiantes().elementAt(i).getCI();
                if (!CIS.contains(ci_estudiante)) {
                    stat = "update EstudianteSencillo set id_brigada = " + idB + " where CI = '" + ci_estudiante + "'";
                    C.getConsulta().execute(stat);
                } else {
                    CIS.remove(ci_estudiante);
                }
            }

            for (int i = 0; i < CIS.size(); i++) {
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
    public boolean agregar_brigada(Brigada B) {

        C.conectar();

        String stat = "select * from brigada where ano_brigada = " + B.getAnno_brigada() + " and id_carrera = (select id_carrera from carrera where nombre_carrera = '" + B.getCarrera() + "') and anno = " + B.getAnno();
        try {
            ResultSet RS = C.getConsulta().executeQuery(stat);
            if (RS.next()) {
                throw new SQLException();
            }

            stat = "insert into brigada values(null, " + B.getAnno_brigada() + ", (select id_carrera from carrera where nombre_carrera = '" + B.getCarrera() + "'), " + B.getAnno() + ")";
            C.getConsulta().execute(stat);

            stat = "select id_brigada from brigada where ano_brigada = " + B.getAnno_brigada() + " and id_carrera = (select id_carrera from carrera where nombre_carrera = '" + B.getCarrera() + "') and anno = " + B.getAnno();
            RS = C.getConsulta().executeQuery(stat);
            int idB = RS.getInt("id_brigada");

            for (int i = 0; i < B.getEstudiantes().size(); i++) {
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
            if (RS.next()) {
                throw new SQLException();
            }

            stat = "insert into carrera values(null, '" + Carr.getNombre() + "')";
            C.getConsulta().execute(stat);

            stat = "select * from carrera where nombre_carrera = '" + Carr.getNombre() + "'";
            RS = C.getConsulta().executeQuery(stat);
            int idC = RS.getInt("id_carrera");

            for (int i = 0; i < Carr.getAsignaturas().size(); i++) {
                for (int j = 0; j < Carr.getAsignaturas().elementAt(i).size(); j++) {
                    Tupla<Integer, String> T = Carr.getAsignaturas().elementAt(i).elementAt(j);
                    int idA = 0;

                    stat = "select * from asignaturas where nombre_asignatura = '" + T.getN2() + "'";
                    RS = C.getConsulta().executeQuery(stat);
                    if (RS.next()) {
                        idA = RS.getInt("id_asignatura");
                    } else {
                        stat = "insert into asignaturas values(null, '" + T.getN2() + "')";
                        C.getConsulta().execute(stat);
                        stat = "select * from asignaturas where nombre_asignatura = '" + T.getN2() + "'";
                        RS = C.getConsulta().executeQuery(stat);
                        idA = RS.getInt("id_asignatura");
                    }

                    stat = "insert into asignaturas_semestre values(" + idC + ", " + idA + ", " + (i + 1) + ", " + T.getN1() + ")";
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

    public boolean agregar_estudiante(Estudiante E, String Carrera) {
        C.conectar();

        String stat = "select * from EstudianteSencillo where CI = '" + E.getCI() + "'";

        try {

            ResultSet RS = C.getConsulta().executeQuery(stat);
            if (RS.next()) {

                int idB = RS.getInt("id_brigada");

                if (idB != 0) {
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
            
            Vector<Tupla<Integer, String>> V = new Vector<>();
            
            do {
            int idA = RS.getInt("id_asignatura");
            String NA = RS.getString("nombre_asignatura");
            V.add(new Tupla<>(idA, NA));
            
            } while (RS.next());
            
                for(int i = 0; i < V.size(); i++){
                //A veces pone la asignatura doble
                    String execute = "insert into notas_estudiante values(" + idE + ", " + V.elementAt(i).getN1() + ", 0, 0, '" + V.elementAt(i).getN2() + "')";
                C.getConsulta().execute(execute);
                }

        } catch (SQLException ex) {
            System.err.println(ex);
            C.desconectar();
            return false;
        }

        C.desconectar();
        return true;
    }

    public boolean editar_estudiante(Brigada B, DatosEstudiante DE) {
        int becado = 0;
        int militante = 0;
        int hijos = 0;
        int bebidas = 0;
        int fumador = 0;
        int datosMoviles = 0;
        int activo = 0;
        int[] familiares = new int[10];
        int[] electronics = new int[4];
        int feliz = 0;
        int gusto_estudio = 0;
        int gusto_carrera = 0;
        Vector<Integer> deportes = new Vector<>();
        Vector<Integer> enfermedades = new Vector<>();
        
        C.conectar();

        String stat = "select id_brigada from brigada where ano_brigada = " + B.getAnno_brigada() + " and id_carrera = (select id_carrera from carrera where nombre_carrera = '" + B.getCarrera() + "') and anno = " + B.getAnno();

        try {
            ResultSet RS = C.getConsulta().executeQuery(stat);
            int idB = RS.getInt("id_brigada");

            stat = "select id_estudiante from Estudiante where CI = '" + DE.getCI() + "'";
            RS = C.getConsulta().executeQuery(stat);
            if (RS.next()) {
                throw new SQLException();
            }
            if (DE.isBecado()) {
                becado = 1;
            }
            if (DE.isMilitante()) {
                militante = 1;
            }
            if (DE.isHijos()) {
                hijos = 1;
            }
            if (DE.isBebidas_alcoholicas()) {
                bebidas = 1;
            }
            if (DE.isFumador()) {
                fumador = 1;
            }
            if (DE.isFeliz()) {
                feliz = 1;
            }
            if (DE.isGusta_carrera()) {
                gusto_carrera = 1;
            }
            if (DE.isGusta_estudio()) {
                gusto_estudio = 1;
            }
            if (DE.isDatos_moviles()) {
                datosMoviles = 1;
            }
            if (DE.isActivo()) {
                activo = 1;
            }
            for (int i = 0; i < DE.getConvivencia().length; i++) {
                if (DE.getConvivencia()[i] == true) {
                    familiares[i] = 1;
                } else {
                    familiares[i] = 0;
                }
            }
            for (int i = 0; i < DE.getElectronicos().length; i++) {
                if (DE.getElectronicos()[i] == true) {
                    electronics[i] = 1;
                } else {
                    electronics[i] = 0;
                }
            }
            int id_religion=0;
            if(!DE.getReligion().equals("")){
            stat = "select id_religion from religion where religion = '" + DE.getReligion()+"'";
           RS = C.getConsulta().executeQuery(stat);
           id_religion=RS.getInt("id_religion");
            }
            stat = "insert into Estudiante values(null, '" + DE.getNombre_estudiante() + "', '" + DE.getCI() + "', " + DE.getTelefono_particular() + ", " + DE.getTelefono_fijo() + ", " + datosMoviles + ", '" + DE.getEmail() + "', "
                    + "'" + DE.getSexo() + "', " + DE.getEdad() + ", " + becado + ", " + DE.getColor_de_piel() + ", " + militante + ", "
                    + +DE.getEstado_civil() + ", " + hijos + ", '" + DE.getDireccion_particular() + "'," + DE.getZona()
                    + ","+id_religion+ ", " + bebidas + ", " + fumador + ", " + DE.getParticipacion_brigada() + ", " + idB
                    + "," + DE.getNivel_ingles() + ", " + activo + ")";
            C.getConsulta().execute(stat);
            stat = "select id_estudiante from Estudiante where CI = '" + DE.getCI() + "'";
            RS = C.getConsulta().executeQuery(stat);
            int idE = RS.getInt("id_estudiante");

            if (!DE.getManifestaciones_artisticas().isEmpty()) {
                Vector<Integer> maifestaciones = new Vector<>();
                for (int i = 0; i < DE.getManifestaciones_artisticas().size(); i++) {
                    stat = "select id_manifestacion from manifestacion_artistica where manifestacion = '"+DE.getManifestaciones_artisticas().elementAt(i)+"'";
                    RS=C.getConsulta().executeQuery(stat);
                    maifestaciones.add(RS.getInt(1));
                }
                for (int i = 0; i < maifestaciones.size(); i++) {
                    stat = "insert into artes_estudiante values(" + idE + ", '" + maifestaciones.elementAt(i) + "')";
                    C.getConsulta().execute(stat);
                }
            }

            stat = "insert into convivencia values(" + idE + ", " + familiares[0] + ", " + familiares[1] + ", " + familiares[2] + ", " + familiares[3] + ", " + familiares[4]
                    + ", " + familiares[5] + ", " + familiares[6] + ", " + familiares[7] + ", " + familiares[8] + ", " + familiares[9] + ", " + DE.getTotal_familiares() + ", " + DE.getIngreso_total()
                    + "," + DE.getRelaciones() + " )";
            C.getConsulta().execute(stat);
            if(!DE.getDeportes().isEmpty()){
                for (int i = 0; i < DE.getDeportes().size(); i++) {
                stat = "select id_deporte from deporte where deporte = '" + DE.getDeportes().elementAt(i) + "'";
                RS = C.getConsulta().executeQuery(stat);
                deportes.add(RS.getInt("id_deporte"));

            }
            for (int i = 0; i < deportes.size(); i++) {
                stat = "insert into deporte_estudiante values(" + idE + ", " + deportes.elementAt(i) + ")";
                C.getConsulta().execute(stat);
            }
            }

            stat = "insert into Electronics values(null, " + idE + ", " + electronics[0] + ", " + electronics[1] + ", " + electronics[2] + ", " + electronics[3] + ")";
            C.getConsulta().execute(stat);

            if(!DE.getEnfermedades().isEmpty()){
            for (int i = 0; i < DE.getEnfermedades().size(); i++) {
                stat = "select id_enfermedad from enfermedad where enfermedad = '" + DE.getEnfermedades().elementAt(i) + "'";
                RS = C.getConsulta().executeQuery(stat);
                enfermedades.add(RS.getInt("id_enfermedad"));
            }
            for (int i = 0; i < enfermedades.size(); i++) {
                stat = "insert into enfermedades_estudiante values(" + idE + ", " + enfermedades.elementAt(i) + ")";
                C.getConsulta().execute(stat);
            }
            }
            Vector<Integer>medicamnetos= new Vector<>();
            if(!DE.getMedicamentos().isEmpty()){
                for (int i = 0; i < DE.getMedicamentos().size(); i++) {
                    stat = "select id_medicamento from medicamento where medicamento = '"+DE.getMedicamentos().elementAt(i)+"'";
                    RS= C.getConsulta().executeQuery(stat);
                    medicamnetos.add(RS.getInt("id_medicamento"));
                }
            for (int i = 0; i < medicamnetos.size(); i++) {
                stat = "insert into medicamentos_estudiante values(" + idE + ", " + medicamnetos.elementAt(i) + ")";
                C.getConsulta().execute(stat);
            }
            }

            stat = "insert into psiquis_estudiante values(" + idE + ", '" + DE.getDeseos_futuros() + "', '" + DE.getActividades_tiempo_libre() + "', '" + DE.getProyectos_vida() + "', '"
                    + DE.getRasgos_habitos() + "', " + feliz + ", " + gusto_estudio + ", " + gusto_carrera + ")";

            C.getConsulta().execute(stat);
        } catch (SQLException e) {
            System.err.println(e);
            C.desconectar();
            return false;
        }

        C.desconectar();
        return true;
    }

    public Vector<String> obtener_nombres_eventos() {
        C.conectar();
        Vector<String> nombres = new Vector<>();
        String stat = "select nombre_evento from nombre_evento";
        try {
            ResultSet RS = C.getConsulta().executeQuery(stat);

            while (RS.next()) {
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

        try {

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
    	String stat = "select id_evento from eventos where id_nombre_evento = (select id_nombre_evento from nombre_evento where nombre_evento = '" + E.getNombre() + "') and fecha_evento = '" + E.getAnno() + "'";
    	
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
			
			stat = "insert into eventos_estudiante values(" + idEST + ", " + idEV + ", (select id_logro from logros_evento where id_evento = " + idEV + " and logro_evento = '" + logro.getN1() + "'))";
			C.getConsulta().execute(stat);
			
		} catch (SQLException e) {
			C.desconectar();
			return false;		
			}
    	C.desconectar();
    	return true;

    }

    public Vector<String> obtener_asignaturas() {
        Vector<String> asig = new Vector<>();
        C.conectar();

        try {

            String stat = "select * from asignaturas order by nombre_asignatura";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            while (RS.next()) {
                asig.add(RS.getString("nombre_asignatura"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return asig;
    }

    public boolean agregar_asignatura(String X) {

        try {
            C.conectar();
            String stat = "select * from asignaturas where nombre_asignatura = '" + X + "'";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            if (RS.next()) {
                throw new SQLException();
            }

            stat = "insert into asignaturas values(null, '" + X + "')";
            C.getConsulta().execute(stat);

        } catch (SQLException ex) {
            C.desconectar();
            return false;
        }

        C.desconectar();
        return true;
    }

    public Vector<String> obtener_carreras() {
        Vector<String> carreras = new Vector<>();
        C.conectar();
        String stat = "select * from carrera";
        try {

            ResultSet RS = C.getConsulta().executeQuery(stat);
            if (!RS.next()) {
                throw new SQLException();
            }
            do {
                carreras.add(RS.getString("nombre_carrera"));
            } while (RS.next());

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

            while (RS.next()) {
                int anno = RS.getInt("anno_brigada");
                int semestre = RS.getInt("semestre");
                String nombre_asig = RS.getString("nombre_asignatura");

                while (anno > Asignaturas.size()) {
                    Asignaturas.add(new Vector<>());
                }

                Asignaturas.elementAt(anno - 1).add(new Tupla<>(semestre, nombre_asig));

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

        C.conectar();
        try {
            
            String stat = "select * from carrera join asignaturas_semestre join asignaturas on carrera.id_carrera = asignaturas_semestre.id_carrera and asignaturas_semestre.id_asignatura = asignaturas.id_asignatura where carrera.nombre_carrera = '" + Carr.getNombre() + "'";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            int idC = RS.getInt(1);
            
            boolean[][] mapa = new boolean[5][20];
            
            Vector<Tupla<Integer, Tupla<Integer, Integer>>> asignaturasBorrar = new Vector<>();
            
            do{
                
                String nombreA = RS.getString(8);
                int idA = RS.getInt(4);
                int annoB = RS.getInt(5);
                int semestre = RS.getInt(6);
                boolean flag = false;
                if(annoB <= Carr.getAsignaturas().size()){
                for(int i = 0; i < Carr.getAsignaturas().elementAt(annoB-1).size(); i++){
                    if(Carr.getAsignaturas().elementAt(annoB-1).elementAt(i).getN1() == semestre && Carr.getAsignaturas().elementAt(annoB-1).elementAt(i).getN2().equals(nombreA)){
                        mapa[annoB-1][i] = true;
                        flag = true;
                        break;
                    }
                }
                }
                if(!flag){
                    asignaturasBorrar.add(new Tupla<>(idA, new Tupla<>(annoB, semestre)));
                }
                
                
            }while(RS.next());
            
            for(int i = 0; i < Carr.getAsignaturas().size(); i++){
                for(int j = 0; j < Carr.getAsignaturas().elementAt(i).size(); j++){
                    if(!mapa[i][j]){
                        stat = "insert into asignaturas_semestre values(" + idC + ",  (select id_asignatura from asignaturas where nombre_asignatura = '" + Carr.getAsignaturas().elementAt(i).elementAt(j).getN2() + "'), " + (i+1) + ", " + Carr.getAsignaturas().elementAt(i).elementAt(j).getN1() + ")";
                        C.getConsulta().execute(stat);
                    }
                }
            }
            
            for(int i = 0; i < asignaturasBorrar.size(); i++){
                stat = "delete from asignaturas_semestre where id_carrera = " + idC + " and id_asignatura = " + asignaturasBorrar.elementAt(i).getN1() + " and anno_brigada = " + asignaturasBorrar.elementAt(i).getN2().getN1() + " and semestre = " + asignaturasBorrar.elementAt(i).getN2().getN2();
                C.getConsulta().execute(stat);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        C.desconectar();
    }

    //ERIC DE AQUI PA ARRIBA
    public Vector<String> getDimensiones() {
        C.conectar();
        Vector<String> v = new Vector<>();
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

    public Vector<Brigada> obtenerBrigadas() {
        Vector<Brigada> V = new Vector<>();
        C.conectar();
        try {

            String stat = "select * from brigada join carrera on brigada.id_carrera = carrera.id_carrera";

            ResultSet RS = C.getConsulta().executeQuery(stat);

            Vector<Integer> brigadasID = new Vector<>();
            if (!RS.next()) {
                throw new SQLException();
            }
            do {

                int anno = RS.getInt("anno");
                int annoB = RS.getInt("ano_brigada");
                String nombreCarrera = RS.getString("nombre_carrera");
                brigadasID.add(RS.getInt("id_brigada"));
                V.add(new Brigada(nombreCarrera, anno, annoB, new Vector<>()));

            } while (RS.next());

            for (int i = 0; i < brigadasID.size(); i++) {
                stat = "select * from EstudianteSencillo where id_brigada = " + brigadasID.elementAt(i);
                RS = C.getConsulta().executeQuery(stat);
                if (RS.next()) {
                    do {
                        V.elementAt(i).addStudent(new Estudiante(RS.getString("nombre_estudiante"), RS.getString("CI")));

                    } while (RS.next());
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
        Vector<Tupla<Integer, Integer>> anno_annoBrigada = new Vector<>();
        Vector<Integer> Id_brigadas = new Vector<>();
        Vector<Estudiante> estudiantes = new Vector<>();
        try {
            C.conectar();
            String stat = "select id_carrera from carrera where nombre_carrera= '" + carreraSeleccionada + "'";
            ResultSet rs = C.getConsulta().executeQuery(stat);
            int id_carrera = rs.getInt("id_carrera");
            stat = "select * from brigada where id_carrera=" + id_carrera;
            rs = C.getConsulta().executeQuery(stat);
            while (rs.next()) {
                anno_annoBrigada.add(new Tupla<>(rs.getInt("ano_brigada"), rs.getInt("anno")));
                Id_brigadas.add(rs.getInt("id_brigada"));
            }
            for (int i = 0; i < Id_brigadas.size(); i++) {
                stat = "select * from estudiante where id_brigada=" + Id_brigadas.elementAt(i);
                rs = C.getConsulta().executeQuery(stat);
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

    public void agregarEventoEstudiantePorParticipacion(String fecha, int dimension, String evento, int valorDelLogro, Vector<Estudiante> estudiantesSeleccionados) {
        Vector<Integer> id_estudiantes = new Vector<>();
        try {
            C.conectar();
            String stat = "Select id_nombre_evento from nombre_evento where nombre_evento='" + evento + "'";
            ResultSet rs = C.getConsulta().executeQuery(stat);
            int id_nombre_evento = rs.getInt("id_nombre_evento");
            stat = "insert into eventos values(null," + id_nombre_evento + "," + dimension + ",'" + fecha + "')";
            C.getConsulta().execute(stat);
            stat = " SELECT * FROM eventos ORDER BY id_evento DESC LIMIT 1";
            rs = C.getConsulta().executeQuery(stat);
            int id_evento = rs.getInt("id_evento");
            stat = "insert into logros_evento values(null," + id_evento + ",Participacion," + valorDelLogro + ") ";
            C.getConsulta().execute(stat);
            stat = " SELECT * FROM logros_evento ORDER BY id_logro DESC LIMIT 1";
            rs = C.getConsulta().executeQuery(stat);
            int id_logro = rs.getInt("id_logro");
            for (int i = 0; i < estudiantesSeleccionados.size(); i++) {
                stat = "select id_estudiante from EstudianteSencillo where CI='" + estudiantesSeleccionados.elementAt(i).getCI() + "'";
                rs = C.getConsulta().executeQuery(stat);
                id_estudiantes.add(rs.getInt("id_estdiante"));
            }
            for (int i = 0; i < id_estudiantes.size(); i++) {
                stat = "insert into eventos_estudiante values(" + id_estudiantes.elementAt(i) + "," + id_evento + "," + id_logro + ")";
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
            ResultSet rs = C.getConsulta().executeQuery(stat);
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
            if (RS.next()) {
                do {
                    manifestaciones.add(RS.getString("manifestacion"));

                } while (RS.next());
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
            while (RS.next()) {
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
            while (RS.next()) {
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
            while (RS.next()) {
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
            if (RS.next()) {
                do {
                    Religiones.add(RS.getString("religion"));

                } while (RS.next());
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
            if (RS.next()) {
                throw new SQLException();
            }
            stat = "insert into religion values (null, '" + religion + "')";
            C.getConsulta().execute(stat);
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
            while (RS.next()) {
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
            if (RS.next()) {
                throw new SQLException();
            }
            stat = "insert into manifestacion_artistica values (null, '" + temp + "')";
            C.getConsulta().execute(stat);
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
            if (RS.next()) {
                do {
                    deportes.add(RS.getString("deporte"));

                } while (RS.next());
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
            
            if(temp.equals("")){
            throw new SQLException();
            }
            String stat = "select deporte from deporte where deporte = '" + temp + "'";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            if (RS.next()) {
                throw new SQLException();
            }
            stat = "insert into deporte values (null, '" + temp + "')";
            C.getConsulta().execute(stat);
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
            if (RS.next()) {
                do {
                    enfermedades.add(RS.getString("enfermedad"));

                } while (RS.next());
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
            if (RS.next()) {
                throw new SQLException();
            }
            stat = "insert into enfermedad values (null, '" + temp + "')";
            C.getConsulta().execute(stat);
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
            if (RS.next()) {
                do {
                    medicamentos.add(RS.getString("medicamento"));

                } while (RS.next());
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
            if (RS.next()) {
                throw new SQLException();
            }
            stat = "insert into medicamento values (null, '" + temp + "')";
            C.getConsulta().execute(stat);
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
            while (RS.next()) {
                convivencia.add(RS.getString("relaciones"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        C.desconectar();
        return convivencia;

    }

    public Vector<Nota> obtenerNotas(Carrera carr, Estudiante E, int anno_brugada) {

        C.conectar();
        Vector<Nota> notas = new Vector<>();

        try {

            String stat = "select id_estudiante from EstudianteSencillo where CI = '" + E.getCI() + "'";

            ResultSet RS = C.getConsulta().executeQuery(stat);

            int idE = RS.getInt("id_estudiante");

            stat = "select * from asignaturas_semestre join carrera join asignaturas on asignaturas_semestre.id_carrera = carrera.id_carrera and asignaturas.id_asignatura = asignaturas_semestre.id_asignatura where carrera.id_carrera = (select id_carrera from carrera where nombre_carrera = '" + carr.getNombre() + "') and anno_brigada = " + anno_brugada;

            RS = C.getConsulta().executeQuery(stat);
            if(RS.next()) {

                do{
                int idA = RS.getInt(2);
                String NA = RS.getString("nombre_asignatura");
                notas.add(new Nota(idA, 0, 0, NA));
                }while(RS.next());
            }

            for (int i = 0; i < notas.size(); i++) {
                stat = "select * from notas_estudiante where id_asignatura = " + notas.elementAt(i).getIdAsignatura() + " and id_estudiante = " + idE;
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

    public boolean esPrimerSemestre(int idAsignatura,String NombreCarrera ) {
        C.conectar();

        try {

            String stat = "select semestre from asignaturas_semestre where id_asignatura = " + idAsignatura +" and id_carrera = (select id_carrera from carrera where nombre_carrera ='"+NombreCarrera+"')";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            if (RS.getInt("semestre") == 1) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean esSegundoSemestre(int idAsignatura,String nombreCarrera) {
        C.conectar();

        try {

            String stat = "select semestre from asignaturas_semestre where id_asignatura = " + idAsignatura +" and id_carrera = (select id_carrera from carrera where nombre_carrera ='"+nombreCarrera+"')";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            if (RS.getInt("semestre") == 2) {
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
    C.conectar();
        try {
            

            String stat = "update notas_estudiante set nota_asignatura = " + nota.getNota() + " where id_asignatura = " + nota.getIdAsignatura() + " and id_estudiante = (select id_estudiante from EstudianteSencillo where CI = '" + E.getCI() + "')";

            boolean RS = C.getConsulta().execute(stat);
            C.desconectar();

        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        C.desconectar();
    }

    public Vector<Tupla<Integer, String>> obtenerEventos() {

        C.conectar();

        Vector<Tupla<Integer, String>> eventos = new Vector<>();
        try {

            String stat = "select * from eventos";
            ResultSet RS = C.getConsulta().executeQuery(stat);

            if (RS.next()) {
                do {
                    eventos.add(new Tupla<>(RS.getInt("id_evento"), RS.getString("fecha_evento")));

                } while (RS.next());
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

            String stat = "select id_brigada from brigada where ano_brigada = " + B.getAnno_brigada() + " and id_carrera = (select id_carrera from carrera where nombre_carrera = '" + B.getCarrera() + "') and anno = " + B.getAnno();
            ResultSet RS = C.getConsulta().executeQuery(stat);
            int idB = RS.getInt("id_brigada");

            stat = "select * from eventos_brigada join eventos join nombre_evento on eventos_brigada.id_evento = eventos.id_evento and eventos.id_nombre_evento = nombre_evento.id_nombre_evento where eventos_brigada.id_brigada = " + idB;
            RS = C.getConsulta().executeQuery(stat);

            if (RS.next()) {
                do {
                    eventos.add(new Evento(RS.getString("nombre_evento"), RS.getInt("id_dimension"), RS.getString("fecha_evento")));

                } while (RS.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        C.desconectar();
        return eventos;
    }

    public Vector<Tupla<Integer, String>> obtenerBrigadaEventos(Brigada B) {

        C.conectar();
        Vector<Tupla<Integer, String>> eventos = new Vector<>();

        try {

            String stat = "select id_brigada from brigada where ano_brigada = " + B.getAnno_brigada() + " and id_carrera = (select id_carrera from carrera where nombre_carrera = '" + B.getCarrera() + "') and anno = " + B.getAnno();
            ResultSet RS = C.getConsulta().executeQuery(stat);
            int idB = RS.getInt("id_brigada");

            stat = "select * from eventos_brigada join eventos join nombre_evento on eventos_brigada.id_evento = eventos.id_evento and eventos.id_nombre_evento = nombre_evento.id_nombre_evento where eventos_brigada.id_brigada = " + idB;

            RS = C.getConsulta().executeQuery(stat);

            if (RS.next()) {
                do {
                    eventos.add(new Tupla<>(RS.getInt(4), RS.getString("fecha_evento")));

                } while (RS.next());

            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        C.desconectar();
        return eventos;
    }

    public Object obtenerNombreEvento(Integer id) {

        String nombreEvento = "";
        try {
            C.conectar();

            String stat = "select nombre_evento.nombre_evento from eventos join nombre_evento on nombre_evento.id_nombre_evento = eventos.id_nombre_evento where eventos.id_nombre_evento = " + id;
            ResultSet RS = C.getConsulta().executeQuery(stat);
            nombreEvento = RS.getString("nombre_evento");

        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        C.desconectar();
        return nombreEvento;
    }

    public void actualizarEventosBrigada(Brigada B, Vector<Tupla<Integer, String>> eventosBrigada, Vector<Tupla<Integer, String>> eventosEliminados) {

        C.conectar();

        try {
            String stat = "select id_brigada from brigada where ano_brigada = " + B.getAnno_brigada() + " and id_carrera = (select id_carrera from carrera where nombre_carrera = '" + B.getCarrera() + "') and anno = " + B.getAnno();
            ResultSet RS = C.getConsulta().executeQuery(stat);
            int idB = RS.getInt("id_brigada");
            
            

            for (int i = 0; i < eventosBrigada.size(); i++) {
               
                 stat = "select id_evento from eventos where fecha_evento = '" + eventosBrigada.elementAt(i).getN2() + "' and id_nombre_evento = " + eventosBrigada.elementAt(i).getN1();
                 RS = C.getConsulta().executeQuery(stat);
                 int idE = RS.getInt("id_evento");
                
                stat = "select * from eventos_brigada where id_brigada = " + idB + " and id_evento = " + idE;
                RS = C.getConsulta().executeQuery(stat);
                if (!RS.next()) {
                    stat = "insert into eventos_brigada values(" + idB + ", " + idE + ")";
                    C.getConsulta().execute(stat);
                }
            }

            for (int i = 0; i < eventosEliminados.size(); i++) {
                
                stat = "select id_evento from eventos where fecha_evento = '" + eventosBrigada.elementAt(i).getN2() + "' and id_nombre_evento = " + eventosBrigada.elementAt(i).getN1();
                 RS = C.getConsulta().executeQuery(stat);
                 int idE = RS.getInt("id_evento");
                 
                stat = "select * from eventos_brigada where id_brigada = " + idB + " and id_evento = " + idE;
                RS = C.getConsulta().executeQuery(stat);
                if (RS.next()) {
                    stat = "delete from eventos_brigada where id_brigada = " + idB + " and id_evento = " + idE;
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
        int sol = 0;

        try {

            String stat = "select id_brigada from brigada where ano_brigada = " + brigada.getAnno_brigada() + " and id_carrera = (select id_carrera from carrera where nombre_carrera = '" + brigada.getCarrera() + "') and anno = " + brigada.getAnno();
            ResultSet RS = C.getConsulta().executeQuery(stat);
            int idB = RS.getInt("id_brigada");

            stat = "select * from eventos_brigada join eventos on eventos_brigada.id_evento = eventos.id_evento where eventos_brigada.id_brigada = " + idB; 
            RS = C.getConsulta().executeQuery(stat);
            Vector<Integer> eventosID = new Vector<>();
            do{
                
                eventosID.add(RS.getInt(2));
                
            }while(RS.next());
            
            int sum = 0;
            for(int i = 0; i < eventosID.size(); i++){
                stat = "select max(valor_logro) from logros_evento where id_evento = " + eventosID.elementAt(i);
                RS = C.getConsulta().executeQuery(stat);
                sum += RS.getInt(1);
            }
            
            sol = sum;

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
            int suma = RS.getInt(1);

            stat = "select count(nota_asignatura) from notas_estudiante where id_estudiante = (select id_estudiante from EstudianteSencillo where CI = '" + est.getCI() + "') and nota_asignatura != 0";
            RS = C.getConsulta().executeQuery(stat);
            int cantidad = RS.getInt(1);

            if(cantidad != 0){
            promedio = (double)suma / (double)cantidad;
            }
            else{
                promedio = 0.0;
            }
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
            String stat2 = "select id_estudiante from EstudianteSencillo where CI = '" + est.getCI() + "'";
            ResultSet RS = C.getConsulta().executeQuery(stat2);
            int idE = RS.getInt(1);
            
            String stat = "select sum (logros_evento.valor_logro) from logros_evento join eventos_estudiante on logros_evento.id_logro = eventos_estudiante.id_logro where eventos_estudiante.id_estudiante = " + idE;
            RS = C.getConsulta().executeQuery(stat);
            sol = RS.getInt(1);
            
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

            if (RS.next()) {
                do {
                    String fecha = RS.getString("fecha_evento");
                    int nuevoAnno = Integer.parseInt(fecha.substring(fecha.length() - 4));

                    if (anno == nuevoAnno) {
                        C.desconectar();
                        return true;
                    }
                } while (RS.next());
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

            
            String stat = "select * from eventos_estudiante where id_estudiante = (select id_estudiante from EstudianteSencillo where CI = '" + est.getCI() + "' and id_evento = (select id_evento from eventos where id_dimension = " + E.getDimension() + " and fecha_evento = '" + E.getAnno() + "' and id_nombre_evento = (select id_nombre_evento from nombre_evento where nombre_evento = '" + E.getNombre() + "')))";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            
            if(RS.next()){
                C.desconectar();

                return true;

            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }

        C.desconectar();

        return false;
    }

    public Object obtenerLogroEstudiante(Evento E, Estudiante est) {

        C.conectar();

        String logro = "";

        try {

            
            String stat = "select * from eventos_estudiante where id_estudiante = (select id_estudiante from EstudianteSencillo where CI = '" + est.getCI() + "' and id_evento = (select id_evento from eventos where id_dimension = " + E.getDimension() + " and fecha_evento = '" + E.getAnno() + "' and id_nombre_evento = (select id_nombre_evento from nombre_evento where nombre_evento = '" + E.getNombre() + "')))";

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

            if (RS.next()) {
                int idL = RS.getInt("id_logro");
                stat = "update logros_evento set valor_logro = " + logro.getN2() + " where id_logro = " + idL;
                C.getConsulta().execute(stat);
            } else {
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

            if (RS.next()) {
                do {
                    logros.add(new Tupla<>(RS.getString("logro_evento"), RS.getInt("valor_logro")));

                } while (RS.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }

        C.desconectar();
        return logros;
    }

    public void eliminarEstudianteEvento(Estudiante est, Evento eventoSeleccionado) {

        C.conectar();
    	String stat = "select id_evento from eventos where id_nombre_evento = (select id_nombre_evento from nombre_evento where nombre_evento = '" + eventoSeleccionado.getNombre() + "') and fecha_evento = '" + eventoSeleccionado.getAnno() + "'";
    	
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

    public boolean existeDatosEstudiante(String CI) {
        C.conectar();
        try {
            String stat = "select id_estudiante from Estudiante where CI = '" + CI + "'";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            if (RS.next()) {
                C.desconectar();
                return true;
            }
        } catch (SQLException ex) {
            C.desconectar();
            return false;
        }
        C.desconectar();
        return false;
    }

    public DatosEstudiante obtenerDatosEsttudiante(Estudiante estudiante) {
        int telefono_particular = 0;//ok
        int telefono_fijo = 0;//ok
        boolean datos_moviles = false;//ok
        String email = "";//ok
        String sexo = "";//ok
        int edad = 0;//ok
        boolean becado = false;//ok
        int color_de_piel = 0;//ok
        boolean militante = false;//ok
        int estado_civil = 0;//ok
        boolean hijos = false;//ok
        String direccion_particular = "";//ok
        int zona = 0;//ok
        String religion = "";//ok
        boolean bebidas_alcoholicas = false;//ok
        boolean fumador = false;//ok
        int participacion_brigada = 0;//ok
        Vector<String> manifestaciones_artisticas = new Vector<>();//ok
        boolean[] convivencia = new boolean[10];//ok
        int total_familiares = 0;//ok
        int ingreso_total = 0;//ok
        int relaciones = 0;//ok
        Vector<String> deportes = new Vector<>();//ok
        boolean[] electronicos = new boolean[4];//ok
        Vector<String> enfermedades = new Vector<>();//ok
        boolean activo = true;
        Vector<Tupla<Integer, Integer>> eventos = new Vector<>();//de donde se saca esto 
        Vector<String> medicamentos = new Vector<>();//ok
        String deseos_futuros = "";//ok
        String actividades_tiempo_libre = "";//ok
        String proyectos_vida = "";//ok
        String rasgos_habitos = "";//ok
        boolean feliz = false;//ok
        boolean gusta_carrera = false;//ok
        boolean gusta_estudio = false;//ok
        int nivel_ingles = 0;//ok
        int id_estudiante = 0;//ok

        try {

            C.conectar();
            String stat = "select * from Estudiante where CI ='" + estudiante.getCI() + "'";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            id_estudiante = RS.getInt("id_estudiante");
            telefono_fijo = RS.getInt("telefono_fijo");
            telefono_particular = RS.getInt("telefono_particular");
            datos_moviles = RS.getBoolean("datos_moviles");
            email = RS.getString("email");
            sexo = RS.getString("sexo");
            edad = RS.getInt("edad");
            becado = RS.getBoolean("becado");
            color_de_piel = RS.getInt("id_color_piel");
            militante = RS.getBoolean("militante");
            estado_civil = RS.getInt("id_estado_civil");
            hijos = RS.getBoolean("hijos");
            direccion_particular = RS.getString("direccion_particular");
            zona = RS.getInt("id_zona");
            int IDreligion = RS.getInt("id_religion");
            bebidas_alcoholicas = RS.getBoolean("bebidas_alcoholicas");
            fumador = RS.getBoolean("fumador");
            participacion_brigada = RS.getInt("participacion_brigada");
            nivel_ingles = RS.getInt("id_nivel_ingles");
            religion="";
           if(IDreligion!=0){
            stat = "select religion from religion where id_religion = " + IDreligion;
            RS = C.getConsulta().executeQuery(stat);
             religion = RS.getString("religion");
           }
            
           
           String  stat2 = "select id_manifestacion from artes_estudiante where id_estudiante =" + id_estudiante;
            RS = C.getConsulta().executeQuery(stat2);
            Vector<Integer> IDmanifestacionesArtisticas = new Vector<>();
            while (RS.next()) {
                IDmanifestacionesArtisticas.add(RS.getInt("id_manifestacion"));
            }
            for (int i = 0; i < IDmanifestacionesArtisticas.size(); i++) {
               String  stat3 = "select manifestacion from manifestacion_artistica where id_manifestacion =" + IDmanifestacionesArtisticas.elementAt(i);
                RS = C.getConsulta().executeQuery(stat3);
                manifestaciones_artisticas.add(RS.getString("manifestacion"));
            }
            stat = "select * from convivencia where id_estudiante=" + id_estudiante;
            RS = C.getConsulta().executeQuery(stat);
            convivencia[0] = RS.getBoolean("padre");
            convivencia[1] = RS.getBoolean("madre");
            convivencia[2] = RS.getBoolean("hermana(s)");
            convivencia[3] = RS.getBoolean("hermano(s)");
            convivencia[4] = RS.getBoolean("abuelo paterno");
            convivencia[5] = RS.getBoolean("abuelo materno");
            convivencia[6] = RS.getBoolean("abuela paterna");
            convivencia[7] = RS.getBoolean("abuela materna");
            convivencia[8] = RS.getBoolean("otros familiares");
          
            total_familiares = RS.getInt("total_familiares");
            ingreso_total = RS.getInt("ingreso_hogar");
            relaciones = RS.getInt("id_relaciones");
            stat = "select id_deporte from deporte_estudiante where id_estudiante = " + id_estudiante;
            RS = C.getConsulta().executeQuery(stat);
            Vector<Integer> IDdeportes = new Vector<>();
            while (RS.next()) {
                IDdeportes.add(RS.getInt("id_deporte"));
            }
            for (int i = 0; i < IDdeportes.size(); i++) {
                stat = "select deporte from deporte where id_deporte = " + IDdeportes.elementAt(i);
                RS = C.getConsulta().executeQuery(stat);
                deportes.add(RS.getString("deporte"));
            }
            stat = "select * from Electronics where id_estudiante = " + id_estudiante;
            RS = C.getConsulta().executeQuery(stat);
            electronicos[0] = RS.getBoolean("pc");
            electronicos[1] = RS.getBoolean("laptop");
            electronicos[2] = RS.getBoolean("movil");
            electronicos[3] = RS.getBoolean("tablet");
            stat = "select * from enfermedades_estudiante where id_estudiante =" + id_estudiante;
            RS = C.getConsulta().executeQuery(stat);
            Vector<Integer> IDenfermedad = new Vector<>();
            while (RS.next()) {
                IDenfermedad.add(RS.getInt("id_enfermedad"));
            }
            for (int i = 0; i < IDenfermedad.size(); i++) {
                stat = "select enfermedad from enfermedad where id_enfermedad = " + IDenfermedad.elementAt(i);
                RS = C.getConsulta().executeQuery(stat);
                enfermedades.add(RS.getString("enfermedad"));
            }
            stat = "select id_medicamento from medicamentos_estudiante where id_estudiante = " + id_estudiante;
            RS = C.getConsulta().executeQuery(stat);
            Vector<Integer> IDmedicamnetos = new Vector<>();
            while (RS.next()) {
                IDmedicamnetos.add(RS.getInt("id_medicamento"));
            }
            for (int i = 0; i < IDmedicamnetos.size(); i++) {
                stat = "select medicamento from medicamento where id_medicamento = " + IDmedicamnetos.elementAt(i);
                RS = C.getConsulta().executeQuery(stat);
                medicamentos.add(RS.getString("medicamento"));
            }
            stat = "select * from psiquis_estudiante where id_estudiante = " + id_estudiante;
            RS = C.getConsulta().executeQuery(stat);
            deseos_futuros = RS.getString("deseos_futuros");
            actividades_tiempo_libre = RS.getString("actividades_tiempo_libre");
            proyectos_vida = RS.getString("proyectos_de_vida");
            rasgos_habitos = RS.getString("rasgos_y_habitos");
            feliz = RS.getBoolean("felicidad");
            gusta_estudio = RS.getBoolean("gusto_por_estudio");
            gusta_carrera = RS.getBoolean("gusto_por_carrera");

          
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
            C.desconectar();
        }
        DatosEstudiante dt = new DatosEstudiante(email, sexo, telefono_particular, telefono_fijo, datos_moviles, email, sexo, edad, becado, color_de_piel, militante, estado_civil, hijos, direccion_particular, zona, religion, bebidas_alcoholicas, fumador, participacion_brigada, manifestaciones_artisticas, convivencia, total_familiares, ingreso_total, relaciones, deportes, electronicos, enfermedades, activo, eventos, medicamentos, deseos_futuros, actividades_tiempo_libre, proyectos_vida, rasgos_habitos, feliz, gusta_carrera, gusta_estudio, nivel_ingles);

        return dt;
    }

    public void actualizarEstudiante(DatosEstudiante e, Brigada b) {
        int becado = 0;
        int militante = 0;
        int hijos = 0;
        int bebidas = 0;
        int fumador = 0;
        int[] familiares = new int[10];
        int[] electronics = new int[4];
        int feliz = 0;
        int gusto_estudio = 0;
        int gusto_carrera = 0;
        Vector<Integer> deportes = new Vector<>();
        Vector<Integer> enfermedades = new Vector<>();
        Vector<Integer> manifestaciones = new Vector<>();
        Vector<Integer> medicamentos = new Vector<>();
        C.conectar();

        String stat = "select id_brigada from brigada where ano_brigada = " + b.getAnno_brigada() + " and id_carrera = (select id_carrera from carrera where nombre_carrera = '" + b.getCarrera() + "') and anno = " + b.getAnno();

        try {
            ResultSet RS = C.getConsulta().executeQuery(stat);
            int idB = RS.getInt("id_brigada");
//             stat = "select id_estudiante from Estudiante where CI = '" + e.getCI() + "'";
//            RS = C.getConsulta().executeQuery(stat);
//           int id_estudiante =RS.getInt("id_estudiante");
            if (e.isBecado()) {
                becado = 1;
            }
            if (e.isMilitante()) {
                militante = 1;
            }
            if (e.isHijos()) {
                hijos = 1;
            }
            if (e.isBebidas_alcoholicas()) {
                bebidas = 1;
            }
            if (e.isFumador()) {
                fumador = 1;
            }
            if (e.isFeliz()) {
                feliz = 1;
            }
            if (e.isGusta_carrera()) {
                gusto_carrera = 1;
            }
            if (e.isGusta_estudio()) {
                gusto_estudio = 1;
            }
            for (int i = 0; i < e.getConvivencia().length; i++) {
                if (e.getConvivencia()[i] == true) {
                    familiares[i] = 1;
                } else {
                    familiares[i] = 0;
                }
            }
            for (int i = 0; i < e.getElectronicos().length; i++) {
                if (e.getElectronicos()[i] == true) {
                    electronics[i] = 1;
                } else {
                    electronics[i] = 0;
                }
            }
            stat = "update  Estudiante set nombreCompleto = '" + e.getNombre_estudiante() + "', CI = '" + e.getCI() + "', telefono_particular = " + e.getTelefono_particular() + ",telefono_fijo = " + e.getTelefono_fijo() + ", datos_moviles =" + e.isDatos_moviles() + ", email = '" + e.getEmail() + "', sexo = " + "'" + e.getSexo() + "', edad = " + e.getEdad() + ",becado =" + becado + ", id_color_piel = (select id_color_piel from color_piel where color_piel = '" + e.getColor_de_piel() + "'), militante =" + militante + ", id_estado_civil =" + "(select id_estado_civil from estado_civil where estado_civil = '" + e.getEstado_civil() + "'), hijos =" + hijos + ", direccion_particular = '" + e.getDireccion_particular() + "', id_zona = (select id_zona from zona where zona = '" + e.getZona() + "'), id_religion = (select id_religion from religion where religion = '" + e.getReligion() + "'), bebidas_alcoholicas = " + bebidas + ", fumador = " + fumador + ",participacion_brigada = " + e.getParticipacion_brigada() + ", " + idB + ", id_nivel_ingles = (select id_nivel_ingles from nivel_ingles where nivel_ingles = '" + e.getNivel_ingles() + "'), " + e.isActivo() + " where CI='" + e.getCI() + "'";
            C.getConsulta().execute(stat);
            stat = "select id_estudiante from Estudiante where CI = '" + e.getCI() + "'";
            RS = C.getConsulta().executeQuery(stat);
            int idE = RS.getInt("id_estudiante");
            for (int i = 0; i < e.getManifestaciones_artisticas().size(); i++) {
                stat = "select manifestacion from manifestacion_artistica where" + e.getManifestaciones_artisticas().elementAt(i);
                RS = C.getConsulta().executeQuery(stat);
                manifestaciones.add(RS.getInt("id_manifestacion"));
            }
            for (int i = 0; i < manifestaciones.size(); i++) {
                stat = "update  artes_estudiante set  id_manifestacion " + manifestaciones.elementAt(i) + "where id_estudiante =" + idE;
                C.getConsulta().execute(stat);
            }

            stat = "update  convivencia set padre=" + familiares[0] + ", madre= " + familiares[1] + ", hermana(s)=" + familiares[2] + ", hermano(s)=" + familiares[3] + ", abuelo paterno = " + familiares[4] + ", abuelo materno = " + familiares[5] + ", abuela paterna = " + familiares[6] + ", abuela materna = " + familiares[7] + ",otros familiares = " + familiares[8] + ", padres_divorciados = " + familiares[9] + ", total_familiares = " + e.getTotal_familiares() + ", ingreso_hogar = " + e.getIngreso_total() + ", id_relaciones = (select id_relaciones from relaciones convivencia where relaciones = '" + e.getRelaciones() + "') where id_estudiante=" + idE;
            C.getConsulta().execute(stat);
            for (int i = 0; i < e.getDeportes().size(); i++) {
                stat = "select id_deporte from deporte where deporte = '" + e.getDeportes().elementAt(1) + "'";
                RS = C.getConsulta().executeQuery(stat);
                deportes.add(RS.getInt("id_deporte"));
            }
            for (int i = 0; i < deportes.size(); i++) {
                stat = "update  deporte_estudiante set id_deporte =" + deportes.elementAt(i) + "where id_estudiante";
                C.getConsulta().execute(stat);
            }

            stat = "update  Electronics set pc = " + e.getElectronicos()[0] + ",laptop " + e.getElectronicos()[1] + ", movil" + e.getElectronicos()[2] + ",tablet " + e.getElectronicos()[3] + "where id_estudiante =" + idE;
            C.getConsulta().execute(stat);

            for (int i = 0; i < e.getEnfermedades().size(); i++) {
                stat = "select id_enfermedad from enfermedad where enfermedad = '" + e.getEnfermedades().elementAt(i) + "'";
                RS = C.getConsulta().executeQuery(stat);
                enfermedades.add(RS.getInt("id_enfermedad"));
            }
            for (int i = 0; i < enfermedades.size(); i++) {
                stat = "update enfermedades_estudiante set id_enfermedad = " + enfermedades.elementAt(i);
                C.getConsulta().execute(stat);
            }
            for (int i = 0; i < e.getMedicamentos().size(); i++) {
                stat = "select id_medicamento from medicamneto where medicamento ='" + e.getMedicamentos().elementAt(i) + "'";
                RS = C.getConsulta().executeQuery(stat);
                medicamentos.add(RS.getInt("id_medicamento"));
            }
            for (int i = 0; i < medicamentos.size(); i++) {
                stat = "update  medicamentos_estudiante set id_medicamento = " + medicamentos.elementAt(i) + "where id_estudiante = " + idE;
                C.getConsulta().execute(stat);
            }

            stat = "update  psiquis_estudiante set deseos_futuros = '" + e.getDeseos_futuros() + "', actividades_tiempo_libre = '" + e.getActividades_tiempo_libre() + "', proyectos_de_vida = '" + e.getProyectos_vida() + "',rasgos_y_habitos ='" + e.getRasgos_habitos() + "', felicidad = " + feliz + ", gusto_por_estudio = " + gusto_estudio + ", gusto_por_carrera = " + gusto_carrera + "where id_estudiante =" + idE;
            C.getConsulta().execute(stat);
        } catch (SQLException ex) {
            C.desconectar();

        }
        C.desconectar();
    }

    public void agregarEstudianteBrigada(Estudiante E, Brigada B) {

        C.conectar();
        
        try {
            String stat = "select id_estudiante from EstudianteSencillo where CI = '" + E.getCI() + "'";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            int idE = RS.getInt("id_estudiante");
            
            stat = "select id_brigada from brigada where ano_brigada = " + B.getAnno_brigada() + " and id_carrera = (select id_carrera from carrera where nombre_carrera = '" + B.getCarrera() + "') and anno = " + B.getAnno();
            RS = C.getConsulta().executeQuery(stat);
            int idB = RS.getInt("id_brigada");
            
            stat = "update EstudianteSencillo set id_brigada = " + idB + " where id_estudiante = " + idE;
            C.getConsulta().execute(stat);
            
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        C.desconectar();
    }

    public boolean obtenerDimensionEvento(Tupla<Integer, String> evento, String dimension) {
        
        C.conectar();
        try {
            String stat = "select nombre_dimension from eventos join dimensiones join nombre_evento on dimensiones.id_dimension = eventos.id_dimension and eventos.id_nombre_evento = nombre_evento.id_nombre_evento where eventos.id_nombre_evento = " + evento.getN1() + " and eventos.fecha_evento = '" + evento.getN2() + "'";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            String nombreDimension = RS.getString("nombre_dimension");
            if(dimension.equals(nombreDimension)){
                C.desconectar();
                RS.close();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        C.desconectar();

        return false;
    }

    public Vector<Tupla<Integer, String>> obtenerEventosAnno(int anno) {

         C.conectar();
        Vector<Tupla<Integer, String>> eventos = new Vector<>();

        try {

            String stat = "select * from eventos where fecha_evento like '%" + anno + "%'";

            ResultSet RS = C.getConsulta().executeQuery(stat);

            if (RS.next()) {
                do {
                    eventos.add(new Tupla<>(RS.getInt(2), RS.getString("fecha_evento")));

                } while (RS.next());

            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        C.desconectar();
        return eventos;
        
    }
}
