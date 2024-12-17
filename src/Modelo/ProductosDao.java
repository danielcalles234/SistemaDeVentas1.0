
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


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
                pro.setNombre_proveedor(rs.getString("nombre_proveedor"));  // Cambiado
                pro.setNombre_medida(rs.getString("nombre_medida"));        // Cambiado
                pro.setNombre_categoria(rs.getString("nombre_categoria"));  // Cambiado
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return pro;
    }

    public Productos buscarCodigo(String codigo) {
        String sql = "SELECT * FROM productos WHERE codigo = ?";
        Productos pro = new Productos();
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            if (rs.next()) {
                pro.setId(rs.getInt("id"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setPrecio_compra(rs.getDouble("precio_compra"));
                pro.setNombre_proveedor(rs.getString("nombre_proveedor")); // Cambiado
                pro.setNombre_medida(rs.getString("nombre_medida"));       // Cambiado
                pro.setNombre_categoria(rs.getString("nombre_categoria")); // Cambiado
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return pro;
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


    
    public boolean registrarCompra(String nombre, String total){
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







    
    public boolean ActualizarStock(int cant, String descripcion) {
    String sql = "UPDATE productos SET cantidad = ? WHERE descripcion = ?";
    try {
        con = cn.getConexion();
        ps = con.prepareStatement(sql);
        ps.setInt(1, cant);
        ps.setString(2, descripcion);
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
    String sql = "SELECT c.*, d.id AS id_detalle, d.id_producto, p.descripcion AS producto, " +
             "p.precio_compra, d.precio, d.cantidad, d.subtotal " +
             "FROM compras c " +
             "INNER JOIN detalle_compra d ON c.id = d.id_compra " +
             "INNER JOIN productos p ON d.id_producto = p.id " +
             "WHERE c.id = ?";

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



    

    
}

