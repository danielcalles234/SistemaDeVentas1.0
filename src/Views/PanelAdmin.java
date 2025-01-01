
package Views;

import Controllers.CategoriaControllers;
import Controllers.ClientesControllers;
import Controllers.ComprasControllers;
import Controllers.ConfigControllers;
import Controllers.MedidaControllers;
import Controllers.ProductosControllers;
import Controllers.ProveedorControllers;
import Controllers.UsuariosControllers;
import Controllers.VentasControllers;
import Modelo.Categorias;
import Modelo.CategoriasDao;
import Modelo.Clientes;
import Modelo.ClientesDao;
import Modelo.Compras;
import Modelo.ComprasDao;
import Modelo.Configuracion;
import Modelo.Medidas;
import Modelo.MedidasDao;
import Modelo.Productos;
import Modelo.ProductosDao;
import Modelo.Proveedor;
import Modelo.ProveedorDao;
import Modelo.UsuarioDao;
import Modelo.Usuarios;
import Modelo.Ventas;
import Modelo.VentasDao;
import Reportes.GraficosReportes;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class PanelAdmin extends javax.swing.JFrame {
    Usuarios us = new Usuarios();
    UsuarioDao usDao = new UsuarioDao();
    Clientes cl = new Clientes();
    ClientesDao clDao = new ClientesDao();
    Proveedor pr = new Proveedor();
    ProveedorDao prDao = new ProveedorDao();
    Categorias cat = new Categorias();
    CategoriasDao catDao = new CategoriasDao();
    Medidas md = new Medidas();
    MedidasDao mdDao = new MedidasDao();
    Productos pro = new Productos();
    ProductosDao proDao = new ProductosDao();
    Configuracion cof = new Configuracion();
    Compras comp = new Compras();
    ComprasDao compDao = new ComprasDao();
    
    
    Ventas vent = new Ventas();
    VentasDao ventDao = new VentasDao();

    public PanelAdmin() {
    }

    public PanelAdmin(int id, String nombre) {
    initComponents(); // Inicializa los componentes gráficos
    cargarDatos(); // Cargar datos iniciales
    cargarGraficoProductos(); // Cargar los gráficos

    // Configurar controladores
    ConfigControllers config = new ConfigControllers(cof, usDao, this);
    UsuariosControllers users = new UsuariosControllers(us, usDao, this);
    ClientesControllers cliente = new ClientesControllers(cl, clDao, this);
    ProveedorControllers proveedor = new ProveedorControllers(pr, prDao, this);
    CategoriaControllers categorias = new CategoriaControllers(cat, catDao, this);
    MedidaControllers medida = new MedidaControllers(md, mdDao, this);
    ProductosControllers producto = new ProductosControllers(pro, proDao, this);
    ComprasControllers compras = new ComprasControllers(comp, compDao, this);
    VentasControllers ventas = new VentasControllers(vent, ventDao, this);

    this.setLocationRelativeTo(null); // Centra la ventana
    txtIdUsers.setText("" + id);
    btnUsers.setText(nombre);
    jTabbedPane1.setSelectedIndex(9);
}


    
    
    public void cargarDatos() {
    ComprasDao comprasDao = new ComprasDao();
    ProductosDao productosDao = new ProductosDao();
    ClientesDao clientesDao = new ClientesDao();
    VentasDao ventasDao = new VentasDao();

    int totalCompras = comprasDao.contarCompras();
    int totalProductos = productosDao.contarProductos();
    int totalClientes = clientesDao.contarClientes();
    int totalVentas = ventasDao.contarVentas();

    lblTotalCompras.setText(String.valueOf(totalCompras));
    lblTotalProductos.setText(String.valueOf(totalProductos));
    lblTotalClientes.setText(String.valueOf(totalClientes));
    lblTotalVentas.setText(String.valueOf(totalVentas));
}
    
    
    

private void cargarGraficoProductos() {
    try {
        ProductosDao productosDao = new ProductosDao();
        GraficosReportes graficos = new GraficosReportes();

        // Obtener datos para los gráficos
        List<Productos> productosStockMinimo = productosDao.obtenerProductosStockMinimo();
        List<Productos> productosMasVendidos = productosDao.obtenerProductosMasVendidos();

        // Crear gráficos
        JPanel graficoStockMinimo = graficos.crearGraficoStockMinimo(productosStockMinimo);
        JPanel graficoMasVendidos = graficos.crearGraficoMasVendidos(productosMasVendidos);

        // Configurar paneles para los gráficos con BorderLayout
        panelGraficoStockMinimo.setLayout(new java.awt.BorderLayout());
        panelGraficoMasVendidos.setLayout(new java.awt.BorderLayout());

        // Agregar gráficos a los paneles
        panelGraficoStockMinimo.removeAll();
        panelGraficoStockMinimo.add(graficoStockMinimo, java.awt.BorderLayout.CENTER);
        panelGraficoStockMinimo.revalidate();
        panelGraficoStockMinimo.repaint();

        panelGraficoMasVendidos.removeAll();
        panelGraficoMasVendidos.add(graficoMasVendidos, java.awt.BorderLayout.CENTER);
        panelGraficoMasVendidos.revalidate();
        panelGraficoMasVendidos.repaint();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al cargar gráficos: " + e.getMessage());
    }
}















    @SuppressWarnings("unchecked")
    private void initComponents() {//GEN-BEGIN:initComponents

        JPopupUsuarios = new javax.swing.JPopupMenu();
        JMenuEliminarUsers = new javax.swing.JMenuItem();
        JMenuReingresarUsers = new javax.swing.JMenuItem();
        JPopupClientes = new javax.swing.JPopupMenu();
        JMenuEliminarCli = new javax.swing.JMenuItem();
        JMenuReingresarCli = new javax.swing.JMenuItem();
        JPopupProveedor = new javax.swing.JPopupMenu();
        JMenuEliminarProv = new javax.swing.JMenuItem();
        JMenuReingresarProv = new javax.swing.JMenuItem();
        JPopupCategorias = new javax.swing.JPopupMenu();
        JMenuEliminarCat = new javax.swing.JMenuItem();
        JMenuReingresarCat = new javax.swing.JMenuItem();
        JPopupMedidas = new javax.swing.JPopupMenu();
        JMenuEliminarMedida = new javax.swing.JMenuItem();
        JMenuReingresarMedida = new javax.swing.JMenuItem();
        JPopupProductos = new javax.swing.JPopupMenu();
        JMenuEliminarPro = new javax.swing.JMenuItem();
        JMenuReingresarPro = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        JPanelNuevaVenta = new javax.swing.JPanel();
        jLabelNuevaVenta = new javax.swing.JLabel();
        JPanelNuevaCompra = new javax.swing.JPanel();
        jLabelNuevaCompra = new javax.swing.JLabel();
        JPanelProductos = new javax.swing.JPanel();
        jLabelProductos = new javax.swing.JLabel();
        JPanelClientes = new javax.swing.JPanel();
        jLabelClientes = new javax.swing.JLabel();
        JPanelProveedor = new javax.swing.JPanel();
        jLabelProveedor = new javax.swing.JLabel();
        JPanelMedidas = new javax.swing.JPanel();
        jLabelMedidas = new javax.swing.JLabel();
        JPanelCategorias = new javax.swing.JPanel();
        jLabelCat = new javax.swing.JLabel();
        JPanelConfig = new javax.swing.JPanel();
        jLabelConfig = new javax.swing.JLabel();
        JPanelUsers = new javax.swing.JPanel();
        jLabelUsers = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelPuntoDeVenta = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtBuscarProducto = new javax.swing.JTextField();
        btnUsers = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        txtIdUsers = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        txtNombrePro = new javax.swing.JLabel();
        txtTelefonoPro = new javax.swing.JLabel();
        txtDireccionPro = new javax.swing.JLabel();
        txtNombreProv = new javax.swing.JTextField();
        txtTelefonoProv = new javax.swing.JTextField();
        btnNuevoProv = new javax.swing.JButton();
        btnRegistrarProv = new javax.swing.JButton();
        btnModificarProv = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtDireccionProv = new javax.swing.JTextPane();
        txtRucPro = new javax.swing.JLabel();
        txtRucProv = new javax.swing.JTextField();
        txtBuscarProv = new javax.swing.JTextField();
        txtIdProv = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TableProveedor = new javax.swing.JTable();
        PaginadoProv = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        txtNombrePro4 = new javax.swing.JLabel();
        txtTelefonoPro2 = new javax.swing.JLabel();
        txtDireccionPro2 = new javax.swing.JLabel();
        txtNombreProv1 = new javax.swing.JTextField();
        txtTelefonoProv1 = new javax.swing.JTextField();
        btnNuevoProv1 = new javax.swing.JButton();
        btnRegistrarProv1 = new javax.swing.JButton();
        btnModificarProv1 = new javax.swing.JButton();
        jScrollPane15 = new javax.swing.JScrollPane();
        txtDireccionProv1 = new javax.swing.JTextPane();
        txtRucPro2 = new javax.swing.JLabel();
        txtRucProv1 = new javax.swing.JTextField();
        txtBuscarProv1 = new javax.swing.JTextField();
        txtIdProv1 = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        TableProveedor1 = new javax.swing.JTable();
        PaginadoProv1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        txtNombrePro1 = new javax.swing.JLabel();
        txtTelefonoPro1 = new javax.swing.JLabel();
        txtDireccionPro1 = new javax.swing.JLabel();
        txtNombreUser = new javax.swing.JTextField();
        btnNuevoUser = new javax.swing.JButton();
        btnRegistrarUser = new javax.swing.JButton();
        btnModificarUser = new javax.swing.JButton();
        txtRucPro1 = new javax.swing.JLabel();
        txtUsuarioUser = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cbxCajaUser = new javax.swing.JComboBox<>();
        cbxRolUser = new javax.swing.JComboBox<>();
        txtClaveUser = new javax.swing.JPasswordField();
        txtIdUser = new javax.swing.JTextField();
        txtBuscarUsers = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        TableUsers = new javax.swing.JTable();
        PaginadoUsers = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        txtNombrePro2 = new javax.swing.JLabel();
        txtNombreCat = new javax.swing.JTextField();
        btnNuevoCat = new javax.swing.JButton();
        btnRegistrarCat = new javax.swing.JButton();
        btnModificarCat = new javax.swing.JButton();
        txtBuscarCat = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        txtIdCat = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        TableCat = new javax.swing.JTable();
        PaginadoCat = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        txtNombrePro3 = new javax.swing.JLabel();
        txtNombreMedida = new javax.swing.JTextField();
        btnNuevoMedida = new javax.swing.JButton();
        btnRegistrarMedida = new javax.swing.JButton();
        btnModificarMedida = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtNombreCortoMedida = new javax.swing.JTextField();
        txtBuscarMed = new javax.swing.JTextField();
        txtIdMedida = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        TableMedida = new javax.swing.JTable();
        PaginadoMedida = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        TableVentas = new javax.swing.JTable();
        PaginadoVentas = new javax.swing.JPanel();
        btnHistorialVenta = new javax.swing.JButton();
        txtBuscarVenta = new javax.swing.JTextField();
        txtIdVenta = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        TableCompras = new javax.swing.JTable();
        txtBuscarCompra = new javax.swing.JTextField();
        btnHistorialCompra = new javax.swing.JButton();
        txtIdCompra = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtNombreEmpresa = new javax.swing.JTextField();
        txtTelefonoEmpresa = new javax.swing.JTextField();
        btnModificarEmpresa = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        txtDireccionEmpresa = new javax.swing.JTextPane();
        jLabel36 = new javax.swing.JLabel();
        txtRucEmpresa = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        txtMensaje = new javax.swing.JTextPane();
        txtIdEmpresa = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCodigoPro = new javax.swing.JTextField();
        txtDescripcionPro = new javax.swing.JTextField();
        txtPrecioCompraPro = new javax.swing.JTextField();
        txtPrecioVentaPro = new javax.swing.JTextField();
        cbxProveedorPro = new javax.swing.JComboBox<>();
        cbxMedidaPro = new javax.swing.JComboBox<>();
        cbxCatPro = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        btnNuevoPro = new javax.swing.JButton();
        btnRegistrarPro = new javax.swing.JButton();
        btnModificarPro = new javax.swing.JButton();
        txtIdPro = new javax.swing.JTextField();
        btnGenerarExcel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableProductos = new javax.swing.JTable();
        PaginadorPro = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNombreCli = new javax.swing.JTextField();
        txtTelefonoCli = new javax.swing.JTextField();
        btnNuevoCli = new javax.swing.JButton();
        btnRegistrarCli = new javax.swing.JButton();
        btnModificarCli = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDireccionCli = new javax.swing.JTextPane();
        txtIdCli = new javax.swing.JTextField();
        txtBuscarCli = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableClientes = new javax.swing.JTable();
        PaginadorCli = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        TableNuevaVenta = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtCodNV = new javax.swing.JTextField();
        txtProductoNV = new javax.swing.JTextField();
        txtCantNV = new javax.swing.JTextField();
        txtPrecioNV = new javax.swing.JTextField();
        txtTotalNV = new javax.swing.JTextField();
        txtStock = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        btnGenerarVenta = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        cbxClienteVentas = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        JLabelTotalPagar = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtIdNV = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jScrollPane10 = new javax.swing.JScrollPane();
        TableNuevaCompra = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtCodNC = new javax.swing.JTextField();
        txtProductoNC = new javax.swing.JTextField();
        txtCantNC = new javax.swing.JTextField();
        txtPrecioNC = new javax.swing.JTextField();
        txtTotalNC = new javax.swing.JTextField();
        btnGenerarCompra = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        cbxProCompra = new javax.swing.JComboBox<>();
        txtPagarConNC = new javax.swing.JTextField();
        txtVueltoCompra = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        JLabelTotalCompra = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txtIdNC = new javax.swing.JTextField();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        lblTotalProductos = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        lblTotalCompras = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        lblTotalClientes = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        lblTotalVentas = new javax.swing.JLabel();
        panelGraficoStockMinimo = new javax.swing.JPanel();
        panelGraficoMasVendidos = new javax.swing.JPanel();

        JMenuEliminarUsers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        JMenuEliminarUsers.setText("Eliminar");
        JPopupUsuarios.add(JMenuEliminarUsers);

        JMenuReingresarUsers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/exchange.png"))); // NOI18N
        JMenuReingresarUsers.setText("Reingresar");
        JPopupUsuarios.add(JMenuReingresarUsers);

        JMenuEliminarCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        JMenuEliminarCli.setText("Eliminar");
        JPopupClientes.add(JMenuEliminarCli);

        JMenuReingresarCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/exchange.png"))); // NOI18N
        JMenuReingresarCli.setText("Reingresar");
        JPopupClientes.add(JMenuReingresarCli);

        JMenuEliminarProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        JMenuEliminarProv.setText("Eliminar");
        JPopupProveedor.add(JMenuEliminarProv);

        JMenuReingresarProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/exchange.png"))); // NOI18N
        JMenuReingresarProv.setText("Reingresar");
        JPopupProveedor.add(JMenuReingresarProv);

        JMenuEliminarCat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        JMenuEliminarCat.setText("Eliminar");
        JPopupCategorias.add(JMenuEliminarCat);

        JMenuReingresarCat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/exchange.png"))); // NOI18N
        JMenuReingresarCat.setText("Reingresar");
        JPopupCategorias.add(JMenuReingresarCat);

        JMenuEliminarMedida.setText("Eliminar");
        JPopupMedidas.add(JMenuEliminarMedida);

        JMenuReingresarMedida.setText("Reingresar");
        JPopupMedidas.add(JMenuReingresarMedida);

        JMenuEliminarPro.setText("Eliminar");
        JPopupProductos.add(JMenuEliminarPro);

        JMenuReingresarPro.setText("Reingresar");
        JPopupProductos.add(JMenuReingresarPro);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JPanelNuevaVenta.setBackground(new java.awt.Color(102, 102, 102));

        jLabelNuevaVenta.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabelNuevaVenta.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNuevaVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNuevaVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Nventa.png"))); // NOI18N
        jLabelNuevaVenta.setText("Nueva Venta");
        jLabelNuevaVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout JPanelNuevaVentaLayout = new javax.swing.GroupLayout(JPanelNuevaVenta);
        JPanelNuevaVenta.setLayout(JPanelNuevaVentaLayout);
        JPanelNuevaVentaLayout.setHorizontalGroup(
            JPanelNuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelNuevaVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        JPanelNuevaVentaLayout.setVerticalGroup(
            JPanelNuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelNuevaVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(JPanelNuevaVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 40));

        JPanelNuevaCompra.setBackground(new java.awt.Color(102, 102, 102));

        jLabelNuevaCompra.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabelNuevaCompra.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNuevaCompra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNuevaCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Carrito-de-compras.png"))); // NOI18N
        jLabelNuevaCompra.setText("Nueva Compra");
        jLabelNuevaCompra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout JPanelNuevaCompraLayout = new javax.swing.GroupLayout(JPanelNuevaCompra);
        JPanelNuevaCompra.setLayout(JPanelNuevaCompraLayout);
        JPanelNuevaCompraLayout.setHorizontalGroup(
            JPanelNuevaCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelNuevaCompra, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        JPanelNuevaCompraLayout.setVerticalGroup(
            JPanelNuevaCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelNuevaCompra, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(JPanelNuevaCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 200, 40));

        JPanelProductos.setBackground(new java.awt.Color(102, 102, 102));

        jLabelProductos.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabelProductos.setForeground(new java.awt.Color(255, 255, 255));
        jLabelProductos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/producto.png"))); // NOI18N
        jLabelProductos.setText("Productos");
        jLabelProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout JPanelProductosLayout = new javax.swing.GroupLayout(JPanelProductos);
        JPanelProductos.setLayout(JPanelProductosLayout);
        JPanelProductosLayout.setHorizontalGroup(
            JPanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelProductos, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        JPanelProductosLayout.setVerticalGroup(
            JPanelProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelProductos, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(JPanelProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 200, 40));

        JPanelClientes.setBackground(new java.awt.Color(102, 102, 102));

        jLabelClientes.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabelClientes.setForeground(new java.awt.Color(255, 255, 255));
        jLabelClientes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Clientes.png"))); // NOI18N
        jLabelClientes.setText("Clientes");
        jLabelClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout JPanelClientesLayout = new javax.swing.GroupLayout(JPanelClientes);
        JPanelClientes.setLayout(JPanelClientesLayout);
        JPanelClientesLayout.setHorizontalGroup(
            JPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        JPanelClientesLayout.setVerticalGroup(
            JPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(JPanelClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 200, 40));

        JPanelProveedor.setBackground(new java.awt.Color(102, 102, 102));

        jLabelProveedor.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabelProveedor.setForeground(new java.awt.Color(255, 255, 255));
        jLabelProveedor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/proveedor.png"))); // NOI18N
        jLabelProveedor.setText("Proveedores");
        jLabelProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout JPanelProveedorLayout = new javax.swing.GroupLayout(JPanelProveedor);
        JPanelProveedor.setLayout(JPanelProveedorLayout);
        JPanelProveedorLayout.setHorizontalGroup(
            JPanelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        JPanelProveedorLayout.setVerticalGroup(
            JPanelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(JPanelProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 200, 40));

        JPanelMedidas.setBackground(new java.awt.Color(102, 102, 102));

        jLabelMedidas.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabelMedidas.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMedidas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMedidas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/detallista.png"))); // NOI18N
        jLabelMedidas.setText("Medidas");
        jLabelMedidas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout JPanelMedidasLayout = new javax.swing.GroupLayout(JPanelMedidas);
        JPanelMedidas.setLayout(JPanelMedidasLayout);
        JPanelMedidasLayout.setHorizontalGroup(
            JPanelMedidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelMedidas, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        JPanelMedidasLayout.setVerticalGroup(
            JPanelMedidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelMedidas, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(JPanelMedidas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 200, 40));

        JPanelCategorias.setBackground(new java.awt.Color(102, 102, 102));

        jLabelCat.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabelCat.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/box.png"))); // NOI18N
        jLabelCat.setText("Categorias");
        jLabelCat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout JPanelCategoriasLayout = new javax.swing.GroupLayout(JPanelCategorias);
        JPanelCategorias.setLayout(JPanelCategoriasLayout);
        JPanelCategoriasLayout.setHorizontalGroup(
            JPanelCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelCat, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        JPanelCategoriasLayout.setVerticalGroup(
            JPanelCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelCat, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(JPanelCategorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 200, 40));

        JPanelConfig.setBackground(new java.awt.Color(102, 102, 102));

        jLabelConfig.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabelConfig.setForeground(new java.awt.Color(255, 255, 255));
        jLabelConfig.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/config.png"))); // NOI18N
        jLabelConfig.setText("Configuracion");
        jLabelConfig.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout JPanelConfigLayout = new javax.swing.GroupLayout(JPanelConfig);
        JPanelConfig.setLayout(JPanelConfigLayout);
        JPanelConfigLayout.setHorizontalGroup(
            JPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelConfig, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        JPanelConfigLayout.setVerticalGroup(
            JPanelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelConfig, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(JPanelConfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 200, 40));

        JPanelUsers.setBackground(new java.awt.Color(102, 102, 102));

        jLabelUsers.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabelUsers.setForeground(new java.awt.Color(255, 255, 255));
        jLabelUsers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelUsers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/users.png"))); // NOI18N
        jLabelUsers.setText("Usuarios");
        jLabelUsers.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout JPanelUsersLayout = new javax.swing.GroupLayout(JPanelUsers);
        JPanelUsers.setLayout(JPanelUsersLayout);
        JPanelUsersLayout.setHorizontalGroup(
            JPanelUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelUsers, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        JPanelUsersLayout.setVerticalGroup(
            JPanelUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelUsers, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(JPanelUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 200, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 200, 560));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelPuntoDeVenta.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabelPuntoDeVenta.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPuntoDeVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPuntoDeVenta.setText("Punto De Venta");
        jPanel2.add(jLabelPuntoDeVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 39, 200, 26));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 100));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(txtBuscarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 30, 250, 35));

        btnUsers.setText("Users");
        jPanel3.add(btnUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 30, 150, 35));
        jPanel3.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, -1, -1));

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/consultas.png"))); // NOI18N
        jPanel3.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, -1, -1));
        jPanel3.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 460, -1));

        jLabel41.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("Finca Latina");
        jPanel3.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(-220, -90, 1090, 280));

        txtIdUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdUsersActionPerformed(evt);
            }
        });
        jPanel3.add(txtIdUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 70, -1, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 1100, 100));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setComponentPopupMenu(JPopupProductos);

        jPanel6.setBackground(new java.awt.Color(102, 204, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Nuevo Proveedor"));

        txtNombrePro.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtNombrePro.setText("Nombre");

        txtTelefonoPro.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtTelefonoPro.setText("Telefono");

        txtDireccionPro.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtDireccionPro.setText("Direccion");

        btnNuevoProv.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnNuevoProv.setText("Nuevo");
        btnNuevoProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProvActionPerformed(evt);
            }
        });

        btnRegistrarProv.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnRegistrarProv.setText("Registrar");

        btnModificarProv.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnModificarProv.setText("Modificar");

        jScrollPane4.setViewportView(txtDireccionProv);

        txtRucPro.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtRucPro.setText("Ruc");

        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/lupa.png"))); // NOI18N

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDireccionPro)
                                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtRucPro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtNombrePro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(txtTelefonoPro))
                                .addGap(32, 32, 32))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtBuscarProv)
                            .addComponent(txtNombreProv, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                            .addComponent(txtTelefonoProv)
                            .addComponent(jScrollPane4)
                            .addComponent(txtRucProv)))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(btnNuevoProv, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(btnRegistrarProv)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModificarProv))
                            .addComponent(txtIdProv, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(txtBuscarProv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtRucPro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(txtRucProv, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreProv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombrePro))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefonoProv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefonoPro))
                .addGap(21, 21, 21)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDireccionPro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(txtIdProv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevoProv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistrarProv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificarProv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
        );

        jPanel6.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 310, 480));

        TableProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Ruc", "Nombre", "Telefono", "Direccion", "Estado"
            }
        ));
        TableProveedor.setComponentPopupMenu(JPopupProveedor);
        jScrollPane5.setViewportView(TableProveedor);

        jPanel6.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 730, 380));

        javax.swing.GroupLayout PaginadoProvLayout = new javax.swing.GroupLayout(PaginadoProv);
        PaginadoProv.setLayout(PaginadoProvLayout);
        PaginadoProvLayout.setHorizontalGroup(
            PaginadoProvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        PaginadoProvLayout.setVerticalGroup(
            PaginadoProvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        jPanel6.add(PaginadoProv, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 410, 730, 90));

        jPanel14.setBackground(new java.awt.Color(102, 204, 255));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder("Nuevo Proveedor"));

        txtNombrePro4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtNombrePro4.setText("Nombre");

        txtTelefonoPro2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtTelefonoPro2.setText("Telefono");

        txtDireccionPro2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtDireccionPro2.setText("Direccion");

        btnNuevoProv1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnNuevoProv1.setText("Nuevo");
        btnNuevoProv1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProv1ActionPerformed(evt);
            }
        });

        btnRegistrarProv1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnRegistrarProv1.setText("Registrar");

        btnModificarProv1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnModificarProv1.setText("Modificar");

        jScrollPane15.setViewportView(txtDireccionProv1);

        txtRucPro2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtRucPro2.setText("Ruc");

        jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/lupa.png"))); // NOI18N

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDireccionPro2)
                                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtRucPro2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtNombrePro4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(txtTelefonoPro2))
                                .addGap(32, 32, 32))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtBuscarProv1)
                            .addComponent(txtNombreProv1, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                            .addComponent(txtTelefonoProv1)
                            .addComponent(jScrollPane15)
                            .addComponent(txtRucProv1)))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(btnNuevoProv1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(btnRegistrarProv1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModificarProv1))
                            .addComponent(txtIdProv1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(txtBuscarProv1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtRucPro2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(txtRucProv1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreProv1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombrePro4))
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefonoProv1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefonoPro2))
                .addGap(21, 21, 21)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDireccionPro2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(txtIdProv1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevoProv1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistrarProv1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificarProv1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
        );

        jPanel14.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 310, 480));

        TableProveedor1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Ruc", "Nombre", "Telefono", "Direccion", "Estado"
            }
        ));
        TableProveedor1.setComponentPopupMenu(JPopupProveedor);
        jScrollPane16.setViewportView(TableProveedor1);

        jPanel14.add(jScrollPane16, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 730, 380));

        javax.swing.GroupLayout PaginadoProv1Layout = new javax.swing.GroupLayout(PaginadoProv1);
        PaginadoProv1.setLayout(PaginadoProv1Layout);
        PaginadoProv1Layout.setHorizontalGroup(
            PaginadoProv1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        PaginadoProv1Layout.setVerticalGroup(
            PaginadoProv1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        jPanel14.add(PaginadoProv1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 410, 730, 90));

        jPanel6.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jTabbedPane1.addTab("Proveedor", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 102, 102));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder("Nuevo Usuario"));

        txtNombrePro1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtNombrePro1.setText("Nombre");

        txtTelefonoPro1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtTelefonoPro1.setText("Contraseña");

        txtDireccionPro1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtDireccionPro1.setText("Caja");

        btnNuevoUser.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnNuevoUser.setText("Nuevo");

        btnRegistrarUser.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnRegistrarUser.setText("Registrar");

        btnModificarUser.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnModificarUser.setText("Modificar");

        txtRucPro1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtRucPro1.setText("Usuario");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setText("Rol");

        cbxCajaUser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "General" }));

        cbxRolUser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador" }));

        txtBuscarUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarUsersActionPerformed(evt);
            }
        });

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/lupa.png"))); // NOI18N

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(btnNuevoUser, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRegistrarUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificarUser))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDireccionPro1)
                            .addComponent(txtTelefonoPro1)
                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtRucPro1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNombrePro1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel12)
                            .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdUser, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNombreUser)
                                .addComponent(txtUsuarioUser)
                                .addComponent(cbxCajaUser, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbxRolUser, 0, 173, Short.MAX_VALUE)
                                .addComponent(txtClaveUser)
                                .addComponent(txtBuscarUsers)))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscarUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtRucPro1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(txtUsuarioUser, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreUser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombrePro1))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefonoPro1)
                    .addComponent(txtClaveUser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccionPro1)
                    .addComponent(cbxCajaUser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cbxRolUser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtIdUser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevoUser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistrarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76))
        );

        jPanel7.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 310, 480));

        TableUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Usuario", "Nombre", "Rol", "Caja", "Estado"
            }
        ));
        TableUsers.setComponentPopupMenu(JPopupUsuarios);
        jScrollPane7.setViewportView(TableUsers);

        jPanel7.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 730, 380));

        javax.swing.GroupLayout PaginadoUsersLayout = new javax.swing.GroupLayout(PaginadoUsers);
        PaginadoUsers.setLayout(PaginadoUsersLayout);
        PaginadoUsersLayout.setHorizontalGroup(
            PaginadoUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        PaginadoUsersLayout.setVerticalGroup(
            PaginadoUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        jPanel7.add(PaginadoUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 410, 730, 90));

        jTabbedPane1.addTab("Nuevo Usuario", jPanel7);

        jPanel8.setBackground(new java.awt.Color(51, 153, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder("Nueva Categoria"));

        txtNombrePro2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtNombrePro2.setText("Nombre");

        btnNuevoCat.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnNuevoCat.setText("Nuevo");

        btnRegistrarCat.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnRegistrarCat.setText("Registrar");

        btnModificarCat.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnModificarCat.setText("Modificar");

        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/lupa.png"))); // NOI18N

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(btnNuevoCat, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRegistrarCat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificarCat))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(txtNombrePro2)
                                .addGap(55, 55, 55))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel45)
                                .addGap(25, 25, 25)))
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdCat, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtBuscarCat)
                                .addComponent(txtNombreCat, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap(74, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarCat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreCat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombrePro2))
                .addGap(18, 18, 18)
                .addComponent(txtIdCat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevoCat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistrarCat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificarCat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(142, 142, 142))
        );

        jPanel8.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 310, 480));

        TableCat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Estado"
            }
        ));
        TableCat.setComponentPopupMenu(JPopupCategorias);
        jScrollPane8.setViewportView(TableCat);

        jPanel8.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 730, 380));

        javax.swing.GroupLayout PaginadoCatLayout = new javax.swing.GroupLayout(PaginadoCat);
        PaginadoCat.setLayout(PaginadoCatLayout);
        PaginadoCatLayout.setHorizontalGroup(
            PaginadoCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        PaginadoCatLayout.setVerticalGroup(
            PaginadoCatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        jPanel8.add(PaginadoCat, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 410, 730, 90));

        jTabbedPane1.addTab("Nueva Categoria", jPanel8);

        jPanel9.setBackground(new java.awt.Color(51, 255, 204));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder("Nueva Medida"));

        txtNombrePro3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtNombrePro3.setText("Nombre");

        btnNuevoMedida.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnNuevoMedida.setText("Nuevo");

        btnRegistrarMedida.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnRegistrarMedida.setText("Registrar");

        btnModificarMedida.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnModificarMedida.setText("Modificar");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setText("Nombre Corto");

        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/lupa.png"))); // NOI18N

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtIdMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel21Layout.createSequentialGroup()
                            .addGap(14, 14, 14)
                            .addComponent(btnNuevoMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnRegistrarMedida)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnModificarMedida))
                        .addGroup(jPanel21Layout.createSequentialGroup()
                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel21Layout.createSequentialGroup()
                                    .addComponent(txtNombrePro3)
                                    .addGap(55, 55, 55))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel46)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)))
                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtBuscarMed)
                                .addComponent(txtNombreMedida, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                                .addComponent(txtNombreCortoMedida)))))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarMed, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombrePro3))
                .addGap(34, 34, 34)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNombreCortoMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(128, 128, 128)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevoMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistrarMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificarMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(85, 85, 85)
                .addComponent(txtIdMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jPanel9.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 310, 480));

        TableMedida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Nombre Corto", "Estado"
            }
        ));
        TableMedida.setComponentPopupMenu(JPopupMedidas);
        jScrollPane9.setViewportView(TableMedida);

        jPanel9.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 730, 380));

        javax.swing.GroupLayout PaginadoMedidaLayout = new javax.swing.GroupLayout(PaginadoMedida);
        PaginadoMedida.setLayout(PaginadoMedidaLayout);
        PaginadoMedidaLayout.setHorizontalGroup(
            PaginadoMedidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        PaginadoMedidaLayout.setVerticalGroup(
            PaginadoMedidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        jPanel9.add(PaginadoMedida, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 410, 730, 90));

        jTabbedPane1.addTab("Nueva Medida", jPanel9);

        jPanel11.setBackground(new java.awt.Color(204, 204, 0));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Cliente", "Total", "Fecha"
            }
        ));
        jScrollPane11.setViewportView(TableVentas);
        if (TableVentas.getColumnModel().getColumnCount() > 0) {
            TableVentas.getColumnModel().getColumn(0).setMinWidth(80);
            TableVentas.getColumnModel().getColumn(0).setPreferredWidth(80);
            TableVentas.getColumnModel().getColumn(0).setMaxWidth(120);
            TableVentas.getColumnModel().getColumn(2).setMinWidth(150);
            TableVentas.getColumnModel().getColumn(2).setPreferredWidth(150);
            TableVentas.getColumnModel().getColumn(2).setMaxWidth(200);
            TableVentas.getColumnModel().getColumn(3).setMinWidth(200);
            TableVentas.getColumnModel().getColumn(3).setPreferredWidth(200);
            TableVentas.getColumnModel().getColumn(3).setMaxWidth(250);
        }

        jPanel11.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 1050, 310));

        javax.swing.GroupLayout PaginadoVentasLayout = new javax.swing.GroupLayout(PaginadoVentas);
        PaginadoVentas.setLayout(PaginadoVentasLayout);
        PaginadoVentasLayout.setHorizontalGroup(
            PaginadoVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PaginadoVentasLayout.setVerticalGroup(
            PaginadoVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel11.add(PaginadoVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 1050, 50));

        btnHistorialVenta.setText("Generar Reporte");
        btnHistorialVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistorialVentaActionPerformed(evt);
            }
        });
        jPanel11.add(btnHistorialVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 120, 30));
        jPanel11.add(txtBuscarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 340, 30));

        txtIdVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdVentaActionPerformed(evt);
            }
        });
        jPanel11.add(txtIdVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 70, -1, -1));

        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/lupa.png"))); // NOI18N
        jPanel11.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 40, 40));

        jTabbedPane1.addTab("Ventas", jPanel11);

        jPanel12.setBackground(new java.awt.Color(0, 153, 153));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableCompras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Proveedor", "Total", "Fecha"
            }
        ));
        TableCompras.setRowHeight(23);
        jScrollPane12.setViewportView(TableCompras);

        jPanel12.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 1050, 390));
        jPanel12.add(txtBuscarCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 400, 30));

        btnHistorialCompra.setText("Generar Reporte");
        jPanel12.add(btnHistorialCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, 120, 30));
        jPanel12.add(txtIdCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 60, -1, -1));

        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/lupa.png"))); // NOI18N
        jPanel12.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jTabbedPane1.addTab("Compras", jPanel12);

        jPanel13.setBackground(new java.awt.Color(0, 255, 204));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de la empresa"));

        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel29.setText("Nombre");

        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel34.setText("Telefono");

        jLabel35.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel35.setText("Direccion");

        btnModificarEmpresa.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnModificarEmpresa.setText("Modificar");

        jScrollPane13.setViewportView(txtDireccionEmpresa);

        jLabel36.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel36.setText("Ruc");

        jLabel37.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel37.setText("Mensaje");

        jScrollPane14.setViewportView(txtMensaje);

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addComponent(txtIdEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnModificarEmpresa)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addComponent(txtTelefonoEmpresa)
                    .addComponent(txtNombreEmpresa)
                    .addComponent(txtRucEmpresa)
                    .addComponent(jScrollPane14))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(txtRucEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefonoEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addGap(21, 21, 21)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addGap(37, 37, 37)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificarEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jPanel13.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 320, 480));

        jTabbedPane1.addTab("Configuracion", jPanel13);

        jPanel4.setBackground(new java.awt.Color(102, 255, 102));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Nuevo Producto"));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Codigo");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Descripcion");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Precio Compra");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Precio Venta");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("Proveedor");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Medida");

        cbxProveedorPro.setEditable(true);

        cbxMedidaPro.setEditable(true);

        cbxCatPro.setEditable(true);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("Categoria");

        btnNuevoPro.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnNuevoPro.setText("Nuevo");

        btnRegistrarPro.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnRegistrarPro.setText("Registrar");

        btnModificarPro.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnModificarPro.setText("Modificar");

        btnGenerarExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/excel.png"))); // NOI18N
        btnGenerarExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtCodigoPro, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cbxProveedorPro, 0, 148, Short.MAX_VALUE)
                                .addComponent(cbxMedidaPro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbxCatPro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtDescripcionPro))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtPrecioVentaPro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                    .addComponent(txtPrecioCompraPro, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtIdPro, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnGenerarExcel)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(btnNuevoPro, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificarPro)
                        .addGap(18, 18, 18)
                        .addComponent(btnRegistrarPro)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCodigoPro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDescripcionPro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPrecioCompraPro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtPrecioVentaPro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdPro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbxProveedorPro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbxMedidaPro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxCatPro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(12, 12, 12)
                .addComponent(btnGenerarExcel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificarPro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevoPro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistrarPro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 21, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 330, 480));

        TableProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Codigo", "Descripcion", "Precio", "Stock", "Estado"
            }
        ));
        TableProductos.setComponentPopupMenu(JPopupProductos);
        TableProductos.setRowHeight(23);
        jScrollPane1.setViewportView(TableProductos);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 730, 380));

        javax.swing.GroupLayout PaginadorProLayout = new javax.swing.GroupLayout(PaginadorPro);
        PaginadorPro.setLayout(PaginadorProLayout);
        PaginadorProLayout.setHorizontalGroup(
            PaginadorProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        PaginadorProLayout.setVerticalGroup(
            PaginadorProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        jPanel4.add(PaginadorPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 410, 730, 90));

        jTabbedPane1.addTab("Nuevo Producto", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 51));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder("Nuevo Cliente"));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("Nombre");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setText("Telefono");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("Direccion");

        btnNuevoCli.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnNuevoCli.setText("Nuevo");

        btnRegistrarCli.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnRegistrarCli.setText("Registrar");

        btnModificarCli.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnModificarCli.setText("Modificar");

        jScrollPane3.setViewportView(txtDireccionCli);

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/lupa.png"))); // NOI18N

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(btnNuevoCli, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRegistrarCli)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificarCli))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9))
                                .addGap(32, 32, 32))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel43)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombreCli)
                            .addComponent(txtTelefonoCli)
                            .addComponent(jScrollPane3)
                            .addComponent(txtBuscarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdCli, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBuscarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreCli, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTelefonoCli, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(21, 21, 21)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(10, 10, 10)
                .addComponent(txtIdCli, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevoCli, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistrarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 310, 480));

        TableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Telefono", "Direccion", "Estado"
            }
        ));
        TableClientes.setComponentPopupMenu(JPopupClientes);
        jScrollPane2.setViewportView(TableClientes);

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 730, 380));

        javax.swing.GroupLayout PaginadorCliLayout = new javax.swing.GroupLayout(PaginadorCli);
        PaginadorCli.setLayout(PaginadorCliLayout);
        PaginadorCliLayout.setHorizontalGroup(
            PaginadorCliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        PaginadorCliLayout.setVerticalGroup(
            PaginadorCliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        jPanel5.add(PaginadorCli, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 410, 730, 90));

        jTabbedPane1.addTab("Nuevo Cliente", jPanel5);

        jPanel10.setBackground(new java.awt.Color(255, 204, 204));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel10.add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, -1, 180));

        TableNuevaVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Descripcion", "Cantidad", "Precio", "Total"
            }
        ));
        jScrollPane6.setViewportView(TableNuevaVenta);

        jPanel10.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 147, 1050, 270));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setText("Codigo");
        jPanel10.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel15.setText("Producto");
        jPanel10.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, -1, -1));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel16.setText("Cant");
        jPanel10.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, -1, -1));

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel17.setText("Precio");
        jPanel10.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, -1, -1));

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel18.setText("Total");
        jPanel10.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 60, -1, -1));
        jPanel10.add(txtCodNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 130, 30));
        jPanel10.add(txtProductoNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 210, 30));
        jPanel10.add(txtCantNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, 70, 30));
        jPanel10.add(txtPrecioNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, 60, 30));
        jPanel10.add(txtTotalNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 100, 60, 30));
        jPanel10.add(txtStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 100, 50, 30));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel19.setText("Stock");
        jPanel10.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 60, -1, -1));

        btnGenerarVenta.setBackground(new java.awt.Color(204, 204, 204));
        btnGenerarVenta.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnGenerarVenta.setText("Generar");
        jPanel10.add(btnGenerarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 100, -1, -1));

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel20.setText("Cliente");
        jPanel10.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, -1, -1));

        jPanel10.add(cbxClienteVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 460, 200, -1));
        jPanel10.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 460, 100, -1));
        jPanel10.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 460, 110, -1));

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel21.setText("Total Pagar");
        jPanel10.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 460, -1, -1));

        JLabelTotalPagar.setText("-------------");
        jPanel10.add(JLabelTotalPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 460, -1, -1));

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel22.setText("Pagar con");
        jPanel10.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 460, -1, -1));

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel23.setText("Vuelto");
        jPanel10.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 460, -1, -1));
        jPanel10.add(txtIdNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 100, 40, -1));

        jTabbedPane1.addTab("Nueva Venta", jPanel10);

        jPanel16.setBackground(new java.awt.Color(255, 204, 204));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel16.add(jTabbedPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, -1, 180));

        TableNuevaCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Descripcion", "Cantidad", "Precio", "Total"
            }
        ));
        TableNuevaCompra.setRowHeight(23);
        jScrollPane10.setViewportView(TableNuevaCompra);

        jPanel16.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 1050, 270));

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel24.setText("Codigo");
        jPanel16.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel25.setText("Producto");
        jPanel16.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, -1, -1));

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel26.setText("Cant");
        jPanel16.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, -1, -1));

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel27.setText("Precio");
        jPanel16.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, -1, -1));

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel28.setText("Total");
        jPanel16.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 60, -1, -1));
        jPanel16.add(txtCodNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 130, 30));
        jPanel16.add(txtProductoNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 210, 30));
        jPanel16.add(txtCantNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, 70, 30));
        jPanel16.add(txtPrecioNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 100, 60, 30));
        jPanel16.add(txtTotalNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 100, 60, 30));

        btnGenerarCompra.setBackground(new java.awt.Color(204, 204, 204));
        btnGenerarCompra.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnGenerarCompra.setText("Generar Compra");
        btnGenerarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarCompraActionPerformed(evt);
            }
        });
        jPanel16.add(btnGenerarCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 90, -1, -1));

        jLabel30.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel30.setText("Proveedor");
        jPanel16.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, -1, -1));

        jPanel16.add(cbxProCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 460, 200, -1));
        jPanel16.add(txtPagarConNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 460, 100, -1));
        jPanel16.add(txtVueltoCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 460, 110, -1));

        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel31.setText("Total Pagar");
        jPanel16.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 460, -1, -1));

        JLabelTotalCompra.setText("-------------");
        jPanel16.add(JLabelTotalCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 460, -1, -1));

        jLabel32.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel32.setText("Pagar con");
        jPanel16.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 460, -1, -1));

        jLabel33.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel33.setText("Vuelto");
        jPanel16.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 460, -1, -1));
        jPanel16.add(txtIdNC, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 100, 30, 30));

        jTabbedPane1.addTab("Nueva Compra", jPanel16);

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));

        jPanel25.setBackground(new java.awt.Color(51, 204, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Productos");

        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/producto.png"))); // NOI18N

        lblTotalProductos.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblTotalProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel50))))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTotalProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel26.setBackground(new java.awt.Color(51, 204, 0));

        jLabel51.setBackground(new java.awt.Color(255, 255, 255));
        jLabel51.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("Compras");

        jLabel52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/compras.png"))); // NOI18N

        lblTotalCompras.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel52)
                        .addComponent(jLabel51))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(lblTotalCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel52)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTotalCompras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel27.setBackground(new java.awt.Color(255, 255, 102));

        jLabel53.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel53.setText("Clientes");

        jLabel54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Clientes.png"))); // NOI18N

        lblTotalClientes.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel53)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblTotalClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel54))))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel53)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel54)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTotalClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel28.setBackground(new java.awt.Color(255, 102, 102));

        jLabel55.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("Ventas");

        jLabel56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Carrito-de-compras.png"))); // NOI18N

        lblTotalVentas.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblTotalVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel56)
                    .addComponent(jLabel55))
                .addGap(70, 70, 70))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel56)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        panelGraficoStockMinimo.setPreferredSize(new java.awt.Dimension(400, 300));

        javax.swing.GroupLayout panelGraficoStockMinimoLayout = new javax.swing.GroupLayout(panelGraficoStockMinimo);
        panelGraficoStockMinimo.setLayout(panelGraficoStockMinimoLayout);
        panelGraficoStockMinimoLayout.setHorizontalGroup(
            panelGraficoStockMinimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        panelGraficoStockMinimoLayout.setVerticalGroup(
            panelGraficoStockMinimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        panelGraficoMasVendidos.setPreferredSize(new java.awt.Dimension(400, 300));

        javax.swing.GroupLayout panelGraficoMasVendidosLayout = new javax.swing.GroupLayout(panelGraficoMasVendidos);
        panelGraficoMasVendidos.setLayout(panelGraficoMasVendidosLayout);
        panelGraficoMasVendidosLayout.setHorizontalGroup(
            panelGraficoMasVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        panelGraficoMasVendidosLayout.setVerticalGroup(
            panelGraficoMasVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(panelGraficoStockMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(163, 163, 163)
                        .addComponent(panelGraficoMasVendidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelGraficoStockMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelGraficoMasVendidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab12", jPanel24);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 1100, 550));

        pack();
    }//GEN-END:initComponents

    private void txtBuscarUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarUsersActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarUsersActionPerformed

    private void btnGenerarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGenerarCompraActionPerformed

    private void btnHistorialVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHistorialVentaActionPerformed

    private void txtIdVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdVentaActionPerformed

    private void txtIdUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdUsersActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdUsersActionPerformed

    private void btnNuevoProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevoProvActionPerformed

    private void btnGenerarExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarExcelActionPerformed
         try {
        Reportes.Excel.reporte(); // Llama al método que genera el reporte
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al generar el reporte: " + e.getMessage());
    }
    }//GEN-LAST:event_btnGenerarExcelActionPerformed

    private void btnNuevoProv1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProv1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevoProv1ActionPerformed

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
            java.util.logging.Logger.getLogger(PanelAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PanelAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PanelAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PanelAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PanelAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel JLabelTotalCompra;
    public javax.swing.JLabel JLabelTotalPagar;
    public javax.swing.JMenuItem JMenuEliminarCat;
    public javax.swing.JMenuItem JMenuEliminarCli;
    public javax.swing.JMenuItem JMenuEliminarMedida;
    public javax.swing.JMenuItem JMenuEliminarPro;
    public javax.swing.JMenuItem JMenuEliminarProv;
    public javax.swing.JMenuItem JMenuEliminarUsers;
    public javax.swing.JMenuItem JMenuReingresarCat;
    public javax.swing.JMenuItem JMenuReingresarCli;
    public javax.swing.JMenuItem JMenuReingresarMedida;
    public javax.swing.JMenuItem JMenuReingresarPro;
    public javax.swing.JMenuItem JMenuReingresarProv;
    public javax.swing.JMenuItem JMenuReingresarUsers;
    public javax.swing.JPanel JPanelCategorias;
    public javax.swing.JPanel JPanelClientes;
    public javax.swing.JPanel JPanelConfig;
    public javax.swing.JPanel JPanelMedidas;
    public javax.swing.JPanel JPanelNuevaCompra;
    public javax.swing.JPanel JPanelNuevaVenta;
    public javax.swing.JPanel JPanelProductos;
    public javax.swing.JPanel JPanelProveedor;
    public javax.swing.JPanel JPanelUsers;
    private javax.swing.JPopupMenu JPopupCategorias;
    private javax.swing.JPopupMenu JPopupClientes;
    private javax.swing.JPopupMenu JPopupMedidas;
    private javax.swing.JPopupMenu JPopupProductos;
    private javax.swing.JPopupMenu JPopupProveedor;
    private javax.swing.JPopupMenu JPopupUsuarios;
    private javax.swing.JPanel PaginadoCat;
    private javax.swing.JPanel PaginadoMedida;
    private javax.swing.JPanel PaginadoProv;
    private javax.swing.JPanel PaginadoProv1;
    private javax.swing.JPanel PaginadoUsers;
    public javax.swing.JPanel PaginadoVentas;
    public javax.swing.JPanel PaginadorCli;
    public javax.swing.JPanel PaginadorPro;
    public javax.swing.JTable TableCat;
    public javax.swing.JTable TableClientes;
    public javax.swing.JTable TableCompras;
    public javax.swing.JTable TableMedida;
    public javax.swing.JTable TableNuevaCompra;
    public javax.swing.JTable TableNuevaVenta;
    public javax.swing.JTable TableProductos;
    public javax.swing.JTable TableProveedor;
    public javax.swing.JTable TableProveedor1;
    public javax.swing.JTable TableUsers;
    public javax.swing.JTable TableVentas;
    public javax.swing.JButton btnGenerarCompra;
    private javax.swing.JButton btnGenerarExcel;
    public javax.swing.JButton btnGenerarVenta;
    public javax.swing.JButton btnHistorialCompra;
    public javax.swing.JButton btnHistorialVenta;
    public javax.swing.JButton btnModificarCat;
    public javax.swing.JButton btnModificarCli;
    public javax.swing.JButton btnModificarEmpresa;
    public javax.swing.JButton btnModificarMedida;
    public javax.swing.JButton btnModificarPro;
    public javax.swing.JButton btnModificarProv;
    public javax.swing.JButton btnModificarProv1;
    public javax.swing.JButton btnModificarUser;
    public javax.swing.JButton btnNuevoCat;
    public javax.swing.JButton btnNuevoCli;
    public javax.swing.JButton btnNuevoMedida;
    public javax.swing.JButton btnNuevoPro;
    public javax.swing.JButton btnNuevoProv;
    public javax.swing.JButton btnNuevoProv1;
    public javax.swing.JButton btnNuevoUser;
    public javax.swing.JButton btnRegistrarCat;
    public javax.swing.JButton btnRegistrarCli;
    public javax.swing.JButton btnRegistrarMedida;
    public javax.swing.JButton btnRegistrarPro;
    public javax.swing.JButton btnRegistrarProv;
    public javax.swing.JButton btnRegistrarProv1;
    public javax.swing.JButton btnRegistrarUser;
    private javax.swing.JButton btnUsers;
    public javax.swing.JComboBox<String> cbxCajaUser;
    public javax.swing.JComboBox<Object> cbxCatPro;
    public javax.swing.JComboBox<Object> cbxClienteVentas;
    public javax.swing.JComboBox<Object> cbxMedidaPro;
    public javax.swing.JComboBox<Object> cbxProCompra;
    public javax.swing.JComboBox<Object> cbxProveedorPro;
    public javax.swing.JComboBox<String> cbxRolUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JLabel jLabelCat;
    public javax.swing.JLabel jLabelClientes;
    public javax.swing.JLabel jLabelConfig;
    public javax.swing.JLabel jLabelMedidas;
    public javax.swing.JLabel jLabelNuevaCompra;
    public javax.swing.JLabel jLabelNuevaVenta;
    public javax.swing.JLabel jLabelProductos;
    public javax.swing.JLabel jLabelProveedor;
    private javax.swing.JLabel jLabelPuntoDeVenta;
    public javax.swing.JLabel jLabelUsers;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    public javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    public javax.swing.JTextField jTextField1;
    public javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lblTotalClientes;
    private javax.swing.JLabel lblTotalCompras;
    private javax.swing.JLabel lblTotalProductos;
    private javax.swing.JLabel lblTotalVentas;
    private javax.swing.JPanel panelGraficoMasVendidos;
    private javax.swing.JPanel panelGraficoStockMinimo;
    public javax.swing.JTextField txtBuscarCat;
    public javax.swing.JTextField txtBuscarCli;
    public javax.swing.JTextField txtBuscarCompra;
    public javax.swing.JTextField txtBuscarMed;
    public javax.swing.JTextField txtBuscarProducto;
    public javax.swing.JTextField txtBuscarProv;
    public javax.swing.JTextField txtBuscarProv1;
    public javax.swing.JTextField txtBuscarUsers;
    public javax.swing.JTextField txtBuscarVenta;
    public javax.swing.JTextField txtCantNC;
    public javax.swing.JTextField txtCantNV;
    public javax.swing.JPasswordField txtClaveUser;
    public javax.swing.JTextField txtCodNC;
    public javax.swing.JTextField txtCodNV;
    public javax.swing.JTextField txtCodigoPro;
    public javax.swing.JTextField txtDescripcionPro;
    public javax.swing.JTextPane txtDireccionCli;
    public javax.swing.JTextPane txtDireccionEmpresa;
    private javax.swing.JLabel txtDireccionPro;
    private javax.swing.JLabel txtDireccionPro1;
    private javax.swing.JLabel txtDireccionPro2;
    public javax.swing.JTextPane txtDireccionProv;
    public javax.swing.JTextPane txtDireccionProv1;
    public javax.swing.JTextField txtIdCat;
    public javax.swing.JTextField txtIdCli;
    public javax.swing.JTextField txtIdCompra;
    public javax.swing.JTextField txtIdEmpresa;
    public javax.swing.JTextField txtIdMedida;
    public javax.swing.JTextField txtIdNC;
    public javax.swing.JTextField txtIdNV;
    public javax.swing.JTextField txtIdPro;
    public javax.swing.JTextField txtIdProv;
    public javax.swing.JTextField txtIdProv1;
    public javax.swing.JTextField txtIdUser;
    private javax.swing.JTextField txtIdUsers;
    public javax.swing.JTextField txtIdVenta;
    public javax.swing.JTextPane txtMensaje;
    public javax.swing.JTextField txtNombreCat;
    public javax.swing.JTextField txtNombreCli;
    public javax.swing.JTextField txtNombreCortoMedida;
    public javax.swing.JTextField txtNombreEmpresa;
    public javax.swing.JTextField txtNombreMedida;
    private javax.swing.JLabel txtNombrePro;
    private javax.swing.JLabel txtNombrePro1;
    private javax.swing.JLabel txtNombrePro2;
    private javax.swing.JLabel txtNombrePro3;
    private javax.swing.JLabel txtNombrePro4;
    public javax.swing.JTextField txtNombreProv;
    public javax.swing.JTextField txtNombreProv1;
    public javax.swing.JTextField txtNombreUser;
    public javax.swing.JTextField txtPagarConNC;
    public javax.swing.JTextField txtPrecioCompraPro;
    public javax.swing.JTextField txtPrecioNC;
    public javax.swing.JTextField txtPrecioNV;
    public javax.swing.JTextField txtPrecioVentaPro;
    public javax.swing.JTextField txtProductoNC;
    public javax.swing.JTextField txtProductoNV;
    public javax.swing.JTextField txtRucEmpresa;
    private javax.swing.JLabel txtRucPro;
    private javax.swing.JLabel txtRucPro1;
    private javax.swing.JLabel txtRucPro2;
    public javax.swing.JTextField txtRucProv;
    public javax.swing.JTextField txtRucProv1;
    public javax.swing.JTextField txtStock;
    public javax.swing.JTextField txtTelefonoCli;
    public javax.swing.JTextField txtTelefonoEmpresa;
    private javax.swing.JLabel txtTelefonoPro;
    private javax.swing.JLabel txtTelefonoPro1;
    private javax.swing.JLabel txtTelefonoPro2;
    public javax.swing.JTextField txtTelefonoProv;
    public javax.swing.JTextField txtTelefonoProv1;
    public javax.swing.JTextField txtTotalNC;
    public javax.swing.JTextField txtTotalNV;
    public javax.swing.JTextField txtUsuarioUser;
    public javax.swing.JTextField txtVueltoCompra;
    // End of variables declaration//GEN-END:variables
}
