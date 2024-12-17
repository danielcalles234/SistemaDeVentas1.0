
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;

public class CategoriasDao {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean registrar (Categorias cat){
        String sql = "INSERT INTO proveedor (categoria) VALUES(?)";
        try { 
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(2, cat.getNombre());
            ps.execute();
            return true;
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
            
    }
    
     public List<Categorias> ListaCategoria(String valor) {
        List<Categorias> listaCat = new ArrayList<>();
        String sql = "SELECT * FROM categorias ORDER BY estado ASC"; 
        String buscar = "SELECT * FROM categorias WHERE categoria LIKE '%" + valor + "%'"; 

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
                Categorias cat = new Categorias();
                cat.setId(rs.getInt("id"));          
                cat.setNombre(rs.getString("categoria")); 
                cat.setEstado(rs.getString("estado")); 
                listaCat.add(cat);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listaCat;
    }
     
     public boolean modificar(Categorias cat) {
    String sql = "UPDATE categoria SET categoria = ? WHERE id = ?";
    try {
        con = cn.getConexion();
        ps = con.prepareStatement(sql);
        ps.setString(1, cat.getNombre()); // Asigna el nombre
        ps.setString(2, cat.getEstado()); // Asigna el estado
        ps.setInt(3, cat.getId());       // Asigna el ID
        ps.execute();
        return true;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        return false;
    }
}
     
     public boolean accion(String estado, int id) {
    String sql = "UPDATE categoria SET estado = ? WHERE id = ?";
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
