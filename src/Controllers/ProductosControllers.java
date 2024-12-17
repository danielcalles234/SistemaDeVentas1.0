
package Controllers;

import Modelo.Combo;
import Modelo.Productos;
import Modelo.ProductosDao;
import Modelo.Tables;
import Views.PanelAdmin;
import Views.Print;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class ProductosControllers implements ActionListener, MouseListener, KeyListener{

    
    private Productos pro;
    private ProductosDao proDao;
    private PanelAdmin views;
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel tmp;
    
    public ProductosControllers(Productos pro, ProductosDao proDao, PanelAdmin views) {
        
    this.pro = pro;
    this.proDao = proDao;
    this.views = views;
    this.views.btnRegistrarPro.addActionListener(this);
    this.views.btnModificarPro.addActionListener(this);
    this.views.btnNuevoPro.addActionListener(this);
    this.views.JMenuEliminarPro.addActionListener(this);
    this.views.JMenuReingresarPro.addActionListener(this);
    this.views.TableProductos.addMouseListener(this);
    this.views.jLabelProductos.addMouseListener(this);
    this.views.txtCodNC.addKeyListener(this);
    this.views.txtCantNC.addKeyListener(this);
    this.views.txtPagarConNC.addKeyListener(this);
    this.views.btnGenerarCompra.addActionListener(this);
    listarProductos();
    cargarProductos();
}

    
    @Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == views.btnRegistrarPro) {
        if (views.txtCodigoPro.getText().equals("")
                || views.txtDescripcionPro.getText().equals("")
                || views.txtPrecioCompraPro.getText().equals("")
                || views.txtPrecioVentaPro.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
        } else {
            pro.setCodigo(views.txtCodigoPro.getText());
            pro.setDescripcion(views.txtDescripcionPro.getText());
            pro.setPrecio_compra(Double.parseDouble(views.txtPrecioCompraPro.getText()));
            pro.setPrecio_venta(Double.parseDouble(views.txtPrecioVentaPro.getText()));
            Combo itemP = (Combo) views.cbxProveedorPro.getSelectedItem();
            Combo itemC = (Combo) views.cbxCatPro.getSelectedItem();
            Combo itemM = (Combo) views.cbxMedidaPro.getSelectedItem();
            pro.setNombre_proveedor(itemP.getNombre());  // Cambiado a nombre
            pro.setNombre_categoria(itemC.getNombre()); // Cambiado a nombre
            pro.setNombre_medida(itemM.getNombre());    // Cambiado a nombre

            if (proDao.registrar(pro)) {
                limpiarTable();
                listarProductos();
                JOptionPane.showMessageDialog(null, "Producto registrado con éxito");
            } else {
                JOptionPane.showMessageDialog(null, "Error al registrar el producto");
            }
        }
    } else if (e.getSource() == views.btnModificarPro) {
        if (views.txtCodigoPro.getText().equals("")
                || views.txtDescripcionPro.getText().equals("")
                || views.txtPrecioCompraPro.getText().equals("")
                || views.txtPrecioVentaPro.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
        } else {
            pro.setCodigo(views.txtCodigoPro.getText());
            pro.setDescripcion(views.txtDescripcionPro.getText());
            pro.setPrecio_compra(Double.parseDouble(views.txtPrecioCompraPro.getText()));
            pro.setPrecio_venta(Double.parseDouble(views.txtPrecioVentaPro.getText()));
            Combo itemP = (Combo) views.cbxProveedorPro.getSelectedItem();
            Combo itemC = (Combo) views.cbxCatPro.getSelectedItem();
            Combo itemM = (Combo) views.cbxMedidaPro.getSelectedItem();
            pro.setNombre_proveedor(itemP.getNombre());  // Cambiado a nombre
            pro.setNombre_categoria(itemC.getNombre()); // Cambiado a nombre
            pro.setNombre_medida(itemM.getNombre());    // Cambiado a nombre
            pro.setId(Integer.parseInt(views.txtIdPro.getText()));

            if (proDao.modificar(pro)) {
                limpiarTable();
                listarProductos();
                JOptionPane.showMessageDialog(null, "Producto modificado con éxito");
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar el producto");
            }
        } 
    }else if (e.getSource() ==  views.btnGenerarCompra){
            insertarCompra();
}else{
        
    }
}

public void listarProductos() {
    // Limpia la tabla antes de listar
    limpiarTable();

    Tables color = new Tables();
    views.TableProductos.setDefaultRenderer(views.TableUsers.getColumnClass(0), color);
    List<Productos> lista = proDao.ListaProductos(views.txtBuscarProducto.getText());
    modelo = (DefaultTableModel) views.TableProductos.getModel();
    Object[] ob = new Object[9]; // Incluye las nuevas columnas
    for (int i = 0; i < lista.size(); i++) {
        ob[0] = lista.get(i).getId();
        ob[1] = lista.get(i).getCodigo();
        ob[2] = lista.get(i).getDescripcion();
        ob[3] = lista.get(i).getPrecio_venta();
        ob[4] = lista.get(i).getCantidad();
        ob[5] = lista.get(i).getEstado();
        ob[6] = lista.get(i).getNombre_proveedor(); // Muestra nombre del proveedor
        ob[7] = lista.get(i).getNombre_medida();    // Muestra nombre de la medida
        ob[8] = lista.get(i).getNombre_categoria(); // Muestra nombre de la categoría
        modelo.addRow(ob);
    }
    views.TableProductos.setModel(modelo);
    JTableHeader header = views.TableUsers.getTableHeader();
    header.setOpaque(false);
    header.setBackground(Color.blue);
    header.setForeground(Color.white);
}

public void limpiarTable() {
    int rowCount = modelo.getRowCount();
    for (int i = rowCount - 1; i >= 0; i--) {
        modelo.removeRow(i);
    }
}

public void limpiarTableDetalle() {
    int rowCount = tmp.getRowCount();
    for (int i = rowCount - 1; i >= 0; i--) {
        tmp.removeRow(i);
    }
}


@Override
public void mouseClicked(MouseEvent e) {
    if (e.getSource() == views.TableProductos) {
        int fila = views.TableProductos.rowAtPoint(e.getPoint());
        views.txtIdPro.setText(views.TableProductos.getValueAt(fila, 0).toString());
        pro = proDao.buscarPro(Integer.parseInt(views.txtIdPro.getText()));
        views.txtCodigoPro.setText(pro.getCodigo());
        views.txtDescripcionPro.setText(pro.getDescripcion());
        views.txtPrecioCompraPro.setText("" + pro.getPrecio_compra());
        views.txtPrecioVentaPro.setText("" + pro.getPrecio_venta());
        views.cbxProveedorPro.setSelectedItem(new Combo(0, pro.getNombre_proveedor())); // Cambiado
        views.cbxMedidaPro.setSelectedItem(new Combo(0, pro.getNombre_medida()));       // Cambiado
        views.cbxCatPro.setSelectedItem(new Combo(0, pro.getNombre_categoria()));      // Cambiado
        
    }else if (e.getSource() == views.jLabelProductos) {
    views.jTabbedPane1.setSelectedIndex(8);
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

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
public void keyPressed(KeyEvent e) {
    if (e.getSource() == views.txtCodNC) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (views.txtCodNC.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese el código");
            } else {
                String cod = views.txtCodNC.getText();
                pro = proDao.buscarCodigo(cod);
                views.txtIdNC.setText("" + pro.getId());
                views.txtProductoNC.setText(pro.getDescripcion());
                views.txtPrecioNC.setText("" + pro.getPrecio_compra());
                views.txtCantNC.requestFocus();
            }
        }
    }else if (e.getSource() == views.txtCantNC) {
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        try {
            int cant = Integer.parseInt(views.txtCantNC.getText());
            String desc = views.txtProductoNC.getText();
            double precio = Double.parseDouble(views.txtPrecioNC.getText());
            int id = Integer.parseInt(views.txtIdNC.getText());

            // Verifica que la cantidad es mayor a 0
            if (cant > 0) {
                 tmp = (DefaultTableModel) views.TableNuevaCompra.getModel();

                // Verifica que el modelo de la tabla tenga 5 columnas
                if (tmp.getColumnCount() != 5) {
                    JOptionPane.showMessageDialog(null, "Error: El modelo de la tabla debe tener exactamente 5 columnas. Actualmente tiene: " + tmp.getColumnCount());
                    return;
                }

                // Prepara los datos de la fila
                Object[] obj = new Object[5]; // Cambiado a 5 columnas
                obj[0] = id;                 // Id
                obj[1] = desc;               // Descripción
                obj[2] = cant;               // Cantidad
                obj[3] = precio;             // Precio
                obj[4] = cant * precio;      // Total

                // Agrega la fila al modelo
                tmp.addRow(obj);
                views.TableNuevaCompra.setModel(tmp);

                // Limpia los campos y recalcula el total
                limpiarCampos();
                calcularCompra();
                views.txtCodNC.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor a 0.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error: Verifique los datos ingresados.");
        }
    }
}




}


    @Override
public void keyReleased(KeyEvent e) {
    if (e.getSource() == views.txtCantNC) {
        int cantidad;
        double precio;
        if (views.txtCantNC.getText().equals("")) {
            cantidad = 1;
            precio = Double.parseDouble(views.txtPrecioNC.getText());
            views.txtTotalNC.setText("" + precio);
        } else {
            cantidad = Integer.parseInt(views.txtCantNC.getText());
            precio = Double.parseDouble(views.txtPrecioNC.getText());
            views.txtTotalNC.setText("" + cantidad * precio);
        }
        //codigo para pagar y aparesca el vuelto
    }else if (e.getSource() == views.txtPagarConNC) {
    int pagar;
    if (views.txtPagarConNC.getText().equals("")) {
        views.txtVueltoCompra.setText("");
    } else {
        pagar = Integer.parseInt(views.txtPagarConNC.getText());
        double total = Double.parseDouble(views.JLabelTotalCompra.getText());
        views.txtVueltoCompra.setText("" + (pagar - total));
    }
}

}


private void  limpiarCampos(){
    views.txtCodNC.setText("");
    views.txtIdNC.setText("");
    views.txtProductoNC.setText("");
    views.txtCantNC.setText("");
    views.txtPrecioNC.setText("");
    views.txtTotalNC.setText("");
}
private void calcularCompra() {
    double total = 0.00;
    int numFila = views.TableNuevaCompra.getRowCount(); // Número de filas en la tabla

    for (int i = 0; i < numFila; i++) {
        // Aquí podrías estar accediendo a una columna que no existe
        total += Double.parseDouble(views.TableNuevaCompra.getValueAt(i, 4).toString());
    }

    views.JLabelTotalCompra.setText(String.valueOf(total));
}


private void insertarCompra() {
    Combo nombre_p = (Combo) views.cbxProCompra.getSelectedItem();
    String nombre_proveedor = nombre_p.getNombre();
    String total = views.JLabelTotalCompra.getText();

    if (total == null || total.isEmpty() || Double.parseDouble(total) <= 0) {
        JOptionPane.showMessageDialog(null, "El total de la compra debe ser mayor a 0.");
        return;
    }

    // Registra la compra
    if (proDao.registrarCompra(nombre_proveedor, total)) {
        System.out.println("Compra registrada exitosamente.");

        // Obtiene el último ID de compra
        int id_compra = proDao.obtenerUltimoIdCompra();
        System.out.println("ID de la compra recuperado: " + id_compra);

        if (id_compra == -1) {
            JOptionPane.showMessageDialog(null, "No se pudo obtener el ID de la compra. Verifique la base de datos.");
            return;
        }

        // Registra los detalles de la compra
        for (int i = 0; i < views.TableNuevaCompra.getRowCount(); i++) {
            int id_producto = Integer.parseInt(views.TableNuevaCompra.getValueAt(i, 0).toString());
            String descripcion_producto = views.TableNuevaCompra.getValueAt(i, 1).toString();
            int cantidad = Integer.parseInt(views.TableNuevaCompra.getValueAt(i, 2).toString());
            double precio = Double.parseDouble(views.TableNuevaCompra.getValueAt(i, 3).toString());
            double sub_total = precio * cantidad;

            if (!proDao.registrarCompraDetalle(id_compra, id_producto, descripcion_producto, precio, cantidad, sub_total)) {
                JOptionPane.showMessageDialog(null, "Error al registrar detalle para: " + descripcion_producto);
            } else {
                System.out.println("Detalle registrado para: " + descripcion_producto);
            }

            // Actualiza el stock
            pro = proDao.buscarPro(id_producto);
            if (pro != null) {
                int stockActual = pro.getCantidad() + cantidad;
                proDao.ActualizarStock(stockActual, pro.getDescripcion());
            }
        }

        // Llama a la clase Print
        Print p = new Print(String.valueOf(id_compra));
        p.setVisible(true);
        p.setLocationRelativeTo(null);

        // Actualiza la interfaz
        cargarProductos();
        limpiarTableDetalle();
        JOptionPane.showMessageDialog(null, "Compra generada y stock actualizado.");
    } else {
        JOptionPane.showMessageDialog(null, "Error al registrar la compra.");
    }
}








private void cargarProductos() {
    // Limpia la tabla antes de recargar datos
    DefaultTableModel modelo = (DefaultTableModel) views.TableProductos.getModel();
    modelo.setRowCount(0);

    // Obtiene la lista de productos desde el DAO
    List<Productos> listaProductos = proDao.ListaProductos("");
    Object[] fila = new Object[9]; // Ajusta el tamaño de acuerdo a tus columnas

    // Agrega los productos a la tabla
    for (Productos producto : listaProductos) {
        fila[0] = producto.getId();
        fila[1] = producto.getCodigo();
        fila[2] = producto.getDescripcion();
        fila[3] = producto.getPrecio_venta();
        fila[4] = producto.getCantidad(); // Stock actualizado
        fila[5] = producto.getEstado();
        fila[6] = producto.getNombre_proveedor();
        fila[7] = producto.getNombre_medida();
        fila[8] = producto.getNombre_categoria();
        modelo.addRow(fila);
    }

    // Actualiza la tabla en la interfaz gráfica
    views.TableProductos.setModel(modelo);
}











    
}
