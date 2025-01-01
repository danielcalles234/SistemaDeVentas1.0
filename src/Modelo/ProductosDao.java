package Modelo;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.Set;

public class ProductosDao {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean registrar(Productos pro) {
        String sql = "INSERT INTO productos (codigo, descripcion, precio_compra, precio_venta, nombre_proveedor, nombre_medida, nombre_categoria) VALUES(?,?,?,?,?,?,?)";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getDescripcion());
            ps.setDouble(3, pro.getPrecio_compra());
            ps.setDouble(4, pro.getPrecio_venta());
            ps.setString(5, pro.getNombre_proveedor());  // Cambiado
            ps.setString(6, pro.getNombre_medida());     // Cambiado
            ps.setString(7, pro.getNombre_categoria());  // Cambiado
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }

    public List<Productos> ListaProductos(String valor) {
        List<Productos> listaPro = new ArrayList<>();
        String sql = "SELECT * FROM productos ORDER BY estado ASC";
        String buscar = "SELECT * FROM productos WHERE codigo LIKE '%" + valor + "%' OR descripcion LIKE '%" + valor + "%'";
        try {
            con = cn.getConexion();
            if (valor.equalsIgnoreCase("")) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
            } else {
                ps = con.prepareStatement(buscar);
                rs = ps.executeQuery();
            }
            while (rs.next()) {
                Productos pro = new Productos();
                pro.setId(rs.getInt("id"));
                pro.setCodigo(rs.getString("codigo"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setPrecio_venta(rs.getDouble("precio_venta"));
                pro.setCantidad(rs.getInt("cantidad"));
                pro.setEstado(rs.getString("estado"));
                pro.setNombre_proveedor(rs.getString("nombre_proveedor")); // Cambiado
                pro.setNombre_medida(rs.getString("nombre_medida"));       // Cambiado
                pro.setNombre_categoria(rs.getString("nombre_categoria")); // Cambiado
                listaPro.add(pro);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listaPro;
    }

    public boolean modificar(Productos pro) {
        String sql = "UPDATE productos SET codigo = ?, descripcion = ?, precio_compra = ?, precio_venta = ?, nombre_proveedor = ?, nombre_medida = ?, nombre_categoria = ? WHERE id = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getDescripcion());
            ps.setDouble(3, pro.getPrecio_compra());
            ps.setDouble(4, pro.getPrecio_venta());
            ps.setString(5, pro.getNombre_proveedor());  // Cambiado
            ps.setString(6, pro.getNombre_medida());     // Cambiado
            ps.setString(7, pro.getNombre_categoria());  // Cambiado
            ps.setInt(8, pro.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }

    public Productos buscarPro(int id) {
        String sql = "SELECT * FROM productos WHERE id = ?";
        Productos pro = new Productos();
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                pro.setCodigo(rs.getString("codigo"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setPrecio_compra(rs.getDouble("precio_compra"));
                pro.setPrecio_venta(rs.getDouble("precio_venta"));
                pro.setCantidad(rs.getInt("cantidad")); // Agrega esta línea para asignar el stock
                pro.setNombre_proveedor(rs.getString("nombre_proveedor"));
                pro.setNombre_medida(rs.getString("nombre_medida"));
                pro.setNombre_categoria(rs.getString("nombre_categoria"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return pro;
    }

    public Productos buscarCodigo(String codigo) {
        String sql = "SELECT * FROM productos WHERE codigo = ? AND  estado = 'Activo'";
        Productos pro = null; // Inicializa el producto como null
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, codigo); // Asigna el código al parámetro
            rs = ps.executeQuery();
            if (rs.next()) {
                pro = new Productos(); // Crea una instancia solo si se encuentra el producto
                pro.setId(rs.getInt("id"));
                pro.setCodigo(rs.getString("codigo"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setPrecio_compra(rs.getDouble("precio_compra"));
                pro.setPrecio_venta(rs.getDouble("precio_venta"));
                pro.setCantidad(rs.getInt("cantidad")); // Stock disponible
                pro.setNombre_proveedor(rs.getString("nombre_proveedor"));
                pro.setNombre_medida(rs.getString("nombre_medida"));
                pro.setNombre_categoria(rs.getString("nombre_categoria"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return pro; // Retorna el producto encontrado o null si no se encontró
    }

    public Productos buscarDescripcion(String descripcion) {
        String sql = "SELECT * FROM productos WHERE descripcion = ?";
        Productos pro = new Productos();
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, descripcion);
            rs = ps.executeQuery();
            if (rs.next()) {
                pro.setCantidad(rs.getInt("cantidad"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return pro;
    }
    //Modelo Compras

    public boolean registrarCompra(String nombre, String total) {
        String sql = "INSERT INTO compras (nombre_proveedor, total) VALUES (?,?)";

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, total);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public boolean registrarCompraDetalle(int id_compra, int id_producto, String descripcion_producto, double precio, int cantidad, double sub_total) {
        String sql = "INSERT INTO detalle_compra (id_compra, id_producto, descripcion_producto, precio, cantidad, subtotal) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_compra);
            ps.setInt(2, id_producto);
            ps.setString(3, descripcion_producto); // Agrega la descripción del producto
            ps.setDouble(4, precio);
            ps.setInt(5, cantidad);
            ps.setDouble(6, sub_total);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public boolean actualizarStock(int cantidad, int idProducto, boolean esCompra) {
        String sql = esCompra
                ? "UPDATE productos SET cantidad = cantidad + ? WHERE id = ?"
                : "UPDATE productos SET cantidad = cantidad - ? WHERE id = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cantidad); // Sumar o restar dependiendo de la operación
            ps.setInt(2, idProducto); // Producto al que se le actualiza el stock
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }

    public String nombre_compra() {
        String nombreCompra = "";
        String sql = "SELECT nombre_compra FROM detalle_compra ORDER BY id DESC LIMIT 1"; // Ordena por el último registro
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                nombreCompra = rs.getString("nombre_compra");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return nombreCompra;
    }

    public List<Productos> ListaDetalle(int id_compra) {
        List<Productos> listaPro = new ArrayList<>();
        String sql = "SELECT c.*, d.id AS id_detalle, d.id_producto, p.descripcion AS producto, "
                + "p.precio_compra, d.precio, d.cantidad, d.subtotal "
                + "FROM compras c "
                + "INNER JOIN detalle_compra d ON c.id = d.id_compra "
                + "INNER JOIN productos p ON d.id_producto = p.id "
                + "WHERE c.id = ?";

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_compra);
            rs = ps.executeQuery();

            while (rs.next()) {
                Productos pro = new Productos();
                pro.setCantidad(rs.getInt("cantidad"));
                pro.setDescripcion(rs.getString("producto"));
                pro.setPrecio_compra(rs.getDouble("precio_compra"));
                pro.setPrecio_venta(rs.getDouble("subtotal"));
                listaPro.add(pro);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return listaPro;
    }

    public void generarReporte(int id_compra) {
        double totalGeneral = 0.00;
        String fecha = "";
        String nomProveedor = "";
        String dirProveedor = "";
        String telProveedor = "";
        String mensaje = "";

        try {
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.WHITE);
            String url = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
            FileOutputStream archivo;
            File salida = new File(url + File.separator + "compra.pdf");
            archivo = new FileOutputStream(salida);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            //Contenido del documento
            PdfPTable empresa = new PdfPTable(3);
            empresa.setWidthPercentage(100);
            float[] tamañoEncabezado = new float[]{30f, 40f, 30f};
            empresa.setWidths(tamañoEncabezado);
            empresa.setHorizontalAlignment(Element.ALIGN_LEFT);
            empresa.getDefaultCell().setBorder(0);
            //capturar y agragar logotipo   
            Image Img = Image.getInstance(getClass().getResource("/Img/fincalatinalogo.png"));
            empresa.addCell(Img);

            //consulta los datos de la empresa
            String sql = "SELECT * FROM configuracion";
            try {
                con = cn.getConexion();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    mensaje = rs.getString("mensaje");
                    //agregar datos de la empresa
                    empresa.addCell("Ruc: " + rs.getString("ruc") + "\nNombre: " + rs.getString("nombre")
                            + "\nTelefono: " + rs.getString("telefono") + "\nDirección: " + rs.getString("direccion"));
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
            //consulta los datos del proveedor
            String consultaProveedor = "SELECT p.proveedor, p.telefono, p.direccion, c.fecha, c.total FROM compras c INNER JOIN proveedor p ON c.nombre_proveedor = p.proveedor WHERE c.id =" + id_compra;

            try {
                con = cn.getConexion();
                ps = con.prepareStatement(consultaProveedor);
                rs = ps.executeQuery();
                if (rs.next()) {
                    //agregar datos del proveedor
                    nomProveedor = rs.getString("proveedor");
                    telProveedor = rs.getString("telefono");
                    dirProveedor = rs.getString("direccion");
                    totalGeneral = rs.getDouble("total");
                    fecha = rs.getString("fecha");
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }

            //Datos del vendedor       
            empresa.addCell("N° Compra: " + id_compra + "\nComprador: " + "Finca Latina"
                    + "\nFecha: " + fecha);

            doc.add(empresa);
            doc.add(Chunk.NEWLINE);

            // Título proveedor
            Paragraph titProveedor = new Paragraph();
            titProveedor.add("Datos del Proveedor");
            titProveedor.setAlignment(Element.ALIGN_CENTER);
            doc.add(titProveedor);
            doc.add(Chunk.NEWLINE);

            //Mostrar datos del proveedor       
            PdfPTable proveedor = new PdfPTable(3);
            proveedor.setWidthPercentage(100);
            float[] tamañoProveedor = new float[]{40f, 20f, 40f};
            proveedor.setWidths(tamañoProveedor);
            proveedor.setHorizontalAlignment(Element.ALIGN_LEFT);
            proveedor.getDefaultCell().setBorder(0);

            // Encabezado proveedor
            PdfPCell nomP = new PdfPCell(new Phrase("Nombre", negrita));
            PdfPCell telP = new PdfPCell(new Phrase("Teléfono", negrita));
            PdfPCell dirP = new PdfPCell(new Phrase("Dirección", negrita));

            // Quitar bordes de los encabezados
            nomP.setBorder(0);
            telP.setBorder(0);
            dirP.setBorder(0);

            // Background del encabezado
            nomP.setBackgroundColor(BaseColor.DARK_GRAY);
            telP.setBackgroundColor(BaseColor.DARK_GRAY);
            dirP.setBackgroundColor(BaseColor.DARK_GRAY);

            // Agregar los encabezados del proveedor
            proveedor.addCell(nomP);
            proveedor.addCell(telP);
            proveedor.addCell(dirP);

            //agregar datos del proveedor
            proveedor.addCell(nomProveedor);
            proveedor.addCell(telProveedor);
            proveedor.addCell(dirProveedor);

            doc.add(proveedor);
            doc.add(Chunk.NEWLINE);
            //fin proveedor

            // Título productos
            Paragraph titProductos = new Paragraph();
            titProductos.add("Dellates de la Compra");
            titProductos.setAlignment(Element.ALIGN_CENTER);
            doc.add(titProductos);
            doc.add(Chunk.NEWLINE);

            //Mostrar datos del productos      
            PdfPTable Producto = new PdfPTable(4);
            proveedor.setWidthPercentage(100);
            float[] tamañoProducto = new float[]{50f, 10f, 20f, 20f};
            Producto.setWidths(tamañoProducto);
            Producto.setHorizontalAlignment(Element.ALIGN_LEFT);
            Producto.getDefaultCell().setBorder(0);

            // Encabezado proveedor
            PdfPCell desPro = new PdfPCell(new Phrase("Descripción", negrita));
            PdfPCell cantPro = new PdfPCell(new Phrase("Cant", negrita));
            PdfPCell precioPro = new PdfPCell(new Phrase("Precio", negrita));
            PdfPCell subPro = new PdfPCell(new Phrase("SubTotal", negrita));

            // Quitar bordes de los encabezados
            desPro.setBorder(0);
            cantPro.setBorder(0);
            precioPro.setBorder(0);
            subPro.setBorder(0);

            // Background del encabezado
            desPro.setBackgroundColor(BaseColor.DARK_GRAY);
            cantPro.setBackgroundColor(BaseColor.DARK_GRAY);
            precioPro.setBackgroundColor(BaseColor.DARK_GRAY);
            subPro.setBackgroundColor(BaseColor.DARK_GRAY);

            // Agregar los encabezados de los productos
            Producto.addCell(desPro);
            Producto.addCell(cantPro);
            Producto.addCell(precioPro);
            Producto.addCell(subPro);

            //consulta los datos del producto
            String consultaProductos = "SELECT d.descripcion_producto, d.cantidad, d.precio, d.subtotal "
                    + "FROM compras c "
                    + "INNER JOIN detalle_compra d ON c.id = d.id_compra "
                    + "WHERE c.id = ?";

            try {
                con = cn.getConexion();
                ps = con.prepareStatement(consultaProductos); // Cambiar a consultaProductos
                ps.setInt(1, id_compra); // Asignar el valor de id_compra al parámetro
                rs = ps.executeQuery();
                while (rs.next()) {
                    // agregar datos del productos
                    Producto.addCell(rs.getString("descripcion_producto"));
                    Producto.addCell(String.valueOf(rs.getInt("cantidad")));
                    Producto.addCell(String.valueOf(rs.getDouble("precio")));
                    Producto.addCell(String.valueOf(rs.getDouble("subtotal")));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }

            doc.add(Producto);
            doc.add(Chunk.NEWLINE);
            //fin detalle productos

            //Mostrar Total a pagar
            Paragraph total = new Paragraph();
            total.add("total a pagar: " + totalGeneral);
            total.setAlignment(Element.ALIGN_RIGHT);
            doc.add(total);
            doc.add(Chunk.NEWLINE);

            //Mostrar mensaje
            Paragraph agradecimiento = new Paragraph();
            agradecimiento.add(mensaje);
            agradecimiento.setAlignment(Element.ALIGN_CENTER);

            doc.add(agradecimiento);
            doc.add(Chunk.NEWLINE);
            doc.add(Chunk.NEWLINE);

            doc.close();
            archivo.close();

            Desktop.getDesktop().open(salida);

        } catch (DocumentException | HeadlessException | IOException e) {
        }
    }

    public int obtenerIdProducto(String descripcion) {
        String sql = "SELECT id FROM productos WHERE descripcion = ?";
        int idProducto = -1;
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, descripcion);
            rs = ps.executeQuery();
            if (rs.next()) {
                idProducto = rs.getInt("id");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return idProducto;
    }

    public int obtenerUltimoIdCompra() {
        int idCompra = -1; // Valor por defecto en caso de error
        String sql = "SELECT id FROM compras ORDER BY id DESC LIMIT 1";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                idCompra = rs.getInt("id"); // Recupera el último ID
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return idCompra;
    }

    public int obtenerIdCompra(String nombre_proveedor) {
        String sql = "SELECT id FROM compras WHERE nombre_proveedor = ? LIMIT 1";
        int idCompra = -1;
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre_proveedor);
            rs = ps.executeQuery();
            if (rs.next()) {
                idCompra = rs.getInt("id");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return idCompra;
    }

    public boolean registrarVenta(String nombre_cliente, String total) {
        String sql = "INSERT INTO ventas (nombre_cliente, total) VALUES (?, ?)";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre_cliente);
            ps.setString(2, total);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public boolean registrarVentaDetalle(int id_venta, int id_producto, String descripcion_producto, double precio, int cantidad, double sub_total) {
        String sql = "INSERT INTO detalle_venta (id_venta, id_producto, descripcion_producto, precio, cantidad, subtotal) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_venta);
            ps.setInt(2, id_producto);
            ps.setString(3, descripcion_producto);
            ps.setDouble(4, precio);
            ps.setInt(5, cantidad);
            ps.setDouble(6, sub_total);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public int obtenerUltimoIdVenta() {
        int idVenta = -1;
        String sql = "SELECT id FROM ventas ORDER BY id DESC LIMIT 1";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                idVenta = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return idVenta;
    }

    public boolean accion(String estado, int id) {
        String sql = "UPDATE productos SET estado = ? WHERE id = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, estado);
            ps.setInt(2, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public void generarReporteVenta(int id_venta) {
        double totalGeneral = 0.00;
        String fecha = "";
        String nomCliente = "";
        String dirCliente = "";
        String telCliente = "";
        String mensaje = "";

        try {
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.WHITE);
            String url = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
            FileOutputStream archivo;
            File salida = new File(url + File.separator + "venta.pdf");
            archivo = new FileOutputStream(salida);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            //Contenido del documento
            PdfPTable empresa = new PdfPTable(3);
            empresa.setWidthPercentage(100);
            float[] tamañoEncabezado = new float[]{30f, 40f, 30f};
            empresa.setWidths(tamañoEncabezado);
            empresa.setHorizontalAlignment(Element.ALIGN_LEFT);
            empresa.getDefaultCell().setBorder(0);
            //capturar y agragar logotipo   
            Image Img = Image.getInstance(getClass().getResource("/Img/fincalatinalogo.png"));
            empresa.addCell(Img);

            //consulta los datos de la empresa
            String sql = "SELECT * FROM configuracion";
            try {
                con = cn.getConexion();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    mensaje = rs.getString("mensaje");
                    //agregar datos de la empresa
                    empresa.addCell("Ruc: " + rs.getString("ruc") + "\nNombre: " + rs.getString("nombre")
                            + "\nTelefono: " + rs.getString("telefono") + "\nDirección: " + rs.getString("direccion"));
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
            // Consulta los datos del cliente
            String consultaCliente = "SELECT c.nombre, c.telefono, c.direccion, v.fecha, v.total "
                    + "FROM ventas v "
                    + "INNER JOIN clientes c ON v.nombre_cliente = c.nombre "
                    + "WHERE v.id = ?";

            try {
                con = cn.getConexion();
                ps = con.prepareStatement(consultaCliente);
                ps.setInt(1, id_venta); // Asigna el valor del ID de venta
                rs = ps.executeQuery();
                if (rs.next()) {
                    // Agregar datos del cliente
                    nomCliente = rs.getString("nombre");
                    telCliente = rs.getString("telefono");
                    dirCliente = rs.getString("direccion");
                    totalGeneral = rs.getDouble("total");
                    fecha = rs.getString("fecha");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }

            //Datos del vendedor       
            empresa.addCell("N° Venta: " + id_venta + "\nVendedor: " + "Finca Latina"
                    + "\nFecha: " + fecha);

            doc.add(empresa);
            doc.add(Chunk.NEWLINE);

            // Título proveedor
            Paragraph titCliente = new Paragraph();
            titCliente.add("Datos del Cliente");
            titCliente.setAlignment(Element.ALIGN_CENTER);
            doc.add(titCliente);
            doc.add(Chunk.NEWLINE);

            //Mostrar datos del proveedor       
            PdfPTable cliente = new PdfPTable(3);
            cliente.setWidthPercentage(100);
            float[] tamañoCliente = new float[]{40f, 20f, 40f};
            cliente.setWidths(tamañoCliente);
            cliente.setHorizontalAlignment(Element.ALIGN_LEFT);
            cliente.getDefaultCell().setBorder(0);

            // Encabezado proveedor
            PdfPCell nomCli = new PdfPCell(new Phrase("Nombre", negrita));
            PdfPCell telCli = new PdfPCell(new Phrase("Teléfono", negrita));
            PdfPCell dirCli = new PdfPCell(new Phrase("Dirección", negrita));

            // Quitar bordes de los encabezados
            nomCli.setBorder(0);
            telCli.setBorder(0);
            dirCli.setBorder(0);

            // Background del encabezado
            nomCli.setBackgroundColor(BaseColor.DARK_GRAY);
            telCli.setBackgroundColor(BaseColor.DARK_GRAY);
            dirCli.setBackgroundColor(BaseColor.DARK_GRAY);

            // Agregar los encabezados del proveedor
            cliente.addCell(nomCli);
            cliente.addCell(telCli);
            cliente.addCell(dirCli);

            //agregar datos del proveedor
            cliente.addCell(nomCliente);
            cliente.addCell(telCliente);
            cliente.addCell(dirCliente);

            doc.add(cliente);
            doc.add(Chunk.NEWLINE);
            //fin proveedor

            // Título productos
            Paragraph titProductos = new Paragraph();
            titProductos.add("Dellates de la Venta");
            titProductos.setAlignment(Element.ALIGN_CENTER);
            doc.add(titProductos);
            doc.add(Chunk.NEWLINE);

            //Mostrar datos del productos      
            PdfPTable Producto = new PdfPTable(4);
            Producto.setWidthPercentage(100);
            float[] tamañoProducto = new float[]{50f, 10f, 20f, 20f};
            Producto.setWidths(tamañoProducto);
            Producto.setHorizontalAlignment(Element.ALIGN_LEFT);
            Producto.getDefaultCell().setBorder(0);

            // Encabezado proveedor
            PdfPCell desPro = new PdfPCell(new Phrase("Descripción", negrita));
            PdfPCell cantPro = new PdfPCell(new Phrase("Cant", negrita));
            PdfPCell precioPro = new PdfPCell(new Phrase("Precio", negrita));
            PdfPCell subPro = new PdfPCell(new Phrase("SubTotal", negrita));

            // Quitar bordes de los encabezados
            desPro.setBorder(0);
            cantPro.setBorder(0);
            precioPro.setBorder(0);
            subPro.setBorder(0);

            // Background del encabezado
            desPro.setBackgroundColor(BaseColor.DARK_GRAY);
            cantPro.setBackgroundColor(BaseColor.DARK_GRAY);
            precioPro.setBackgroundColor(BaseColor.DARK_GRAY);
            subPro.setBackgroundColor(BaseColor.DARK_GRAY);

            // Agregar los encabezados de los productos
            Producto.addCell(desPro);
            Producto.addCell(cantPro);
            Producto.addCell(precioPro);
            Producto.addCell(subPro);

            //consulta los datos del producto
            String consultaProductos = "SELECT d.descripcion_producto, d.cantidad, d.precio, d.subtotal "
                    + "FROM ventas v "
                    + "INNER JOIN detalle_venta d ON v.id = d.id_venta "
                    + "WHERE v.id = ?";

            try {
                con = cn.getConexion();
                ps = con.prepareStatement(consultaProductos); // Cambiar a consultaProductos
                ps.setInt(1, id_venta); // Asignar el valor de id_venta al parámetro
                rs = ps.executeQuery();
                while (rs.next()) {
                    // agregar datos del productos
                    Producto.addCell(rs.getString("descripcion_producto"));
                    Producto.addCell(rs.getString("cantidad"));
                    Producto.addCell(rs.getString("precio"));
                    Producto.addCell(rs.getString("subtotal"));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }

            doc.add(Producto);
            doc.add(Chunk.NEWLINE);
            //fin detalle productos

            //Mostrar Total a pagar
            Paragraph total = new Paragraph();
            total.add("total a pagar: " + totalGeneral);
            total.setAlignment(Element.ALIGN_RIGHT);
            doc.add(total);
            doc.add(Chunk.NEWLINE);

            //Mostrar mensaje
            Paragraph agradecimiento = new Paragraph();
            agradecimiento.add(mensaje);
            agradecimiento.setAlignment(Element.ALIGN_CENTER);

            doc.add(agradecimiento);
            doc.add(Chunk.NEWLINE);
            doc.add(Chunk.NEWLINE);

            doc.close();
            archivo.close();

            Desktop.getDesktop().open(salida);

        } catch (DocumentException | HeadlessException | IOException e) {
        }
    }
    
    
    
    public int contarProductos() {
    int totalProductos = 0;
    String sql = "SELECT COUNT(*) AS total FROM productos";
    try (Connection conn = Conexion.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
            totalProductos = rs.getInt("total");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return totalProductos;
}
    
    
    
    public int contarProductosStockMinimo() {
    int total = 0;
    String sql = "SELECT COUNT(*) FROM productos WHERE cantidad <= 5"; // Ajusta el valor según tu criterio
    try (Connection conn = Conexion.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
            total = rs.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return total;
}
    
    
    public int contarProductosVendidos() {
    int total = 0;
    String sql = "SELECT SUM(cantidad) FROM detalle_venta"; // Ajusta el nombre de la tabla según tu base de datos
    try (Connection conn = Conexion.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
            total = rs.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return total;
}
    
    
    public List<Productos> obtenerProductosStockMinimo() {
    List<Productos> lista = new ArrayList<>();
    String sql = "SELECT descripcion, cantidad FROM productos WHERE cantidad <= 10"; // Usa 'descripcion'
    try (Connection conn = Conexion.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            Productos producto = new Productos();
            producto.setDescripcion(rs.getString("descripcion")); // Usa 'descripcion'
            producto.setCantidad(rs.getInt("cantidad"));
            lista.add(producto);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return lista;
}
    
    
    public List<Productos> obtenerProductosMasVendidos() {
    List<Productos> lista = new ArrayList<>();
    String sql = "SELECT p.descripcion, SUM(dv.cantidad) AS total_vendido " +
                 "FROM detalle_venta dv " +
                 "INNER JOIN productos p ON dv.id_producto = p.id " +
                 "GROUP BY dv.id_producto " +
                 "ORDER BY total_vendido DESC " +
                 "LIMIT 5";
    try (Connection conn = Conexion.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            Productos producto = new Productos();
            producto.setDescripcion(rs.getString("descripcion"));
            producto.setCantidad(rs.getInt("total_vendido"));
            lista.add(producto);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return lista;
}









}
