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
import static utiles.Secuencias_cadenas.sonNumeros;

/**
 *
 * @author joanmanuel
 */
public class Crear_carrera extends javax.swing.JFrame {

    
    private String nombre_carrera;
    private Vector<Vector<Tupla<Integer, String>>> Asignaturas;
    private Vector<String> NombreAsig;
    private Base_de_Datos.Gestion  G = new Gestion();
    private boolean edicion;
    
    public Crear_carrera(String NC) {
        initComponents();
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
        
        
    }
    
    public Crear_carrera(Carrera Carr){
         initComponents();
        nombre_carrera = Carr.getNombre();
        Asignaturas = Carr.getAsignaturas();
        NombreAsig = G.obtener_asignaturas();
        
        for(int i = 0; i < Asignaturas.size(); i++){
            Annos.addItem((i+1)+"");
            for(int j = 0; j < Asignaturas.elementAt(i).size(); j++){
                NombreAsig.remove(Asignaturas.elementAt(i).elementAt(j).getN2());
            }
        }
        
        CarreraNombre.setText(nombre_carrera);        

        Annos.setSelectedIndex(0);
        actualizarTablaAsig(NombreAsig);
        
        actualizarTablaSem(Annos.getSelectedIndex());
        
      buttonGroup1.add(PrimerSem);
      buttonGroup1.add(SegundoSem);
      PrimerSem.setSelected(true);
      edicion = true;
    }
    
    
    private void actualizarTablaAsig(Vector<String> V) {
            DefaultTableModel df= new DefaultTableModel();
            seleccionAsig= new JTable(df);
            jScrollPane1.setViewportView(seleccionAsig);
            df.addColumn("Nombre de la asignatura");
            
            Object[] ob = new Object[1];
            for (int i = 0; i < V.size(); i++) {
            ob[0] = V.elementAt(i);
            
            df.addRow(ob);
        }
            
             seleccionAsig.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
            int fila = seleccionAsig.rowAtPoint(e.getPoint());
            int columna = seleccionAsig.columnAtPoint(e.getPoint());
            
            if(fila > -1){
                
                String asig = (String)seleccionAsig.getValueAt(fila, columna);
                
                int semestre;
                if(buttonGroup1.isSelected(PrimerSem.getModel())){
                    semestre = 1;
                }
                else{
                    semestre = 2;
                }
                
                int anno = Integer.parseInt((String)Annos.getSelectedItem());
                 
                int respuesta = JOptionPane.showConfirmDialog(null, "Desea agregar la asignatura al semestre " + semestre + " del anno " + anno + "?");
                
                if(respuesta == 0){
                    Asignaturas.elementAt(anno-1).add(new Tupla<>(semestre, asig));
                    NombreAsig.remove(asig);
                    
                    actualizarTablaSem(anno-1);
                    actualizarTablaAsig(NombreAsig);
                }
                
            }
        }
        });
    }
    
    public void actualizarTablaSem(int anno){
          DefaultTableModel df= new DefaultTableModel();
            AsigXSem = new JTable(df);
            jScrollPane2.setViewportView(AsigXSem);
            df.addColumn("Nombre de la asignatura");
            df.addColumn("Anno");
            
            Vector<String> primerSemestre = new Vector<>();
            Vector<String> segundoSemestre = new Vector<>();
            
            for (int i = 0; i < Asignaturas.elementAt(anno).size(); i++){
                if(Asignaturas.elementAt(anno).elementAt(i).getN1()==1){
                    primerSemestre.add(Asignaturas.elementAt(anno).elementAt(i).getN2());
                }
                else{
                    segundoSemestre.add(Asignaturas.elementAt(anno).elementAt(i).getN2());
                }
            } 
            
            Object[] ob = new Object[2];
            
            ob[0] = "Primer Semestre";
            ob[1] = "";
            df.addRow(ob);
            
            for (int i = 0; i < primerSemestre.size(); i++) {
            ob[0] = primerSemestre.elementAt(i);
            ob[1] = anno+1;
            
            df.addRow(ob);
        }
            
            ob[0] = "Segundo Semestre";
            ob[1] = "";
            df.addRow(ob);
            
            for (int i = 0; i < segundoSemestre.size(); i++) {
            ob[0] = segundoSemestre.elementAt(i);
            ob[1] = anno+1;
            
            df.addRow(ob);
        }
            
             AsigXSem.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
            int fila = AsigXSem.rowAtPoint(e.getPoint());
            int columna = AsigXSem.columnAtPoint(e.getPoint());
            
            if(fila > -1){
                
                String asig = (String)AsigXSem.getValueAt(fila, columna);
                if(!(asig.equals("Primer Semestre") || asig.equals("Segundo Semestre"))){
               
                    Menu_seleccion.setLocation(e.getPoint());
                    Menu_seleccion.setVisible(true);
                }
               
            }
        }
        });
            
    }

    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        Menu_seleccion = new javax.swing.JPopupMenu();
        Cambiar_semestre = new javax.swing.JMenuItem();
        Eliminar = new javax.swing.JMenuItem();
        Carrera = new javax.swing.JLabel();
        CarreraNombre = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        seleccionAsig = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        AsigXSem = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        AsignaturaNombre = new javax.swing.JTextField();
        AgregarAsig = new javax.swing.JButton();
        Finalizar = new javax.swing.JButton();
        PrimerSem = new javax.swing.JRadioButton();
        SegundoSem = new javax.swing.JRadioButton();
        Anno = new javax.swing.JLabel();
        Annos = new javax.swing.JComboBox<>();
        Annadir_anno = new javax.swing.JButton();
        Eliminar_anno = new javax.swing.JButton();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Carrera.setText("Carrera:");
        getContentPane().add(Carrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 18, 90, -1));
        getContentPane().add(CarreraNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 161, 18));

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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 168, 282, 321));

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

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(331, 168, 296, 321));

        jLabel3.setText("Asignatura:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 78, -1, -1));

        AsignaturaNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                AsignaturaNombreKeyReleased(evt);
            }
        });
        getContentPane().add(AsignaturaNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 75, 180, -1));

        AgregarAsig.setText("Agregar Asignatura");
        AgregarAsig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarAsigActionPerformed(evt);
            }
        });
        getContentPane().add(AgregarAsig, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 135, -1, -1));

        Finalizar.setText("Finalizar");
        Finalizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                FinalizarMouseReleased(evt);
            }
        });
        getContentPane().add(Finalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, -1, -1));

        PrimerSem.setText("Primer Semestre");
        getContentPane().add(PrimerSem, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, -1, -1));

        SegundoSem.setText("Segundo Semestre");
        getContentPane().add(SegundoSem, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 136, -1, -1));

        Anno.setText("Anno");
        getContentPane().add(Anno, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, -1, -1));

        Annos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnnosActionPerformed(evt);
            }
        });
        getContentPane().add(Annos, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, -1, -1));

        Annadir_anno.setText("Annadir Anno");
        Annadir_anno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Annadir_annoActionPerformed(evt);
            }
        });
        getContentPane().add(Annadir_anno, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, -1, -1));

        Eliminar_anno.setText("Eliminar Ultimo Anno");
        Eliminar_anno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Eliminar_annoActionPerformed(evt);
            }
        });
        getContentPane().add(Eliminar_anno, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Annadir_annoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Annadir_annoActionPerformed
        
        int cantA = Annos.getItemCount()+1;
            
        Annos.addItem(cantA+"");
        Asignaturas.add(new Vector<>());   
        
    }//GEN-LAST:event_Annadir_annoActionPerformed

    private void Eliminar_annoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Eliminar_annoActionPerformed
        
        if(Annos.getItemCount()<=1){
            JOptionPane.showMessageDialog(null, "Una carrera debe tener al menos un anno", "Error" , JOptionPane.ERROR_MESSAGE );
        return;
        }
        
        Annos.setSelectedIndex(Annos.getItemCount()-2);
        Annos.removeItemAt(Annos.getItemCount()-1);
        Asignaturas.remove(Asignaturas.size()-1);
        actualizarTablaSem(Annos.getItemCount()-1);
        actualizarTablaAsig(NombreAsig);
        PrimerSem.setSelected(true);
        
    }//GEN-LAST:event_Eliminar_annoActionPerformed

    private void AnnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnnosActionPerformed
        
        actualizarTablaSem(Annos.getSelectedIndex());
        PrimerSem.setSelected(true);
        actualizarTablaAsig(NombreAsig);
        
    }//GEN-LAST:event_AnnosActionPerformed

    private void AsignaturaNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AsignaturaNombreKeyReleased
        
         if(sonNumeros(evt.getKeyChar())){
            Character caracterEtrada = evt.getKeyChar();
            String reeplazo = AsignaturaNombre.getText().replaceAll(caracterEtrada.toString(),"");
         AsignaturaNombre.setText(reeplazo);
        }
         
          String temp = AsignaturaNombre.getText();
         if(temp.length()>=3){
             Vector<String> Similares = new Vector<>();
             for(int i = 0; i < NombreAsig.size(); i++){
                 if(Secuencias_cadenas.mayor_subcadena(temp, NombreAsig.elementAt(i))){
                     Similares.add(NombreAsig.elementAt(i));
                 }
             }
             actualizarTablaAsig(Similares);
         }
         else if(temp.length()<3){
             actualizarTablaAsig(NombreAsig);
         }
        
    }//GEN-LAST:event_AsignaturaNombreKeyReleased

    private void AgregarAsigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarAsigActionPerformed
        
        if(AsignaturaNombre.getText().equals("")){
            JOptionPane.showMessageDialog(null, "No hay ningun nombre de asignatura");
        return;
        }
        
        String temp = AsignaturaNombre.getText();
        Vector<String> Similares = new Vector<>();
             for(int i = 0; i < NombreAsig.size(); i++){
                 if(Secuencias_cadenas.LongestCommonSubsequence(temp, NombreAsig.elementAt(i))>=75.00){
                     Similares.add(NombreAsig.elementAt(i));
                 }
             }
             
             if(!Similares.isEmpty()){
             
             String[] S = new String[Similares.size()];
             Similares.copyInto(S);
             
                    String  x =(String) JOptionPane.showInputDialog(null, "Estas asignaturas son similares a lo escrito. Seleccione una de las opciones si se ha equivocado", "Sugerencia",JOptionPane.QUESTION_MESSAGE,null , S, S[0]);
            
                    if(x == null){
                        NombreAsig.add(temp);
                        G.agregar_asignatura(temp);
                        Vector<String> V = new Vector<>();
                        V.add(temp);
                        actualizarTablaAsig(V);
                    }
                    else{
                        Vector<String> V = new Vector<>();
                        V.add(x);
                        actualizarTablaAsig(V);
                    }
             }
             else{
                 NombreAsig.add(temp);
                        G.agregar_asignatura(temp);
                        Vector<String> V = new Vector<>();
                        V.add(temp);
                        actualizarTablaAsig(V);
             }
                    
    }//GEN-LAST:event_AgregarAsigActionPerformed

    private void Cambiar_semestreMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Cambiar_semestreMouseReleased
        
        if(evt.getButton() == MouseEvent.BUTTON1){
            String S = (String)AsigXSem.getValueAt(AsigXSem.getSelectedRow(), AsigXSem.getSelectedColumn());
            
            int anno = Annos.getSelectedIndex();
            
            for(int i = 0; i < Asignaturas.elementAt(anno).size(); i++){
                
                if(Asignaturas.elementAt(anno).elementAt(i).getN2().equals(S)){
                    if(Asignaturas.elementAt(anno).elementAt(i).getN1() == 1){
                       Asignaturas.elementAt(anno).elementAt(i).setN1(2);
                    }
                    else{
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
        
         if(evt.getButton() == MouseEvent.BUTTON1){
            String S = (String)AsigXSem.getValueAt(AsigXSem.getSelectedRow(), AsigXSem.getSelectedColumn());
            
            int anno = Annos.getSelectedIndex()+1;
            
            for(int i = 0; i < Asignaturas.elementAt(anno).size(); i++){
                
                if(Asignaturas.elementAt(anno).elementAt(i).getN2().equals(S)){
                    NombreAsig.add(S);
                    Asignaturas.elementAt(anno).remove(i);
                    break;
                }
            }
            actualizarTablaSem(anno);
            
            Menu_seleccion.setVisible(false);
            
        }
        
    }//GEN-LAST:event_EliminarMouseReleased

    private void FinalizarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FinalizarMouseReleased
        
        String s = "Se presentan los siguientes problemas: ";
        String s2 = "\n El ano: ";
        String s3 = "no tiene asignaturas.";
        String s4 = "\n Los siguientes semestres del anno ";
        String s5 = "no tienen asignaturas: ";
        for(int i = 0; i < Asignaturas.size(); i++){
            if(Asignaturas.elementAt(i).isEmpty()){
                s  = s + s2 + (i+1) + s3;
                continue;
            }
            boolean primerS = false;
            boolean segundoS = false;
            
            for(int j = 0; j < Asignaturas.elementAt(i).size(); j++){
                if(Asignaturas.elementAt(i).elementAt(j).getN1()==1){
                    primerS=true;
                }
                else{
                    segundoS=true;
                }
            }
            if(!primerS || !segundoS){
                s = s + s4 + (i+1) + s5;
            }
            if(!primerS){
                s = s + "\n 1";
            }
            if(!segundoS){
                s = s + "\n 2";
            }
        }
        
        if(!s.equals("Se presentan los siguientes problemas: ")){
            JOptionPane.showMessageDialog(null, s);
            return;
        }
        
        Carrera C = new Carrera(nombre_carrera, Asignaturas);
        
        
        if(edicion){
        G.editar_carrera(C);
        
        Gestor_carreras GC = new Gestor_carreras(false);
        GC.setVisible(true);
        this.dispose();
        }
        else{
        G.agregar_carrera(C);
            Crear_brigada CB = new Crear_brigada(C.getNombre());
            CB.setVisible(true);
            this.dispose();
        }
        
    }//GEN-LAST:event_FinalizarMouseReleased

    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AgregarAsig;
    private javax.swing.JButton Annadir_anno;
    private javax.swing.JLabel Anno;
    private javax.swing.JComboBox<String> Annos;
    private javax.swing.JTable AsigXSem;
    private javax.swing.JTextField AsignaturaNombre;
    private javax.swing.JMenuItem Cambiar_semestre;
    private javax.swing.JLabel Carrera;
    private javax.swing.JLabel CarreraNombre;
    private javax.swing.JMenuItem Eliminar;
    private javax.swing.JButton Eliminar_anno;
    private javax.swing.JButton Finalizar;
    private javax.swing.JPopupMenu Menu_seleccion;
    private javax.swing.JRadioButton PrimerSem;
    private javax.swing.JRadioButton SegundoSem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable seleccionAsig;
    // End of variables declaration//GEN-END:variables
}
