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
public class Editor_carrera extends javax.swing.JFrame {

    
    private String nombre_carrera;
    private Vector<Vector<Tupla<Integer, String>>> Asignaturas;
    private Vector<String> NombreAsig;
    private Base_de_Datos.Gestion  G = new Gestion();
    private boolean edicion;
    
    public Editor_carrera(String NC) {
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
        
        
      this.setLocationRelativeTo(null);
      this.setTitle("Editor de carrera: " + NC);
    }
    
    public Editor_carrera(Carrera Carr){
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
      
      this.setLocationRelativeTo(null);
      this.setTitle("Editor de carrera: " + Carr.getNombre());
    }
    
    
    private void actualizarTablaAsig(Vector<String> V) {
            DefaultTableModel df= new DefaultTableModel(){
             @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            };
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
                 
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea agregar la asignatura al semestre " + semestre + " del año " + anno + "?");
                
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
          DefaultTableModel df= new DefaultTableModel(){
           @Override
            public boolean isCellEditable(int row, int column) {
                return column==1;
            }
          };
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
            
            if(fila > -1){
                
                String asig = (String)AsigXSem.getValueAt(fila, 0);
                if(!(asig.equals("Primer Semestre") || asig.equals("Segundo Semestre"))){
               
                    Menu_seleccion.setLocation(e.getXOnScreen(), e.getYOnScreen());
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

        Carrera.setText("Carrera:");

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

        AsignaturaL.setText("Asignatura:");

        AsignaturaNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                AsignaturaNombreKeyReleased(evt);
            }
        });

        AgregarAsig.setText("Agregar Asignatura");
        AgregarAsig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarAsigActionPerformed(evt);
            }
        });

        Finalizar.setText("Finalizar");
        Finalizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                FinalizarMouseReleased(evt);
            }
        });

        PrimerSem.setText("Primer Semestre");

        SegundoSem.setText("Segundo Semestre");

        Anno.setText("Año");

        Annos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnnosActionPerformed(evt);
            }
        });

        Annadir_anno.setText("Añadir Año");
        Annadir_anno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Annadir_annoActionPerformed(evt);
            }
        });

        Eliminar_anno.setText("Eliminar último año");
        Eliminar_anno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Eliminar_annoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Carrera, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(CarreraNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(99, 99, 99)
                .addComponent(Anno)
                .addGap(34, 34, 34)
                .addComponent(Annos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addComponent(Annadir_anno))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(AsignaturaL)
                .addGap(28, 28, 28)
                .addComponent(AsignaturaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(211, 211, 211)
                .addComponent(Eliminar_anno))
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(AgregarAsig)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PrimerSem)
                    .addComponent(SegundoSem))
                .addGap(226, 226, 226)
                .addComponent(Finalizar))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Carrera)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CarreraNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Anno)
                            .addComponent(Annos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Annadir_anno))))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(AsignaturaL))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(AsignaturaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Eliminar_anno))
                .addGap(17, 17, 17)
                .addComponent(PrimerSem)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(AgregarAsig)
                        .addComponent(SegundoSem))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(Finalizar)))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Annadir_annoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Annadir_annoActionPerformed
        
        int cantA = Annos.getItemCount()+1;
            
        Annos.addItem(cantA+"");
        Asignaturas.add(new Vector<>());   
        
    }//GEN-LAST:event_Annadir_annoActionPerformed

    private void Eliminar_annoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Eliminar_annoActionPerformed
        
        if(Annos.getItemCount()<=1){
            JOptionPane.showMessageDialog(null, "Una carrera debe tener al menos un año", "Error" , JOptionPane.ERROR_MESSAGE );
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
            JOptionPane.showMessageDialog(null, "No hay ningún nombre de asignatura");
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
             
             AsignaturaNombre.setText("");
                    
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
            
            int anno = Annos.getSelectedIndex();
            
            for(int i = 0; i < Asignaturas.elementAt(anno).size(); i++){
                
                if(Asignaturas.elementAt(anno).elementAt(i).getN2().equals(S)){
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
        String s3 = "no tiene asignaturas.";
        String s4 = "\n Los siguientes semestres del año ";
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
            Editor_brigada CB = new Editor_brigada(C.getNombre());
            CB.setVisible(true);
            this.dispose();
        }
        
    }//GEN-LAST:event_FinalizarMouseReleased

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        Menu_seleccion.setVisible(false);
    }//GEN-LAST:event_formMouseClicked

    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AgregarAsig;
    private javax.swing.JButton Annadir_anno;
    private javax.swing.JLabel Anno;
    private javax.swing.JComboBox<String> Annos;
    private javax.swing.JTable AsigXSem;
    private javax.swing.JLabel AsignaturaL;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable seleccionAsig;
    // End of variables declaration//GEN-END:variables
}
