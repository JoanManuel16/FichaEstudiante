/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Visuales;

import Base_de_Datos.Gestion;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import utiles.Secuencias_cadenas;
import utiles.Tupla;
import clases.Carrera;
import javax.swing.JCheckBox;
import utiles.RadioButtonEditor;
import utiles.RadioButtonRenderer;
import static utiles.Secuencias_cadenas.sonNumeros;
import dialogs.AbstractFrame;
import dialogs.ConfirmDialog;
import dialogs.InputDialog;
import dialogs.MessageDialog;

/**
 *
 * @author joanmanuel
 */
public class Editor_carrera extends AbstractFrame {

    private String nombre_carrera;
    private Vector<Vector<Tupla<Integer, String>>> Asignaturas;
    private Vector<String> NombreAsig;
    private Base_de_Datos.Gestion G = new Gestion();
    private boolean edicion;
    private boolean Main;
    private Vector<JCheckBox> checkBox;
    private Vector<JCheckBox> chekBoxTablaXSemestre;
    private Vector<String> asignaturasSeleccionadas;
    private Vector<String> por_eliminar;

    private String tempGlobal;
    
    public Editor_carrera(String NC, boolean Main) {
        initComponents();
        por_eliminar= new Vector<>();
        nombre_carrera = NC;
        Asignaturas = new Vector<>();
        Asignaturas.add(new Vector<>());
       
        NombreAsig = G.obtener_asignaturas();

        CarreraNombre.setText(nombre_carrera);
        Annos.addItem("1");

        actualizarTablaAsig(NombreAsig);

        actualizarTablaSem(Annos.getSelectedIndex());

        buttonGroup1.add(PrimerSem);
        buttonGroup1.add(SegundoSem);
        PrimerSem.setSelected(true);
        edicion = false;
        this.Main = Main;
        AgregarAsig.setEnabled(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Editor de carrera: " + NC);
        asignaturasSeleccionadas = new Vector<>();
    }

    public Editor_carrera(Carrera Carr, boolean Main) {
        initComponents();
        por_eliminar= new Vector<>();
        nombre_carrera = Carr.getNombre();
        Asignaturas = Carr.getAsignaturas();
        NombreAsig = G.obtener_asignaturas();
        AgregarAsig.setVisible(false);
        for (int i = 0; i < Asignaturas.size(); i++) {
            Annos.addItem((i + 1) + "");
            for (int j = 0; j < Asignaturas.elementAt(i).size(); j++) {
                NombreAsig.remove(Asignaturas.elementAt(i).elementAt(j).getN2());
            }
        }

        CarreraNombre.setText(nombre_carrera);
        Finalizar.setText("Terminar edición de carrera");

        Annos.setSelectedIndex(0);
        actualizarTablaAsig(NombreAsig);
        this.Main = Main;

        actualizarTablaSem(Annos.getSelectedIndex());

        buttonGroup1.add(PrimerSem);
        buttonGroup1.add(SegundoSem);
        PrimerSem.setSelected(true);
        edicion = true;

        this.setLocationRelativeTo(null);
        this.setTitle("Editor de carrera: " + Carr.getNombre());
        asignaturasSeleccionadas = new Vector<>();
    }

    @Override
    public void confirmDialog_devolverValor(Object O, int seleccion) {
        if(seleccion == 1){
            confirmOption1(O);
        }
        else if(seleccion == 2){
            confirmOption2(O);
        }
    }
    
    private void confirmOption1(Object O){
        
        boolean opcion = (boolean)O;
            if (!opcion) {
                return;
            }

        Annos.setSelectedIndex(Annos.getItemCount() - 2);
        Annos.removeItemAt(Annos.getItemCount() - 1);
        Asignaturas.remove(Asignaturas.size() - 1);
        actualizarTablaSem(Annos.getItemCount() - 1);
        actualizarTablaAsig(NombreAsig);
        PrimerSem.setSelected(true);
        
    }
    
    private void confirmOption2(Object O){
        
            boolean x = (boolean)O;
        
            if (x) {
                NombreAsig.add(tempGlobal);
                G.agregar_asignatura(tempGlobal);
                Vector<String> V = new Vector<>();
                V.add(tempGlobal);
                actualizarTablaAsig(V);
            }
    }

    @Override
    public void inputDialog_devolverValor(Object O, Object valorInicial, int seleccion) {
              
            String x = (String)O;
            if (x.equals(valorInicial)) {
                NombreAsig.add(x);
                G.agregar_asignatura(x);
                Vector<String> V = new Vector<>();
                V.add(x);
                actualizarTablaAsig(V);
            } else {
                Vector<String> V = new Vector<>();
                V.add(x);
                actualizarTablaAsig(V);
            }
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        Menu_seleccion = new javax.swing.JPopupMenu();
        Cambiar_semestre = new javax.swing.JMenuItem();
        Eliminar = new javax.swing.JMenuItem();
        Carrera = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        seleccionAsig = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        AsigXSem = new javax.swing.JTable();
        AsignaturaL = new javax.swing.JLabel();
        AsignaturaNombre = new javax.swing.JTextField();
        AgregarAsig = new javax.swing.JButton();
        Finalizar = new javax.swing.JButton();
        PrimerSem = new javax.swing.JRadioButton();
        SegundoSem = new javax.swing.JRadioButton();
        Anno = new javax.swing.JLabel();
        Annos = new javax.swing.JComboBox<>();
        Annadir_anno = new javax.swing.JButton();
        Eliminar_anno = new javax.swing.JButton();
        CarreraNombre = new javax.swing.JTextField();
        Atras = new javax.swing.JButton();
        ButtonIzquierda = new javax.swing.JButton();
        ButtonDerecha = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        gestorBrigadas = new javax.swing.JMenu();
        gestorCarreras = new javax.swing.JMenuItem();
        brigadasExistentes = new javax.swing.JMenuItem();
        gestorEventos = new javax.swing.JMenuItem();
        regresarMain = new javax.swing.JMenu();

        Cambiar_semestre.setText("Cambiar asignatura de semestre");
        Cambiar_semestre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Cambiar_semestreMouseReleased(evt);
            }
        });
        Menu_seleccion.add(Cambiar_semestre);

        Eliminar.setText("Eliminar asignatura");
        Eliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                EliminarMouseReleased(evt);
            }
        });
        Menu_seleccion.add(Eliminar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        Carrera.setText("Nombre de la carrera:");

        seleccionAsig.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(seleccionAsig);

        AsigXSem.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(AsigXSem);

        AsignaturaL.setText("Buscar asignatura:");

        AsignaturaNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                AsignaturaNombreKeyReleased(evt);
            }
        });

        AgregarAsig.setText("Agregar asignatura");
        AgregarAsig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarAsigActionPerformed(evt);
            }
        });

        Finalizar.setText("Crear carrera");
        Finalizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                FinalizarMouseReleased(evt);
            }
        });
        Finalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FinalizarActionPerformed(evt);
            }
        });

        PrimerSem.setText("Primer Semestre");

        SegundoSem.setText("Segundo Semestre");

        Anno.setText("Año de la carrera");

        Annos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnnosActionPerformed(evt);
            }
        });

        Annadir_anno.setText("Añadir año a la carrera");
        Annadir_anno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Annadir_annoActionPerformed(evt);
            }
        });

        Eliminar_anno.setText("Eliminar último año de la carrera");
        Eliminar_anno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Eliminar_annoActionPerformed(evt);
            }
        });

        Atras.setText("Atrás");
        Atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AtrasActionPerformed(evt);
            }
        });

        ButtonIzquierda.setText("Izquierda");
        ButtonIzquierda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonIzquierdaActionPerformed(evt);
            }
        });

        ButtonDerecha.setText("Derecha");
        ButtonDerecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDerechaActionPerformed(evt);
            }
        });

        gestorBrigadas.setText("Gestores");

        gestorCarreras.setText("Gestor de Carreras");
        gestorCarreras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gestorCarrerasActionPerformed(evt);
            }
        });
        gestorBrigadas.add(gestorCarreras);

        brigadasExistentes.setText("Gestor de Brigadas");
        brigadasExistentes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                brigadasExistentesMouseReleased(evt);
            }
        });
        gestorBrigadas.add(brigadasExistentes);

        gestorEventos.setText("Gestor de Eventos");
        gestorEventos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gestorEventosActionPerformed(evt);
            }
        });
        gestorBrigadas.add(gestorEventos);

        jMenuBar1.add(gestorBrigadas);

        regresarMain.setText("Regresar a la Ventana Principal");
        regresarMain.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                regresarMainMenuSelected(evt);
            }
        });
        regresarMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarMainActionPerformed(evt);
            }
        });
        jMenuBar1.add(regresarMain);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(PrimerSem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(SegundoSem)
                                .addGap(313, 313, 313)
                                .addComponent(Finalizar)
                                .addGap(18, 18, 18)
                                .addComponent(Atras))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ButtonIzquierda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ButtonDerecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(Carrera, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(AsignaturaL)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(CarreraNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(Anno))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(AsignaturaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(AgregarAsig)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Annos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Eliminar_anno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Annadir_anno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Carrera)
                            .addComponent(CarreraNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Anno)
                            .addComponent(Annos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AsignaturaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AgregarAsig)
                            .addComponent(AsignaturaL)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Annadir_anno)
                        .addGap(18, 18, 18)
                        .addComponent(Eliminar_anno)))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SegundoSem)
                    .addComponent(PrimerSem)
                    .addComponent(Finalizar)
                    .addComponent(Atras))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(ButtonDerecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ButtonIzquierda)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Annadir_annoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Annadir_annoActionPerformed
        if (Annos.getItemCount() < 6) {
            int cantA = Annos.getItemCount() + 1;
            Annos.addItem(cantA + "");
            Asignaturas.add(new Vector<>());
        } else {
            MessageDialog messageDialog = new MessageDialog("No se pueden agregar más de 6 años a esta carrera", "Error", this);
            messageDialog.setVisible(true);
            this.setEnabled(false);
        }
    }//GEN-LAST:event_Annadir_annoActionPerformed

    private void Eliminar_annoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Eliminar_annoActionPerformed

        if (Annos.getItemCount() <= 1) {
            MessageDialog messageDialog = new MessageDialog("Una carrera debe tener al menos un año. No se puede eliminar el año 1", "Error", this);
            messageDialog.setVisible(true);
            this.setEnabled(false);
            
        } else {
            
            ConfirmDialog confirmDialog = new ConfirmDialog(1, "Si elimina este año de la carrera se perderán junto con él todas las asignaturas del año. ¿Desea proceder?", "", this);
            confirmDialog.setVisible(true);
            this.setEnabled(false);

        }
    }//GEN-LAST:event_Eliminar_annoActionPerformed

    private void AnnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnnosActionPerformed

        actualizarTablaSem(Annos.getSelectedIndex());
        PrimerSem.setSelected(true);
        actualizarTablaAsig(NombreAsig);

    }//GEN-LAST:event_AnnosActionPerformed

    private void AsignaturaNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AsignaturaNombreKeyReleased

        if (sonNumeros(evt.getKeyChar())) {
            Character caracterEtrada = evt.getKeyChar();
            String reeplazo = AsignaturaNombre.getText().replaceAll(caracterEtrada.toString(), "");
            AsignaturaNombre.setText(reeplazo);
        }

        String temp = AsignaturaNombre.getText();
        if (temp.length() >= 3) {
            AgregarAsig.setEnabled(true);
            Vector<String> Similares = new Vector<>();
            for (int i = 0; i < NombreAsig.size(); i++) {
                if (Secuencias_cadenas.mayor_subcadena(temp, NombreAsig.elementAt(i))) {
                    Similares.add(NombreAsig.elementAt(i));
                }
            }
            actualizarTablaAsig(Similares);
        } else if (temp.length() < 3) {
            actualizarTablaAsig(NombreAsig);
            AgregarAsig.setEnabled(false);
        }

    }//GEN-LAST:event_AsignaturaNombreKeyReleased

    private void AgregarAsigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarAsigActionPerformed

        if (AsignaturaNombre.getText().equals("")) {
            MessageDialog messageDialog = new MessageDialog("No hay escrito un nombre de asignatura en el campo de texto a la derecha de la etiqueta \"Nombre de la asignatura\". Escriba uno para proceder.", "Error", this);
            messageDialog.setVisible(true);
            this.setEnabled(false);
            
            return;
        }

        String temp = AsignaturaNombre.getText();
        Vector<String> Similares = new Vector<>();
        for (int i = 0; i < NombreAsig.size(); i++) {
            if (Secuencias_cadenas.LongestCommonSubsequence(temp, NombreAsig.elementAt(i)) >= 75.00) {
                Similares.add(NombreAsig.elementAt(i));
            }
        }

        if (!Similares.isEmpty()) {

            String[] S = new String[Similares.size()];
            Similares.copyInto(S);

            InputDialog inputDialog = new InputDialog(1, "Existen asignaturas con nombres similares al de la asignatura que ha escrito. Seleccione uno de ellos o pulse en cancelar si no se ha equivocado.", "Sugerencia", S, temp, this);
            inputDialog.setVisible(true);
            this.setEnabled(false);
  
        } else {
            
            tempGlobal = temp;
            ConfirmDialog confirmDialog = new ConfirmDialog(2, "Se agregará la asignatura a la base de datos. ¿Desea proceder?", "", this);
            confirmDialog.setVisible(true);
            this.setEnabled(false);
            
        }

        AsignaturaNombre.setText("");

    }//GEN-LAST:event_AgregarAsigActionPerformed

    private void Cambiar_semestreMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Cambiar_semestreMouseReleased

        if (evt.getButton() == MouseEvent.BUTTON1) {
            String S = (String) AsigXSem.getValueAt(AsigXSem.getSelectedRow(), AsigXSem.getSelectedColumn());

            int anno = Annos.getSelectedIndex();

            for (int i = 0; i < Asignaturas.elementAt(anno).size(); i++) {

                if (Asignaturas.elementAt(anno).elementAt(i).getN2().equals(S)) {
                    if (Asignaturas.elementAt(anno).elementAt(i).getN1() == 1) {
                        Asignaturas.elementAt(anno).elementAt(i).setN1(2);
                    } else {
                        Asignaturas.elementAt(anno).elementAt(i).setN1(1);
                    }
                    break;
                }
            }
            actualizarTablaSem(anno);

            Menu_seleccion.setVisible(false);

        }

    }//GEN-LAST:event_Cambiar_semestreMouseReleased

    private void EliminarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarMouseReleased

        if (evt.getButton() == MouseEvent.BUTTON1) {
            String S = (String) AsigXSem.getValueAt(AsigXSem.getSelectedRow(), AsigXSem.getSelectedColumn());

            int anno = Annos.getSelectedIndex();

            for (int i = 0; i < Asignaturas.elementAt(anno).size(); i++) {

                if (Asignaturas.elementAt(anno).elementAt(i).getN2().equals(S)) {
                    NombreAsig.add(S);
                    Asignaturas.elementAt(anno).remove(i);
                    break;
                }
            }
            actualizarTablaSem(anno);
            actualizarTablaAsig(NombreAsig);

            Menu_seleccion.setVisible(false);

        }

    }//GEN-LAST:event_EliminarMouseReleased

    private void FinalizarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FinalizarMouseReleased

        String s = "Se presentan los siguientes problemas: ";
        String s2 = "\n El año: ";
        String s3 = " no tiene asignaturas.";
        String s4 = "\n Los siguientes semestres del año ";
        String s5 = " no tienen asignaturas: ";
        for (int i = 0; i < Asignaturas.size(); i++) {
            if (Asignaturas.elementAt(i).isEmpty()) {
                s = s + s2 + (i + 1) + s3;
                continue;
            }
            boolean primerS = false;
            boolean segundoS = false;

            for (int j = 0; j < Asignaturas.elementAt(i).size(); j++) {
                if (Asignaturas.elementAt(i).elementAt(j).getN1() == 1) {
                    primerS = true;
                } else {
                    segundoS = true;
                }
            }
            if (!primerS || !segundoS) {
                s = s + s4 + (i + 1) + s5;
            }
            if (!primerS) {
                s = s + "\n 1";
            }
            if (!segundoS) {
                s = s + "\n 2";
            }
        }

        if (!s.equals("Se presentan los siguientes problemas: ")) {
            MessageDialog messageDialog = new MessageDialog(s, "", this);
            messageDialog.setVisible(true);
            this.setEnabled(false);
            return;
        }

        Carrera C = new Carrera(nombre_carrera, Asignaturas);

        if (edicion) {
            G.editar_carrera(C, CarreraNombre.getText());

            Gestor_carreras GC = new Gestor_carreras(false);
            GC.setVisible(true);
            this.dispose();
        } else {
            G.agregar_carrera(C, CarreraNombre.getText());
            Editor_brigada CB = new Editor_brigada(C.getNombre());
            CB.setVisible(true);
            this.dispose();
        }

    }//GEN-LAST:event_FinalizarMouseReleased

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        Menu_seleccion.setVisible(false);
    }//GEN-LAST:event_formMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing


    }//GEN-LAST:event_formWindowClosing

    private void gestorCarrerasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gestorCarrerasActionPerformed
        Gestor_carreras GC = new Gestor_carreras(true);
        GC.setVisible(true);
        dispose();
    }//GEN-LAST:event_gestorCarrerasActionPerformed

    private void brigadasExistentesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_brigadasExistentesMouseReleased
        Gestor_brigada gestorBrigada = new Gestor_brigada(1);
        gestorBrigada.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_brigadasExistentesMouseReleased

    private void gestorEventosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gestorEventosActionPerformed

        Gestor_eventos GE = new Gestor_eventos();
        GE.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_gestorEventosActionPerformed

    private void regresarMainMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_regresarMainMenuSelected

        Main M = new Main();
        M.setVisible(true);
        dispose();

    }//GEN-LAST:event_regresarMainMenuSelected

    private void regresarMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresarMainActionPerformed

        Main M = new Main();
        M.setVisible(true);
        dispose();

    }//GEN-LAST:event_regresarMainActionPerformed

    private void FinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FinalizarActionPerformed

    }//GEN-LAST:event_FinalizarActionPerformed

    private void AtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AtrasActionPerformed
        Gestor_carreras GC = new Gestor_carreras(false);
        GC.setVisible(true);
        dispose();
    }//GEN-LAST:event_AtrasActionPerformed

    private void ButtonDerechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDerechaActionPerformed

        int semestre;
        if (buttonGroup1.isSelected(PrimerSem.getModel())) {
            semestre = 1;
        } else {
            semestre = 2;
        }

        int anno = Integer.parseInt((String) Annos.getSelectedItem());

        for (int i = 0; i < asignaturasSeleccionadas.size(); i++) {

            Asignaturas.elementAt(anno - 1).add(new Tupla<>(semestre, asignaturasSeleccionadas.elementAt(i)));
            NombreAsig.remove(asignaturasSeleccionadas.elementAt(i));

        }
        asignaturasSeleccionadas.clear();
        actualizarTablaSem(anno - 1);
        actualizarTablaAsig(NombreAsig);
    }//GEN-LAST:event_ButtonDerechaActionPerformed

    private void ButtonIzquierdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonIzquierdaActionPerformed
        String S = "";

        int anno = Annos.getSelectedIndex();

        
            
               
        for (String s : por_eliminar) {
            for (int j = 0; j < Asignaturas.elementAt(anno).size(); j++) {

                        if (Asignaturas.elementAt(anno).elementAt(j).getN2().equals(s)) {
                            NombreAsig.add(s);
                            Asignaturas.elementAt(anno).remove(j);
          
                        }
                    }
        }
             
        actualizarTablaSem(anno);
        actualizarTablaAsig(NombreAsig);
        por_eliminar.clear();
    }//GEN-LAST:event_ButtonIzquierdaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AgregarAsig;
    private javax.swing.JButton Annadir_anno;
    private javax.swing.JLabel Anno;
    private javax.swing.JComboBox<String> Annos;
    private javax.swing.JTable AsigXSem;
    private javax.swing.JLabel AsignaturaL;
    private javax.swing.JTextField AsignaturaNombre;
    private javax.swing.JButton Atras;
    private javax.swing.JButton ButtonDerecha;
    private javax.swing.JButton ButtonIzquierda;
    private javax.swing.JMenuItem Cambiar_semestre;
    private javax.swing.JLabel Carrera;
    private javax.swing.JTextField CarreraNombre;
    private javax.swing.JMenuItem Eliminar;
    private javax.swing.JButton Eliminar_anno;
    private javax.swing.JButton Finalizar;
    private javax.swing.JPopupMenu Menu_seleccion;
    private javax.swing.JRadioButton PrimerSem;
    private javax.swing.JRadioButton SegundoSem;
    private javax.swing.JMenuItem brigadasExistentes;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JMenu gestorBrigadas;
    private javax.swing.JMenuItem gestorCarreras;
    private javax.swing.JMenuItem gestorEventos;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenu regresarMain;
    private javax.swing.JTable seleccionAsig;
    // End of variables declaration//GEN-END:variables

private void actualizarTablaAsig(Vector<String> V) {
        checkBox = new Vector<>();
        DefaultTableModel df = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1;
            }
        };
        seleccionAsig = new JTable(df);
        jScrollPane1.setViewportView(seleccionAsig);
        df.addColumn("Nombre de la asignatura");
        df.addColumn("Seleccion");

        Object[] ob = new Object[2];
        for (int i = 0; i < V.size(); i++) {
            ob[0] = V.elementAt(i);
            checkBox.add(new JCheckBox("", false));
            ob[1] = checkBox.lastElement();
            df.addRow(ob);
        }
        seleccionAsig.getColumn("Seleccion").setCellRenderer(
                new RadioButtonRenderer());
        seleccionAsig.getColumn("Seleccion").setCellEditor(
                new RadioButtonEditor(new JCheckBox()));

        seleccionAsig.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = seleccionAsig.rowAtPoint(e.getPoint());
                int columna = seleccionAsig.columnAtPoint(e.getPoint());

                if (columna == 1) {

                    String asig = (String) seleccionAsig.getValueAt(fila, 0);

                    if (asignaturasSeleccionadas.contains(asig)) {
                        asignaturasSeleccionadas.remove(asig);
                    } else {
                        asignaturasSeleccionadas.add(asig);

                    }

                }

            }
        });
    }

    public void actualizarTablaSem(int anno) {
        chekBoxTablaXSemestre= new Vector<>();
        DefaultTableModel df = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
        };
        AsigXSem = new JTable(df);
        jScrollPane2.setViewportView(AsigXSem);
        df.addColumn("Nombre de la asignatura");
        df.addColumn("Año");
        df.addColumn("Selecion");

        Vector<String> primerSemestre = new Vector<>();
        Vector<String> segundoSemestre = new Vector<>();

        for (int i = 0; i < Asignaturas.elementAt(anno).size(); i++) {
            if (Asignaturas.elementAt(anno).elementAt(i).getN1() == 1) {
                primerSemestre.add(Asignaturas.elementAt(anno).elementAt(i).getN2());
            } else {
                segundoSemestre.add(Asignaturas.elementAt(anno).elementAt(i).getN2());
            }
        }

        Object[] ob = new Object[3];

        ob[0] = "Primer Semestre";
        ob[1] = "";
        ob[2] = new JCheckBox("", false);
        df.addRow(ob);

        for (int i = 0; i < primerSemestre.size(); i++) {
            ob[0] = primerSemestre.elementAt(i);
            ob[1] = anno + 1;
            chekBoxTablaXSemestre.add(new JCheckBox("", false));
            ob[2] = chekBoxTablaXSemestre.lastElement();
            df.addRow(ob);
        }

        ob[0] = "Segundo Semestre";
        ob[1] = "";
        ob[2] = new JCheckBox("", false);
        df.addRow(ob);

        for (int i = 0; i < segundoSemestre.size(); i++) {
            ob[0] = segundoSemestre.elementAt(i);
            ob[1] = anno + 1;
            chekBoxTablaXSemestre.add(new JCheckBox("", false));
            ob[2] = chekBoxTablaXSemestre.lastElement();
            df.addRow(ob);
        }
        AsigXSem.getColumn("Selecion").setCellRenderer(
                new RadioButtonRenderer());
        AsigXSem.getColumn("Selecion").setCellEditor(
                new RadioButtonEditor(new JCheckBox()));

        AsigXSem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = AsigXSem.rowAtPoint(e.getPoint());

                if (fila > -1) {

                    String asig = (String) AsigXSem.getValueAt(fila, 0);
                    if (!(asig.equals("Primer Semestre") || asig.equals("Segundo Semestre"))&& fila==0) {

                        Menu_seleccion.setLocation(e.getXOnScreen(), e.getYOnScreen());
                        Menu_seleccion.setVisible(true);
                    }
                else{
                        if(por_eliminar.contains(asig)){
                            por_eliminar.remove(asig);
                        }
                else{
                            por_eliminar.add(asig);
                        }
                    }

                }
            }
        });
    }
}
