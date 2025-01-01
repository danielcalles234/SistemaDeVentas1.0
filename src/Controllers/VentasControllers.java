package Controllers;

import Modelo.Ventas;
import Modelo.VentasDao;
import Modelo.ProductosDao;
import Views.PanelAdmin;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class VentasControllers implements KeyListener, ActionListener, MouseListener {
    private Ventas venta;
    private VentasDao ventaDao;
    private PanelAdmin views;
    DefaultTableModel modelo = new DefaultTableModel();

    public VentasControllers(Ventas venta, VentasDao ventaDao, PanelAdmin views) {
        this.venta = venta;
        this.ventaDao = ventaDao;
        this.views = views;
        this.views.txtBuscarVenta.addKeyListener(this);
        this.views.btnHistorialVenta.addActionListener(this);
        this.views.TableVentas.addMouseListener(this);
        listarVentas();
    }

    // Método para listar ventas en la tabla
    public void listarVentas() {
        limpiarTable(); // Limpia la tabla antes de agregar nuevas filas
        List<Ventas> lista = ventaDao.ListaVentas(views.txtBuscarVenta.getText()); // Llama al método del DAO
        modelo = (DefaultTableModel) views.TableVentas.getModel(); // Obtiene el modelo de la tabla
        Object[] ob = new Object[4]; // Define un arreglo para las columnas
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getNombre_cliente();
            ob[2] = lista.get(i).getTotal();
            ob[3] = lista.get(i).getFecha();
            modelo.addRow(ob); // Agrega una fila al modelo
        }
        views.TableVentas.setModel(modelo); // Establece el modelo actualizado en la tabla

        // Configura el encabezado de la tabla
        JTableHeader header = views.TableVentas.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.blue);
        header.setForeground(Color.white);
    }

    // Método para limpiar la tabla antes de listar
    public void limpiarTable() {
        int rowCount = modelo.getRowCount(); // Obtiene la cantidad de filas actuales
        for (int i = rowCount - 1; i >= 0; i--) { // Recorre de abajo hacia arriba
            modelo.removeRow(i); // Elimina cada fila
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource() == views.txtBuscarVenta) {
            limpiarTable();
            listarVentas();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btnHistorialVenta) {
            if (views.txtIdVenta.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Selecciona una fila");
            } else {
                int id_venta = Integer.parseInt(views.txtIdVenta.getText());
                ProductosDao proDao = new ProductosDao();
                proDao.generarReporteVenta(id_venta); // Cambiado a generarReporteVenta
                
                // Después de registrar una compra, actualizamos los números
            views.cargarDatos(); 
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.TableVentas) {
            int fila = views.TableVentas.rowAtPoint(e.getPoint());
            views.txtIdVenta.setText(views.TableVentas.getValueAt(fila, 0).toString());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
