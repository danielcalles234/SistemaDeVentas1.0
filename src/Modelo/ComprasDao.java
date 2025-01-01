package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ComprasDao {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List<Compras> ListaCompras(String valor) {
        List<Compras> lista = new ArrayList<>();
        // Consulta principal para listar compras
        String sql = "SELECT id, nombre_proveedor AS proveedor, total, fecha FROM compras ORDER BY id DESC";
        // Consulta para buscar compras por proveedor o fecha
        String buscar = "SELECT id, nombre_proveedor AS proveedor, total, fecha FROM compras " +
                        "WHERE nombre_proveedor LIKE '%" + valor + "%' OR fecha LIKE '%" + valor + "%' " +
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
                Compras comp = new Compras();
                comp.setId(rs.getInt("id"));
                comp.setNombre_proveedor(rs.getString("proveedor")); // Alias "proveedor" de la consulta
                comp.setTotal(rs.getDouble("total"));
                comp.setFecha(rs.getString("fecha"));
                lista.add(comp);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return lista;
    }
    
    
    
    public int contarCompras() {
    int totalCompras = 0;
    String sql = "SELECT COUNT(*) AS total FROM compras";
    try (Connection conn = Conexion.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
            totalCompras = rs.getInt("total");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return totalCompras;
}

}

