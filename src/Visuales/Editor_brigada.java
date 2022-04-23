/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Visuales;

import Base_de_Datos.Gestion;
import clases.Brigada;
import clases.DatosEstudiante;
import clases.Estudiante;
import clases.RadioButtonEditor;
import clases.RadioButtonRenderer;
import com.toedter.calendar.JYearChooser;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Stack;
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
public class Editor_brigada extends javax.swing.JFrame {

    private final Gestion G = new Gestion();
    private final Vector<Estudiante> estudiantes;
    private final Brigada B;
    private Vector<Tupla<Integer, String>> eventosBrigada;
    private Vector<Tupla<Integer, String>> eventos;
    private Vector<Tupla<Integer, String>> eventosEliminados = new Vector<>();
    private Vector<JRadioButton> radioButtonEventos = new Vector<>();
    private boolean actualizacion;

    public Editor_brigada(String Carr) {
        initComponents();

        B = null;
        actualizacion = false;
        estudiantes = new Vector<>();
        actualizarTabla(estudiantes);

        Carrera_seleccionada.setText(Carr);
        Anno_seleccionado.setText("1");

        JYearChooser YC = new JYearChooser();
        int Anno_actual = YC.getYear();
        for (int i = Anno_actual - 5; i <= Anno_actual + 5; i++) {
            Annos.addItem(i + "");
        }

        eventosBrigada = new Vector<>();
        eventos = G.obtenerEventos();

        Pasar_anno.setVisible(false);

        EditarEstudiante.setVisible(false);
    }

    public Editor_brigada(Brigada B) {
        initComponents();

        actualizacion = true;
        estudiantes = B.getEstudiantes();
        actualizarTabla(estudiantes);

        this.B = B;

        Carrera_seleccionada.setText(B.getCarrera());
        Anno_seleccionado.setText(B.getAnno_brigada() + "");

        for (int i = B.getAnno() - 5; i <= B.getAnno() + 5; i++) {
            Annos.addItem(i + "");
        }

        eventosBrigada = G.obtenerBrigadaEventos(B);
        eventos = G.obtenerEventos();

        Pasar_anno.setVisible(true);

        EditarEstudiante.setVisible(true);

        Annos.setEnabled(false);
        Annos.setSelectedItem(B.getAnno());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        agregar_estudiante = new javax.swing.JDialog();
        nombre = new javax.swing.JLabel();
        nombreT = new javax.swing.JTextField();
        CI = new javax.swing.JLabel();
        CIT = new javax.swing.JTextField();
        Aceptar = new javax.swing.JButton();
        MenuEstudiantes = new javax.swing.JPopupMenu();
        EditarEstudiante = new javax.swing.JMenuItem();
        EliminarEstudiante = new javax.swing.JMenuItem();
        agregarEvento = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaEventos = new javax.swing.JTable();
        aceptar = new javax.swing.JButton();
        Carrera_seleccionada = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaEst = new javax.swing.JTable();
        Anno_brigada = new javax.swing.JLabel();
        Finalizar = new javax.swing.JButton();
        Agregar_estudiante = new javax.swing.JButton();
        Carrera = new javax.swing.JLabel();
        Anno_seleccionado = new javax.swing.JLabel();
        Anno = new javax.swing.JLabel();
        Annos = new javax.swing.JComboBox<>();
        Pasar_anno = new javax.swing.JButton();
        agregarEventos = new javax.swing.JButton();

        nombre.setText("Nombre y Apellidos:");

        nombreT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nombreTKeyReleased(evt);
            }
        });

        CI.setText("Carnet de Identidad:");

        CIT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CITKeyReleased(evt);
            }
        });

        Aceptar.setText("Aceptar");
        Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout agregar_estudianteLayout = new javax.swing.GroupLayout(agregar_estudiante.getContentPane());
        agregar_estudiante.getContentPane().setLayout(agregar_estudianteLayout);
        agregar_estudianteLayout.setHorizontalGroup(
            agregar_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(agregar_estudianteLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(agregar_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(agregar_estudianteLayout.createSequentialGroup()
                        .addComponent(nombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nombreT, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(agregar_estudianteLayout.createSequentialGroup()
                        .addComponent(CI)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(agregar_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Aceptar)
                            .addComponent(CIT))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        agregar_estudianteLayout.setVerticalGroup(
            agregar_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(agregar_estudianteLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(agregar_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombre)
                    .addComponent(nombreT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(agregar_estudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CI)
                    .addComponent(CIT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(Aceptar)
                .addContainerGap())
        );

        EditarEstudiante.setText("Editar Estudiante");
        EditarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarEstudianteActionPerformed(evt);
            }
        });
        MenuEstudiantes.add(EditarEstudiante);

        EliminarEstudiante.setText("Eliminar Estudiante");
        EliminarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarEstudianteActionPerformed(evt);
            }
        });
        MenuEstudiantes.add(EliminarEstudiante);

        agregarEvento.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                agregarEventoWindowOpened(evt);
            }
        });

        tablaEventos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tablaEventos);

        aceptar.setText("Aceptar");
        aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout agregarEventoLayout = new javax.swing.GroupLayout(agregarEvento.getContentPane());
        agregarEvento.getContentPane().setLayout(agregarEventoLayout);
        agregarEventoLayout.setHorizontalGroup(
            agregarEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(agregarEventoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(agregarEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(aceptar)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        agregarEventoLayout.setVerticalGroup(
            agregarEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(agregarEventoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(aceptar)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TablaEst.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TablaEst);

        Anno_brigada.setText("Anno de brigada:");

        Finalizar.setText("Finalizar");
        Finalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FinalizarActionPerformed(evt);
            }
        });

        Agregar_estudiante.setText("Agregar Estudiante");
        Agregar_estudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Agregar_estudianteActionPerformed(evt);
            }
        });

        Carrera.setText("Carrera:");

        Anno.setText("Anno:");

        Pasar_anno.setText("Pasar de anno");
        Pasar_anno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pasar_annoActionPerformed(evt);
            }
        });

        agregarEventos.setText("Agregar Eventos");
        agregarEventos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarEventosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Carrera_seleccionada, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(Anno_brigada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(Anno_seleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Finalizar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(Carrera, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Anno)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Annos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Pasar_anno))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(agregarEventos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Agregar_estudiante, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Carrera, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Carrera_seleccionada, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Anno_seleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Anno_brigada))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Anno)
                            .addComponent(Annos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(Agregar_estudiante)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(agregarEventos)
                        .addGap(15, 15, 15)
                        .addComponent(Pasar_anno)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Finalizar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Agregar_estudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Agregar_estudianteActionPerformed

        agregar_estudiante.setVisible(true);
        agregar_estudiante.setSize(500, 300);
        agregar_estudiante.setLocationRelativeTo(null);

    }//GEN-LAST:event_Agregar_estudianteActionPerformed

    private void nombreTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreTKeyReleased
        if (Secuencias_cadenas.sonNumeros(evt.getKeyChar())) {

            Character caracterEtrada = evt.getKeyChar();
            String reeplazo = nombreT.getText().replaceAll(caracterEtrada.toString(), "");
            nombreT.setText(reeplazo);
        }
    }//GEN-LAST:event_nombreTKeyReleased

    private void CITKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CITKeyReleased

        Secuencias_cadenas.borrarLetras(evt.getKeyChar(), CIT);

    }//GEN-LAST:event_CITKeyReleased

    private void AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AceptarActionPerformed

        //acordarse de la verificacion del CI conchetumadre
        String[] nombres = nombreT.getText().split(" ");

        if (nombres.length < 3) {
            JOptionPane.showMessageDialog(null, "Este nombre no es viable. Se necesitan al menos dos apellidos.");
            return;
        }

        Estudiante E = new Estudiante(nombreT.getText(), CIT.getText());

        boolean flag = G.agregar_estudiante(E, Carrera_seleccionada.getText());
        if (flag) {

            estudiantes.add(E);
            actualizarTabla(estudiantes);
            agregar_estudiante.setVisible(false);
            CIT.setText("");
            nombreT.setText("");
        }
    }//GEN-LAST:event_AceptarActionPerformed

    private void EditarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarEstudianteActionPerformed
        if (G.existeDatosEstudiante(estudiantes.elementAt(TablaEst.getSelectedRow()).getCI())) {
            DatosEstudiante de = G.obtenerDatosEsttudiante(estudiantes.elementAt(TablaEst.getSelectedRow()));
            Editor_estudiante EE = new Editor_estudiante(estudiantes.elementAt(TablaEst.getSelectedRow()), Carrera_seleccionada.getText(), B, de);
        } else {
            Editor_estudiante EE = new Editor_estudiante(estudiantes.elementAt(TablaEst.getSelectedRow()), Carrera_seleccionada.getText(), B);
            EE.setVisible(true);
        }
    }//GEN-LAST:event_EditarEstudianteActionPerformed

    private void EliminarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarEstudianteActionPerformed

        Estudiante E = estudiantes.elementAt(TablaEst.getSelectedRow());
        estudiantes.remove(E);

        actualizarTabla(estudiantes);
        MenuEstudiantes.setVisible(false);

    }//GEN-LAST:event_EliminarEstudianteActionPerformed

    private void FinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FinalizarActionPerformed

        Brigada B = new Brigada(Carrera_seleccionada.getText(), this.B.getAnno(), this.B.getAnno_brigada(), estudiantes);
        if (!actualizacion) {
            G.agregar_brigada(B);
        } else {
            G.actualizarBrigada(B);
        }
        G.actualizarEventosBrigada(B, eventosBrigada, eventosEliminados);
        Main M = new Main();
        M.setVisible(true);
        dispose();

    }//GEN-LAST:event_FinalizarActionPerformed

    private void Pasar_annoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pasar_annoActionPerformed

        Brigada new_brigada = new Brigada(B.getCarrera(), B.getAnno() + 1, B.getAnno_brigada() + 1, B.getEstudiantes());
        G.agregar_brigada(new_brigada);
        Editor_brigada EB = new Editor_brigada(new_brigada);
        EB.setVisible(true);
        dispose();

    }//GEN-LAST:event_Pasar_annoActionPerformed

    private void agregarEventosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarEventosActionPerformed

        agregarEvento.setVisible(true);
        agregarEvento.setSize(800, 600);
        agregarEvento.setLocationRelativeTo(null);

        actualizarTablaEventos();

    }//GEN-LAST:event_agregarEventosActionPerformed

    private void aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarActionPerformed

        agregarEvento.setVisible(false);

    }//GEN-LAST:event_aceptarActionPerformed

    private void agregarEventoWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_agregarEventoWindowOpened

    }//GEN-LAST:event_agregarEventoWindowOpened

    private void actualizarTabla(Vector<Estudiante> V) {
        DefaultTableModel df = new DefaultTableModel();
        TablaEst = new JTable(df);
        jScrollPane1.setViewportView(TablaEst);
        df.addColumn("Nombre del estudiante");
        df.addColumn("Carnet de Identidad");

        Object[] ob = new Object[2];
        for (int i = 0; i < V.size(); i++) {
            ob[0] = (String) V.elementAt(i).getNombre_estudiante();
            ob[1] = (String) V.elementAt(i).getCI();

            df.addRow(ob);
        }

        TablaEst.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = TablaEst.rowAtPoint(e.getPoint());
                int columna = 0;

                if (fila > -1) {
                    MenuEstudiantes.setLocation(e.getLocationOnScreen());
                    MenuEstudiantes.setVisible(true);
                }
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Aceptar;
    private javax.swing.JButton Agregar_estudiante;
    private javax.swing.JLabel Anno;
    private javax.swing.JLabel Anno_brigada;
    private javax.swing.JLabel Anno_seleccionado;
    private javax.swing.JComboBox<String> Annos;
    private javax.swing.JLabel CI;
    private javax.swing.JTextField CIT;
    private javax.swing.JLabel Carrera;
    private javax.swing.JLabel Carrera_seleccionada;
    private javax.swing.JMenuItem EditarEstudiante;
    private javax.swing.JMenuItem EliminarEstudiante;
    private javax.swing.JButton Finalizar;
    private javax.swing.JPopupMenu MenuEstudiantes;
    private javax.swing.JButton Pasar_anno;
    private javax.swing.JTable TablaEst;
    private javax.swing.JButton aceptar;
    private javax.swing.JDialog agregarEvento;
    private javax.swing.JButton agregarEventos;
    private javax.swing.JDialog agregar_estudiante;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel nombre;
    private javax.swing.JTextField nombreT;
    private javax.swing.JTable tablaEventos;
    // End of variables declaration//GEN-END:variables

    private void actualizarTablaEventos() {

        DefaultTableModel d = new DefaultTableModel();
        Object[] OBJ = new Object[3];
        d.addColumn("Evento");
        d.addColumn("Año");
        d.addColumn("Seleccion");
        radioButtonEventos = new Vector<>();
        for (int i = 0; i < eventos.size(); i++) {
            OBJ[0] = G.obtenerNombreEvento(eventos.elementAt(i).getN1());
            radioButtonEventos.add(new JRadioButton("", false));
            OBJ[1] = eventos.elementAt(i).getN2();
            OBJ[2] = radioButtonEventos.lastElement();
            if (eventosBrigada.contains(eventos.elementAt(i))) {
                radioButtonEventos.lastElement().setSelected(true);
            }
            d.addRow(OBJ);
        }

        tablaEventos = new JTable(d);

        tablaEventos.setFont(new Font("arial", Font.BOLD, 14));
        tablaEventos.setRowHeight(30);
        tablaEventos.setShowGrid(true);

        tablaEventos.getColumn("Seleccion").setCellRenderer(
                new RadioButtonRenderer());
        tablaEventos.getColumn("Seleccion").setCellEditor(
                new RadioButtonEditor(new JCheckBox()));
        jScrollPane2.setViewportView(tablaEventos);

        tablaEventos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tablaEventos.rowAtPoint(e.getPoint());
                int columna = 3;

                if (fila > -1) {

                    if (radioButtonEventos.elementAt(fila).isSelected()) {
                        eventosBrigada.add(eventos.elementAt(fila));
                        if (eventosEliminados.contains(eventos.elementAt(fila))) {
                            eventosEliminados.remove(eventos.elementAt(fila));
                        }

                    } else {
                        eventosEliminados.add(eventos.elementAt(fila));
                        if (eventosBrigada.contains(eventos.elementAt(fila))) {
                            eventosBrigada.remove(eventos.elementAt(fila));
                        }
                    }

                }
            }
        });

    }
}
