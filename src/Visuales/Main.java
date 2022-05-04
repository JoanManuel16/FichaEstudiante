/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Visuales;

/**
 *
 * @author joanmanuel
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        this.setTitle("Ventana principal");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        gestorBrigadas = new javax.swing.JMenu();
        brigadasExistentes = new javax.swing.JMenuItem();
        crearBrigada = new javax.swing.JMenuItem();
        eventos = new javax.swing.JMenu();
        gestorEventos = new javax.swing.JMenuItem();
        annadirEstudiantesEvento = new javax.swing.JMenuItem();
        notas = new javax.swing.JMenu();
        filtrarNotas = new javax.swing.JMenuItem();
        ICI = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Arial", 3, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Gestor de fichas de estudiantes");

        gestorBrigadas.setText("Gestor de Brigada");

        brigadasExistentes.setText("Brigadas Existentes");
        brigadasExistentes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                brigadasExistentesMouseReleased(evt);
            }
        });
        gestorBrigadas.add(brigadasExistentes);

        crearBrigada.setText("Crear Nueva Brigada");
        crearBrigada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearBrigadaActionPerformed(evt);
            }
        });
        gestorBrigadas.add(crearBrigada);

        jMenuBar1.add(gestorBrigadas);

        eventos.setText("Eventos");

        gestorEventos.setText("Gestor de eventos");
        gestorEventos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gestorEventosActionPerformed(evt);
            }
        });
        eventos.add(gestorEventos);

        annadirEstudiantesEvento.setText("Añadir Estudiantes a un evento");
        annadirEstudiantesEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annadirEstudiantesEventoActionPerformed(evt);
            }
        });
        eventos.add(annadirEstudiantesEvento);

        jMenuBar1.add(eventos);

        notas.setText("Notas");

        filtrarNotas.setText("Filtrar por Notas");
        notas.add(filtrarNotas);

        ICI.setText("ICI");
        ICI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ICIActionPerformed(evt);
            }
        });
        notas.add(ICI);

        jMenuBar1.add(notas);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(156, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void crearBrigadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearBrigadaActionPerformed
       
        Gestor_carreras GC = new Gestor_carreras(false);
        GC.setVisible(true);
        dispose();
        
    }//GEN-LAST:event_crearBrigadaActionPerformed

    private void gestorEventosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gestorEventosActionPerformed
       
        Gestor_Eventos GE = new Gestor_Eventos();
        GE.setVisible(true);
                this.dispose();
    }//GEN-LAST:event_gestorEventosActionPerformed

    private void brigadasExistentesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_brigadasExistentesMouseReleased
        Gestor_brigada gestorBrigada = new Gestor_brigada(1);
        gestorBrigada.setVisible(true);
        dispose();
    }//GEN-LAST:event_brigadasExistentesMouseReleased

    private void ICIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ICIActionPerformed
        
        Gestor_brigada GB = new Gestor_brigada(2);
        GB.setVisible(true);
        this.dispose();
        
        
    }//GEN-LAST:event_ICIActionPerformed

    private void annadirEstudiantesEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annadirEstudiantesEventoActionPerformed
        
        Gestor_brigada GB = new Gestor_brigada(3);
        GB.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_annadirEstudiantesEventoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ICI;
    private javax.swing.JMenuItem annadirEstudiantesEvento;
    private javax.swing.JMenuItem brigadasExistentes;
    private javax.swing.JMenuItem crearBrigada;
    private javax.swing.JMenu eventos;
    private javax.swing.JMenuItem filtrarNotas;
    private javax.swing.JMenu gestorBrigadas;
    private javax.swing.JMenuItem gestorEventos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu notas;
    // End of variables declaration//GEN-END:variables
}
