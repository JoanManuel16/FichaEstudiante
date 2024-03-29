/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Visuales;

import Base_de_Datos.Gestion;
import clases.Brigada;
import clases.Carrera;
import clases.DatosEstudiante;
import clases.Estudiante;
import clases.Nota;
import utiles.RadioButtonEditor;
import utiles.RadioButtonRenderer;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import utiles.Secuencias_cadenas;
import dialogs.AbstractFrame;
import dialogs.ConfirmDialog;
import dialogs.InputDialog;
import dialogs.MessageDialog;

/**
 *
 * @author joanmanuel
 */
public class Editor_estudiante extends AbstractFrame {
    
    private Vector<String> manifestacionesArtisticas;
    private Vector<JCheckBox> radioButtonManifestaciones;
    private Vector<String> deportes;
    private Vector<String> deportesDB;
    private Vector<JCheckBox> radioButtonDeportes;
    private Vector<String> enfermedades;
    private Vector<String> enfermedadesDB;
    private Vector<JCheckBox> radioButtonEnfermedades;
    private Vector<String> medicamentos;
    private Vector<String> medicamentosDB;
    private Vector<JCheckBox> radioButtonMedicamentos;
    private Vector<String> manifestaciones;
    private Vector<Nota> notas;
    private Brigada b;
    private Gestion g = new Gestion();
    private Carrera carr;
    private Vector<String> religiones;
    private Estudiante E;
    private DatosEstudiante datosEstudiante;
    private boolean flag = false;
    private String stringTemporal;
    
    public Editor_estudiante(Estudiante E, String carrera, Brigada b) {
        initComponents();
        this.E = E;
        this.b = b;
        setTitle("Editando los datos del estudiante " + E.getNombre_estudiante());
        setResizable(false);
        setLocationRelativeTo(null);
        deportesDB = new Vector<>();
        religiones = new Vector<>();
        enfermedadesDB = new Vector<>();
        medicamentosDB = new Vector<>();
        masculino.setSelected(true);
        radioButtonManifestaciones = new Vector<>();
        manifestacionesArtisticas = g.obtenerManifestaciones();
        manifestaciones = new Vector<>();
        
        radioButtonDeportes = new Vector<>();
        deportes = g.obtenerDeportes();
        actualizarTablaDeportes(deportes);
        
        radioButtonEnfermedades = new Vector<>();
        enfermedades = g.obtenerEnfermedades();
        actualizarTablaEnfermedades(enfermedades);
        
        radioButtonMedicamentos = new Vector<>();
        medicamentos = g.obtenerMedicamentos();
        actualizarTablaMedicamentos(medicamentos);
        
        this.carr = g.obtener_carrera(carrera);
        
        nombreEstudianteT.setText(E.getNombre_estudiante());
        carnetTexto.setText(E.getCI());
        carreraT.setText(carr.getNombre());
        carreraT.setEditable(false);
        
        buttonGroup1.add(masculino);
        buttonGroup1.add(femenino);
        actualizarTablaManifestaciones(manifestacionesArtisticas);
        Vector<String> zonas = g.obtenerZonas();
        for (int i = 0; i < zonas.size(); i++) {
            zonaOpciones.addItem(zonas.elementAt(i));
        }
        
        Vector<String> colorPieles = g.obtenerColorPiel();
        for (int i = 0; i < colorPieles.size(); i++) {
            colorDePielComboBox.addItem(colorPieles.elementAt(i));
        }
        
        Vector<String> estCivil = g.obtenerEstadoCivil();
        for (int i = 0; i < estCivil.size(); i++) {
            estadoCivilComboBox.addItem(estCivil.elementAt(i));            
        }
        estadoCivilComboBox.setSelectedIndex(1);
        
        religiones.add("Nueva Religión");
        religiones.addAll(g.obtenerReligiones());
        for (int i = 0; i < religiones.size(); i++) {
            religionComboBox.addItem(religiones.elementAt(i));
        }
        
        buttonGroup2.add(participacionBrigadaBien);
        buttonGroup2.add(participacionBrigadaRegular);
        buttonGroup2.add(participaicoBrigadaMal);
        
        Vector<String> nivIng = g.obtenerNivelIngles();
        for (int i = 0; i < nivIng.size(); i++) {
            nivelDeInglesComboBox.addItem(nivIng.elementAt(i));
        }
        
        Vector<String> convivencia = g.obtenerConvivencia();
        for (int i = 0; i < convivencia.size(); i++) {
            relacionesConvivenciaComboBox.addItem(convivencia.elementAt(i));
        }
        
        for (int i = 0; i < b.getAnno_brigada(); i++) {
            AnnoComboBox.addItem((i + 1) + "");
        }
        
        notas = g.obtenerNotas(carr, E, AnnoComboBox.getSelectedIndex() + 1);
        
        actualizarTablaNotas(notas);
        
    }
    
    public Editor_estudiante(Estudiante E, String carrera, Brigada b, DatosEstudiante d) {
        initComponents();
        flag = true;
        this.E = E;
        this.b = b;
        setTitle("Editando los datos del estudiante " + E.getNombre_estudiante());
        setResizable(false);
        setLocationRelativeTo(null);
        datosEstudiante = d;
        if (d.getSexo().equals("masculino")) {
            masculino.setSelected(true);
        } else {
            femenino.setSelected(true);
        }
        manifestaciones = d.getManifestaciones_artisticas();
        radioButtonManifestaciones = new Vector<>();
        manifestacionesArtisticas = g.obtenerManifestaciones();
        actualizarTablaManifestaciones(manifestacionesArtisticas);
        
        deportesDB = d.getDeportes();
        radioButtonDeportes = new Vector<>();
        deportes = g.obtenerDeportes();
        actualizarTablaDeportes(deportes);
        
        enfermedadesDB = d.getEnfermedades();
        radioButtonEnfermedades = new Vector<>();
        enfermedades = g.obtenerEnfermedades();
        actualizarTablaEnfermedades(enfermedades);
        
        medicamentosDB = d.getMedicamentos();
        radioButtonMedicamentos = new Vector<>();
        medicamentos = g.obtenerMedicamentos();
        actualizarTablaMedicamentos(medicamentos);
        
        this.carr = g.obtener_carrera(carrera);
        
        nombreEstudianteT.setText(E.getNombre_estudiante());
        carnetTexto.setText(E.getCI());
        carreraT.setText(carr.getNombre());
        carreraT.setEditable(false);
        
        buttonGroup1.add(masculino);
        buttonGroup1.add(femenino);
        
        Vector<String> zonas = g.obtenerZonas();
        
        for (int i = 0; i < zonas.size(); i++) {
            zonaOpciones.addItem(zonas.elementAt(i));
        }
        zonaOpciones.setSelectedIndex(d.getZona() - 1);
        Vector<String> colorPieles = g.obtenerColorPiel();
        for (int i = 0; i < colorPieles.size(); i++) {
            colorDePielComboBox.addItem(colorPieles.elementAt(i));
        }
        colorDePielComboBox.setSelectedIndex(d.getColor_de_piel() - 1);
        Vector<String> estCivil = g.obtenerEstadoCivil();
        for (int i = 0; i < estCivil.size(); i++) {
            estadoCivilComboBox.addItem(estCivil.elementAt(i));
        }
        estadoCivilComboBox.setSelectedIndex(d.getEstado_civil() - 1);
        
        religiones = new Vector<>();
        religiones.add("Nueva Religión");
        religiones.addAll(g.obtenerReligiones());
        for (int i = 0; i < religiones.size(); i++) {
            religionComboBox.addItem(religiones.elementAt(i));
        }
        if (d.getReligion().equals("")) {
            religionComboBox.setSelectedIndex(0);
        } else {
            religionComboBox.setSelectedItem(d.getReligion());
        }
        
        religionTexto.setEnabled(false);
        annadirReligion.setEnabled(false);
        
        buttonGroup2.add(participacionBrigadaBien);
        buttonGroup2.add(participacionBrigadaRegular);
        buttonGroup2.add(participaicoBrigadaMal);
        switch (d.getParticipacion_brigada()) {
            case 1 ->
                participacionBrigadaBien.setSelected(true);
            case 2 ->
                participacionBrigadaRegular.setSelected(true);
            case 3 ->
                participaicoBrigadaMal.setSelected(true);
        }
        
        Vector<String> nivIng = g.obtenerNivelIngles();
        for (int i = 0; i < nivIng.size(); i++) {
            nivelDeInglesComboBox.addItem(nivIng.elementAt(i));
        }
        nivelDeInglesComboBox.setSelectedIndex(d.getNivel_ingles() - 1);
        
        Vector<String> convivencia = g.obtenerConvivencia();
        for (int i = 0; i < convivencia.size(); i++) {
            relacionesConvivenciaComboBox.addItem(convivencia.elementAt(i));
        }
        relacionesConvivenciaComboBox.setSelectedIndex(d.getRelaciones() - 1);
        for (int i = 0; i < carr.getAsignaturas().size(); i++) {
            AnnoComboBox.addItem((i + 1) + "");
        }
        notas = g.obtenerNotas(carr, E, AnnoComboBox.getSelectedIndex() + 1);
        
        actualizarTablaNotas(notas);
        edadT.setText(d.getEdad() + "");
        deseosFuturosT.setText(d.getDeseos_futuros());
        actividadesTiempoLibreT.setText(d.getActividades_tiempo_libre());
        proyectosVidaT.setText(d.getProyectos_vida());
        rasgosHabitosT.setText(d.getRasgos_habitos());
        telefonoFijoT.setText(d.getTelefono_fijo() + "");
        telefonoParticularT.setText(d.getTelefono_particular() + "");
        becadoBooton.setSelected(d.isBecado());
        movileDataBooton.setSelected(d.isDatos_moviles());
        hijosBooton.setSelected(d.isHijos());
        bebidasAl.setSelected(d.isBebidas_alcoholicas());
        fumadorBooton.setSelected(d.isFumador());
        direccionParticularT.setText(d.getDireccion_particular());
        totalFamiliaresT.setText(d.getTotal_familiares() + "");
        ingresoHogarT.setText(d.getIngreso_total() + "");
        felicidadOpcion.setSelected(d.isFeliz());
        estudioOpcion.setSelected(d.isGusta_estudio());
        carreraOpcion.setSelected(d.isGusta_carrera());
        boolean[] convivenciaArray = d.getConvivencia();
        padre.setSelected(convivenciaArray[0]);
        madre.setSelected(convivenciaArray[1]);
        hermanas.setSelected(convivenciaArray[2]);
        hermanos.setSelected(convivenciaArray[3]);
        abueloP.setSelected(convivenciaArray[4]);
        abueloM.setSelected(convivenciaArray[5]);
        abuelaP.setSelected(convivenciaArray[6]);
        abuelaM.setSelected(convivenciaArray[7]);
        otrosFamiliares.setSelected(convivenciaArray[8]);
        padresDivorciadosOpcion.setSelected(convivenciaArray[9]);
        emailT.setText(d.getEmail());
        computadora.setSelected(d.getElectronicos()[0]);
        laptop.setSelected(d.getElectronicos()[1]);
        movil.setSelected(d.getElectronicos()[2]);
        tablet.setSelected(d.getElectronicos()[3]);
        militanteRadioB.setSelected(d.isMilitante());
        
    }

    @Override
    public void inputDialog_devolverValor(Object O, Object valorInicial, int seleccion) {
    
        String x = (String)O;
        
        switch (seleccion) {
            case 1 -> inputOption1(x, valorInicial);
            case 2 -> inputOption2(x, valorInicial);
            case 3 -> inputOption3(x, valorInicial);
            case 4 -> inputOption4(x, valorInicial);
            case 5 -> inputOption5(x, valorInicial);
            case 6 -> inputOption6(x, valorInicial);
            default -> {
            }
        }
        
    }
    
    private void inputOption1(String x, Object valorInicial){
         if (x == valorInicial) {
                manifestacionesArtisticas.add(x);
                g.agregarManifestacionArtistica(x);
                actualizarTablaManifestaciones(manifestacionesArtisticas);
            } else {
                Vector<String> V = new Vector<>();
                V.add(x);
                actualizarTablaManifestaciones(V);
            }
    }
    
    private void inputOption2(String x, Object valorInicial){
        if (x == valorInicial) {
                deportes.add(x);
                g.agregarDeporte(x);
                actualizarTablaDeportes(deportes);
            } else {
                Vector<String> V = new Vector<>();
                V.add(x);
                actualizarTablaDeportes(V);
            }
    }
    
    private void inputOption3(String x, Object valorInicial){
        if (x == valorInicial) {
                medicamentos.add(x);
                g.agregarMedicamento(x);
                actualizarTablaMedicamentos(medicamentos);
            } else {
                Vector<String> V = new Vector<>();
                V.add(x);
                actualizarTablaMedicamentos(V);
            }
    }
    
    private void inputOption4(String x, Object valorInicial){
        if (x == valorInicial) {
                enfermedades.add(x);
                g.agregarEnfermedad(x);
                actualizarTablaEnfermedades(enfermedades);
            } else {
                Vector<String> V = new Vector<>();
                V.add(x);
                actualizarTablaEnfermedades(V);
            }
    }
    
    private void inputOption5(String x, Object valorInicial){
        if (x == valorInicial) {
                religiones.add(x);
                g.agregarReligion(x);
                religionComboBox.addItem(x);
                religionComboBox.setSelectedItem(x);
            } else {
                religionComboBox.setSelectedItem(x);
            }
    }
    
    private void inputOption6(String x, Object valorInicial){
        if (x != valorInicial) {
            int nota = Integer.parseInt(x);
            
            String asignatura = (String) tablaNotas.getValueAt(tablaNotas.getSelectedRow(), 0);
            
            for (int i = 0; i < notas.size(); i++) {
                if (notas.elementAt(i).getNombreAsignatura().equals(asignatura)) {
                    notas.elementAt(i).setNota(nota);
                    g.actualizarNota(notas.elementAt(i), E);
                    
                    actualizarTablaNotas(notas);
                    break;
                }
            }
        }
    }
    

    @Override
    public void confirmDialog_devolverValor(Object O, int seleccion) {
    
        if((boolean)O){
            switch (seleccion) {
                case 1 -> confirmOption1();
                case 2 -> confirmOption2();
                case 3 -> confirmOption3();
                case 4 -> confirmOption4();
                case 5 -> confirmOption5();
                default -> {
                }
            }
        }
        
    }
    
    private void confirmOption1(){
        manifestacionesArtisticas.add(stringTemporal);
        g.agregarManifestacionArtistica(stringTemporal);
        actualizarTablaManifestaciones(manifestacionesArtisticas);
            
    }
    
    private void confirmOption2(){
          deportes.add(stringTemporal);
          g.agregarDeporte(stringTemporal);
          actualizarTablaDeportes(deportes);
    }
    
    private void confirmOption3(){
          medicamentos.add(stringTemporal);
          g.agregarMedicamento(stringTemporal);
          actualizarTablaMedicamentos(medicamentos);
    }
    
    private void confirmOption4(){
          enfermedades.add(stringTemporal);
          g.agregarEnfermedad(stringTemporal);
          actualizarTablaEnfermedades(enfermedades);
    }
    
    private void confirmOption5(){
          religiones.add(stringTemporal);
          g.agregarReligion(stringTemporal);
          religionComboBox.addItem(stringTemporal);
          religionComboBox.setSelectedItem(stringTemporal);
         
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        menuNotas = new javax.swing.JPopupMenu();
        editarNota = new javax.swing.JMenuItem();
        mensajeDialog = new javax.swing.JDialog();
        Informacion = new javax.swing.JLabel();
        aceptarBoton = new javax.swing.JButton();
        cancelarBoton = new javax.swing.JButton();
        panelPestanas = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        nombreEstudiante = new javax.swing.JLabel();
        nombreEstudianteT = new javax.swing.JTextField();
        carnet = new javax.swing.JLabel();
        carnetTexto = new javax.swing.JTextField();
        sexo = new javax.swing.JLabel();
        masculino = new javax.swing.JRadioButton();
        femenino = new javax.swing.JRadioButton();
        edad = new javax.swing.JLabel();
        edadT = new javax.swing.JTextField();
        zona = new javax.swing.JLabel();
        zonaOpciones = new javax.swing.JComboBox<>();
        finalizar = new javax.swing.JButton();
        carrera = new javax.swing.JLabel();
        carreraT = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaManifestaciones = new javax.swing.JTable();
        agregarManifestacion = new javax.swing.JButton();
        manifestacionTexto = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaDeportes = new javax.swing.JTable();
        agregarDeporte = new javax.swing.JButton();
        deporteTexto = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        TablaEnfermedades = new javax.swing.JTable();
        agregarEnfermedad = new javax.swing.JButton();
        enfermedadTexto = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TablaMedicamentos = new javax.swing.JTable();
        agregarMedicamento = new javax.swing.JButton();
        medicamentoTexto = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        convivencia = new javax.swing.JLabel();
        padre = new javax.swing.JCheckBox();
        madre = new javax.swing.JCheckBox();
        hermanos = new javax.swing.JCheckBox();
        hermanas = new javax.swing.JCheckBox();
        abueloP = new javax.swing.JCheckBox();
        abueloM = new javax.swing.JCheckBox();
        abuelaP = new javax.swing.JCheckBox();
        abuelaM = new javax.swing.JCheckBox();
        otrosFamiliares = new javax.swing.JCheckBox();
        padresDivorciados = new javax.swing.JLabel();
        padresDivorciadosOpcion = new javax.swing.JRadioButton();
        totalFamiliares = new javax.swing.JLabel();
        totalFamiliaresT = new javax.swing.JTextField();
        ingresoHogar = new javax.swing.JLabel();
        ingresoHogarT = new javax.swing.JTextField();
        relacionesConvivencia = new javax.swing.JLabel();
        relacionesConvivenciaComboBox = new javax.swing.JComboBox<>();
        felicidad = new javax.swing.JLabel();
        gustaEstudiar = new javax.swing.JLabel();
        gustaCarrera = new javax.swing.JLabel();
        felicidadOpcion = new javax.swing.JRadioButton();
        estudioOpcion = new javax.swing.JRadioButton();
        carreraOpcion = new javax.swing.JRadioButton();
        electronicos = new javax.swing.JLabel();
        movil = new javax.swing.JCheckBox();
        tablet = new javax.swing.JCheckBox();
        computadora = new javax.swing.JCheckBox();
        laptop = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        deseosFuturos = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        deseosFuturosT = new javax.swing.JTextArea();
        actividadesTiempoLibre = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        actividadesTiempoLibreT = new javax.swing.JTextArea();
        proyectosVida = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        proyectosVidaT = new javax.swing.JTextArea();
        rasgosHabitos = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        rasgosHabitosT = new javax.swing.JTextArea();
        jPanel9 = new javax.swing.JPanel();
        AnnoComboBox = new javax.swing.JComboBox<>();
        Anno = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tablaNotas = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        telefonoParticular = new javax.swing.JLabel();
        telefonoFijo = new javax.swing.JLabel();
        datosMoviles = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        becado = new javax.swing.JLabel();
        colorPiel = new javax.swing.JLabel();
        militante = new javax.swing.JLabel();
        estadoCivil = new javax.swing.JLabel();
        hijos = new javax.swing.JLabel();
        direccionParticular = new javax.swing.JLabel();
        religion = new javax.swing.JLabel();
        bebidasAlc = new javax.swing.JLabel();
        fumador = new javax.swing.JLabel();
        participacionBrigada = new javax.swing.JLabel();
        nivelIngles = new javax.swing.JLabel();
        telefonoParticularT = new javax.swing.JTextField();
        telefonoFijoT = new javax.swing.JTextField();
        emailT = new javax.swing.JTextField();
        movileDataBooton = new javax.swing.JRadioButton();
        becadoBooton = new javax.swing.JRadioButton();
        colorDePielComboBox = new javax.swing.JComboBox<>();
        militanteRadioB = new javax.swing.JRadioButton();
        estadoCivilComboBox = new javax.swing.JComboBox<>();
        direccionParticularT = new javax.swing.JTextField();
        hijosBooton = new javax.swing.JRadioButton();
        religionComboBox = new javax.swing.JComboBox<>();
        religionTexto = new javax.swing.JTextField();
        bebidasAl = new javax.swing.JRadioButton();
        fumadorBooton = new javax.swing.JRadioButton();
        participacionBrigadaBien = new javax.swing.JRadioButton();
        participacionBrigadaRegular = new javax.swing.JRadioButton();
        participaicoBrigadaMal = new javax.swing.JRadioButton();
        nivelDeInglesComboBox = new javax.swing.JComboBox<>();
        annadirReligion = new javax.swing.JButton();

        editarNota.setText("Editar Nota Seleccionada");
        editarNota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editarNotaMouseClicked(evt);
            }
        });
        menuNotas.add(editarNota);

        mensajeDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                mensajeDialogWindowClosing(evt);
            }
        });

        Informacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        aceptarBoton.setText("Aceptar");
        aceptarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarBotonActionPerformed(evt);
            }
        });

        cancelarBoton.setText("Cancelar");
        cancelarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBotonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mensajeDialogLayout = new javax.swing.GroupLayout(mensajeDialog.getContentPane());
        mensajeDialog.getContentPane().setLayout(mensajeDialogLayout);
        mensajeDialogLayout.setHorizontalGroup(
            mensajeDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mensajeDialogLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(mensajeDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(mensajeDialogLayout.createSequentialGroup()
                        .addComponent(aceptarBoton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelarBoton))
                    .addComponent(Informacion, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        mensajeDialogLayout.setVerticalGroup(
            mensajeDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mensajeDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Informacion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(mensajeDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceptarBoton)
                    .addComponent(cancelarBoton))
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        panelPestanas.setMaximumSize(new java.awt.Dimension(850, 500));
        panelPestanas.setMinimumSize(new java.awt.Dimension(850, 500));
        panelPestanas.setPreferredSize(new java.awt.Dimension(850, 500));

        jPanel1.setPreferredSize(new java.awt.Dimension(850, 500));

        nombreEstudiante.setText("Nombre del estudiante");

        nombreEstudianteT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nombreEstudianteTKeyReleased(evt);
            }
        });

        carnet.setText("Carnet de Identidad");

        carnetTexto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                carnetTextoKeyReleased(evt);
            }
        });

        sexo.setText("Género");

        masculino.setText("Masculino");

        femenino.setText("Femenino");

        edad.setText("Edad");

        edadT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                edadTKeyReleased(evt);
            }
        });

        zona.setText("Zona");

        finalizar.setText("Terminar edición de los datos del estudiante");
        finalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalizarActionPerformed(evt);
            }
        });

        carrera.setText("Carrera");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(finalizar)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(65, 65, 65)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(3, 3, 3)
                                            .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(10, 10, 10)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(masculino)
                                                .addComponent(femenino))
                                            .addGap(28, 28, 28))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(carrera, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(carreraT)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(carnet)
                                    .addComponent(nombreEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(carnetTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nombreEstudianteT, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edad, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(zona, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(zonaOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edadT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(413, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreEstudiante)
                    .addComponent(nombreEstudianteT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(carnet)
                    .addComponent(carnetTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(carreraT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carrera))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(masculino)
                    .addComponent(sexo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(femenino)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(zona)
                            .addComponent(zonaOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(edadT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(edad)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 192, Short.MAX_VALUE)
                .addComponent(finalizar)
                .addGap(30, 30, 30))
        );

        panelPestanas.addTab("Datos básicos", jPanel1);

        jPanel3.setPreferredSize(new java.awt.Dimension(850, 500));

        TablaManifestaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(TablaManifestaciones);

        agregarManifestacion.setText("Añadir");
        agregarManifestacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                agregarManifestacionMouseClicked(evt);
            }
        });

        manifestacionTexto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                manifestacionTextoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(agregarManifestacion)
                        .addGap(18, 18, 18)
                        .addComponent(manifestacionTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 546, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregarManifestacion)
                    .addComponent(manifestacionTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        panelPestanas.addTab("Manifestaciones artísticas", jPanel3);

        jPanel4.setPreferredSize(new java.awt.Dimension(850, 500));

        TablaDeportes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(TablaDeportes);

        agregarDeporte.setText("Añadir");
        agregarDeporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                agregarDeporteMouseClicked(evt);
            }
        });

        deporteTexto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                deporteTextoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(agregarDeporte)
                        .addGap(18, 18, 18)
                        .addComponent(deporteTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 546, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregarDeporte)
                    .addComponent(deporteTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        panelPestanas.addTab("Deportes", jPanel4);

        jPanel5.setPreferredSize(new java.awt.Dimension(850, 500));

        TablaEnfermedades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(TablaEnfermedades);

        agregarEnfermedad.setText("Añadir");
        agregarEnfermedad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                agregarEnfermedadMouseClicked(evt);
            }
        });

        enfermedadTexto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                enfermedadTextoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(agregarEnfermedad)
                        .addGap(18, 18, 18)
                        .addComponent(enfermedadTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 546, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregarEnfermedad)
                    .addComponent(enfermedadTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        panelPestanas.addTab("Enfermedades", jPanel5);

        jPanel6.setPreferredSize(new java.awt.Dimension(850, 500));

        TablaMedicamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(TablaMedicamentos);

        agregarMedicamento.setText("Añadir");
        agregarMedicamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                agregarMedicamentoMouseClicked(evt);
            }
        });

        medicamentoTexto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                medicamentoTextoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(agregarMedicamento)
                        .addGap(18, 18, 18)
                        .addComponent(medicamentoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 546, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregarMedicamento)
                    .addComponent(medicamentoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        panelPestanas.addTab("Medicamentos", jPanel6);

        jPanel7.setPreferredSize(new java.awt.Dimension(850, 500));

        convivencia.setText("Convivencia");

        padre.setText("Padre");

        madre.setText("Madre");

        hermanos.setText("Hermano(s)");

        hermanas.setText("Hermana(s)");

        abueloP.setText("Abuelo paterno");

        abueloM.setText("Abuelo materno");

        abuelaP.setText("Abuela paterna");

        abuelaM.setText("Abuela materna");

        otrosFamiliares.setText("Otros familiares");

        padresDivorciados.setText("Padres divorciados");

        totalFamiliares.setText("Total de familiares en el núcleo");

        totalFamiliaresT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                totalFamiliaresTKeyReleased(evt);
            }
        });

        ingresoHogar.setText("Ingreso en el hogar");

        ingresoHogarT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ingresoHogarTKeyReleased(evt);
            }
        });

        relacionesConvivencia.setText("Relaciones de convivencia:");

        felicidad.setText("¿Es feliz el estudiante?");

        gustaEstudiar.setText("¿Le gusta el estudio?");

        gustaCarrera.setText("¿Le gusta la carrera?");

        electronicos.setText("Equipos electrónicos:");

        movil.setText("Móvil");

        tablet.setText("Tablet");

        computadora.setText("Computadora");

        laptop.setText("Laptop");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(convivencia)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(totalFamiliares)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalFamiliaresT, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addComponent(abueloP)
                                    .addGap(18, 18, 18)
                                    .addComponent(abueloM)
                                    .addGap(18, 18, 18)
                                    .addComponent(abuelaP))
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addComponent(relacionesConvivencia)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(relacionesConvivenciaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addComponent(abuelaM))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(padre)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(madre)
                            .addGap(18, 18, 18)
                            .addComponent(hermanos)
                            .addGap(18, 18, 18)
                            .addComponent(hermanas)
                            .addGap(18, 18, 18)
                            .addComponent(otrosFamiliares)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(ingresoHogar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ingresoHogarT, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(padresDivorciados)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(padresDivorciadosOpcion))
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                    .addComponent(gustaCarrera)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(carreraOpcion))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                    .addComponent(gustaEstudiar)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(estudioOpcion))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                    .addComponent(felicidad)
                                    .addGap(18, 18, 18)
                                    .addComponent(felicidadOpcion))))
                        .addGap(178, 178, 178)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(computadora)
                                    .addComponent(movil))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tablet)
                                    .addComponent(laptop)))
                            .addComponent(electronicos))))
                .addContainerGap(331, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(convivencia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(padre)
                                    .addComponent(madre)
                                    .addComponent(hermanos)
                                    .addComponent(hermanas)
                                    .addComponent(otrosFamiliares))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(abueloP)
                                    .addComponent(abueloM)
                                    .addComponent(abuelaP)
                                    .addComponent(abuelaM))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(totalFamiliares)
                                    .addComponent(totalFamiliaresT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(padresDivorciados))
                            .addComponent(padresDivorciadosOpcion))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(electronicos)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tablet)
                                    .addComponent(movil))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(computadora)
                                    .addComponent(laptop))
                                .addGap(9, 9, 9))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ingresoHogar)
                                    .addComponent(ingresoHogarT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(relacionesConvivencia)
                                    .addComponent(relacionesConvivenciaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(felicidadOpcion)
                                    .addComponent(felicidad))))
                        .addGap(18, 18, 18)
                        .addComponent(gustaEstudiar))
                    .addComponent(estudioOpcion))
                .addGap(26, 26, 26)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gustaCarrera)
                    .addComponent(carreraOpcion))
                .addContainerGap(97, Short.MAX_VALUE))
        );

        panelPestanas.addTab("Psiquis", jPanel7);

        jPanel8.setPreferredSize(new java.awt.Dimension(850, 500));

        deseosFuturos.setText("Deseos futuros");

        deseosFuturosT.setColumns(20);
        deseosFuturosT.setRows(5);
        jScrollPane3.setViewportView(deseosFuturosT);

        actividadesTiempoLibre.setText("Actividades en tiempo libre");

        actividadesTiempoLibreT.setColumns(20);
        actividadesTiempoLibreT.setRows(5);
        jScrollPane4.setViewportView(actividadesTiempoLibreT);

        proyectosVida.setText("Proyectos de vida");

        proyectosVidaT.setColumns(20);
        proyectosVidaT.setRows(5);
        jScrollPane7.setViewportView(proyectosVidaT);

        rasgosHabitos.setText("Rasgos y hábitos");

        rasgosHabitosT.setColumns(20);
        rasgosHabitosT.setRows(5);
        jScrollPane8.setViewportView(rasgosHabitosT);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deseosFuturos)
                            .addComponent(proyectosVida))
                        .addGap(334, 334, 334)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(actividadesTiempoLibre)
                            .addComponent(rasgosHabitos)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deseosFuturos)
                    .addComponent(actividadesTiempoLibre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(proyectosVida)
                    .addComponent(rasgosHabitos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                    .addComponent(jScrollPane8))
                .addContainerGap())
        );

        panelPestanas.addTab("Información del estudiante", jPanel8);

        jPanel9.setPreferredSize(new java.awt.Dimension(850, 500));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel9MouseClicked(evt);
            }
        });

        AnnoComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                AnnoComboBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        Anno.setText("Año");

        tablaNotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane9.setViewportView(tablaNotas);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Anno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AnnoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Anno)
                        .addComponent(AnnoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelPestanas.addTab("Notas", jPanel9);

        jPanel10.setPreferredSize(new java.awt.Dimension(850, 500));

        jLabel1.setText("Esto está en reparación");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(307, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(266, Short.MAX_VALUE))
        );

        panelPestanas.addTab("Eventos", jPanel10);

        jPanel2.setPreferredSize(new java.awt.Dimension(850, 500));

        telefonoParticular.setText("Teléfono particular");

        telefonoFijo.setText("Teléfono fijo");

        datosMoviles.setText("Datos móviles");

        email.setText("Email");

        becado.setText("Becado");

        colorPiel.setText("Color de piel");

        militante.setText("Militante");

        estadoCivil.setText("Estado civil");

        hijos.setText("Hijos");

        direccionParticular.setText("Dirección particular");

        religion.setText("Religión");

        bebidasAlc.setText("¿Bebe el estudiante?");

        fumador.setText("¿Fuma el estudiante?");

        participacionBrigada.setText("Participación en la brigada");

        nivelIngles.setText("Nivel de inglés");

        telefonoParticularT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                telefonoParticularTKeyReleased(evt);
            }
        });

        telefonoFijoT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                telefonoFijoTKeyReleased(evt);
            }
        });

        emailT.setToolTipText("ejemplo@dominio.ext");

        direccionParticularT.setToolTipText("nombre de calle, numero de casa, % calle 1 y calle 2, reparto, municipio, provincia, pais");

        religionComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                religionComboBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        religionTexto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                religionTextoKeyReleased(evt);
            }
        });

        participacionBrigadaBien.setText("Buena");

        participacionBrigadaRegular.setText("Regular");

        participaicoBrigadaMal.setText("Mala");

        annadirReligion.setText("Añadir");
        annadirReligion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annadirReligionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(direccionParticularT)
                            .addComponent(direccionParticular))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(telefonoFijo)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(telefonoFijoT, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(email)
                                        .addComponent(datosMoviles)
                                        .addComponent(becado)
                                        .addComponent(colorPiel)
                                        .addComponent(militante)
                                        .addComponent(estadoCivil))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(movileDataBooton)
                                        .addComponent(emailT, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(becadoBooton)
                                        .addComponent(colorDePielComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(militanteRadioB)
                                        .addComponent(estadoCivilComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(telefonoParticular)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(telefonoParticularT, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 296, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(religion)
                                    .addComponent(hijos))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(religionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(hijosBooton))
                                .addGap(117, 117, 117))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(nivelIngles)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(nivelDeInglesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(participacionBrigada)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(participacionBrigadaRegular)
                                                .addComponent(participacionBrigadaBien)
                                                .addComponent(participaicoBrigadaMal))))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(religionTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(annadirReligion))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addComponent(bebidasAlc)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(bebidasAl))
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addComponent(fumador)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(fumadorBooton)))
                                            .addGap(40, 40, 40))))
                                .addGap(66, 66, 66))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(telefonoParticularT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telefonoParticular))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(hijos)
                            .addComponent(hijosBooton))
                        .addGap(5, 5, 5)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(telefonoFijo)
                    .addComponent(religion)
                    .addComponent(telefonoFijoT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(religionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(movileDataBooton)
                            .addComponent(datosMoviles))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(email)
                            .addComponent(emailT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(becado)
                            .addComponent(becadoBooton)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(religionTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(annadirReligion))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bebidasAlc)
                            .addComponent(bebidasAl))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fumador)
                            .addComponent(fumadorBooton))))
                .addGap(52, 52, 52)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(colorPiel)
                            .addComponent(colorDePielComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(militante)
                            .addComponent(militanteRadioB))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(estadoCivil)
                            .addComponent(estadoCivilComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(participacionBrigada, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(participacionBrigadaBien))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(nivelIngles)
                                    .addComponent(nivelDeInglesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(participacionBrigadaRegular)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(participaicoBrigadaMal)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(direccionParticular)
                .addGap(5, 5, 5)
                .addComponent(direccionParticularT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        panelPestanas.addTab("Otros datos", jPanel2);

        getContentPane().add(panelPestanas, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agregarManifestacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agregarManifestacionMouseClicked
        
        if (manifestacionTexto.getText().equals("")) {
            MessageDialog messageDialog = new MessageDialog("El campo de texto de manifestaciones está vacío", "", this);
            messageDialog.setVisible(true);
            this.setEnabled(false);
            return;
        }
        
        String temp = manifestacionTexto.getText();
        Vector<String> Similares = new Vector<>();
        for (int i = 0; i < manifestacionesArtisticas.size(); i++) {
            if (Secuencias_cadenas.LongestCommonSubsequence(temp, manifestacionesArtisticas.elementAt(i)) >= 75.00) {
                Similares.add(manifestacionesArtisticas.elementAt(i));
            }
        }
        
        if (!Similares.isEmpty()) {
            
            String[] S = new String[Similares.size()];
            Similares.copyInto(S);
            
            InputDialog inputDialog = new InputDialog(1, "Existen manifestaciones artísticas con nombres similares al de la manifestación que ha escrito. Seleccione uno de ellos o pulse en cancelar si no se ha equivocado.", "Sugerencia", S, temp,this);
            inputDialog.setVisible(true);
            this.setEnabled(false);
            
        } else {
            
            stringTemporal = temp;
            ConfirmDialog confirmDialog = new ConfirmDialog(1, "Desea añadir esta manifestación a la lista?", "Información", this);
            confirmDialog.setVisible(true);
            this.setEnabled(false);
            
           
        }
        manifestacionTexto.setText("");
        manifestaciones.removeAllElements();
    }//GEN-LAST:event_agregarManifestacionMouseClicked

    private void agregarDeporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agregarDeporteMouseClicked
        
        if (deporteTexto.getText().equals("")) {
            MessageDialog messageDialog = new MessageDialog("El campo de texto de deportes está vacío", "", this);
            messageDialog.setVisible(true);
            this.setEnabled(false);
            
            return;
        }
        
        String temp = deporteTexto.getText();
        Vector<String> Similares = new Vector<>();
        for (int i = 0; i < deportes.size(); i++) {
            if (Secuencias_cadenas.LongestCommonSubsequence(temp, deportes.elementAt(i)) >= 75.00) {
                Similares.add(deportes.elementAt(i));
            }
        }
        
        if (!Similares.isEmpty()) {
            
            String[] S = new String[Similares.size()];
            Similares.copyInto(S);
            
            InputDialog inputDialog = new InputDialog(2, "Existen deportes con nombres similares al del deporte que ha escrito. Seleccione uno de ellos o pulse en cancelar si no se ha equivocado.", "Sugerencia", S, temp,this);
            inputDialog.setVisible(true);
            this.setEnabled(false);
            
            
        } else {
            
            stringTemporal = temp;
            ConfirmDialog confirmDialog = new ConfirmDialog(2, "Desea añadir este deporte a la lista?", "Información", this);
            confirmDialog.setVisible(true);
            this.setEnabled(false);
        }
        deporteTexto.setText("");
        deportesDB.removeAllElements();
    }//GEN-LAST:event_agregarDeporteMouseClicked

    private void agregarMedicamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agregarMedicamentoMouseClicked
        
        if (medicamentoTexto.getText().equals("")) {
            MessageDialog messageDialog = new MessageDialog("El campo de texto de medicamentos está vacío", "", this);
            messageDialog.setVisible(true);
            this.setEnabled(false);
                        
            return;
        }
        
        String temp = medicamentoTexto.getText();
        Vector<String> Similares = new Vector<>();
        for (int i = 0; i < medicamentos.size(); i++) {
            if (Secuencias_cadenas.LongestCommonSubsequence(temp, medicamentos.elementAt(i)) >= 75.00) {
                Similares.add(medicamentos.elementAt(i));
            }
        }
        
        if (!Similares.isEmpty()) {
            
            String[] S = new String[Similares.size()];
            Similares.copyInto(S);
            
            InputDialog inputDialog = new InputDialog(3, "Existen medicamentos con nombres similares al del medicamento que ha escrito. Seleccione uno de ellos o pulse en cancelar si no se ha equivocado.", "Sugerencia", S, temp,this);
            inputDialog.setVisible(true);
            this.setEnabled(false);
            
        } else {
            stringTemporal = temp;
            ConfirmDialog confirmDialog = new ConfirmDialog(3, "Desea añadir este medicamento a la lista?", "Información", this);
            confirmDialog.setVisible(true);
            this.setEnabled(false);
        }
        medicamentoTexto.setText("");
        medicamentosDB.removeAllElements();
    }//GEN-LAST:event_agregarMedicamentoMouseClicked

    private void agregarEnfermedadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agregarEnfermedadMouseClicked
        
        if (enfermedadTexto.getText().equals("")) {
            MessageDialog messageDialog = new MessageDialog("El campo de texto de enfermedades está vacío", "", this);
            messageDialog.setVisible(true);
            this.setEnabled(false);
            
            return;
        }
        
        String temp = enfermedadTexto.getText();
        Vector<String> Similares = new Vector<>();
        for (int i = 0; i < enfermedades.size(); i++) {
            if (Secuencias_cadenas.LongestCommonSubsequence(temp, enfermedades.elementAt(i)) >= 75.00) {
                Similares.add(enfermedades.elementAt(i));
            }
        }
        
        if (!Similares.isEmpty()) {
            
            String[] S = new String[Similares.size()];
            Similares.copyInto(S);
            
            InputDialog inputDialog = new InputDialog(4, "Existen enfermedades con nombres similares al de la enfermedad que ha escrito. Seleccione uno de ellos o pulse en cancelar si no se ha equivocado.", "Sugerencia", S, temp,this);
            inputDialog.setVisible(true);
            this.setEnabled(false);            
            
        } else {
            
            stringTemporal = temp;
            ConfirmDialog confirmDialog = new ConfirmDialog(4, "Desea añadir esta enfermedad a la lista?", "Información", this);
            confirmDialog.setVisible(true);
            this.setEnabled(false);
            
        }
        enfermedadTexto.setText("");
        enfermedadesDB.removeAllElements();

    }//GEN-LAST:event_agregarEnfermedadMouseClicked

    private void nombreEstudianteTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreEstudianteTKeyReleased
        
        if (Secuencias_cadenas.sonNumeros(evt.getKeyChar())) {
            
            Character caracterEtrada = evt.getKeyChar();
            String reeplazo = nombreEstudianteT.getText().replaceAll(caracterEtrada.toString(), "");
            nombreEstudianteT.setText(reeplazo);
        }
        

    }//GEN-LAST:event_nombreEstudianteTKeyReleased

    private void carnetTextoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_carnetTextoKeyReleased
        
        Secuencias_cadenas.borrarLetras(evt.getKeyChar(), carnetTexto);

    }//GEN-LAST:event_carnetTextoKeyReleased

    private void edadTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edadTKeyReleased
        
        Secuencias_cadenas.borrarLetras(evt.getKeyChar(), edadT);

    }//GEN-LAST:event_edadTKeyReleased

    private void telefonoParticularTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefonoParticularTKeyReleased
        
        Secuencias_cadenas.borrarLetras(evt.getKeyChar(), telefonoParticularT);

    }//GEN-LAST:event_telefonoParticularTKeyReleased

    private void telefonoFijoTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefonoFijoTKeyReleased
        
        Secuencias_cadenas.borrarLetras(evt.getKeyChar(), telefonoFijoT);

    }//GEN-LAST:event_telefonoFijoTKeyReleased

    private void religionTextoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_religionTextoKeyReleased
        
        if (Secuencias_cadenas.sonNumeros(evt.getKeyChar())) {
            
            Character caracterEtrada = evt.getKeyChar();
            String reeplazo = nombreEstudianteT.getText().replaceAll(caracterEtrada.toString(), "");
            nombreEstudianteT.setText(reeplazo);
        }

    }//GEN-LAST:event_religionTextoKeyReleased

    private void annadirReligionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annadirReligionActionPerformed
        
        if (religionTexto.getText().equals("")) {
            MessageDialog messageDialog = new MessageDialog("El campo de texto de religiones está vacío", "", this);
            messageDialog.setVisible(true);
            this.setEnabled(false);
            
            return;
        }
        
        String temp = religionTexto.getText();
        Vector<String> Similares = new Vector<>();
        for (int i = 0; i < religiones.size(); i++) {
            if (Secuencias_cadenas.LongestCommonSubsequence(temp, religiones.elementAt(i)) >= 75.00) {
                Similares.add(religiones.elementAt(i));
            }
        }
        
        if (!Similares.isEmpty()) {
            
            String[] S = new String[Similares.size()];
            Similares.copyInto(S);
            
            InputDialog inputDialog = new InputDialog(5, "Existen religiones con nombres similares al de la religión que ha escrito. Seleccione uno de ellos o pulse en cancelar si no se ha equivocado.", "Sugerencia", S, temp,this);
            inputDialog.setVisible(true);
            this.setEnabled(false);            
            
        } else {
            
            ConfirmDialog confirmDialog = new ConfirmDialog(5, "Desea añadir esta religion a la lista?", "Información", this);
            confirmDialog.setVisible(true);
            this.setEnabled(false);
            }
        religionTexto.setEnabled(false);
        religionTexto.setText("");
        annadirReligion.setEnabled(false);
        
    }//GEN-LAST:event_annadirReligionActionPerformed

    private void manifestacionTextoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_manifestacionTextoKeyReleased
        
        if (Secuencias_cadenas.sonNumeros(evt.getKeyChar())) {
            
            Character caracterEtrada = evt.getKeyChar();
            String reeplazo = manifestacionTexto.getText().replaceAll(caracterEtrada.toString(), "");
            manifestacionTexto.setText(reeplazo);
        }
        
        String temp = manifestacionTexto.getText();
        if (temp.length() >= 3) {
            Vector<String> Similares = new Vector<>();
            for (int i = 0; i < manifestacionesArtisticas.size(); i++) {
                if (Secuencias_cadenas.mayor_subcadena(temp, manifestacionesArtisticas.elementAt(i))) {
                    Similares.add(manifestacionesArtisticas.elementAt(i));
                }
            }
            actualizarTablaManifestaciones(Similares);
        } else if (temp.length() < 3) {
            actualizarTablaManifestaciones(manifestacionesArtisticas);
        }

    }//GEN-LAST:event_manifestacionTextoKeyReleased

    private void deporteTextoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deporteTextoKeyReleased
        
        if (Secuencias_cadenas.sonNumeros(evt.getKeyChar())) {
            
            Character caracterEtrada = evt.getKeyChar();
            String reeplazo = deporteTexto.getText().replaceAll(caracterEtrada.toString(), "");
            deporteTexto.setText(reeplazo);
        }
        
        String temp = deporteTexto.getText();
        if (temp.length() >= 3) {
            Vector<String> Similares = new Vector<>();
            for (int i = 0; i < deportes.size(); i++) {
                if (Secuencias_cadenas.mayor_subcadena(temp, deportes.elementAt(i))) {
                    Similares.add(deportes.elementAt(i));
                }
            }
            actualizarTablaDeportes(Similares);
        } else if (temp.length() < 3) {
            actualizarTablaDeportes(deportes);
        }

    }//GEN-LAST:event_deporteTextoKeyReleased

    private void enfermedadTextoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enfermedadTextoKeyReleased
        
        if (Secuencias_cadenas.sonNumeros(evt.getKeyChar())) {
            
            Character caracterEtrada = evt.getKeyChar();
            String reeplazo = enfermedadTexto.getText().replaceAll(caracterEtrada.toString(), "");
            enfermedadTexto.setText(reeplazo);
        }
        
        String temp = enfermedadTexto.getText();
        if (temp.length() >= 3) {
            Vector<String> Similares = new Vector<>();
            for (int i = 0; i < enfermedades.size(); i++) {
                if (Secuencias_cadenas.mayor_subcadena(temp, enfermedades.elementAt(i))) {
                    Similares.add(enfermedades.elementAt(i));
                }
            }
            actualizarTablaEnfermedades(Similares);
        } else if (temp.length() < 3) {
            actualizarTablaEnfermedades(enfermedades);
        }

    }//GEN-LAST:event_enfermedadTextoKeyReleased

    private void medicamentoTextoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_medicamentoTextoKeyReleased
        
        if (Secuencias_cadenas.sonNumeros(evt.getKeyChar())) {
            
            Character caracterEtrada = evt.getKeyChar();
            String reeplazo = medicamentoTexto.getText().replaceAll(caracterEtrada.toString(), "");
            medicamentoTexto.setText(reeplazo);
        }
        
        String temp = medicamentoTexto.getText();
        if (temp.length() >= 3) {
            Vector<String> Similares = new Vector<>();
            for (int i = 0; i < medicamentos.size(); i++) {
                if (Secuencias_cadenas.mayor_subcadena(temp, medicamentos.elementAt(i))) {
                    Similares.add(medicamentos.elementAt(i));
                }
            }
            actualizarTablaMedicamentos(Similares);
        } else if (temp.length() < 3) {
            actualizarTablaMedicamentos(medicamentos);
        }

    }//GEN-LAST:event_medicamentoTextoKeyReleased

    private void totalFamiliaresTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalFamiliaresTKeyReleased
        
        Secuencias_cadenas.borrarLetras(evt.getKeyChar(), totalFamiliaresT);

    }//GEN-LAST:event_totalFamiliaresTKeyReleased

    private void ingresoHogarTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ingresoHogarTKeyReleased
        
        Secuencias_cadenas.borrarLetras(evt.getKeyChar(), ingresoHogarT);

    }//GEN-LAST:event_ingresoHogarTKeyReleased

    private void AnnoComboBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_AnnoComboBoxPopupMenuWillBecomeInvisible
        
        notas = g.obtenerNotas(carr, E, AnnoComboBox.getSelectedIndex() + 1);
        actualizarTablaNotas(notas);

    }//GEN-LAST:event_AnnoComboBoxPopupMenuWillBecomeInvisible

    private void editarNotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editarNotaMouseClicked
        
        menuNotas.setVisible(false);
        
        String posiblesNotas[] = {"0", "2", "3", "4", "5"};
        
        InputDialog inputDialog = new InputDialog(6, "Selecciona la nota del estudiante", "Sugerencia", posiblesNotas, "", this);
        inputDialog.setVisible(true);
        this.setEnabled(false);
        
    }//GEN-LAST:event_editarNotaMouseClicked

    private void finalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalizarActionPerformed
        
        String MB = "";
        if (nombreEstudianteT.getText().equals("")) {
            MB = MB + "0";
        } else {
            MB = MB + "1";
        }
        if (carnetTexto.getText().equals("")) {
            MB = MB + "0";
        } else {
            MB = MB + "1";
        }
        if (edadT.getText().equals("")) {
            MB = MB + "0";
        } else {
            MB = MB + "1";
        }
        if (direccionParticularT.getText().equals("")) {
            MB = MB + "0";
        } else {
            MB = MB + "1";
        }
        
        if (MB.contains("0")) {
            
            String S = "Hay campos obligatorios vacíos: ";
            
            if (MB.charAt(0) == '0') {
                S = S + "\n Nombre del estudiante";
            }
            if (MB.charAt(1) == '0') {
                S = S + "\n Carnet de identidad";
            }
            if (MB.charAt(2) == '0') {
                S = S + "\n Edad";
            }
            if (MB.charAt(3) == '0') {
                S = S + "\n Dirección particular";
            }
            
            
            MessageDialog messageDialog = new MessageDialog(S, "Error", this);
            messageDialog.setVisible(true);
            this.setVisible(true);
            return;
        }
        
        String nombre[] = nombreEstudianteT.getText().split(" ");
        if (nombre.length <= 2) {
            MessageDialog messageDialog = new MessageDialog("El nombre completo del estudiante es incorrecto, pues debe tener al menos dos apellidos", "Error", this);
            messageDialog.setVisible(true);
            this.setVisible(true);            
            return;
        }
        if (!Secuencias_cadenas.carnetIdentidadCorrecto(carnetTexto.getText())) {
            MessageDialog messageDialog = new MessageDialog("El carnet de identidad del estudiante es incorrecto", "Error", this);
            messageDialog.setVisible(true);
            this.setVisible(true);            
            return;
        }
        if (Integer.parseInt(edadT.getText()) <= 15) {
            MessageDialog messageDialog = new MessageDialog("La edad del estudiante es incorrecta. Debe ser mayor de 15 años", "Error", this);
            messageDialog.setVisible(true);
            this.setVisible(true);            
            return;
        }
        
        String sexo = "";
        int participacion = 0;
        if (masculino.isSelected()) {
            sexo = "masculino";
        } else {
            sexo = "femenino";
        }
        if (participacionBrigadaBien.isSelected()) {
            participacion = 1;
        } else if (participacionBrigadaRegular.isSelected()) {
            participacion = 2;
        } else if (participaicoBrigadaMal.isSelected()) {
            participacion = 3;
        }
        String telefonoParticular = "";
        if (!telefonoParticularT.getText().equals("")) {
            telefonoParticular = telefonoParticularT.getText();
        }
        String telefonoFijo = "";
        if (!telefonoFijoT.getText().equals("")) {
            telefonoFijo = telefonoFijoT.getText();
        }
        String religion = "";
        if (!religiones.isEmpty() && religionComboBox.getSelectedIndex() != 0) {
            religion = religiones.elementAt(religionComboBox.getSelectedIndex());
        }
        int totalFamiliares = 0;
        if (!totalFamiliaresT.getText().equals("")) {
            totalFamiliares = Integer.parseInt(totalFamiliaresT.getText());
        }
        int ingresosTotales = 0;
        if (!ingresoHogarT.getText().equals("")) {
            ingresosTotales = Integer.parseInt(ingresoHogarT.getText());
        }
        
        boolean[] electronic = {computadora.isSelected(), laptop.isSelected(), movil.isSelected(), tablet.isSelected()};
        boolean[] familiares = {padre.isSelected(), madre.isSelected(), hermanas.isSelected(), hermanos.isSelected(), abueloP.isSelected(), abueloM.isSelected(), abuelaP.isSelected(), abuelaM.isSelected(), otrosFamiliares.isSelected(), padresDivorciadosOpcion.isSelected()};
        
        DatosEstudiante e = new DatosEstudiante(nombreEstudianteT.getText(), carnetTexto.getText(), telefonoParticular, telefonoFijo, movileDataBooton.isSelected(), emailT.getText(), sexo, Integer.parseInt(edadT.getText()), becadoBooton.isSelected(), colorDePielComboBox.getSelectedIndex() + 1, militanteRadioB.isSelected(), estadoCivilComboBox.getSelectedIndex() + 1, hijosBooton.isSelected(), direccionParticularT.getText(), zonaOpciones.getSelectedIndex() + 1, religion, bebidasAl.isSelected(), fumadorBooton.isSelected(), participacion, manifestaciones, familiares, totalFamiliares, ingresosTotales, relacionesConvivenciaComboBox.getSelectedIndex() + 1, deportesDB, electronic, enfermedadesDB, true, new Vector<>(), medicamentosDB, deseosFuturosT.getText(), actividadesTiempoLibreT.getText(), proyectosVidaT.getText(), rasgosHabitosT.getText(), felicidadOpcion.isSelected(), estudioOpcion.isSelected(), carreraOpcion.isSelected(), nivelDeInglesComboBox.getSelectedIndex() + 1);
        if (flag) {
            g.actualizarEstudiante(e, b, E.getCI());
        } else {
            g.editar_estudiante(b, e, E.getCI());
        }
        
        for (int i = 0; i < b.getEstudiantes().size(); i++) {
            if (b.getEstudiantes().elementAt(i).getCI().equals(E.getCI())) {
                b.getEstudiantes().elementAt(i).setCI(carnetTexto.getText());
                b.getEstudiantes().elementAt(i).setNombre_estudiante(nombreEstudianteT.getText());
                break;
            }
        }
        
        this.dispose();

    }//GEN-LAST:event_finalizarActionPerformed

    private void religionComboBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_religionComboBoxPopupMenuWillBecomeInvisible
        if ((religionComboBox.getSelectedItem() + "").equals("Nueva Religión")) {
            religionTexto.setEnabled(true);
            annadirReligion.setEnabled(true);
            
        } else {
            religionTexto.setEnabled(false);
            annadirReligion.setEnabled(false);
        }
    }//GEN-LAST:event_religionComboBoxPopupMenuWillBecomeInvisible

    private void jPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseClicked
        if (menuNotas.isVisible()) {
            menuNotas.setVisible(false);
        }    }//GEN-LAST:event_jPanel9MouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        menuNotas.setVisible(false);
        Editor_brigada ed = new Editor_brigada(b);
        ed.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_formWindowClosed


    private void aceptarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarBotonActionPerformed
        
    }//GEN-LAST:event_aceptarBotonActionPerformed

    private void cancelarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBotonActionPerformed
        
          
    }//GEN-LAST:event_cancelarBotonActionPerformed

    private void mensajeDialogWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_mensajeDialogWindowClosing
        
    }//GEN-LAST:event_mensajeDialogWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Anno;
    private javax.swing.JComboBox<String> AnnoComboBox;
    private javax.swing.JLabel Informacion;
    private javax.swing.JTable TablaDeportes;
    private javax.swing.JTable TablaEnfermedades;
    private javax.swing.JTable TablaManifestaciones;
    private javax.swing.JTable TablaMedicamentos;
    private javax.swing.JCheckBox abuelaM;
    private javax.swing.JCheckBox abuelaP;
    private javax.swing.JCheckBox abueloM;
    private javax.swing.JCheckBox abueloP;
    private javax.swing.JButton aceptarBoton;
    private javax.swing.JLabel actividadesTiempoLibre;
    private javax.swing.JTextArea actividadesTiempoLibreT;
    private javax.swing.JButton agregarDeporte;
    private javax.swing.JButton agregarEnfermedad;
    private javax.swing.JButton agregarManifestacion;
    private javax.swing.JButton agregarMedicamento;
    private javax.swing.JButton annadirReligion;
    private javax.swing.JRadioButton bebidasAl;
    private javax.swing.JLabel bebidasAlc;
    private javax.swing.JLabel becado;
    private javax.swing.JRadioButton becadoBooton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton cancelarBoton;
    private javax.swing.JLabel carnet;
    private javax.swing.JTextField carnetTexto;
    private javax.swing.JLabel carrera;
    private javax.swing.JRadioButton carreraOpcion;
    private javax.swing.JTextField carreraT;
    private javax.swing.JComboBox<String> colorDePielComboBox;
    private javax.swing.JLabel colorPiel;
    private javax.swing.JCheckBox computadora;
    private javax.swing.JLabel convivencia;
    private javax.swing.JLabel datosMoviles;
    private javax.swing.JTextField deporteTexto;
    private javax.swing.JLabel deseosFuturos;
    private javax.swing.JTextArea deseosFuturosT;
    private javax.swing.JLabel direccionParticular;
    private javax.swing.JTextField direccionParticularT;
    private javax.swing.JLabel edad;
    private javax.swing.JTextField edadT;
    private javax.swing.JMenuItem editarNota;
    private javax.swing.JLabel electronicos;
    private javax.swing.JLabel email;
    private javax.swing.JTextField emailT;
    private javax.swing.JTextField enfermedadTexto;
    private javax.swing.JLabel estadoCivil;
    private javax.swing.JComboBox<String> estadoCivilComboBox;
    private javax.swing.JRadioButton estudioOpcion;
    private javax.swing.JLabel felicidad;
    private javax.swing.JRadioButton felicidadOpcion;
    private javax.swing.JRadioButton femenino;
    private javax.swing.JButton finalizar;
    private javax.swing.JLabel fumador;
    private javax.swing.JRadioButton fumadorBooton;
    private javax.swing.JLabel gustaCarrera;
    private javax.swing.JLabel gustaEstudiar;
    private javax.swing.JCheckBox hermanas;
    private javax.swing.JCheckBox hermanos;
    private javax.swing.JLabel hijos;
    private javax.swing.JRadioButton hijosBooton;
    private javax.swing.JLabel ingresoHogar;
    private javax.swing.JTextField ingresoHogarT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JCheckBox laptop;
    private javax.swing.JCheckBox madre;
    private javax.swing.JTextField manifestacionTexto;
    private javax.swing.JRadioButton masculino;
    private javax.swing.JTextField medicamentoTexto;
    private javax.swing.JDialog mensajeDialog;
    private javax.swing.JPopupMenu menuNotas;
    private javax.swing.JLabel militante;
    private javax.swing.JRadioButton militanteRadioB;
    private javax.swing.JCheckBox movil;
    private javax.swing.JRadioButton movileDataBooton;
    private javax.swing.JComboBox<String> nivelDeInglesComboBox;
    private javax.swing.JLabel nivelIngles;
    private javax.swing.JLabel nombreEstudiante;
    private javax.swing.JTextField nombreEstudianteT;
    private javax.swing.JCheckBox otrosFamiliares;
    private javax.swing.JCheckBox padre;
    private javax.swing.JLabel padresDivorciados;
    private javax.swing.JRadioButton padresDivorciadosOpcion;
    private javax.swing.JTabbedPane panelPestanas;
    private javax.swing.JLabel participacionBrigada;
    private javax.swing.JRadioButton participacionBrigadaBien;
    private javax.swing.JRadioButton participacionBrigadaRegular;
    private javax.swing.JRadioButton participaicoBrigadaMal;
    private javax.swing.JLabel proyectosVida;
    private javax.swing.JTextArea proyectosVidaT;
    private javax.swing.JLabel rasgosHabitos;
    private javax.swing.JTextArea rasgosHabitosT;
    private javax.swing.JLabel relacionesConvivencia;
    private javax.swing.JComboBox<String> relacionesConvivenciaComboBox;
    private javax.swing.JLabel religion;
    private javax.swing.JComboBox<String> religionComboBox;
    private javax.swing.JTextField religionTexto;
    private javax.swing.JLabel sexo;
    private javax.swing.JTable tablaNotas;
    private javax.swing.JCheckBox tablet;
    private javax.swing.JLabel telefonoFijo;
    private javax.swing.JTextField telefonoFijoT;
    private javax.swing.JLabel telefonoParticular;
    private javax.swing.JTextField telefonoParticularT;
    private javax.swing.JLabel totalFamiliares;
    private javax.swing.JTextField totalFamiliaresT;
    private javax.swing.JLabel zona;
    private javax.swing.JComboBox<String> zonaOpciones;
    // End of variables declaration//GEN-END:variables

    protected void actualizarTablaManifestaciones(Vector<String> manifestacionesArtisticas) {
        DefaultTableModel d = new DefaultTableModel() {
            
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1;
            }
        ;
        };
        Object[] OBJ = new Object[2];
        d.addColumn("Nombre de la Manifestación");
        d.addColumn("Selección");
        boolean bandera = false;
        radioButtonManifestaciones = new Vector<>();
        for (int i = 0; i < manifestacionesArtisticas.size(); i++) {
            if (flag) {
                for (int j = 0; j < datosEstudiante.getManifestaciones_artisticas().size(); j++) {
                    if (manifestacionesArtisticas.elementAt(i).equals(datosEstudiante.getManifestaciones_artisticas().elementAt(j))) {
                        bandera = true;
                        break;
                    }
                }
            }
            OBJ[0] = manifestacionesArtisticas.elementAt(i);
            radioButtonManifestaciones.add(new JCheckBox("", bandera));
            OBJ[1] = radioButtonManifestaciones.lastElement();
            d.addRow(OBJ);
            bandera = false;
        }
        
        TablaManifestaciones = new JTable(d);
        
        TablaManifestaciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                int fila = TablaManifestaciones.rowAtPoint(e.getPoint());
                if (fila > -1) {
                    if (radioButtonManifestaciones.elementAt(fila).isSelected()) {
                        manifestaciones.add(manifestacionesArtisticas.elementAt(fila));
                        
                    } else {
                        if (manifestaciones.contains(manifestacionesArtisticas.elementAt(fila))) {
                            manifestaciones.remove(manifestacionesArtisticas.elementAt(fila));
                        }
                    }
                }
            }
        });
        
        TablaManifestaciones.setFont(new Font("arial", Font.BOLD, 14));
        TablaManifestaciones.setRowHeight(30);
        TablaManifestaciones.setShowGrid(true);
        
        TablaManifestaciones.getColumn("Selección").setCellRenderer(
                new RadioButtonRenderer());
        TablaManifestaciones.getColumn("Selección").setCellEditor(
                new RadioButtonEditor(new JCheckBox()));
        jScrollPane1.setViewportView(TablaManifestaciones);
    }
    
    protected void actualizarTablaDeportes(Vector<String> deporte) {
        
        DefaultTableModel d = new DefaultTableModel() {
            
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1;
            }
        ;
        };
        Object[] OBJ = new Object[2];
        d.addColumn("Deporte");
        d.addColumn("Selección");
        boolean bandera = false;
        radioButtonDeportes = new Vector<>();
        for (int i = 0; i < deporte.size(); i++) {
            if (flag) {
                for (int j = 0; j < datosEstudiante.getDeportes().size(); j++) {
                    if (deporte.elementAt(i).equals(datosEstudiante.getDeportes().elementAt(j))) {
                        bandera = true;
                        break;
                    }
                }
            }
            OBJ[0] = deporte.elementAt(i);
            radioButtonDeportes.add(new JCheckBox("", bandera));
            OBJ[1] = radioButtonDeportes.lastElement();
            d.addRow(OBJ);
            bandera = false;
        }
        
        TablaDeportes = new JTable(d);
        
        TablaDeportes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                int fila = TablaDeportes.rowAtPoint(e.getPoint());
                if (fila > -1) {
                    if (radioButtonDeportes.elementAt(fila).isSelected()) {
                        deportesDB.add(deportes.elementAt(fila));
                        
                    } else {
                        if (deportesDB.contains(deportes.elementAt(fila))) {
                            deportesDB.remove(deportes.elementAt(fila));
                        }
                    }
                }
            }
        });
        
        TablaDeportes.setFont(new Font("arial", Font.BOLD, 14));
        TablaDeportes.setRowHeight(30);
        TablaDeportes.setShowGrid(true);
        
        TablaDeportes.getColumn("Selección").setCellRenderer(
                new RadioButtonRenderer());
        TablaDeportes.getColumn("Selección").setCellEditor(
                new RadioButtonEditor(new JCheckBox()));
        jScrollPane2.setViewportView(TablaDeportes);
        
    }
    
    protected void actualizarTablaEnfermedades(Vector<String> enfermedade) {
        
        DefaultTableModel d = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1;
            }
        ;
        };
        Object[] OBJ = new Object[2];
        d.addColumn("Enfermedad");
        d.addColumn("Selección");
        boolean bandera = false;
        radioButtonEnfermedades = new Vector<>();
        for (int i = 0; i < enfermedade.size(); i++) {
            if (flag) {
                for (int j = 0; j < datosEstudiante.getEnfermedades().size(); j++) {
                    if (enfermedade.elementAt(i).equals(datosEstudiante.getEnfermedades().elementAt(j))) {
                        bandera = true;
                        break;
                    }
                }
            }
            OBJ[0] = enfermedade.elementAt(i);
            radioButtonEnfermedades.add(new JCheckBox("", bandera));
            OBJ[1] = radioButtonEnfermedades.lastElement();
            d.addRow(OBJ);
            bandera = false;
        }
        
        TablaEnfermedades = new JTable(d);
        TablaEnfermedades.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                int fila = TablaEnfermedades.rowAtPoint(e.getPoint());
                if (fila > -1) {
                    if (radioButtonEnfermedades.elementAt(fila).isSelected()) {
                        enfermedadesDB.add(enfermedades.elementAt(fila));
                        
                    } else {
                        if (enfermedadesDB.contains(enfermedades.elementAt(fila))) {
                            enfermedadesDB.remove(enfermedades.elementAt(fila));
                        }
                    }
                }
            }
        });
        TablaEnfermedades.setFont(new Font("arial", Font.BOLD, 14));
        TablaEnfermedades.setRowHeight(30);
        TablaEnfermedades.setShowGrid(true);
        
        TablaEnfermedades.getColumn("Selección").setCellRenderer(
                new RadioButtonRenderer());
        TablaEnfermedades.getColumn("Selección").setCellEditor(
                new RadioButtonEditor(new JCheckBox()));
        jScrollPane6.setViewportView(TablaEnfermedades);
    }
    
    protected void actualizarTablaMedicamentos(Vector<String> medicamento) {
        
        DefaultTableModel d = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1;
            }
        ;
        };
        Object[] OBJ = new Object[2];
        d.addColumn("Medicamento");
        d.addColumn("Selección");
        boolean bandera = false;
        radioButtonMedicamentos = new Vector<>();
        for (int i = 0; i < medicamento.size(); i++) {
            if (flag) {
                for (int j = 0; j < datosEstudiante.getMedicamentos().size(); j++) {
                    if (medicamento.elementAt(i).equals(datosEstudiante.getMedicamentos().elementAt(j))) {
                        bandera = true;
                        break;
                    }
                }
            }
            OBJ[0] = medicamento.elementAt(i);
            radioButtonMedicamentos.add(new JCheckBox("", bandera));
            OBJ[1] = radioButtonMedicamentos.lastElement();
            d.addRow(OBJ);
            bandera = false;
        }
        
        TablaMedicamentos = new JTable(d);
        TablaMedicamentos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                int fila = TablaMedicamentos.rowAtPoint(e.getPoint());
                if (fila > -1) {
                    if (radioButtonMedicamentos.elementAt(fila).isSelected()) {
                        medicamentosDB.add(medicamentos.elementAt(fila));
                        
                    } else {
                        if (medicamentosDB.contains(medicamentos.elementAt(fila))) {
                            medicamentosDB.remove(medicamentos.elementAt(fila));
                        }
                    }
                }
            }
        });
        
        TablaMedicamentos.setFont(new Font("arial", Font.BOLD, 14));
        TablaMedicamentos.setRowHeight(30);
        TablaMedicamentos.setShowGrid(true);
        
        TablaMedicamentos.getColumn("Selección").setCellRenderer(
                new RadioButtonRenderer());
        TablaMedicamentos.getColumn("Selección").setCellEditor(
                new RadioButtonEditor(new JCheckBox()));
        jScrollPane5.setViewportView(TablaMedicamentos);
        
    }
    
    private void actualizarTablaNotas(Vector<Nota> notas) {
        
        DefaultTableModel d = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        ;
        };
        Object[] OBJ = new Object[2];
        d.addColumn("Asignatura");
        
        d.addColumn("Nota");
        
        OBJ[0] = "Primer Semestre";
        d.addRow(OBJ);
        
        for (int i = 0; i < notas.size(); i++) {
            if (g.esPrimerSemestre(notas.elementAt(i).getIdAsignatura(), carr.getNombre())) {
                OBJ[0] = notas.elementAt(i).getNombreAsignatura();
                OBJ[1] = notas.elementAt(i).getNota();
                d.addRow(OBJ);
            }
        }
        
        OBJ[0] = "Segundo Semestre";
        OBJ[1] = "";
        d.addRow(OBJ);
        
        for (int i = 0; i < notas.size(); i++) {
            if (g.esSegundoSemestre(notas.elementAt(i).getIdAsignatura(), carr.getNombre())) {
                OBJ[0] = notas.elementAt(i).getNombreAsignatura();
                OBJ[1] = notas.elementAt(i).getNota();
                d.addRow(OBJ);
            }
        }
        
        tablaNotas = new JTable(d);
        
        tablaNotas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tablaNotas.rowAtPoint(e.getPoint());
                
                if (fila > -1) {
                    String asig = (String) tablaNotas.getValueAt(fila, 0);
                    if (!(asig.equals("Primer Semestre") || asig.equals("Segundo Semestre"))) {
                        
                        menuNotas.setLocation(e.getXOnScreen(), e.getYOnScreen());
                        menuNotas.setVisible(true);
                    }
                }
            }
        });
        
        jScrollPane9.setViewportView(tablaNotas);
    }
    
}
