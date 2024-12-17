package Controllers;

import Modelo.Combo;
import Modelo.Medidas;
import Modelo.MedidasDao;
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

public class MedidaControllers implements ActionListener, MouseListener, KeyListener {

    private Medidas medida;
    private MedidasDao medidaDao;
    private PanelAdmin views;

    DefaultTableModel modelo = new DefaultTableModel();

    public MedidaControllers(Medidas medida, MedidasDao medidaDao, PanelAdmin views) {
        this.medida = medida;
        this.medidaDao = medidaDao;
        this.views = views;

        // Agregar listeners a botones y eventos
        this.views.btnRegistrarMedida.addActionListener(this);
        this.views.btnModificarMedida.addActionListener(this);
        this.views.JMenuEliminarMedida.addActionListener(this);
        this.views.btnNuevoMedida.addActionListener(this);
        this.views.JMenuReingresarMedida.addActionListener(this);
        this.views.txtBuscarMed.addKeyListener(this);
        this.views.TableMedida.addMouseListener(this);
        this.views.jLabelMedidas.addMouseListener(this);
        
        llenarMedida();
        listarMedidas();
        AutoCompleteDecorator.decorate(views.cbxMedidaPro);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btnRegistrarMedida) {
            if (views.txtNombreMedida.getText().equals("") || views.txtNombreCortoMedida.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
            } else {
                medida.setNombre(views.txtNombreMedida.getText());
                medida.setNombre_corto(views.txtNombreCortoMedida.getText());
                if (medidaDao.registrar(medida)) {
                    limpiarTable();
                    listarMedidas();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Medida registrada");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar la medida");
                }
            }
        } else if (e.getSource() == views.btnModificarMedida) {
            if (views.txtIdMedida.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para modificar");
            } else {
                if (views.txtNombreMedida.getText().equals("") || views.txtNombreCortoMedida.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
                } else {
                    medida.setId(Integer.parseInt(views.txtIdMedida.getText()));
                    medida.setNombre(views.txtNombreMedida.getText());
                    medida.setNombre_corto(views.txtNombreCortoMedida.getText());
                    if (medidaDao.modificar(medida)) {
                        limpiarTable();
                        listarMedidas();
                        limpiar();
                        JOptionPane.showMessageDialog(null, "Medida modificada");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al modificar la medida");
                    }
                }
            }
        }
    }

    private void listarMedidas() {
        Tables color = new Tables();
        views.TableMedida.setDefaultRenderer(views.TableMedida.getColumnClass(0), color);
        List<Medidas> lista = medidaDao.ListaMedidas(views.txtBuscarMed.getText());
        modelo = (DefaultTableModel) views.TableMedida.getModel();
        modelo.setRowCount(0); // Limpia la tabla antes de llenarla
        Object[] ob = new Object[3]; // Tabla: ID, Nombre, Nombre Corto
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getNombre();
            ob[2] = lista.get(i).getNombre_corto();
            modelo.addRow(ob);
        }
        views.TableMedida.setModel(modelo);
        JTableHeader header = views.TableMedida.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.blue);
        header.setForeground(Color.white);
    }

    private void limpiar() {
        views.txtIdMedida.setText("");
        views.txtNombreMedida.setText("");
        views.txtNombreCortoMedida.setText("");
    }

    public void limpiarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.TableMedida) {
            int fila = views.TableMedida.rowAtPoint(e.getPoint());
            views.txtIdMedida.setText(views.TableMedida.getValueAt(fila, 0).toString());
            views.txtNombreMedida.setText(views.TableMedida.getValueAt(fila, 1).toString());
            views.txtNombreCortoMedida.setText(views.TableMedida.getValueAt(fila, 2).toString());
        } else if (e.getSource() == views.jLabelMedidas) {
            views.jTabbedPane1.setSelectedIndex(3); // Cambia al tab correspondiente
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
        if (e.getSource() == views.txtBuscarMed) {
            limpiarTable();
            listarMedidas();
        }
    }
    
    private void llenarMedida(){ 
        List<Medidas> lista = medidaDao.ListaMedidas(views.txtBuscarMed.getText());
    for (int i = 0; i < lista.size(); i++) {
        int id = lista.get(i).getId();
        String nombre = lista.get(i).getNombre();
        views.cbxMedidaPro.addItem(new Combo(id, nombre));
    }
    }
}

