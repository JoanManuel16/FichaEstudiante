/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Visuales;

import Base_de_Datos.Gestion;
import clases.Brigada;
import clases.Estudiante;
import clases.Evento;
import clases.RadioButtonEditor;
import clases.RadioButtonRenderer;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
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
public class EventoEstudiante extends javax.swing.JFrame {

    /**
     * Creates new form EventoEstudiante
     */
    private Brigada brigada;
    private String evento;
    private Vector<JRadioButton>RadioButtonVector;
    private Gestion g;
    private Vector<Tupla<Estudiante, String>> estudiantesEvento;
    private Vector<Tupla<String, Integer>> logros;
    private Vector<JMenuItem> logrosPopMenu;
    private String fecha;
    private int dimension;
    public EventoEstudiante(Brigada b, String evento) {
        this.brigada=b;
        this.evento=evento;
        RadioButtonVector= new Vector<>();
        g = new Gestion();
        llenarCombBox(ComboBoxDimension);
        estudiantesEvento= new Vector<>();
        estudiantesEvento = new Vector<>();
        logros = new Vector<>();
        logrosPopMenu = new Vector<>();
        actualizar_tabla();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DialogSeleccionFecha = new javax.swing.JDialog();
        CalendarDialog = new com.toedter.calendar.JCalendar();
        ButtonAceptarDialog = new javax.swing.JButton();
        ButtonCancelarDialog = new javax.swing.JButton();
        ComboBoxDimension = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        TextFieldValorEvento = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        DialogLogroEventos = new javax.swing.JDialog();
        jLabel3 = new javax.swing.JLabel();
        TextFieldLogro = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TextFieldvalorDelLogro = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        PopupMenuLogros = new javax.swing.JPopupMenu();
        jDialog1 = new javax.swing.JDialog();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ComboBoxDimension1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableEstudiantes = new javax.swing.JTable();
        LabelInformacion = new javax.swing.JLabel();
        ButtonAceptarFrame = new javax.swing.JButton();
        ButtonCancelarFrame = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        ButtonAceptarDialog.setText("Aceptar");
        ButtonAceptarDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonAceptarDialogMouseClicked(evt);
            }
        });

        ButtonCancelarDialog.setText("Cancelar");
        ButtonCancelarDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonCancelarDialogActionPerformed(evt);
            }
        });

        ComboBoxDimension.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jLabel1.setText("Valor");

        TextFieldValorEvento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextFieldValorEventoKeyReleased(evt);
            }
        });

        jLabel2.setText("Dimension");

        javax.swing.GroupLayout DialogSeleccionFechaLayout = new javax.swing.GroupLayout(DialogSeleccionFecha.getContentPane());
        DialogSeleccionFecha.getContentPane().setLayout(DialogSeleccionFechaLayout);
        DialogSeleccionFechaLayout.setHorizontalGroup(
            DialogSeleccionFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogSeleccionFechaLayout.createSequentialGroup()
                .addGroup(DialogSeleccionFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(DialogSeleccionFechaLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(ButtonAceptarDialog)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ButtonCancelarDialog))
                    .addGroup(DialogSeleccionFechaLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(DialogSeleccionFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(DialogSeleccionFechaLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextFieldValorEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(106, 106, 106)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ComboBoxDimension, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(CalendarDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        DialogSeleccionFechaLayout.setVerticalGroup(
            DialogSeleccionFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogSeleccionFechaLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(CalendarDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DialogSeleccionFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ComboBoxDimension, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addGroup(DialogSeleccionFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TextFieldValorEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(DialogSeleccionFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonAceptarDialog)
                    .addComponent(ButtonCancelarDialog))
                .addGap(25, 25, 25))
        );

        jLabel3.setText("Logro del Evento");

        jLabel4.setText("Valor del Logro");

        jButton2.setText("Aceptar");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton3.setText("Cancelar");

        javax.swing.GroupLayout DialogLogroEventosLayout = new javax.swing.GroupLayout(DialogLogroEventos.getContentPane());
        DialogLogroEventos.getContentPane().setLayout(DialogLogroEventosLayout);
        DialogLogroEventosLayout.setHorizontalGroup(
            DialogLogroEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogLogroEventosLayout.createSequentialGroup()
                .addGroup(DialogLogroEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(DialogLogroEventosLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(DialogLogroEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DialogLogroEventosLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(TextFieldvalorDelLogro, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(DialogLogroEventosLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TextFieldLogro, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(DialogLogroEventosLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        DialogLogroEventosLayout.setVerticalGroup(
            DialogLogroEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogLogroEventosLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(DialogLogroEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TextFieldLogro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(DialogLogroEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TextFieldvalorDelLogro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(DialogLogroEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(18, 18, 18))
        );

        jButton4.setText("Aceptar");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        jButton5.setText("Cancelar");

        jLabel5.setText("Fecha del Evento");

        jLabel6.setText("Dimension");

        ComboBoxDimension1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112))
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addGap(22, 22, 22))
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(41, 41, 41)
                        .addComponent(ComboBoxDimension1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ComboBoxDimension1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(17, 17, 17))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TableEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TableEstudiantes);

        LabelInformacion.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        LabelInformacion.setText("Estudiantes que participaron en el evento");
        LabelInformacion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        ButtonAceptarFrame.setText("Aceptar");
        ButtonAceptarFrame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonAceptarFrameMouseClicked(evt);
            }
        });

        ButtonCancelarFrame.setText("Cancelar");

        jButton1.setText("Agregar Logros");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(LabelInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ButtonAceptarFrame)
                                .addGap(136, 136, 136)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ButtonCancelarFrame))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LabelInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonAceptarFrame)
                    .addComponent(ButtonCancelarFrame)
                    .addComponent(jButton1))
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonAceptarFrameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonAceptarFrameMouseClicked
        if(logros.isEmpty()){
        DialogSeleccionFecha.setVisible(true);
        }
        else{
            int cont=0;
            for (int i = 0; i < RadioButtonVector.size() ; i++) {
                if(RadioButtonVector.elementAt(i).isSelected()){
                cont++;
                }
            }
            if(cont==estudiantesEvento.size()){
                llenarCombBox(ComboBoxDimension1);
                jDialog1.setVisible(true);
                Evento e = new Evento(evento,dimension, fecha, logros);
                g.agregar_estudiantes_a_evento(estudiantesEvento, e);
            }
        }
    }//GEN-LAST:event_ButtonAceptarFrameMouseClicked

    private void ButtonCancelarDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCancelarDialogActionPerformed
        DialogSeleccionFecha.setVisible(false);
    }//GEN-LAST:event_ButtonCancelarDialogActionPerformed

    private void ButtonAceptarDialogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonAceptarDialogMouseClicked
        String fecha = CalendarDialog.getDate().toString();
       int dimension = ComboBoxDimension.getSelectedIndex()+1;
       Vector<Estudiante> estudiantesSeleccionados = new Vector<>();
        Vector<Estudiante> estudiantesBrigada = brigada.getEstudiantes();
        
        for (int i = 0; i < estudiantesBrigada.size(); i++) {
                if(RadioButtonVector.elementAt(i).isSelected()){
                    estudiantesSeleccionados.add(estudiantesBrigada.elementAt(i));
                }
        }
       g.agregarEventoEstudiantePorParticipacion(fecha, dimension, evento, Integer.parseInt(TextFieldValorEvento.getText()), estudiantesSeleccionados);
    }//GEN-LAST:event_ButtonAceptarDialogMouseClicked

    private void TextFieldValorEventoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextFieldValorEventoKeyReleased
        Secuencias_cadenas.borrarLetras(evt.getKeyChar(), TextFieldValorEvento);
    }//GEN-LAST:event_TextFieldValorEventoKeyReleased

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
    if(!(TextFieldLogro.getText().equals("")&& TextFieldvalorDelLogro.getText().equals(""))){
        logros.add(new Tupla<>(TextFieldLogro.getText(),Integer.parseInt(TextFieldvalorDelLogro.getText())));
    }
    else{
        JOptionPane.showMessageDialog(null, "hay campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        fecha=jCalendar1.getDate().toString();
        dimension=ComboBoxDimension1.getSelectedIndex()+1;
    }//GEN-LAST:event_jButton4MouseClicked

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonAceptarDialog;
    private javax.swing.JButton ButtonAceptarFrame;
    private javax.swing.JButton ButtonCancelarDialog;
    private javax.swing.JButton ButtonCancelarFrame;
    private com.toedter.calendar.JCalendar CalendarDialog;
    private javax.swing.JComboBox<String> ComboBoxDimension;
    private javax.swing.JComboBox<String> ComboBoxDimension1;
    private javax.swing.JDialog DialogLogroEventos;
    private javax.swing.JDialog DialogSeleccionFecha;
    private javax.swing.JLabel LabelInformacion;
    private javax.swing.JPopupMenu PopupMenuLogros;
    private javax.swing.JTable TableEstudiantes;
    private javax.swing.JTextField TextFieldLogro;
    private javax.swing.JTextField TextFieldValorEvento;
    private javax.swing.JTextField TextFieldvalorDelLogro;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

private void actualizar_tabla(){
    DefaultTableModel d = new DefaultTableModel();
         Object[] OBJ = new Object[3];
          d.addColumn("Nombre del Equipo");
          d.addColumn("CI");
           d.addColumn("Seleccion");
          Vector<Estudiante> esttudiantesBrigada= brigada.getEstudiantes();
        for(int i = 0; i < esttudiantesBrigada.size(); i++){
            OBJ[0] = esttudiantesBrigada.elementAt(i).getNombre_estudiante();
            OBJ[1] = esttudiantesBrigada.elementAt(i).getCI();
            RadioButtonVector.add(new JRadioButton("", false));
            OBJ[2] = RadioButtonVector.lastElement();
            d.addRow(OBJ);
        }
        
        
       TableEstudiantes = new JTable(d);
       
       TableEstudiantes.setFont(new Font("arial", Font.BOLD, 14));
       TableEstudiantes.setRowHeight(30);
       TableEstudiantes.setShowGrid(true);
       
       TableEstudiantes.getColumn("Seleccion").setCellRenderer(
        new RadioButtonRenderer());
       TableEstudiantes.getColumn("Seleccion").setCellEditor(
        new RadioButtonEditor(new JCheckBox()));
       
       TableEstudiantes.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
            int fila = TableEstudiantes.rowAtPoint(e.getPoint());
            int columna = TableEstudiantes.columnAtPoint(e.getPoint());
            
            if(fila > -1){
                
                String asig = (String)TableEstudiantes.getValueAt(fila, columna);
               if(!logros.isEmpty()){
               llenarPopMenu();
               }
            }
        }

       
        });
       
       jScrollPane1.setViewportView(TableEstudiantes);
    }

    private void llenarCombBox(JComboBox jc) {
       Vector<String> dimensiones= g.obtenerDimensiones();
        for (int i = 0; i < dimensiones.size(); i++) {
               jc.addItem(dimensiones.elementAt(i));
        }
    }
     private void llenarPopMenu() {
          for (int i = 0; i < logros.size(); i++) {
              String logro =logros.elementAt(i).getN1();
              JMenuItem jm = new JMenuItem(logro);
              jm.addMouseListener(new MouseAdapter() {
              @Override
            public void mouseClicked(MouseEvent e){
                int indiceLogro = PopupMenuLogros.getComponentIndex(PopupMenuLogros.getComponentAt(e.getPoint()));
               Estudiante a = brigada.getEstudiantes().elementAt(TableEstudiantes.getSelectedRow());
               estudiantesEvento.add(new Tupla<>(a,logros.elementAt(indiceLogro)+""));
        }
              });
              
             PopupMenuLogros.add(jm);
         }
        }
}
