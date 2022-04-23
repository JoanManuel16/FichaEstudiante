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
import clases.RadioButtonEditor;
import clases.RadioButtonRenderer;
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
import utiles.Tupla;

/**
 *
 * @author joanmanuel
 */
public class Editor_estudiante extends javax.swing.JFrame {

    private Vector<String> manifestacionesArtisticas;
    private Vector<JRadioButton> radioButtonManifestaciones;
    private Vector<String> deportes;
    private Vector<JRadioButton> radioButtonDeportes;
    private Vector<String> enfermedades;
    private Vector<JRadioButton> radioButtonEnfermedades;
    private Vector<String> medicamentos;
    private Vector<JRadioButton> radioButtonMedicamentos;
    
    private Vector<Nota> notas;
    private Brigada b;
    private Gestion g = new Gestion();
    private Carrera carr;
    private Vector<String> religiones;
    private Estudiante E;
    private DatosEstudiante datosEstudiante;
    private boolean flag =false;
    public Editor_estudiante(Estudiante E, String carrera,Brigada b) {
        initComponents();
        this.E=E;
        this.b= b;
        religiones= new Vector<>();
        masculino.setSelected(true);
        radioButtonManifestaciones = new Vector<>();
        manifestacionesArtisticas = g.obtenerManifestaciones();
        
        
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
              for(int i = 0; i < zonas.size(); i++){
                  zonaOpciones.addItem(zonas.elementAt(i));
              }
                
        Vector<String> colorPieles = g.obtenerColorPiel();
        for(int i = 0; i < colorPieles.size(); i++){
                  colorDePielComboBox.addItem(colorPieles.elementAt(i));
              }
        
        Vector<String> estCivil = g.obtenerEstadoCivil();
        for(int i = 0; i < estCivil.size(); i++){
                  estadoCivilComboBox.addItem(estCivil.elementAt(i));
              }
         
         religiones.add("Nueva Religión");
         religiones.addAll(g.obtenerReligiones());
        for(int i = 0; i < religiones.size(); i++){
                  religionComboBox.addItem(religiones.elementAt(i));
              }
        religionTexto.setVisible(false);
        annadirReligion.setVisible(false);
        
        buttonGroup2.add(participacionBrigadaBien);
        buttonGroup2.add(participacionBrigadaRegular);
        buttonGroup2.add(participaicoBrigadaMal);
        
        Vector<String> nivIng = g.obtenerNivelIngles();
        for(int i = 0; i < nivIng.size(); i++){
                  nivelDeInglesComboBox.addItem(nivIng.elementAt(i));
        }
        
        
        Vector<String> convivencia = g.obtenerConvivencia();
         for(int i = 0; i < convivencia.size(); i++){
                  relacionesConvivenciaComboBox.addItem(convivencia.elementAt(i));
              }
         
         //hay un error aqui en obtener las notas
        notas = g.obtenerNotas(carr, E,AnnoComboBox.getSelectedIndex()+1);
        
        actualizarTablaNotas(notas);
              
                }
   public Editor_estudiante(Estudiante E, String carrera,Brigada b,DatosEstudiante d) {
  this.E=E;
        this.b= b;
        datosEstudiante = d;
       if(d.getSexo().equals("masculino")){
       masculino.setSelected(true);
       }
       else{
           femenino.setSelected(true);
       }
        radioButtonManifestaciones = new Vector<>();
        manifestacionesArtisticas = g.obtenerManifestaciones();
        actualizarTablaManifestaciones(manifestacionesArtisticas);
        for (int i = 0; i < d.getManifestaciones_artisticas().size(); i++) {
           if(manifestacionesArtisticas.elementAt(i).equals(d.getManifestaciones_artisticas().elementAt(i))){
           radioButtonManifestaciones.elementAt(i).setSelected(true);
           }
       }
        radioButtonDeportes = new Vector<>();
        deportes = g.obtenerDeportes();
        actualizarTablaDeportes(deportes);
        for (int i = 0; i < d.getDeportes().size(); i++) {
           if(deportes.elementAt(i).equals(d.getDeportes().elementAt(i))){
           radioButtonDeportes.elementAt(i).setSelected(true);
           }
       }
        radioButtonEnfermedades = new Vector<>();
        enfermedades = g.obtenerEnfermedades();
        actualizarTablaEnfermedades(enfermedades);
        for (int i = 0; i < d.getEnfermedades().size(); i++) {
           if(enfermedades.elementAt(i).equals(d.getEnfermedades().elementAt(i))){
           radioButtonEnfermedades.elementAt(i).setSelected(true);
           }
       }
        radioButtonMedicamentos = new Vector<>();
        medicamentos = g.obtenerMedicamentos();
        actualizarTablaMedicamentos(medicamentos);
        for (int i = 0; i < d.getMedicamentos().size(); i++) {
           if(medicamentos.elementAt(i).equals(d.getMedicamentos().elementAt(i))){
           radioButtonMedicamentos.elementAt(i).setSelected(true);
           }
       }
        this.carr = g.obtener_carrera(carrera);
        
        nombreEstudianteT.setText(E.getNombre_estudiante());
        carnetTexto.setText(E.getCI());
        carreraT.setText(carr.getNombre());
        carreraT.setEditable(false);
        
        buttonGroup1.add(masculino);
        buttonGroup1.add(femenino);
        
        Vector<String> zonas = g.obtenerZonas();
      
        
              for(int i = 0; i < zonas.size(); i++){
                  zonaOpciones.addItem(zonas.elementAt(i));
              }
                zonaOpciones.setSelectedIndex(d.getZona()-1);
        Vector<String> colorPieles = g.obtenerColorPiel();
        for(int i = 0; i < colorPieles.size(); i++){
                  colorDePielComboBox.addItem(colorPieles.elementAt(i));
              }
        colorDePielComboBox.setSelectedIndex(d.getColor_de_piel()-1);
        Vector<String> estCivil = g.obtenerEstadoCivil();
        for(int i = 0; i < estCivil.size(); i++){
                  estadoCivilComboBox.addItem(estCivil.elementAt(i));
              }
         estadoCivilComboBox.setSelectedIndex(d.getEstado_civil()-1);
         religiones.add("Nueva Religión");
         religiones.addAll(g.obtenerReligiones());
        for(int i = 0; i < religiones.size(); i++){
                  religionComboBox.addItem(religiones.elementAt(i));
              }
        religionComboBox.setSelectedItem(d.getReligion());
        religionTexto.setVisible(false);
        annadirReligion.setVisible(false);
        
        buttonGroup2.add(participacionBrigadaBien);
        buttonGroup2.add(participacionBrigadaRegular);
        buttonGroup2.add(participaicoBrigadaMal);
        switch (d.getParticipacion_brigada()) {
            case 1 -> participacionBrigadaBien.setSelected(true);
            case 2 -> participacionBrigadaRegular.setSelected(true);
            default -> participaicoBrigadaMal.setSelected(true);
        }
        
        Vector<String> nivIng = g.obtenerNivelIngles();
        for(int i = 0; i < nivIng.size(); i++){
                  nivelDeInglesComboBox.addItem(nivIng.elementAt(i));
        }
        nivelDeInglesComboBox.setSelectedIndex(d.getNivel_ingles());
        
        Vector<String> convivencia = g.obtenerConvivencia();
         for(int i = 0; i < convivencia.size(); i++){
                  relacionesConvivenciaComboBox.addItem(convivencia.elementAt(i));
              }
         relacionesConvivenciaComboBox.setSelectedIndex(d.getRelaciones());
         
        notas = g.obtenerNotas(carr, E,AnnoComboBox.getSelectedIndex()+1);
        
        actualizarTablaNotas(notas);
         edadT.setText(d.getEdad() + "");
         deseosFuturosT.setText(d.getDeseos_futuros());
         actividadesTiempoLibreT.setText(d.getActividades_tiempo_libre());
         proyectosVidaT.setText(d.getProyectos_vida());
         rasgosHabitosT.setText(d.getRasgos_habitos());
         telefonoFijoT.setText(d.getTelefono_fijo()+"");
         telefonoParticularT.setText(d.getTelefono_particular()+"");
         becadoBooton.setSelected(d.isBecado());
         movileDataBooton.setSelected(d.isDatos_moviles());
         hijosBooton.setSelected(d.isHijos());
         bebidasAl.setSelected(d.isBebidas_alcoholicas());
         fumadorBooton.setSelected(d.isFumador());
         direccionParticularT.setText(d.getDireccion_particular());
         totalFamiliaresT.setText(d.getTotal_familiares()+"");
         ingresoHogarT.setText(d.getIngreso_total()+"");
         felicidadOpcion.setSelected(d.isFeliz());
         estudioOpcion.setSelected(d.isGusta_estudio());
         carreraOpcion.setSelected(d.isGusta_carrera());
        boolean [] convivenciaArray = d.getConvivencia();
        padre.setSelected(convivenciaArray[0]);
        madre.setSelected(convivenciaArray[1]);
        hermanas.setSelected(convivenciaArray[2]);
        hermanos.setSelected(convivenciaArray[3]);
        abueloP.setSelected(convivenciaArray[4]);
        abueloM.setSelected(convivenciaArray[5]);
        abuelaP.setSelected(convivenciaArray[6]);
        abuelaM.setSelected(convivenciaArray[7]);
        otrosFamiliares.setSelected(convivenciaArray[8]);
        //como sacar los padrea divorciados
        padresDivorciadosOpcion.setSelected(false);
        
         
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

        editarNota.setText("jMenuItem1");
        editarNota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editarNotaMouseClicked(evt);
            }
        });
        menuNotas.add(editarNota);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        nombreEstudiante.setText("Nombre del Estudiante");

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

        finalizar.setText("Finalizar");
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(finalizar)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(nombreEstudiante, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                    .addComponent(carnet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(nombreEstudianteT)
                                    .addComponent(carnetTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                                        .addComponent(carreraT))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edad, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(zona, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(zonaOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edadT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(227, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 177, Short.MAX_VALUE)
                .addComponent(finalizar)
                .addContainerGap())
        );

        panelPestanas.addTab("Datos básicos", jPanel1);

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
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(agregarManifestacion)
                        .addGap(18, 18, 18)
                        .addComponent(manifestacionTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 339, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregarManifestacion)
                    .addComponent(manifestacionTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        panelPestanas.addTab("Manifestaciones artísticas", jPanel3);

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
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(agregarDeporte)
                        .addGap(18, 18, 18)
                        .addComponent(deporteTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 339, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregarDeporte)
                    .addComponent(deporteTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        panelPestanas.addTab("Deportes", jPanel4);

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
                    .addComponent(jScrollPane6)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(agregarEnfermedad)
                        .addGap(18, 18, 18)
                        .addComponent(enfermedadTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 339, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregarEnfermedad)
                    .addComponent(enfermedadTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        panelPestanas.addTab("Enfermedades", jPanel5);

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
                    .addComponent(jScrollPane5)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(agregarMedicamento)
                        .addGap(18, 18, 18)
                        .addComponent(medicamentoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 339, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregarMedicamento)
                    .addComponent(medicamentoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        panelPestanas.addTab("Medicamentos", jPanel6);

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

        totalFamiliares.setText("Total de familiares en el nucleo");

        totalFamiliaresT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                totalFamiliaresTKeyReleased(evt);
            }
        });

        ingresoHogar.setText("Ingreso en el Hogar");

        ingresoHogarT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ingresoHogarTKeyReleased(evt);
            }
        });

        relacionesConvivencia.setText("Relaciones de convivencia:");

        felicidad.setText("Es feliz el estudiante");

        gustaEstudiar.setText("Le gusta el estudio");

        gustaCarrera.setText("Le gusta la carrera");

        electronicos.setText("Equipos electrónicos:");

        movil.setText("Movil");

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
                            .addComponent(padre)
                            .addGap(18, 18, 18)
                            .addComponent(madre)
                            .addGap(18, 18, 18)
                            .addComponent(hermanos)
                            .addGap(18, 18, 18)
                            .addComponent(hermanas)
                            .addGap(18, 18, 18)
                            .addComponent(otrosFamiliares)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(gustaCarrera)
                        .addGap(18, 18, 18)
                        .addComponent(carreraOpcion))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(ingresoHogar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ingresoHogarT, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(felicidad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(felicidadOpcion))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(gustaEstudiar)
                                .addGap(18, 18, 18)
                                .addComponent(estudioOpcion))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(padresDivorciados)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(padresDivorciadosOpcion)))
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
                .addContainerGap(129, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(convivencia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(padresDivorciados)
                    .addComponent(padresDivorciadosOpcion))
                .addGap(4, 4, 4)
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
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(gustaEstudiar)
                        .addGap(26, 26, 26)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gustaCarrera)
                            .addComponent(carreraOpcion)))
                    .addComponent(estudioOpcion))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        panelPestanas.addTab("Psiquis", jPanel7);

        deseosFuturos.setText("Deseos Futuros");

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
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deseosFuturos)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(proyectosVida))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rasgosHabitos)
                            .addComponent(actividadesTiempoLibre))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane8))
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
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                    .addComponent(jScrollPane8))
                .addContainerGap())
        );

        panelPestanas.addTab("Información del estudiante", jPanel8);

        AnnoComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                AnnoComboBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        Anno.setText("Anno");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Anno)
                        .addComponent(AnnoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        panelPestanas.addTab("Notas", jPanel9);

        jLabel1.setText("Esto está en reparación");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(228, Short.MAX_VALUE))
        );

        panelPestanas.addTab("Eventos", jPanel10);

        telefonoParticular.setText("Teléfono Particular");

        telefonoFijo.setText("Teléfono Fijo");

        datosMoviles.setText("Datos Móviles");

        email.setText("Email");

        becado.setText("Becado");

        colorPiel.setText("Color de Piel");

        militante.setText("Militante");

        estadoCivil.setText("Estado Civil");

        hijos.setText("Hijos");

        direccionParticular.setText("Dirección Particular");

        religion.setText("Religión");

        bebidasAlc.setText("Bebidas Alcohólicas");

        fumador.setText("Fumador");

        participacionBrigada.setText("Participación en la Brigada");

        nivelIngles.setText("Nivel Inglés");

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

        estadoCivilComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        direccionParticularT.setToolTipText("nombre de calle, numero de casa, % calle 1 y calle 2, reparto, municipio, provincia, pais");

        religionComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                religionComboBoxActionPerformed(evt);
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(hijos)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(hijosBooton))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(religion)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(religionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hijos)
                    .addComponent(telefonoParticularT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hijosBooton)
                    .addComponent(telefonoParticular))
                .addGap(18, 18, 18)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(direccionParticular)
                .addGap(5, 5, 5)
                .addComponent(direccionParticularT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelPestanas.addTab("Otros datos", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPestanas)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPestanas)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agregarManifestacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agregarManifestacionMouseClicked

           if(manifestacionTexto.getText().equals("")){
            JOptionPane.showMessageDialog(null, "El campo esta vacio");
        }
        
         String temp = manifestacionTexto.getText();
        Vector<String> Similares = new Vector<>();
             for(int i = 0; i < manifestacionesArtisticas.size(); i++){
                 if(Secuencias_cadenas.LongestCommonSubsequence(temp, manifestacionesArtisticas.elementAt(i))>=75.00){
                     Similares.add(manifestacionesArtisticas.elementAt(i));
                 }
             }
             
             if(!Similares.isEmpty()){
             
             String[] S = new String[Similares.size()];
             Similares.copyInto(S);
             
                    String  x =(String) JOptionPane.showInputDialog(null, "Estas manifestaciones artisticas son similares a lo escrito. Seleccione una de las opciones si se ha equivocado", "Sugerencia",JOptionPane.QUESTION_MESSAGE,null , S, S[0]);
            
                    if(x == null){
                        manifestacionesArtisticas.add(temp);
                        g.agregarManifestacionArtistica(temp);
                        actualizarTablaManifestaciones(manifestacionesArtisticas);
                    }
                    else{
                        Vector<String> V = new Vector<>();
                        V.add(x);
                        actualizarTablaManifestaciones(V);
                    }
             }
             else{
                  manifestacionesArtisticas.add(temp);
                  g.agregarManifestacionArtistica(temp);
                  actualizarTablaManifestaciones(manifestacionesArtisticas);
             }
        
    }//GEN-LAST:event_agregarManifestacionMouseClicked

    private void agregarDeporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agregarDeporteMouseClicked
        
          if(deporteTexto.getText().equals("")){
            JOptionPane.showMessageDialog(null, "El campo esta vacio");
        }
        
         String temp = deporteTexto.getText();
        Vector<String> Similares = new Vector<>();
             for(int i = 0; i < deportes.size(); i++){
                 if(Secuencias_cadenas.LongestCommonSubsequence(temp, deportes.elementAt(i))>=75.00){
                     Similares.add(deportes.elementAt(i));
                 }
             }
             
             if(!Similares.isEmpty()){
             
             String[] S = new String[Similares.size()];
             Similares.copyInto(S);
             
                    String  x =(String) JOptionPane.showInputDialog(null, "Estos deportes son similares a lo escrito. Seleccione una de las opciones si se ha equivocado", "Sugerencia",JOptionPane.QUESTION_MESSAGE,null , S, S[0]);
            
                    if(x == null){
                        deportes.add(temp);
                        g.agregarDeporte(temp);
                        actualizarTablaDeportes(deportes);
                    }
                    else{
                        Vector<String> V = new Vector<>();
                        V.add(x);
                        actualizarTablaDeportes(V);
                    }
             }
             else{
                  deportes.add(temp);
                  g.agregarDeporte(temp);
                  actualizarTablaDeportes(deportes);
             }
        
    }//GEN-LAST:event_agregarDeporteMouseClicked

    private void agregarMedicamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agregarMedicamentoMouseClicked
        
        if(medicamentoTexto.getText().equals("")){
            JOptionPane.showMessageDialog(null, "El campo esta vacio");
        }
        
         String temp = medicamentoTexto.getText();
        Vector<String> Similares = new Vector<>();
             for(int i = 0; i < medicamentos.size(); i++){
                 if(Secuencias_cadenas.LongestCommonSubsequence(temp, medicamentos.elementAt(i))>=75.00){
                     Similares.add(medicamentos.elementAt(i));
                 }
             }
             
             if(!Similares.isEmpty()){
             
             String[] S = new String[Similares.size()];
             Similares.copyInto(S);
             
                    String  x =(String) JOptionPane.showInputDialog(null, "Estos medicamentos son similares a lo escrito. Seleccione una de las opciones si se ha equivocado", "Sugerencia",JOptionPane.QUESTION_MESSAGE,null , S, S[0]);
            
                    if(x == null){
                        medicamentos.add(temp);
                        g.agregarMedicamento(temp);
                        actualizarTablaMedicamentos(medicamentos);
                    }
                    else{
                        Vector<String> V = new Vector<>();
                        V.add(x);
                        actualizarTablaMedicamentos(V);
                    }
             }
             else{
                        medicamentos.add(temp);
                        g.agregarMedicamento(temp);
                        actualizarTablaMedicamentos(medicamentos);
             }
        
        
    }//GEN-LAST:event_agregarMedicamentoMouseClicked

    private void agregarEnfermedadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agregarEnfermedadMouseClicked
        
          if(enfermedadTexto.getText().equals("")){
            JOptionPane.showMessageDialog(null, "El campo esta vacio");
        }
        
         String temp = enfermedadTexto.getText();
        Vector<String> Similares = new Vector<>();
             for(int i = 0; i < enfermedades.size(); i++){
                 if(Secuencias_cadenas.LongestCommonSubsequence(temp, enfermedades.elementAt(i))>=75.00){
                     Similares.add(enfermedades.elementAt(i));
                 }
             }
             
             if(!Similares.isEmpty()){
             
             String[] S = new String[Similares.size()];
             Similares.copyInto(S);
             
                    String  x =(String) JOptionPane.showInputDialog(null, "Estas enfermedades son similares a lo escrito. Seleccione una de las opciones si se ha equivocado", "Sugerencia",JOptionPane.QUESTION_MESSAGE,null , S, S[0]);
            
                    if(x == null){
                        enfermedades.add(temp);
                        g.agregarEnfermedad(temp);
                        actualizarTablaEnfermedades(enfermedades);
                    }
                    else{
                        Vector<String> V = new Vector<>();
                        V.add(x);
                        actualizarTablaEnfermedades(V);
                    }
             }
             else{
                   enfermedades.add(temp);
                   g.agregarEnfermedad(temp);
                   actualizarTablaEnfermedades(enfermedades);
             }
        
        
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

    private void religionComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_religionComboBoxActionPerformed
      
        if((religionComboBox.getSelectedItem()+"").equals("Nueva Religión")){
            religionTexto.setVisible(true);
            annadirReligion.setVisible(true);
            
        }        
        
    }//GEN-LAST:event_religionComboBoxActionPerformed

    private void religionTextoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_religionTextoKeyReleased
        
         if (Secuencias_cadenas.sonNumeros(evt.getKeyChar())) {

            Character caracterEtrada = evt.getKeyChar();
            String reeplazo = nombreEstudianteT.getText().replaceAll(caracterEtrada.toString(), "");
            nombreEstudianteT.setText(reeplazo);
        }
        
    }//GEN-LAST:event_religionTextoKeyReleased

    private void annadirReligionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annadirReligionActionPerformed
        
        if(religionTexto.getText().equals("")){
            JOptionPane.showMessageDialog(null, "El campo esta vacio");
        }
        
         String temp = religionTexto.getText();
        Vector<String> Similares = new Vector<>();
             for(int i = 0; i < religiones.size(); i++){
                 if(Secuencias_cadenas.LongestCommonSubsequence(temp, religiones.elementAt(i))>=75.00){
                     Similares.add(religiones.elementAt(i));
                 }
             }
             
             if(!Similares.isEmpty()){
             
             String[] S = new String[Similares.size()];
             Similares.copyInto(S);
             
                    String  x =(String) JOptionPane.showInputDialog(null, "Estas religiones son similares a lo escrito. Seleccione una de las opciones si se ha equivocado", "Sugerencia",JOptionPane.QUESTION_MESSAGE,null , S, S[0]);
            
                    if(x == null){
                        religiones.add(temp);
                        g.agregarReligion(temp);
                        religionComboBox.addItem(temp);
                        religionComboBox.setSelectedItem(temp);
                    }
                    else{
                        religionComboBox.setSelectedItem(x);
                    }
             }
             else{
                  religiones.add(temp);
                  g.agregarReligion(temp);
                  religionComboBox.addItem(temp);
                  religionComboBox.setSelectedItem(temp);
             }
             religionTexto.setVisible(false);
             annadirReligion.setVisible(false);
        
    }//GEN-LAST:event_annadirReligionActionPerformed

    private void manifestacionTextoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_manifestacionTextoKeyReleased
        
         if (Secuencias_cadenas.sonNumeros(evt.getKeyChar())) {

            Character caracterEtrada = evt.getKeyChar();
            String reeplazo = manifestacionTexto.getText().replaceAll(caracterEtrada.toString(), "");
            manifestacionTexto.setText(reeplazo);
        }
         
          String temp = manifestacionTexto.getText();
         if(temp.length()>=3){
             Vector<String> Similares = new Vector<>();
             for(int i = 0; i < manifestacionesArtisticas.size(); i++){
                 if(Secuencias_cadenas.mayor_subcadena(temp, manifestacionesArtisticas.elementAt(i))){
                     Similares.add(manifestacionesArtisticas.elementAt(i));
                 }
             }
             actualizarTablaManifestaciones(Similares);
         }
         else if(temp.length()<3){
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
         if(temp.length()>=3){
             Vector<String> Similares = new Vector<>();
             for(int i = 0; i < deportes.size(); i++){
                 if(Secuencias_cadenas.mayor_subcadena(temp, deportes.elementAt(i))){
                     Similares.add(deportes.elementAt(i));
                 }
             }
             actualizarTablaDeportes(Similares);
         }
         else if(temp.length()<3){
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
         if(temp.length()>=3){
             Vector<String> Similares = new Vector<>();
             for(int i = 0; i < enfermedades.size(); i++){
                 if(Secuencias_cadenas.mayor_subcadena(temp, enfermedades.elementAt(i))){
                     Similares.add(enfermedades.elementAt(i));
                 }
             }
             actualizarTablaEnfermedades(Similares);
         }
         else if(temp.length()<3){
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
         if(temp.length()>=3){
             Vector<String> Similares = new Vector<>();
             for(int i = 0; i < medicamentos.size(); i++){
                 if(Secuencias_cadenas.mayor_subcadena(temp, medicamentos.elementAt(i))){
                     Similares.add(medicamentos.elementAt(i));
                 }
             }
             actualizarTablaMedicamentos(Similares);
         }
         else if(temp.length()<3){
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
       
        notas = g.obtenerNotas(carr, E, AnnoComboBox.getSelectedIndex()+1);
        actualizarTablaNotas(notas);
        
    }//GEN-LAST:event_AnnoComboBoxPopupMenuWillBecomeInvisible

    private void editarNotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editarNotaMouseClicked
      
        String posiblesNotas[] = {"0", "2", "3", "4", "5"};
        String x =(String) JOptionPane.showInputDialog(null, "Seleccione la nota del estudiante", "Sugerencia",JOptionPane.QUESTION_MESSAGE,null , posiblesNotas , posiblesNotas[0]);
        
        if(x != null){
                 int nota = Integer.parseInt(x);
                 
                 String asignatura = (String)tablaNotas.getValueAt(tablaNotas.getSelectedRow(), 0);
                 
                 for(int i = 0; i < notas.size(); i++){
                     if(notas.elementAt(i).getNombreAsignatura().equals(asignatura)){
                         notas.elementAt(i).setNota(nota);
                         g.actualizarNota(notas.elementAt(i), E);
                         actualizarTablaNotas(notas);
                         break;
                     }
                 }
             }
    }//GEN-LAST:event_editarNotaMouseClicked

    private void finalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalizarActionPerformed
        if(nombreEstudianteT.getText().equals("") || carnetTexto.getText().equals("") || carreraT.getText().equals("") || edadT.getText().equals("") || direccionParticularT.equals("")){
            JOptionPane.showMessageDialog(null, "Hay campos obligatorios vacios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String sexo= "";
        int participacion=0;
        if(masculino.isSelected()){
            sexo = "masculino";
        }
        else{
            sexo ="femenino";
        }
        if(participacionBrigadaBien.isSelected()){
            participacion =1;
        }
        else if(participacionBrigadaRegular.isSelected()){
            participacion = 2;
        }
        else if (participaicoBrigadaMal.isSelected()){
            participacion=3;
        }
        int telefonoParticular=0;
        if(!telefonoParticularT.getText().equals("")){
            telefonoParticular = Integer.parseInt(telefonoParticularT.getText());
        }
        int telefonoFijo=0;
        if(!telefonoFijoT.getText().equals("")){
        telefonoFijo=Integer.parseInt(telefonoFijoT.getText());
        }
        String religion="";
        if(!religiones.isEmpty()){
        religion =religiones.elementAt(religionComboBox.getSelectedIndex());
        }
        int totalFamiliares=0;
        if(!totalFamiliaresT.getText().equals("")){
        totalFamiliares=Integer.parseInt(totalFamiliaresT.getText());
        }
        int ingresosTotales=0;
        if(!ingresoHogarT.getText().equals("")){
        ingresosTotales=Integer.parseInt(ingresoHogarT.getText());
        }
        boolean [] electronic = {computadora.isSelected(),laptop.isSelected(),movil.isSelected(),tablet.isSelected()};
        boolean [] familiares = {padre.isSelected(),madre.isSelected(),hermanas.isSelected(),hermanos.isSelected(),abueloP.isSelected(),abueloM.isSelected(),abuelaP.isSelected(),abuelaM.isSelected(),otrosFamiliares.isSelected()};
        DatosEstudiante e = new DatosEstudiante(E.getNombre_estudiante(), E.getCI(),telefonoParticular,telefonoFijo,movileDataBooton.isSelected(), emailT.getText(), sexo, Integer.parseInt(edadT.getText()), becadoBooton.isSelected(), colorDePielComboBox.getSelectedIndex()+1, militanteRadioB.isSelected(), estadoCivilComboBox.getSelectedIndex()+1, hijosBooton.isSelected(), direccionParticularT.getText(), zonaOpciones.getSelectedIndex()+1,religion, bebidasAl.isSelected(), fumadorBooton.isSelected(), participacion, manifestacionesArtisticas, familiares, totalFamiliares,ingresosTotales, relacionesConvivenciaComboBox.getSelectedIndex()+1, deportes,electronic, enfermedades, true, new Vector<>(), medicamentos, deseosFuturosT.getText(), actividadesTiempoLibreT.getText(), proyectosVidaT.getText(), rasgosHabitosT.getText(), felicidadOpcion.isSelected(),estudioOpcion.isSelected() , carreraOpcion.isSelected(), nivelDeInglesComboBox.getSelectedIndex()+1);
        if (flag) {
            g.actualizarEstudiante(e,b);
        }
        else{
        g.editar_estudiante(b, e);
        }
    }//GEN-LAST:event_finalizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Anno;
    private javax.swing.JComboBox<String> AnnoComboBox;
    private javax.swing.JTable TablaDeportes;
    private javax.swing.JTable TablaEnfermedades;
    private javax.swing.JTable TablaManifestaciones;
    private javax.swing.JTable TablaMedicamentos;
    private javax.swing.JCheckBox abuelaM;
    private javax.swing.JCheckBox abuelaP;
    private javax.swing.JCheckBox abueloM;
    private javax.swing.JCheckBox abueloP;
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

    private void actualizarTablaManifestaciones(Vector<String> manifestacionesArtisticas) {
        DefaultTableModel d = new DefaultTableModel();
         Object[] OBJ = new Object[2];
          d.addColumn("Nombre de la Manifestacion");
           d.addColumn("Seleccion");
          
        for(int i = 0; i < manifestacionesArtisticas.size(); i++){
            OBJ[0] = manifestacionesArtisticas.elementAt(i);
            radioButtonManifestaciones.add(new JRadioButton("", false));
            OBJ[1] = radioButtonManifestaciones.lastElement();
            d.addRow(OBJ);
        }
        
        
       TablaManifestaciones = new JTable(d);
       
       TablaManifestaciones.setFont(new Font("arial", Font.BOLD, 14));
       TablaManifestaciones.setRowHeight(30);
       TablaManifestaciones.setShowGrid(true);
       
       TablaManifestaciones.getColumn("Seleccion").setCellRenderer(
        new RadioButtonRenderer());
       TablaManifestaciones.getColumn("Seleccion").setCellEditor(
        new RadioButtonEditor(new JCheckBox()));
       jScrollPane1.setViewportView(TablaManifestaciones);
    }

    private void actualizarTablaDeportes(Vector<String> deportes) {

        DefaultTableModel d = new DefaultTableModel();
         Object[] OBJ = new Object[2];
          d.addColumn("Deporte");
           d.addColumn("Seleccion");
          
        for(int i = 0; i < deportes.size(); i++){
            OBJ[0] = deportes.elementAt(i);
            radioButtonDeportes.add(new JRadioButton("", false));
            OBJ[1] = radioButtonDeportes.lastElement();
            d.addRow(OBJ);
        }
        
        
       TablaDeportes = new JTable(d);
       
       TablaDeportes.setFont(new Font("arial", Font.BOLD, 14));
       TablaDeportes.setRowHeight(30);
       TablaDeportes.setShowGrid(true);
       
       TablaDeportes.getColumn("Seleccion").setCellRenderer(
        new RadioButtonRenderer());
       TablaDeportes.getColumn("Seleccion").setCellEditor(
        new RadioButtonEditor(new JCheckBox()));
       jScrollPane1.setViewportView(TablaDeportes);
        
    }

    private void actualizarTablaEnfermedades(Vector<String> enfermedades) {
        
        DefaultTableModel d = new DefaultTableModel();
         Object[] OBJ = new Object[2];
          d.addColumn("Enfermedad");
           d.addColumn("Seleccion");
          
        for(int i = 0; i < enfermedades.size(); i++){
            OBJ[0] = enfermedades.elementAt(i);
            radioButtonEnfermedades.add(new JRadioButton("", false));
            OBJ[1] = radioButtonEnfermedades.lastElement();
            d.addRow(OBJ);
        }
        
        
       TablaEnfermedades = new JTable(d);
       
       TablaEnfermedades.setFont(new Font("arial", Font.BOLD, 14));
       TablaEnfermedades.setRowHeight(30);
       TablaEnfermedades.setShowGrid(true);
       
       TablaEnfermedades.getColumn("Seleccion").setCellRenderer(
        new RadioButtonRenderer());
       TablaEnfermedades.getColumn("Seleccion").setCellEditor(
        new RadioButtonEditor(new JCheckBox()));
       jScrollPane1.setViewportView(TablaEnfermedades);  
    }

    private void actualizarTablaMedicamentos(Vector<String> medicamentos) {

         DefaultTableModel d = new DefaultTableModel();
         Object[] OBJ = new Object[2];
          d.addColumn("Medicamento");
           d.addColumn("Seleccion");
          
        for(int i = 0; i < medicamentos.size(); i++){
            OBJ[0] = medicamentos.elementAt(i);
            radioButtonMedicamentos.add(new JRadioButton("", false));
            OBJ[1] = radioButtonMedicamentos.lastElement();
            d.addRow(OBJ);
        }
        
        
       TablaMedicamentos = new JTable(d);
       
       TablaMedicamentos.setFont(new Font("arial", Font.BOLD, 14));
       TablaMedicamentos.setRowHeight(30);
       TablaMedicamentos.setShowGrid(true);
       
       TablaMedicamentos.getColumn("Seleccion").setCellRenderer(
        new RadioButtonRenderer());
       TablaMedicamentos.getColumn("Seleccion").setCellEditor(
        new RadioButtonEditor(new JCheckBox()));
       jScrollPane1.setViewportView(TablaMedicamentos);  

    }

    private void actualizarTablaNotas(Vector<Nota> notas) {
     
    DefaultTableModel d = new DefaultTableModel();
         Object[] OBJ = new Object[3];
          d.addColumn("Asignatura");
        
           d.addColumn("Nota");
           
           OBJ[0] = "Primer Semestre";
           d.addRow(OBJ);
          
        for(int i = 0; i < notas.size(); i++){
            if(g.esPrimerSemestre(notas.elementAt(i).getIdAsignatura())){
            OBJ[0] = notas.elementAt(i).getNombreAsignatura();
            OBJ[1] = notas.elementAt(i).getNota();
            d.addRow(OBJ);
            }
        }
        
            OBJ[0] = "Segundo Semestre";
            d.addRow(OBJ);
        
        for(int i = 0; i < notas.size(); i++){
            if(g.esSegundoSemestre(notas.elementAt(i).getIdAsignatura())){
            OBJ[0] = notas.elementAt(i).getNombreAsignatura();
            OBJ[1] = notas.elementAt(i).getNota();
            d.addRow(OBJ);
            }
        }
        
        
       tablaNotas = new JTable(d);
       
       
       
       tablaNotas.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
            int fila = tablaNotas.rowAtPoint(e.getPoint());
            
            
            if(fila > -1){
                String asig = (String)tablaNotas.getValueAt(fila, 0);
                if(!(asig.equals("Primer Semestre") || asig.equals("Segundo Semestre"))){
               
                    menuNotas.setLocation(e.getPoint());
                    menuNotas.setVisible(true);
                }
            }
        }
        });
       
       jScrollPane1.setViewportView(tablaNotas);
    }

}
