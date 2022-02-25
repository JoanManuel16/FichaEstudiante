/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Visuales;

import Base_de_Datos.Gestion;
import java.util.Stack;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import utiles.Tupla;

/**
 *
 * @author joanmanuel
 */
public class Crear_carrera extends javax.swing.JFrame {

    
    private String nombre_carrera;
    private Vector<Vector<Tupla<Integer, String>>> Asignaturas;
    private Vector<String> NombreAsig;
    private Base_de_Datos.Gestion  G = new Gestion();
    
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
        
        
    }
    
    
    private void actualizarTablaAsig(Vector<String> V) {
            DefaultTableModel df= new DefaultTableModel();
            seleccionAsig= new JTable(df);
            jScrollPane2.setViewportView(seleccionAsig);
            df.addColumn("Nombre de la asignatura");
            
            Object[] ob = new Object[1];
            for (int i = 0; i < V.size(); i++) {
            ob[0] = V.elementAt(i);
            
            df.addRow(ob);
        }
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
            ob[1] = anno;
            
            df.addRow(ob);
        }
            
            ob[0] = "Segundo Semestre";
            ob[1] = "";
            df.addRow(ob);
            
            for (int i = 0; i < segundoSemestre.size(); i++) {
            ob[0] = segundoSemestre.elementAt(i);
            ob[1] = anno;
            
            df.addRow(ob);
        }
            
    }

    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
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
        getContentPane().add(AsignaturaNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 75, 180, -1));

        AgregarAsig.setText("Agregar Asignatura");
        getContentPane().add(AgregarAsig, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 135, -1, -1));

        Finalizar.setText("Finalizar");
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
        
        Annos.addItem((Integer.parseInt(Annos.getItemAt(Annos.getItemCount()))+1)+"");
        Annos.setSelectedIndex(Annos.getItemCount());
        Asignaturas.add(new Vector<>());
        actualizarTablaSem(Annos.getItemCount());
        PrimerSem.setSelected(true);

        
    }//GEN-LAST:event_Annadir_annoActionPerformed

    private void Eliminar_annoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Eliminar_annoActionPerformed
        
        if(Annos.getItemCount()<=1){
            JOptionPane.showMessageDialog(null, "Una carrera debe tener al menos un anno", "Error" , JOptionPane.ERROR_MESSAGE );
        return;
        }
        
        Annos.removeItemAt(Annos.getItemCount());
        Annos.setSelectedIndex(Annos.getItemCount());
        Asignaturas.remove(Asignaturas.size()-1);
        actualizarTablaSem(Annos.getItemCount());
        PrimerSem.setSelected(true);
        
    }//GEN-LAST:event_Eliminar_annoActionPerformed

    private void AnnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnnosActionPerformed
        
        actualizarTablaSem(Annos.getSelectedIndex());
        PrimerSem.setSelected(true);
        
    }//GEN-LAST:event_AnnosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AgregarAsig;
    private javax.swing.JButton Annadir_anno;
    private javax.swing.JLabel Anno;
    private javax.swing.JComboBox<String> Annos;
    private javax.swing.JTable AsigXSem;
    private javax.swing.JTextField AsignaturaNombre;
    private javax.swing.JLabel Carrera;
    private javax.swing.JLabel CarreraNombre;
    private javax.swing.JButton Eliminar_anno;
    private javax.swing.JButton Finalizar;
    private javax.swing.JRadioButton PrimerSem;
    private javax.swing.JRadioButton SegundoSem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable seleccionAsig;
    // End of variables declaration//GEN-END:variables
}
