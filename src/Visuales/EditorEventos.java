/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Visuales;

import Base_de_Datos.Gestion;
import clases.Evento;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class EditorEventos extends javax.swing.JFrame {

   
    private Vector<String> NombreEventos;
    private Gestion g;

    public EditorEventos() {
        initComponents();
        nuevoEvento.setVisible(false);
        g = new Gestion();
        NombreEventos = g.obtener_nombres_eventos();
        actualizarTabla(NombreEventos);

        Vector<String> dimensiones = g.obtenerDimensiones();

        for (int i = 0; i < dimensiones.size(); i++) {
            dimensionesComboBox.addItem(dimensiones.elementAt(i));
        }
        jCalendar1.setVisible(false);
        dimensionesL.setVisible(false);
        dimensionesComboBox.setVisible(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Editor de Evento");
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
        TableEventos = new javax.swing.JTable();
        nombreEvento = new javax.swing.JLabel();
        TextNombreEvento = new javax.swing.JTextField();
        aceptar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        nuevoEvento = new javax.swing.JButton();
        dimensionesComboBox = new javax.swing.JComboBox<>();
        dimensionesL = new javax.swing.JLabel();
        jCalendar1 = new com.toedter.calendar.JCalendar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(850, 500));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        TableEventos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TableEventos);

        nombreEvento.setText("Nombre del evento");

        TextNombreEvento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TextNombreEventoMouseClicked(evt);
            }
        });
        TextNombreEvento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextNombreEventoKeyReleased(evt);
            }
        });

        aceptar.setText("Agregar Evento Seleccionado ");
        aceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aceptarMouseClicked(evt);
            }
        });

        cancelar.setText("Cancelar");
        cancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelarMouseClicked(evt);
            }
        });

        nuevoEvento.setText("Nuevo Evento");
        nuevoEvento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nuevoEventoMouseClicked(evt);
            }
        });

        dimensionesL.setText("Dimensiones");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(aceptar)
                .addGap(18, 18, 18)
                .addComponent(nuevoEvento)
                .addGap(50, 50, 50)
                .addComponent(cancelar)
                .addGap(41, 41, 41)
                .addComponent(dimensionesL)
                .addGap(25, 25, 25)
                .addComponent(dimensionesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(nombreEvento)
                .addGap(18, 18, 18)
                .addComponent(TextNombreEvento))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreEvento)
                    .addComponent(TextNombreEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceptar)
                    .addComponent(cancelar)
                    .addComponent(nuevoEvento)
                    .addComponent(dimensionesL)
                    .addComponent(dimensionesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 56, Short.MAX_VALUE))
                    .addComponent(jCalendar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TextNombreEventoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextNombreEventoKeyReleased

        if (sonNumeros(evt.getKeyChar())) {
            Character caracterEtrada = evt.getKeyChar();
            String reeplazo = TextNombreEvento.getText().replaceAll(caracterEtrada.toString(), "");
            TextNombreEvento.setText(reeplazo);
        }

        String temp = TextNombreEvento.getText();
        if (temp.length() >= 3) {
            Vector<String> Similares = new Vector<>();
            nuevoEvento.setVisible(true);
            for (int i = 0; i < NombreEventos.size(); i++) {
                if (Secuencias_cadenas.mayor_subcadena(temp, NombreEventos.elementAt(i))) {
                    Similares.add(NombreEventos.elementAt(i));
                }
            }
            actualizarTabla(Similares);
        } else if (temp.length() < 3) {
            nuevoEvento.setVisible(false);
            actualizarTabla(NombreEventos);
        }
    }//GEN-LAST:event_TextNombreEventoKeyReleased

    private void nuevoEventoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nuevoEventoMouseClicked

        String temp = JOptionPane.showInputDialog(null, "Introduzca el nombre del evento");
        if (temp == null || temp.equals("")) {
            return;
        }

        Vector<String> Similares = new Vector<>();
        for (int i = 0; i < NombreEventos.size(); i++) {
            if (Secuencias_cadenas.LongestCommonSubsequence(temp, NombreEventos.elementAt(i)) >= 75.00) {
                Similares.add(NombreEventos.elementAt(i));
            }
        }

        if (!Similares.isEmpty()) {
            String[] S = new String[Similares.size()];
            Similares.copyInto(S);

            String x = (String) JOptionPane.showInputDialog(null, "Estas carreras son similares a lo escrito. Seleccione una de las opciones si se ha equivocado", "Sugerencia", JOptionPane.QUESTION_MESSAGE, null, S, S[0]);

            if (x == null) {
                g.agregar_nombre_evento(temp);
                NombreEventos.add(temp);
                JOptionPane.showMessageDialog(null, "Evento: " + temp + " agregado satisfactoriamente", "Mensaje del sistema", JOptionPane.QUESTION_MESSAGE);
            }

            Vector<String> V = new Vector<String>();
            V.add(x);

            actualizarTabla(V);

        } else {
            g.agregar_nombre_evento(temp);
            JOptionPane.showMessageDialog(null, "Evento: " + temp + " agregado satisfactoriamente", "Mensaje del sistema", JOptionPane.QUESTION_MESSAGE);
            NombreEventos.add(temp);

            Vector<String> V = new Vector<String>();
            V.add(temp);

            actualizarTabla(V);
        }
    }//GEN-LAST:event_nuevoEventoMouseClicked

    private void cancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelarMouseClicked
        Gestor_Eventos GE = new Gestor_Eventos();
        GE.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cancelarMouseClicked

    private void aceptarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aceptarMouseClicked

        if (TableEventos.getSelectedColumn() > -1) {
            String nombreEvento = (String) TableEventos.getValueAt(TableEventos.getSelectedRow(), TableEventos.getSelectedColumn());
            int dimension = dimensionesComboBox.getSelectedIndex() + 1;
            Date d = jCalendar1.getDate();
            SimpleDateFormat dt = new SimpleDateFormat("YYY");
            int anno = Integer.parseInt(dt.format(d).toUpperCase());
            if (!g.existeEventoFecha(anno, nombreEvento)) {
                dt = new SimpleDateFormat("MM-dd-YYY");
                String fecha = dt.format(d).toUpperCase();
                Evento evento = new Evento(nombreEvento, dimension, fecha);
                g.agregar_evento(evento);

                TextNombreEvento.setText("");
                actualizarTabla(NombreEventos);
                JOptionPane.showMessageDialog(null, "Se ha agregado el evento con éxito");
            } else {
                JOptionPane.showMessageDialog(null, "Este evento ya existe en el año indicado");
            }

        } else {
            JOptionPane.showMessageDialog(null, "No hay evento seleccionado");
        }

    }//GEN-LAST:event_aceptarMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        Main M = new Main();
        M.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        TableEventos.clearSelection();
        jCalendar1.setVisible(false);
        dimensionesL.setVisible(false);
        dimensionesComboBox.setVisible(false);
    }//GEN-LAST:event_formMouseClicked

    private void TextNombreEventoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextNombreEventoMouseClicked
        jCalendar1.setVisible(false);
        dimensionesL.setVisible(false);
        dimensionesComboBox.setVisible(false);
        TableEventos.clearSelection();
    }//GEN-LAST:event_TextNombreEventoMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableEventos;
    private javax.swing.JTextField TextNombreEvento;
    private javax.swing.JButton aceptar;
    private javax.swing.JButton cancelar;
    private javax.swing.JComboBox<String> dimensionesComboBox;
    private javax.swing.JLabel dimensionesL;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nombreEvento;
    private javax.swing.JButton nuevoEvento;
    // End of variables declaration//GEN-END:variables

    private void actualizarTabla(Vector<String> v) {
        DefaultTableModel df = new DefaultTableModel(){
             @Override
             public boolean isCellEditable(int row, int column) {
                return false;         
             };
        };
        TableEventos = new JTable(df);
        jScrollPane1.setViewportView(TableEventos);
        TableEventos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = TableEventos.rowAtPoint(e.getPoint());

                if (fila > -1) {
                    jCalendar1.setVisible(true);
                    dimensionesL.setVisible(true);
                    dimensionesComboBox.setVisible(true);
                } else {
                    jCalendar1.setVisible(false);
                    dimensionesL.setVisible(false);
                    dimensionesComboBox.setVisible(false);
                }
            }
        });
        df.addColumn("Nombre del Evento");

        Object[] ob = new Object[1];
        for (int i = 0; i < v.size(); i++) {
            ob[0] = v.elementAt(i);
            df.addRow(ob);
        }
    }
}
