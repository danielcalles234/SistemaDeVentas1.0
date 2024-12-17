package Controllers;

import Modelo.Categorias;
import Modelo.CategoriasDao;
import Modelo.Combo;
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

public class CategoriaControllers implements ActionListener, MouseListener, KeyListener {

    private Categorias cat;
    private CategoriasDao catDao;
    private PanelAdmin views;

    DefaultTableModel modelo = new DefaultTableModel();

    public CategoriaControllers(Categorias cat, CategoriasDao catDao, PanelAdmin views) {
        this.cat = cat;
        this.catDao = catDao;
        this.views = views;
        this.views.btnRegistrarCat.addActionListener(this);
        this.views.btnModificarCat.addActionListener(this);
        this.views.JMenuEliminarCat.addActionListener(this);
        this.views.btnNuevoCat.addActionListener(this);
        this.views.JMenuReingresarCat.addActionListener(this);
        this.views.txtBuscarCat.addKeyListener(this);
        this.views.TableCat.addMouseListener(this);
        this.views.jLabelCat.addMouseListener(this);
        
        llenarCat();
        listarCategorias();
        AutoCompleteDecorator.decorate(views.cbxCatPro);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btnRegistrarCat) {
            if (views.txtNombreCat.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "El campo Nombre es obligatorio");
            } else {
                cat.setNombre(views.txtNombreCat.getText());
                cat.setEstado("Activo"); // Estado inicial
                if (catDao.registrar(cat)) {
                    limpiarTable();
                    listarCategorias();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Categoría registrada");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar la categoría");
                }
            }
        } else if (e.getSource() == views.btnModificarCat) {
            if (views.txtIdCat.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para modificar");
            } else {
                if (views.txtNombreCat.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "El campo Nombre es obligatorio");
                } else {
                    cat.setId(Integer.parseInt(views.txtIdCat.getText()));
                    cat.setNombre(views.txtNombreCat.getText());
                    if (catDao.modificar(cat)) {
                        limpiarTable();
                        listarCategorias();
                        limpiar();
                        JOptionPane.showMessageDialog(null, "Categoría modificada");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al modificar la categoría");
                    }
                }
            }
        } else if (e.getSource() == views.JMenuReingresarCat) {
            if (views.txtIdCat.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
            } else {
                int id = Integer.parseInt(views.txtIdCat.getText());
                if (catDao.accion("Activo", id)) {
                    limpiarTable();
                    listarCategorias();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Categoría reingresada");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al reingresar la categoría");
                }
            }
        } else if (e.getSource() == views.JMenuEliminarCat) {
            if (views.txtIdCat.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
            } else {
                int id = Integer.parseInt(views.txtIdCat.getText());
                if (catDao.accion("Inactivo", id)) {
                    limpiarTable();
                    listarCategorias();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Categoría eliminada");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar la categoría");
                }
            }
        } else {
            limpiar();
        }
    }

    private void listarCategorias() {
        Tables color = new Tables();
        views.TableCat.setDefaultRenderer(views.TableCat.getColumnClass(0), color);
        List<Categorias> lista = catDao.ListaCategoria(views.txtBuscarCat.getText());
        modelo = (DefaultTableModel) views.TableCat.getModel();
        modelo.setRowCount(0); // Limpia la tabla
        Object[] ob = new Object[3]; // Tabla con 3 columnas: ID, Nombre, Estado
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getNombre();
            ob[2] = lista.get(i).getEstado();
            modelo.addRow(ob);
        }
        views.TableCat.setModel(modelo);
        JTableHeader header = views.TableCat.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.blue);
        header.setForeground(Color.white);
    }

    private void limpiar() {
        views.txtIdCat.setText("");
        views.txtNombreCat.setText("");
    }

    public void limpiarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.TableCat) {
            int fila = views.TableCat.rowAtPoint(e.getPoint());
            views.txtIdCat.setText(views.TableCat.getValueAt(fila, 0).toString());
            views.txtNombreCat.setText(views.TableCat.getValueAt(fila, 1).toString());
        } else if (e.getSource() == views.jLabelCat) {
            views.jTabbedPane1.setSelectedIndex(2);
            limpiarTable();
            listarCategorias();
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
        if (e.getSource() == views.txtBuscarCat) {
            limpiarTable();
            listarCategorias();
        }
    }
    
    private void llenarCat(){ 
        List<Categorias> lista = catDao.ListaCategoria(views.txtBuscarCat.getText());
    for (int i = 0; i < lista.size(); i++) {
        int id = lista.get(i).getId();
        String nombre = lista.get(i).getNombre();
        views.cbxCatPro.addItem(new Combo(id, nombre));
    }
    }
}
