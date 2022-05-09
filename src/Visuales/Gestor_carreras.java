/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Visuales;

import Base_de_Datos.Gestion;
import clases.Carrera;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import utiles.Secuencias_cadenas;
import static utiles.Secuencias_cadenas.sonNumeros;

/**
 *
 * @author joanmanuel
 */
public class Gestor_carreras extends javax.swing.JFrame {

    
    private Vector<String> carreras;
    private Gestion G = new Gestion();
    private boolean Main;
    
    public Gestor_carreras(boolean Main) {
        initComponents();
        carreras = G.obtener_carreras();
        actualizarTabla(carreras);
        this.Main = Main;
        if(Main){
            Finalizar.setVisible(false);
        }
        
        this.setLocationRelativeTo(null);
        this.setTitle("Gestor de carreras");
        
        
    }
    
    
    private void actualizarTabla(Vector<String> V){
        
        DefaultTableModel df= new DefaultTableModel(){
        @Override
             public boolean isCellEditable(int row, int column) {
                return false;         
             };
        };
            Tabla_carreras= new JTable(df);
            jScrollPane1.setViewportView(Tabla_carreras);
            df.addColumn("Nombre de la carrera");
            
            Object[] ob = new Object[1];
            for (int i = 0; i < V.size(); i++) {
            ob[0] = V.elementAt(i);
            
            df.addRow(ob);
        }
            
             Tabla_carreras.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
            int fila = Tabla_carreras.rowAtPoint(e.getPoint());
            int columna = Tabla_carreras.columnAtPoint(e.getPoint());
            
            if(fila > -1){
                
                String carrera = (String)Tabla_carreras.getValueAt(fila, columna);
               
                if(Habilitar.isSelected()){
                    
                    int b = JOptionPane.showConfirmDialog(null, "Desea editar esta carrera?");
                    
                    if(b == 0){
                    
                    Carrera Carr = G.obtener_carrera(carrera);
                    Editor_carrera CC = new Editor_carrera(Carr);
                    
                    CC.setVisible(true);
                    dispose();
                    }
                }
                
                if(!Habilitar.isSelected() && !Main){
                    int b = JOptionPane.showConfirmDialog(null, "Desea seleccionar esta carrera para la brigada?");
                    
                    if(b == 0){
                    
                    Editor_brigada CB = new Editor_brigada(carrera);
                    CB.setVisible(true);
                    dispose();
                    }
                }
               
            }
        }
        });
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Nombre_carrera = new javax.swing.JLabel();
        Texto_carrera = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_carreras = new javax.swing.JTable();
        Crear_carrera = new javax.swing.JButton();
        Habilitar = new javax.swing.JRadioButton();
        Finalizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        Nombre_carrera.setText("Carrera:");

        Texto_carrera.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Texto_carreraKeyReleased(evt);
            }
        });

        Tabla_carreras.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(Tabla_carreras);

        Crear_carrera.setText("Crear nueva carrera");
        Crear_carrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Crear_carreraActionPerformed(evt);
            }
        });

        Habilitar.setText("Habilitar Edicion");

        Finalizar.setText("Finalizar");
        Finalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FinalizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Habilitar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Finalizar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Nombre_carrera)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Texto_carrera, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Crear_carrera)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Texto_carrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Nombre_carrera)
                        .addComponent(Crear_carrera)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Finalizar)
                    .addComponent(Habilitar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Crear_carreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Crear_carreraActionPerformed
        
         if(Texto_carrera.getText().equals("")){
            JOptionPane.showMessageDialog(null, "No hay ningun nombre de carrera");
        return;
        }
        
        String temp = Texto_carrera.getText();
        Vector<String> Similares = new Vector<>();
             for(int i = 0; i < carreras.size(); i++){
                 if(Secuencias_cadenas.LongestCommonSubsequence(temp, carreras.elementAt(i))>=75.00){
                     Similares.add(carreras.elementAt(i));
                 }
             }
             
             if(!Similares.isEmpty()){
             String[] S = new String[Similares.size()];
             Similares.copyInto(S);
             
                    String  x =(String) JOptionPane.showInputDialog(null, "Estas carreras son similares a lo escrito. Seleccione una de las opciones si se ha equivocado", "Sugerencia",JOptionPane.QUESTION_MESSAGE,null , S, S[0]);
            
                    if(x == null){
                        
                    Editor_carrera CC = new Editor_carrera(temp);
                    CC.setVisible(true);
                    this.dispose();
                    }
                    
                    Vector<String> V = new Vector<String>();
                    V.add(x);
                    
                   actualizarTabla(V);
                    
             }
             else{
                  Editor_carrera CC = new Editor_carrera(temp);
                    CC.setVisible(true);
                    this.dispose();
             }
    }//GEN-LAST:event_Crear_carreraActionPerformed

    private void Texto_carreraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Texto_carreraKeyReleased
        
          if(sonNumeros(evt.getKeyChar())){
            Character caracterEtrada = evt.getKeyChar();
            String reeplazo = Texto_carrera.getText().replaceAll(caracterEtrada.toString(),"");
            Texto_carrera.setText(reeplazo);
        }
         
          String temp = Texto_carrera.getText();
         if(temp.length()>=3){
             Vector<String> Similares = new Vector<>();
             for(int i = 0; i < carreras.size(); i++){
                 if(Secuencias_cadenas.mayor_subcadena(temp, carreras.elementAt(i))){
                     Similares.add(carreras.elementAt(i));
                 }
             }
             actualizarTabla(Similares);
         }
         else if(temp.length()<3){
             actualizarTabla(carreras);
         }
        
    }//GEN-LAST:event_Texto_carreraKeyReleased

    private void FinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FinalizarActionPerformed
        dispose();
    }//GEN-LAST:event_FinalizarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        Main m = new Main();
        m.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_formWindowClosed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Crear_carrera;
    private javax.swing.JButton Finalizar;
    private javax.swing.JRadioButton Habilitar;
    private javax.swing.JLabel Nombre_carrera;
    private javax.swing.JTable Tabla_carreras;
    private javax.swing.JTextField Texto_carrera;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
