/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visuales;

import Base_de_Datos.Gestion;
import clases.Brigada;
import clases.Estudiante;
import clases.GenerarReporteICI;
import com.itextpdf.text.DocumentException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import utiles.Secuencias_cadenas;

public class ICI extends javax.swing.JFrame {

    Brigada brigada;
    Gestion g = new Gestion();

    public ICI(Brigada brigada) {
        initComponents();
        setTitle("Gestor del ICI de la brigada seleccionada");
        setResizable(false);
        setLocationRelativeTo(null);
        this.brigada = brigada;
        ButtonReporte.setVisible(false);
        int m = g.obtenerSumaValoresEventos(brigada);

        actividadesExtraL.setText(m + "");

        actualizarTablaICI();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaICI = new javax.swing.JTable();
        actividadesExtra = new javax.swing.JLabel();
        PA = new javax.swing.JLabel();
        PAT = new javax.swing.JTextField();
        aceptar = new javax.swing.JButton();
        actividadesExtraL = new javax.swing.JLabel();
        relacion = new javax.swing.JLabel();
        relacionL = new javax.swing.JLabel();
        ButtonReporte = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        tablaICI.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaICI);

        actividadesExtra.setText("Valor de las actividades extracurriculares");

        PA.setText("PA");

        PAT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PATKeyReleased(evt);
            }
        });

        aceptar.setText("Aceptar");
        aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarActionPerformed(evt);
            }
        });

        relacion.setText("Relacion academico-extracurricular");

        ButtonReporte.setText("Reporte");
        ButtonReporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonReporteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(actividadesExtra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(actividadesExtraL, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(relacion)
                        .addGap(18, 18, 18)
                        .addComponent(relacionL, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(aceptar)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(PA)
                                .addGap(18, 18, 18)
                                .addComponent(PAT, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ButtonReporte))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(actividadesExtra)
                        .addComponent(relacion)
                        .addComponent(relacionL, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(actividadesExtraL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PA)
                            .addComponent(PAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addComponent(aceptar)
                        .addGap(18, 18, 18)
                        .addComponent(ButtonReporte))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PATKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PATKeyReleased
        if (PAT.getText().equals("")) {
            ButtonReporte.setVisible(false);
        }
        Secuencias_cadenas.borrarLetras(evt.getKeyChar(), PAT);

    }//GEN-LAST:event_PATKeyReleased

    private void aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarActionPerformed
        
        if (PAT.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El campo PA no puede estar vacio", "Error", JOptionPane.ERROR_MESSAGE);
            PAT.setText("");
            ButtonReporte.setVisible(false);
            return;
        } 
        if(Integer.parseInt(PAT.getText()) <= 0 ||Integer.parseInt(PAT.getText()) >=100 ){
            JOptionPane.showMessageDialog(null, "El PA debe ser mayor que 0 y menor que 100", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else  {
            double M = (double) (Integer.parseInt(actividadesExtraL.getText()) * 100) / ((double) (Integer.parseInt(PAT.getText())));
            relacionL.setText(M + "");
        }
        ButtonReporte.setVisible(true);
        actualizarTablaICI();
    }//GEN-LAST:event_aceptarActionPerformed

    private void ButtonReporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonReporteMouseClicked
        try {
            Queue estudiantesOrdenados = organizarEstudiantes();
            GenerarReporteICI ICI = new GenerarReporteICI(Double.parseDouble(relacionL.getText()), Integer.parseInt(actividadesExtraL.getText()), Integer.parseInt(PAT.getText()), brigada, estudiantesOrdenados);
            if (ICI.generarReporte()) {
                JOptionPane.showMessageDialog(null, "Reporte creado exitosamente en el escritorio", "Mensaje del sistema", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (DocumentException ex) {
            Logger.getLogger(ICI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ButtonReporteMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        Main m = new Main();
        m.setVisible(true);
    }//GEN-LAST:event_formWindowClosed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonReporte;
    private javax.swing.JLabel PA;
    private javax.swing.JTextField PAT;
    private javax.swing.JButton aceptar;
    private javax.swing.JLabel actividadesExtra;
    private javax.swing.JLabel actividadesExtraL;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel relacion;
    private javax.swing.JLabel relacionL;
    private javax.swing.JTable tablaICI;
    // End of variables declaration//GEN-END:variables

    private void actualizarTablaICI() {

        DefaultTableModel d = new DefaultTableModel() {
            @Override
            public Class getColumnClass(int columna) {
                switch (columna) {
                    case 1 -> {
                        return Double.class;
                    }
                    case 2 -> {
                        return Double.class;
                    }
                    case 3 -> {
                        return Double.class;
                    }
                }
                return String.class;
            }
            
            @Override
             public boolean isCellEditable(int row, int column) {
                return false;         
             };
        };
        Object[] OBJ = new Object[4];
        d.addColumn("Estudiante");
        d.addColumn("Promedio");
        d.addColumn("Valor cuantitativo de las actividades extracurriculares");
        d.addColumn("ICI");

        int m = Integer.parseInt(actividadesExtraL.getText());
        double M = 0.0;
        if (!relacionL.getText().equals("")) {
            M = Double.parseDouble(relacionL.getText());
            for (int i = 0; i < brigada.getEstudiantes().size(); i++) {
                OBJ[0] = brigada.getEstudiantes().elementAt(i).getNombre_estudiante();
                double promedioi = g.obtenerPromedio(brigada.getEstudiantes().elementAt(i));
                OBJ[1] = promedioi;
                int miTemp = g.obtenerValoresEventosEstudiante(brigada.getEstudiantes().elementAt(i));
                OBJ[2] = miTemp;
                OBJ[3] = utiles.ICI.ICI(m, M, promedioi, miTemp);
                d.addRow(OBJ);
            }
        } else {
            for (int i = 0; i < brigada.getEstudiantes().size(); i++) {
                OBJ[0] = brigada.getEstudiantes().elementAt(i).getNombre_estudiante();
                double promedioi = g.obtenerPromedio(brigada.getEstudiantes().elementAt(i));
                OBJ[1] = promedioi;
                int miTemp = g.obtenerValoresEventosEstudiante(brigada.getEstudiantes().elementAt(i));
                OBJ[2] = miTemp;
                OBJ[3] = 0;
                d.addRow(OBJ);
            }
        }
        TableRowSorter<TableModel> modeloOrdenado = new TableRowSorter<TableModel>(d);
        tablaICI = new JTable(d);
        tablaICI.setRowSorter(modeloOrdenado);
        jScrollPane1.setViewportView(tablaICI);

    }

    private Queue organizarEstudiantes() {
        Queue<Estudiante> estudinatesOrdenados = new ArrayDeque<>();

        for (int i = 0; i < tablaICI.getRowCount(); i++) {
            String nombre = (String) tablaICI.getValueAt(i, 0);
            for (int j = 0; j < brigada.getEstudiantes().size(); j++) {
                if (nombre.equals(brigada.getEstudiantes().elementAt(j).getNombre_estudiante())) {
                    estudinatesOrdenados.add(brigada.getEstudiantes().elementAt(j));
                    break;
                }
            }
        }
        return estudinatesOrdenados;
    }
}
