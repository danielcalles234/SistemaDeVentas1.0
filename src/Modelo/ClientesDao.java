package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClientesDao {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public String registrar(Clientes cl) {
        String consulta = "SELECT * FROM clientes WHERE telefono = ?";
        String sql = "INSERT INTO clientes (nombre, telefono, direccion) VALUES(?,?,?)";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(consulta);
            ps.setString(1, cl.getTelefono());
            ps.executeQuery();
            if (rs.next()) {
                return "existe";
            } else {
                ps = con.prepareStatement(sql);
                ps.setString(1, cl.getNombre());
                ps.setString(2, cl.getTelefono());
                ps.setString(3, cl.getDireccion());
                ps.execute();
                return "registrado";
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return "error";
        }

    }

    public List ListaClientes(String valor) {
        List<Clientes> listaCli = new ArrayList<>();
        String sql = "SELECT * FROM clientes ORDER BY estado ASC";
        String buscar = "SELECT * FROM clientes WHERE nombre LIKE '%" + valor + "%' OR telefono LIKE '%" + valor + "%'";
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
                Clientes cl = new Clientes();
                cl.setId(rs.getInt("id"));
                cl.setNombre(rs.getString("nombre"));
                cl.setTelefono(rs.getString("telefono"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setEstado(rs.getString("estado"));
                listaCli.add(cl);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listaCli;
    }

    public String modificar(Clientes cl) {
    String consulta = "SELECT * FROM clientes WHERE telefono = ? AND id != ?";
    String sql = "UPDATE clientes SET nombre = ?, telefono = ?, direccion = ? WHERE id = ?";
    try {
        con = cn.getConexion();

        // Primero verificamos si existe otro cliente con el mismo teléfono
        ps = con.prepareStatement(consulta);
        ps.setString(1, cl.getTelefono());
        ps.setInt(2, cl.getId());
        rs = ps.executeQuery(); // Ejecutamos el SELECT
        if (rs.next()) {
            return "existe"; // Existe otro cliente con el mismo teléfono
        }

        // Si no hay conflicto, procedemos con la actualización
        ps = con.prepareStatement(sql);
        ps.setString(1, cl.getNombre());
        ps.setString(2, cl.getTelefono());
        ps.setString(3, cl.getDireccion());
        ps.setInt(4, cl.getId());
        ps.execute(); // Ejecutamos el UPDATE
        return "modificado";

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        return "error";
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar conexión: " + e.getMessage());
        }
    }
}


    public boolean accion(String estado, int id) {
        String sql = "UPDATE clientes SET estado = ? WHERE id = ?";
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
    
    
    public int contarClientes() {
    int totalClientes = 0;
    String sql = "SELECT COUNT(*) AS total FROM clientes";
    try (Connection conn = Conexion.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
            totalClientes = rs.getInt("total");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return totalClientes;
}

}
