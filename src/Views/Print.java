
package Views;

import Modelo.Productos;
import Modelo.ProductosDao;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class Print extends javax.swing.JFrame {

    
    Productos pro = new Productos();
    ProductosDao proDao = new ProductosDao();
    DefaultTableModel modelo = new DefaultTableModel();

    public Print(String idCompra) {
    initComponents();
    Foli.setText("Compra ID: " + idCompra); // Muestra el ID en el campo Foli
    listar(Integer.parseInt(idCompra)); // Convierte a int para usarlo en listar()
    calcularTotal(); // Calcula el total de la compra
}


    public void listar(int idCompra) { // Cambié nombreCompra a idCompra
    List<Productos> lista = proDao.ListaDetalle(idCompra); // Usa idCompra como parámetro
    modelo = (DefaultTableModel) TableDetalle.getModel();
    modelo.setRowCount(0); // Limpia la tabla antes de agregar datos

    Object[] ob = new Object[4]; // Columnas: Cantidad, Descripción, Precio, Subtotal
    for (int i = 0; i < lista.size(); i++) {
        ob[0] = lista.get(i).getCantidad();       // Cantidad
        ob[1] = lista.get(i).getDescripcion();    // Descripción
        ob[2] = lista.get(i).getPrecio_compra();  // Precio de compra
        ob[3] = lista.get(i).getPrecio_venta();   // Subtotal o Precio de venta
        modelo.addRow(ob);
    }
    TableDetalle.setModel(modelo);

    // Ajuste visual para el encabezado de la tabla
    JTableHeader header = TableDetalle.getTableHeader();
    header.setOpaque(false);
    header.setBackground(Color.blue);
    header.setForeground(Color.white);
}

    
    // Método para calcular el total de la compra
    private void calcularTotal() {
        double total = 0.00;
        int numFila = TableDetalle.getRowCount();
        for (int i = 0; i < numFila; i++) {
            total += Double.parseDouble(String.valueOf(TableDetalle.getValueAt(i, 3))); // Suma la columna "Sub Total"
        }
        TotalPagar.setText("" + total); // Muestra el total en el JLabel
    }
    // Código generado automáticamente para construir la interfaz gráfica


    
    
     
     
    private void initComponents() {//GEN-BEGIN:initComponents

        PanelPrint = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableDetalle = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        TotalPagar = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        Foli = new javax.swing.JLabel();
        Folio = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelPrint.setBackground(new java.awt.Color(255, 255, 255));
        PanelPrint.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Cant.", "Descripcion", "Precio", "Sub Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TableDetalle.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane2.setViewportView(TableDetalle);
        if (TableDetalle.getColumnModel().getColumnCount() > 0) {
            TableDetalle.getColumnModel().getColumn(0).setPreferredWidth(50);
            TableDetalle.getColumnModel().getColumn(1).setPreferredWidth(310);
            TableDetalle.getColumnModel().getColumn(2).setPreferredWidth(70);
            TableDetalle.getColumnModel().getColumn(3).setPreferredWidth(70);
        }

        PanelPrint.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 500, 330));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Nombre:");
        PanelPrint.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 60, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Direccion:");
        PanelPrint.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 70, -1));

        jLabel9.setFont(new java.awt.Font("News701 BT", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 153, 0));
        jLabel9.setText("Datos del Proveedor");
        PanelPrint.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        jLabel13.setFont(new java.awt.Font("Sitka Banner", 1, 24)); // NOI18N
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/finca latina logo.png"))); // NOI18N
        PanelPrint.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 180, 140));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel14.setText("Total");
        PanelPrint.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 520, -1, -1));

        TotalPagar.setText("-----------");
        PanelPrint.add(TotalPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 520, -1, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel16.setText("Ruc");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, -1));

        Foli.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Foli.setText("Folio");
        jPanel4.add(Foli, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        Folio.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Folio.setText("1");
        jPanel4.add(Folio, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 10, -1));

        PanelPrint.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 180, 90));

        getContentPane().add(PanelPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 570, 550));

        jButton2.setText("Imprimir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, -1, -1));

        pack();
    }//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Toolkit tk = PanelPrint.getToolkit();
        PrintJob pj = tk.getPrintJob(this, null, null);
        Graphics g = pj.getGraphics();
        PanelPrint.print(g);
        g.dispose();
        pj.end();
    }//GEN-LAST:event_jButton2ActionPerformed

    

    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Foli;
    private javax.swing.JLabel Folio;
    private javax.swing.JPanel PanelPrint;
    private javax.swing.JTable TableDetalle;
    private javax.swing.JLabel TotalPagar;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    
}
