package Controllers;

import Modelo.Combo;
import Modelo.Productos;
import Modelo.ProductosDao;
import Modelo.Tables;
import Views.PanelAdmin;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ProductosControllers implements ActionListener, MouseListener, KeyListener {

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
        this.views.jLabelNuevaCompra.addMouseListener(this);
        this.views.jLabelNuevaVenta.addMouseListener(this);

        this.views.txtCodNC.addKeyListener(this);
        this.views.txtCantNC.addKeyListener(this);
        this.views.txtPagarConNC.addKeyListener(this);
        this.views.btnGenerarCompra.addActionListener(this);

        this.views.txtCodNV.addKeyListener(this);
        this.views.txtCantNV.addKeyListener(this);      
        this.views.btnGenerarVenta.addActionListener(this);
        
        this.views.addKeyListener(this);

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
                    views.cargarDatos(); // Actualizar los números
                    
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
        } else if (e.getSource() == views.JMenuReingresarPro) {
    if (views.txtIdPro.getText().equals("")) {
        JOptionPane.showMessageDialog(null, "Seleccione una fila.");
    } else {
        int id = Integer.parseInt(views.txtIdPro.getText());
        if (proDao.accion("Activo", id)) {
            limpiarTable();
            listarProductos();
            JOptionPane.showMessageDialog(null, "Producto reingresado con éxito.");
        } else {
            JOptionPane.showMessageDialog(null, "Error al reingresar el producto.");
        }
    }
} else if (e.getSource() == views.JMenuEliminarPro) {
    if (views.txtIdPro.getText().equals("")) {
        JOptionPane.showMessageDialog(null, "Seleccione una fila.");
    } else {
        int id = Integer.parseInt(views.txtIdPro.getText());
        if (proDao.accion("Inactivo", id)) {
            limpiarTable();
            listarProductos();
            views.cargarDatos(); // Actualiza los números en la interfaz
            JOptionPane.showMessageDialog(null, "Producto eliminado con éxito.");
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar el producto.");
        }
    }
}

        
        else if (e.getSource() == views.btnGenerarCompra) {
            insertarCompra();
        } else if (e.getSource() == views.btnGenerarVenta) {
            insertarVenta();
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

        } else if (e.getSource() == views.jLabelProductos) {
            views.jTabbedPane1.setSelectedIndex(7);
            limpiarTable();
            listarProductos();

        } else if (e.getSource() == views.jLabelNuevaCompra) {
            views.jTabbedPane1.setSelectedIndex(10);

        } else if (e.getSource() == views.jLabelNuevaVenta) {
            views.jTabbedPane1.setSelectedIndex(9);
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
                String cod = views.txtCodNC.getText();
               buscarProducto(views.txtCodNC, cod, views.txtIdNC, views.txtProductoNC, views.txtPrecioNC, views.txtCantNC, 0); // Compra

            }
        } //Buscar y agregar producto temp a la venta
        else if (e.getSource() == views.txtCodNV) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                String cod = views.txtCodNV.getText();
                buscarProducto(views.txtCodNV, cod, views.txtIdNV, views.txtProductoNV, views.txtPrecioNV, views.txtCantNV, 1); // Venta

            }
        } else if (e.getSource() == views.txtCantNC) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                int cant = Integer.parseInt(views.txtCantNC.getText());
                String desc = views.txtProductoNC.getText();
                double precio = Double.parseDouble(views.txtPrecioNC.getText());
                int id = Integer.parseInt(views.txtIdNC.getText());
                agregarTemp(cant, desc, precio, id, views.TableNuevaCompra, views.txtCodNC);
                limpiarCompras();
                calcularTotal(views.TableNuevaCompra, views.JLabelTotalCompra);
            }
        } else if (e.getSource() == views.txtCantNV) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (views.txtCantNV.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "INGRESE LA CANTIDAD");
                } else {
                    int cant = Integer.parseInt(views.txtCantNV.getText());
                    int stock = Integer.parseInt(views.txtStock.getText());
                    if (cant > stock) {
                        JOptionPane.showMessageDialog(null, "STOCK NO DISPONIBLE");
                    } else {
                        String desc = views.txtProductoNV.getText();
                        double precio = Double.parseDouble(views.txtPrecioNV.getText());
                        int id = Integer.parseInt(views.txtIdNV.getText());
                        agregarTemp(cant, desc, precio, id, views.TableNuevaVenta, views.txtCodNV);
                        limpiarVentas();
                        calcularTotal(views.TableNuevaVenta, views.JLabelTotalPagar);
                    }
                }

            }
        }
    }

    //"Error al modificar el producto"
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
        } else if (e.getSource() == views.txtPagarConNC) {
            int pagar;
            if (views.txtPagarConNC.getText().equals("")) {
                views.txtVueltoCompra.setText("");
            } else {
                pagar = Integer.parseInt(views.txtPagarConNC.getText());
                double total = Double.parseDouble(views.JLabelTotalCompra.getText());
                views.txtVueltoCompra.setText("" + (pagar - total));
            }
        }else if(e.getSource() == views.txtBuscarProducto){
            views.jTabbedPane1.setSelectedIndex(7);
            limpiarTable();
            listarProductos();
            
        }

    }

    private void limpiarCompras() {
        views.txtCodNC.setText("");
        views.txtIdNC.setText("");
        views.txtProductoNC.setText("");
        views.txtCantNC.setText("");
        views.txtPrecioNC.setText("");
        views.txtTotalNC.setText("");
    }

    private void limpiarVentas() {
        views.txtCodNV.setText("");
        views.txtIdNV.setText("");
        views.txtProductoNV.setText("");
        views.txtCantNV.setText("");
        views.txtPrecioNV.setText("");
        views.txtTotalNV.setText("");
        views.txtStock.setText("");
    }

    private void calcularTotal(JTable tabla, JLabel totalPagar) {
        double total = 0.00;
        int numFila = tabla.getRowCount(); // Número de filas en la tabla

        for (int i = 0; i < numFila; i++) {
            // Aquí podrías estar accediendo a una columna que no existe
            total += Double.parseDouble(tabla.getValueAt(i, 4).toString());
        }

        totalPagar.setText(String.valueOf(total));
    }

   private void insertarCompra() {
    // Obtén la información de la compra
    Combo proveedor = (Combo) views.cbxProCompra.getSelectedItem();
    String nombreProveedor = proveedor.getNombre();
    String total = views.JLabelTotalCompra.getText();

    if (total == null || total.isEmpty() || Double.parseDouble(total) <= 0) {
        JOptionPane.showMessageDialog(null, "El total de la compra debe ser mayor a 0.");
        return;
    }

    // Registrar la compra
    if (proDao.registrarCompra(nombreProveedor, total)) {
        int idCompra = proDao.obtenerUltimoIdCompra();
        for (int i = 0; i < views.TableNuevaCompra.getRowCount(); i++) {
            int idProducto = Integer.parseInt(views.TableNuevaCompra.getValueAt(i, 0).toString());
            int cantidad = Integer.parseInt(views.TableNuevaCompra.getValueAt(i, 2).toString());
            double precio = Double.parseDouble(views.TableNuevaCompra.getValueAt(i, 3).toString());
            double subTotal = cantidad * precio;

            // Registrar detalle de la compra
            proDao.registrarCompraDetalle(idCompra, idProducto, views.TableNuevaCompra.getValueAt(i, 1).toString(), precio, cantidad, subTotal);

            // Sumar al stock
            proDao.actualizarStock(cantidad, idProducto, true);
        }

        // Limpiar la tabla de compras
        limpiarTableDetalle();

        // Generar el reporte de la compra
        proDao.generarReporte(idCompra);

        JOptionPane.showMessageDialog(null, "Compra registrada, stock actualizado y reporte generado.");
        views.cargarDatos(); // Actualizar los números
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

    //agregar productos para la compra
    private void agregarTemp(int cant, String desc, double precio, int id, JTable tabla, JTextField codigo) {

        // Verifica que la cantidad es mayor a 0
        if (cant > 0) {
            tmp = (DefaultTableModel) tabla.getModel();

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
            tabla.setModel(tmp);

            // Limpia los campos y recalcula el total
            codigo.requestFocus();
        }

    }
    
    //buscar productos para la compra y venta
    // Para la compra y venta
  private void buscarProducto(JTextField campo, String cod, JTextField id,
                            JTextField prod, JTextField precio, JTextField cant, int accion) {
    if (campo.getText().equals("")) {
        JOptionPane.showMessageDialog(null, "Ingrese el código");
        return;
    }

    // Llama al método para buscar el producto por código
    pro = proDao.buscarCodigo(cod);

    // Valida si el producto fue encontrado
    if (pro == null) {
        JOptionPane.showMessageDialog(null, "El producto no existe o está inactivo");
        return;
    }

    // Si el producto fue encontrado, continúa con la asignación
    if (pro.getId() > 0) {
        id.setText("" + pro.getId());
        prod.setText(pro.getDescripcion());
        if (accion == 0) { // Compra
            precio.setText("" + pro.getPrecio_compra());
        } else { // Venta
            precio.setText("" + pro.getPrecio_venta());
            views.txtStock.setText("" + pro.getCantidad());
        }
        cant.requestFocus();
    }
}


        
//insertar venta

    private void insertarVenta() {
    // Obtener el nombre del cliente desde el combo
    Combo cliente = (Combo) views.cbxClienteVentas.getSelectedItem();
    String nombre_cliente = cliente.getNombre(); // Usa el nombre directamente
    String total = views.JLabelTotalPagar.getText();

    if (total == null || total.isEmpty() || Double.parseDouble(total) <= 0) {
        JOptionPane.showMessageDialog(null, "El total de la venta debe ser mayor a 0.");
        return;
    }

    // Registrar la venta usando el nombre del cliente
    if (proDao.registrarVenta(nombre_cliente, total)) {
        int id_venta = proDao.obtenerUltimoIdVenta();
        for (int i = 0; i < views.TableNuevaVenta.getRowCount(); i++) {
            int idProducto = Integer.parseInt(views.TableNuevaVenta.getValueAt(i, 0).toString());
            int cantidad = Integer.parseInt(views.TableNuevaVenta.getValueAt(i, 2).toString());
            double precio = Double.parseDouble(views.TableNuevaVenta.getValueAt(i, 3).toString());
            double subTotal = cantidad * precio;

            // Validar el stock actual
            pro = proDao.buscarPro(idProducto);
            if (pro.getCantidad() < cantidad) {
                JOptionPane.showMessageDialog(null, "Stock insuficiente para el producto: " + pro.getDescripcion());
                return;
            }

            // Registrar detalle de la venta
            proDao.registrarVentaDetalle(id_venta, idProducto, pro.getDescripcion(), precio, cantidad, subTotal);

            // Actualizar el stock (restar)
            proDao.actualizarStock(cantidad, idProducto, false);
        }
        limpiarTableDetalle();
        JOptionPane.showMessageDialog(null, "Venta registrada y stock actualizado.");
        proDao.generarReporteVenta(id_venta);
        views.cargarDatos(); // Actualizar los números
    } else {
        JOptionPane.showMessageDialog(null, "Error al registrar la venta.");
    }
}









}
