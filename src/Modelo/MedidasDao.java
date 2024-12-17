package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;

public class MedidasDao {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    // Registrar una nueva medida
    public boolean registrar(Medidas medida) {
        String sql = "INSERT INTO medidas (medida, nombre_corto) VALUES(?, ?)";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, medida.getNombre());       // Asigna el nombre
            ps.setString(2, medida.getNombre_corto());  // Asigna el nombre corto
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }

    // Listar todas las medidas o buscar por nombre
    public List<Medidas> ListaMedidas(String valor) {
        List<Medidas> listaMedidas = new ArrayList<>();
        String sql = "SELECT * FROM medidas ORDER BY id ASC"; 
        String buscar = "SELECT * FROM medidas WHERE medida LIKE '%" + valor + "%' OR nombre_corto LIKE '%" + valor + "%'";

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
                Medidas medida = new Medidas();
                medida.setId(rs.getInt("id"));               // ID de la medida
                medida.setNombre(rs.getString("medida"));     // Nombre de la medida
                medida.setNombre_corto(rs.getString("nombre_corto")); // Nombre corto de la medida
                listaMedidas.add(medida);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listaMedidas;
    }
    
    // Modificar una medida existente
    public boolean modificar(Medidas medida) {
        String sql = "UPDATE medidas SET medida = ?, nombre_corto = ? WHERE id = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, medida.getNombre());       // Asigna el nuevo nombre
            ps.setString(2, medida.getNombre_corto());  // Asigna el nuevo nombre corto
            ps.setInt(3, medida.getId());              // Asigna el ID
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }
    
     public boolean accion(String estado, int id) {
    String sql = "UPDATE medidas SET estado = ? WHERE id = ?";
    try {
        con = cn.getConexion();
        ps = con.prepareStatement(sql);
        ps.setString(1, estado); 
        ps.setInt(2, id);       
        ps.execute();
        return true;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        return false;
    }
}
}
