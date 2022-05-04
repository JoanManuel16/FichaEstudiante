/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Visuales;

import Base_de_Datos.Gestion;
import clases.Brigada;
import clases.Estudiante;
import clases.Evento;
import utiles.RadioButtonEditor;
import utiles.RadioButtonRenderer;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
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
    private Vector<JRadioButton>RadioButtonVector;
    private Gestion g;
    private Vector<Tupla<Estudiante, String>> estudiantesEvento;
    private Vector<Tupla<String, Integer>> logros;
    private Vector<JMenuItem> logrosPopMenu;
    
    private Vector<Evento> eventosBrigada;
    
    private Evento eventoSeleccionado;
    
    
    public EventoEstudiante(Brigada b) {
       initComponents();
       
       this.setLocationRelativeTo(null);
       this.setTitle("Gestor de participacion en eventos");
        
        this.brigada=b;
        RadioButtonVector= new Vector<>();
        g = new Gestion();
        logrosPopMenu = new Vector<>();
        
        estudiantesEvento= new Vector<>();
        
        this.eventosBrigada = g.obtenerEventosBrigada(brigada);
        eventoSeleccionado = null;
        
        JOptionPane.showMessageDialog(null, "Escoja un evento");

        escogerEvento.setVisible(true);
        escogerEvento.setSize(800, 600);
        escogerEvento.setLocationRelativeTo(null);
        
        actualizarTablaEventos();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PopupMenuLogros = new javax.swing.JPopupMenu();
        escogerEvento = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaEventos = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableEstudiantes = new javax.swing.JTable();
        LabelInformacion = new javax.swing.JLabel();
        ButtonAceptarFrame = new javax.swing.JButton();
        eventoActual = new javax.swing.JLabel();
        eventoActualInfo = new javax.swing.JLabel();
        seleccionarEvento = new javax.swing.JButton();

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

        javax.swing.GroupLayout escogerEventoLayout = new javax.swing.GroupLayout(escogerEvento.getContentPane());
        escogerEvento.getContentPane().setLayout(escogerEventoLayout);
        escogerEventoLayout.setHorizontalGroup(
            escogerEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(escogerEventoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        escogerEventoLayout.setVerticalGroup(
            escogerEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(escogerEventoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(0, Short.MAX_VALUE))
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

        eventoActual.setText("Evento:");

        seleccionarEvento.setText("Seleccionar Evento");
        seleccionarEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarEventoActionPerformed(evt);
            }
        });

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
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seleccionarEvento)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(eventoActual)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eventoActualInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(252, 252, 252)
                        .addComponent(ButtonAceptarFrame)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LabelInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(eventoActual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(eventoActualInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(seleccionarEvento)
                .addGap(47, 47, 47)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ButtonAceptarFrame)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonAceptarFrameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonAceptarFrameMouseClicked
        
        Main M = new Main();
        M.setVisible(true);
        dispose();
        
    }//GEN-LAST:event_ButtonAceptarFrameMouseClicked

    private void seleccionarEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarEventoActionPerformed
                
        escogerEvento.setVisible(true);
        escogerEvento.setSize(800, 600);
        escogerEvento.setLocationRelativeTo(null);
                        
    }//GEN-LAST:event_seleccionarEventoActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonAceptarFrame;
    private javax.swing.JLabel LabelInformacion;
    private javax.swing.JPopupMenu PopupMenuLogros;
    private javax.swing.JTable TableEstudiantes;
    private javax.swing.JDialog escogerEvento;
    private javax.swing.JLabel eventoActual;
    private javax.swing.JLabel eventoActualInfo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton seleccionarEvento;
    private javax.swing.JTable tablaEventos;
    // End of variables declaration//GEN-END:variables

private void actualizar_tabla(Evento E){
    DefaultTableModel d = new DefaultTableModel();
         Object[] OBJ = new Object[4];
          d.addColumn("Estudiante");
          d.addColumn("CI");
          d.addColumn("Logro");
          d.addColumn("Seleccion");
          
          Vector<Estudiante> estudiantesBrigada = brigada.getEstudiantes();
          RadioButtonVector = new Vector<>();
          
          for(int i = 0; i < estudiantesBrigada.size(); i++){
            OBJ[0] = estudiantesBrigada.elementAt(i).getNombre_estudiante();
            OBJ[1] = estudiantesBrigada.elementAt(i).getCI();
            RadioButtonVector.add(new JRadioButton("", false));
            OBJ[3] = RadioButtonVector.lastElement();
            if(g.existeEstudianteEvento(E, estudiantesBrigada.elementAt(i))){
                RadioButtonVector.lastElement().setSelected(true);
                OBJ[2] = g.obtenerLogroEstudiante(E, estudiantesBrigada.elementAt(i));
            }
            else{
                OBJ[2] = "No participa";
            }
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
            
            if(fila > -1){
                
                if(!RadioButtonVector.elementAt(fila).isSelected()){
                    
                    g.eliminarEstudianteEvento(brigada.getEstudiantes().elementAt(fila), eventoSeleccionado);
                    actualizar_tabla(eventoSeleccionado);
                }
                else{
               if(!logros.isEmpty()){
               llenarPopMenu();
               PopupMenuLogros.setVisible(true);
               PopupMenuLogros.setLocation(e.getLocationOnScreen());
               }
               else{
                   JOptionPane.showMessageDialog(null, "Este evento aun no tiene logros");
                   RadioButtonVector.elementAt(fila).setSelected(false);
               }
            }
            }
        }

       
        });
       
       jScrollPane1.setViewportView(TableEstudiantes);
    }

    
     private void llenarPopMenu() {
         PopupMenuLogros.removeAll();
          for (int i = 0; i < logros.size(); i++) {
              String logro = logros.elementAt(i).getN1();
              JMenuItem jm = new JMenuItem(logro);
              jm.addMouseListener(new MouseAdapter() {
              @Override
            public void mouseClicked(MouseEvent e){
                
               int indiceLogro = PopupMenuLogros.getComponentIndex(PopupMenuLogros.getComponentAt(e.getPoint()));
               for(int i = 0; i < PopupMenuLogros.getSubElements().length; i++){
                   int tamano = PopupMenuLogros.getSubElements()[i].getComponent().getLocationOnScreen().y + PopupMenuLogros.getSubElements()[i].getComponent().getHeight();
                   int inicio = PopupMenuLogros.getSubElements()[i].getComponent().getLocationOnScreen().y;
                   if(e.getYOnScreen() <= tamano && e.getYOnScreen()>= inicio){
                       indiceLogro = i;
                   }
               }
               
               Estudiante a = brigada.getEstudiantes().elementAt(TableEstudiantes.getSelectedRow());
               
               PopupMenuLogros.setVisible(false);
               if(indiceLogro < 0){
                   JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
                   return;
               }
               g.agregar_evento_a_estudiante(eventoSeleccionado, a, logros.elementAt(indiceLogro));
               actualizar_tabla(eventoSeleccionado);
            }
              });
              
             PopupMenuLogros.add(jm);
         }
        }
     
      private void actualizarTablaEventos() {

         DefaultTableModel d = new DefaultTableModel();
         Object[] OBJ = new Object[3];
          d.addColumn("Evento");
          d.addColumn("Dimension");
          d.addColumn("Anno");
          
        for(int i = 0; i < eventosBrigada.size(); i++){
            OBJ[0] = eventosBrigada.elementAt(i).getNombre();
            OBJ[1] = eventosBrigada.elementAt(i).getDimension();
            OBJ[2] = eventosBrigada.elementAt(i).getAnno();
            d.addRow(OBJ);
        }

        tablaEventos = new JTable(d);
       
        tablaEventos.setFont(new Font("arial", Font.BOLD, 14));
        tablaEventos.setRowHeight(30);
        tablaEventos.setShowGrid(true);
        
        jScrollPane2.setViewportView(tablaEventos);
        
        tablaEventos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tablaEventos.rowAtPoint(e.getPoint());

                if (fila > -1) {
                    eventoSeleccionado = eventosBrigada.elementAt(fila);
                    
                    eventoActualInfo.setText(eventoSeleccionado.getNombre() + " / " + eventoSeleccionado.getDimension()  + " / " + eventoSeleccionado.getAnno());
                    actualizar_tabla(eventoSeleccionado);
                    logros = g.obtenerLogrosEvento(eventoSeleccionado);
                    
                    escogerEvento.dispose();
                }
            }
        });

    }
}
