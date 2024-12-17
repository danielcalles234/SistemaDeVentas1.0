
package Modelo;

import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;
import javax.swing.JTable;
import java.awt.Color;

public class Tables extends DefaultTableCellRenderer{
    
    @Override
public Component getTableCellRendererComponent(JTable jtable, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
    super.getTableCellRendererComponent(jtable, value, isSelected, hasFocus, row, col);

    // Verifica si el valor no es null antes de llamar a toString()
    if (value != null && value.toString().equalsIgnoreCase("Inactivo")) {
        setBackground(Color.RED);
        setForeground(Color.WHITE);
    } else {
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
    }

    return this;
}


}
