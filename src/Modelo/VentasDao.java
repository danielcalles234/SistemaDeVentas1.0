package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class VentasDao {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List<Ventas> ListaVentas(String valor) {
    List<Ventas> lista = new ArrayList<>();
    // Consulta principal para listar ventas
    String sql = "SELECT id, nombre_cliente AS cliente, total, fecha FROM ventas ORDER BY id DESC";
    // Consulta para buscar ventas por cliente o fecha
    String buscar = "SELECT id, nombre_cliente AS cliente, total, fecha FROM ventas " +
                    "WHERE nombre_cliente LIKE '%" + valor + "%' OR fecha LIKE '%" + valor + "%' " +
                    "ORDER BY id DESC";
    try {
        con = cn.getConexion();
        if (valor.equalsIgnoreCase("")) {
            ps = con.prepareStatement(sql);
        } else {
            ps = con.prepareStatement(buscar);
        }
        rs = ps.executeQuery();
        while (rs.next()) {
            Ventas venta = new Ventas();
            venta.setId(rs.getInt("id"));
            venta.setNombre_cliente(rs.getString("cliente")); // Alias "cliente" de la consulta
            venta.setTotal(rs.getDouble("total"));
            venta.setFecha(rs.getString("fecha"));
            lista.add(venta);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
    }
    return lista;
}
    
    
    public int contarVentas() {
    int totalVentas = 0;
    String sql = "SELECT COUNT(*) AS total FROM ventas";
    try (Connection conn = Conexion.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
            totalVentas = rs.getInt("total");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return totalVentas;
}

    
    

}

