
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;

public class ProveedorDao {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean registrar (Proveedor pr){ 
        String sql = "INSERT INTO proveedor (ruc, proveedor, telefono, direccion) VALUES(?,?,?,?)";
        try { 
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, pr.getRuc());
            ps.setString(2, pr.getNombre());
            ps.setString(3, pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.execute();
            return true;
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
            
    }
    
    public List ListaProveedor(String valor) {
    List<Proveedor> listaProv = new ArrayList<>();
    String sql = "SELECT * FROM proveedor ORDER BY estado ASC";
    String buscar = "SELECT * FROM proveedor WHERE ruc LIKE '%"+valor+"%' OR telefono LIKE '%"+valor+"%'";
    try {
        con = cn.getConexion();
        if (valor.equalsIgnoreCase("")) {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
        }else{
            ps = con.prepareStatement(buscar);
            rs = ps.executeQuery();
        }
        while (rs.next()) {
            Proveedor pr = new Proveedor();
            pr.setId(rs.getInt("id"));
            pr.setRuc(rs.getString("ruc"));
            pr.setNombre(rs.getString("proveedor"));
            pr.setTelefono(rs.getString("telefono"));
            pr.setDireccion(rs.getString("direccion"));
            pr.setEstado(rs.getString("estado"));
            listaProv.add(pr);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
              return listaProv;
        }
    
    public boolean modificar(Proveedor pr) {
    String sql = "UPDATE proveedor SET ruc = ?, proveedor = ?, telefono = ?, direccion = ? WHERE id = ?";
    try {
        con = cn.getConexion();
        ps = con.prepareStatement(sql);
        ps.setString(1, pr.getRuc());
        ps.setString(2, pr.getNombre());
        ps.setString(3, pr.getTelefono());
        ps.setString(4, pr.getDireccion());
        ps.setInt(5, pr.getId());
        ps.execute();
        return true;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        return false;
    }
}
    
    public boolean accion(String estado, int id) {
    String sql = "UPDATE proveedor SET estado = ? WHERE id = ?";
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
