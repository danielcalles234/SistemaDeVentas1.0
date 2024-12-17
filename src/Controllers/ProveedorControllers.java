
package Controllers;

import Modelo.Combo;
import Modelo.Proveedor;
import Modelo.ProveedorDao;
import Modelo.Tables;
import Views.PanelAdmin;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;

public class ProveedorControllers implements ActionListener, MouseListener, KeyListener{
    
    private Proveedor pr;
    private ProveedorDao prDao;
    private PanelAdmin views;
    
     DefaultTableModel modelo = new DefaultTableModel();
    public ProveedorControllers(Proveedor pr, ProveedorDao prDao, PanelAdmin views) {
        this.pr = pr;
        this.prDao = prDao;
        this.views = views;
        this.views.btnRegistrarProv.addActionListener(this);
        this.views.btnModificarProv.addActionListener(this);
        this.views.JMenuEliminarProv.addActionListener(this);
        this.views.btnNuevoProv.addActionListener(this);
        this.views.JMenuReingresarProv.addActionListener(this);
        this.views.txtBuscarProv.addKeyListener(this);
        this.views.TableProveedor.addMouseListener(this);
        this.views.jLabelProveedor.addMouseListener(this);
        
        llenarProveedor();
        listarProveedor();
        AutoCompleteDecorator.decorate(views.cbxProCompra);
        AutoCompleteDecorator.decorate(views.cbxProveedorPro);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btnRegistrarProv) {
    if (views.txtRucProv.getText().equals("")
            || views.txtNombreProv.getText().equals("")
            || views.txtTelefonoProv.getText().equals("")
            || views.txtDireccionProv.getText().equals("")) {
        JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
    } else {
        pr.setRuc(views.txtRucProv.getText());
        pr.setNombre(views.txtNombreProv.getText());
        pr.setTelefono(views.txtTelefonoProv.getText());
        pr.setDireccion(views.txtDireccionProv.getText());
        if (prDao.registrar(pr)) {
            limpiarTable();
            listarProveedor();
            limpiar();
            JOptionPane.showMessageDialog(null, "Cliente registrado");
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar");
        }
    }
} else if (e.getSource() == views.btnModificarProv) {
            if (views.txtIdProv.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
            }else{
                if (views.txtRucProv.getText().equals("")
            || views.txtNombreProv.getText().equals("")
            || views.txtTelefonoProv.getText().equals("")
            || views.txtDireccionProv.getText().equals("")) {
        JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
    } else {
        pr.setRuc(views.txtRucProv.getText());
        pr.setNombre(views.txtNombreProv.getText());
        pr.setTelefono(views.txtTelefonoProv.getText());
        pr.setDireccion(views.txtDireccionProv.getText());
        pr.setId(Integer.parseInt(views.txtIdProv.getText()));
        if (prDao.modificar(pr)) {
            limpiarTable();
            listarProveedor();
            limpiar();
            JOptionPane.showMessageDialog(null, "Proveedor Modificado");
        } else {
            JOptionPane.showMessageDialog(null, "Error al Modificar");
        }
    }
            }
}else if (e.getSource() == views.JMenuReingresarProv) {
            if (views.txtIdProv.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Selecciones una fila");
            }else{
                int id = Integer.parseInt(views.txtIdProv.getText());
        if (prDao.accion("Activo", id)) {
            limpiarTable();
            listarProveedor();
            limpiar();
            JOptionPane.showMessageDialog(null, "Proveedor Reinfresado");
        } else {
            JOptionPane.showMessageDialog(null, "Error al reingresar Proveedor");
        }
            }
            
        }else if (e.getSource() == views.JMenuEliminarProv) {
            if (views.txtIdProv.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
                
            }else{
                int id = Integer.parseInt(views.txtIdProv.getText());
        if (prDao.accion("Inactivo", id)) {
            limpiarTable();
            listarProveedor();
            limpiar();
            JOptionPane.showMessageDialog(null, "Proveedor eliminado");
        } else {
            JOptionPane.showMessageDialog(null, "Error al eliminar Proveedor");
        }
            }
            
        }else{
            limpiar();
        }

   }

    private void listarProveedor() {
        Tables color = new Tables();
        views.TableProveedor.setDefaultRenderer(views.TableProveedor.getColumnClass(0), color);
    List<Proveedor> lista = prDao.ListaProveedor(views.txtBuscarProv.getText());
    modelo = (DefaultTableModel) views.TableProveedor.getModel();
    modelo.setRowCount(0);
    Object[] ob = new Object[6];
    for (int i = 0; i < lista.size(); i++) {
        ob[0] = lista.get(i).getId();
        ob[1] = lista.get(i).getRuc();
        ob[2] = lista.get(i).getNombre();
        ob[3] = lista.get(i).getTelefono();
        ob[4] = lista.get(i).getDireccion();
        ob[5] = lista.get(i).getEstado();
        modelo.addRow(ob);
    }
    views.TableProveedor.setModel(modelo);
    JTableHeader header = views.TableProveedor.getTableHeader();
    header.setOpaque(false);
    header.setBackground(Color.blue);
    header.setForeground(Color.white);
    }

    private void limpiar() {
        views.txtIdProv.setText("");
        views.txtRucProv.setText("");
        views.txtNombreProv.setText("");
        views.txtTelefonoProv.setText("");
        views.txtDireccionProv.setText("");
    }
    
    public void limpiarTable() {
    for (int i = 0; i < modelo.getRowCount(); i++) {
        modelo.removeRow(i);
        i = i - 1;
    }
}
    
    
    @Override
public void mouseClicked(MouseEvent e) {
    if (e.getSource() == views.TableProveedor) {
        int fila = views.TableProveedor.rowAtPoint(e.getPoint());
        views.txtIdProv.setText(views.TableProveedor.getValueAt(fila, 0).toString());
        views.txtRucProv.setText(views.TableProveedor.getValueAt(fila, 1).toString());
        views.txtNombreProv.setText(views.TableProveedor.getValueAt(fila, 2).toString());
        views.txtTelefonoProv.setText(views.TableProveedor.getValueAt(fila, 3).toString());
        views.txtDireccionProv.setText(views.TableProveedor.getValueAt(fila, 4).toString());
    }else if (e.getSource() == views.jLabelProveedor) {
    views.jTabbedPane1.setSelectedIndex(0);
}
    
}

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txtBuscarProv) {
            limpiarTable();
            listarProveedor();
        }
        
    }
    private void llenarProveedor(){ 
        List<Proveedor> lista = prDao.ListaProveedor(views.txtBuscarProv.getText());
    for (int i = 0; i < lista.size(); i++) {
        int id = lista.get(i).getId();
        String nombre = lista.get(i).getNombre();
        views.cbxProveedorPro.addItem(new Combo(id, nombre));
        views.cbxProCompra.addItem(new Combo(id, nombre));
    }
    }
    
}
