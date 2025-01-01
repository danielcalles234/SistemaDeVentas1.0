package Controllers;

import Modelo.Compras;
import Modelo.ComprasDao;
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

public class ComprasControllers implements KeyListener, ActionListener, MouseListener{
    private Compras comp;
    private ComprasDao compDao;
    private PanelAdmin views;
    DefaultTableModel modelo = new DefaultTableModel();

    public ComprasControllers(Compras comp, ComprasDao compDao, PanelAdmin views) {
        this.comp = comp;
        this.compDao = compDao;
        this.views = views;
        this.views.txtBuscarCompra.addKeyListener(this);
        this.views.btnHistorialCompra.addActionListener(this);
        this.views.TableCompras.addMouseListener(this);
        listarCompras();
    }

    // Método para listar compras en la tabla
    public void listarCompras() {
        limpiarTable(); // Limpia la tabla antes de agregar nuevas filas
        List<Compras> lista = compDao.ListaCompras(views.txtBuscarCompra.getText()); // Llama al método del DAO
        modelo = (DefaultTableModel) views.TableCompras.getModel(); // Obtiene el modelo de la tabla
        Object[] ob = new Object[4]; // Define un arreglo para las columnas
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getNombre_proveedor();
            ob[2] = lista.get(i).getTotal();
            ob[3] = lista.get(i).getFecha();
            modelo.addRow(ob); // Agrega una fila al modelo
        }
        views.TableCompras.setModel(modelo); // Establece el modelo actualizado en la tabla

        // Configura el encabezado de la tabla
        JTableHeader header = views.TableCompras.getTableHeader();
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
        if (e.getSource() == views.txtBuscarCompra) {
        limpiarTable();
        listarCompras();
    }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == views.btnHistorialCompra) {
        if (views.txtIdCompra.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Selecciona una fila");
        } else {
            int id_compra = Integer.parseInt(views.txtIdCompra.getText());
            ProductosDao proDao = new ProductosDao();
            proDao.generarReporte(id_compra);
            
            // Después de registrar una compra, actualizamos los números
            views.cargarDatos(); 
        }
    }
}

@Override
public void mouseClicked(MouseEvent e) {
    if (e.getSource() == views.TableCompras) {
        int fila = views.TableCompras.rowAtPoint(e.getPoint());
        views.txtIdCompra.setText(views.TableCompras.getValueAt(fila, 0).toString());
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





