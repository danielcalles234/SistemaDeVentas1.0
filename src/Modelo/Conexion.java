
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {


     public static Connection getConexion(){
        try {
            String db = "jdbc:mysql://localhost:3306/puntodeventa";
            return  DriverManager.getConnection(db, "root", "");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return null;
    }
}
