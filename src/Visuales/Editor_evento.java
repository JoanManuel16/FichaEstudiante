/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Visuales;

import Base_de_Datos.Gestion;
import java.util.Vector;
import javax.swing.JOptionPane;
import static utiles.Secuencias_cadenas.sonNumeros;

/**
 *
 * @author joanmanuel
 */
public class Editor_evento extends javax.swing.JFrame {

    /**
     * Creates new form Crear_evento
     */
    private Vector<String>dimensiones;
    private Gestion g;
    public Editor_evento() {
        initComponents();
        g= new Gestion();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Nombre_evento = new javax.swing.JLabel();
        TextNombreEvento = new javax.swing.JTextField();
        ButtonAceptar = new javax.swing.JButton();
        ButtonCAncelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        Nombre_evento.setText("Nombre del Evento");

        TextNombreEvento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextNombreEventoKeyReleased(evt);
            }
        });

        ButtonAceptar.setText("Aceptar");
        ButtonAceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonAceptarMouseClicked(evt);
            }
        });

        ButtonCAncelar.setText("Cancelar");
        ButtonCAncelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonCAncelarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Nombre_evento)
                    .addComponent(ButtonAceptar))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TextNombreEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonCAncelar))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Nombre_evento, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextNombreEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonAceptar)
                    .addComponent(ButtonCAncelar))
                .addGap(46, 46, 46))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TextNombreEventoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextNombreEventoKeyReleased
          if(sonNumeros(evt.getKeyChar())){
            Character caracterEtrada = evt.getKeyChar();
            String reeplazo = TextNombreEvento.getText().replaceAll(caracterEtrada.toString(),"");
            TextNombreEvento.setText(reeplazo);
        }
    }//GEN-LAST:event_TextNombreEventoKeyReleased

    private void ButtonAceptarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonAceptarMouseClicked
        if(TextNombreEvento.getText().equals("")){
            JOptionPane.showMessageDialog(null, "El nombre del evento esta vacio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        g.agregar_nombre_evento(TextNombreEvento.getText());
        TextNombreEvento.setText("");
    }//GEN-LAST:event_ButtonAceptarMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        Main m = new Main();
        m.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void ButtonCAncelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonCAncelarMouseClicked
        this.dispose();
    }//GEN-LAST:event_ButtonCAncelarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonAceptar;
    private javax.swing.JButton ButtonCAncelar;
    private javax.swing.JLabel Nombre_evento;
    private javax.swing.JTextField TextNombreEvento;
    // End of variables declaration//GEN-END:variables
}