package Controllers;

import Modelo.Clientes;
import Modelo.ClientesDao;
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

public class ClientesControllers implements ActionListener, MouseListener, KeyListener {

    private Clientes cl;
    private ClientesDao clDao;
    private PanelAdmin views;
    DefaultTableModel modelo = new DefaultTableModel();

    public ClientesControllers(Clientes cl, ClientesDao clDao, PanelAdmin views) {
        this.cl = cl;
        this.clDao = clDao;
        this.views = views;
        this.views.btnRegistrarCli.addActionListener(this);
        this.views.btnModificarCli.addActionListener(this);
        this.views.btnNuevoCli.addActionListener(this);
        this.views.TableClientes.addMouseListener(this);
        this.views.JMenuEliminarCli.addActionListener(this);
        this.views.JMenuReingresarCli.addActionListener(this);
        this.views.txtBuscarCli.addKeyListener(this);
        this.views.jLabelClientes.addMouseListener(this);
        listarClientes();
        llenarClientes();
        AutoCompleteDecorator.decorate(views.cbxClienteVentas);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btnRegistrarCli) {
            if (views.txtNombreCli.getText().equals("")
                    || views.txtTelefonoCli.getText().equals("")
                    || views.txtDireccionCli.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
            } else {
                cl.setNombre(views.txtNombreCli.getText());
                cl.setTelefono(views.txtTelefonoCli.getText());
                cl.setDireccion(views.txtDireccionCli.getText());
                String respuesta = clDao.registrar(cl);
                switch (respuesta) {
                    case "existe":
                        JOptionPane.showMessageDialog(null, "EL TELÉFONO DEL CLIENTE DEBE SER ÚNICO");
                        break;
                    case "registrado":
                        limpiarTable();
                        listarClientes();
                        limpiar();
                        JOptionPane.showMessageDialog(null, "CLIENTE REGISTRADO");
                        views.cargarDatos(); // Actualizar los números
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Error al registrar");
                        break;
                }

            }
        } else if (e.getSource() == views.btnModificarCli) {
            if (views.txtIdCli.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
            } else {
                if (views.txtNombreCli.getText().equals("")
                        || views.txtTelefonoCli.getText().equals("")
                        || views.txtDireccionCli.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
                } else {
                    cl.setNombre(views.txtNombreCli.getText());
                    cl.setTelefono(views.txtTelefonoCli.getText());
                    cl.setDireccion(views.txtDireccionCli.getText());
                    cl.setId(Integer.parseInt(views.txtIdCli.getText()));
                    String respuesta = clDao.modificar(cl);
                    switch (respuesta) {
                    case "existe":
                        JOptionPane.showMessageDialog(null, "EL TELÉFONO DEL CLIENTE DEBE SER ÚNICO");
                        break;
                    case "modificado":
                        limpiarTable();
                        listarClientes();
                        limpiar();
                        views.cargarDatos(); // Actualiza los números de clientes
                        JOptionPane.showMessageDialog(null, "CLIENTE Modificado");
                        views.cargarDatos(); // Actualizar los números
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Error al registrar");
                        break;
                }
                }
            }
        } else if (e.getSource() == views.JMenuReingresarCli) {
            if (views.txtIdCli.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Selecciones una fila");
            } else {
                int id = Integer.parseInt(views.txtIdCli.getText());
                if (clDao.accion("Activo", id)) {
                    limpiarTable();
                    listarClientes();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Cliente Reingresado");
                    views.cargarDatos(); // Actualizar los números
                } else {
                    JOptionPane.showMessageDialog(null, "Error al reingresar cliente");
                }
            }

        } else if (e.getSource() == views.JMenuEliminarCli) {
            if (views.txtIdCli.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila");

            } else {
                int id = Integer.parseInt(views.txtIdCli.getText());
                if (clDao.accion("Inactivo", id)) {
                    limpiarTable();
                    listarClientes();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Cliente eliminado");
                    views.cargarDatos(); // Actualizar los números
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar cliente");
                }
            }

        } else {
            limpiar();
        }

    }

    public void listarClientes() {
        Tables color = new Tables();
        views.TableClientes.setDefaultRenderer(views.TableClientes.getColumnClass(0), color);
        List<Clientes> lista = clDao.ListaClientes(views.txtBuscarCli.getText());
        modelo = (DefaultTableModel) views.TableClientes.getModel();
        Object[] ob = new Object[5];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getNombre();
            ob[2] = lista.get(i).getTelefono();
            ob[3] = lista.get(i).getDireccion();
            ob[4] = lista.get(i).getEstado();
            modelo.addRow(ob);
        }
        views.TableClientes.setModel(modelo);
        JTableHeader header = views.TableClientes.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.blue);
        header.setForeground(Color.white);
    }

    public void limpiarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.TableClientes) {
            int fila = views.TableClientes.rowAtPoint(e.getPoint());
            views.txtIdCli.setText(views.TableClientes.getValueAt(fila, 0).toString());
            views.txtNombreCli.setText(views.TableClientes.getValueAt(fila, 1).toString());
            views.txtTelefonoCli.setText(views.TableClientes.getValueAt(fila, 2).toString());
            views.txtDireccionCli.setText(views.TableClientes.getValueAt(fila, 3).toString());
        } else if (e.getSource() == views.jLabelClientes) {
            views.jTabbedPane1.setSelectedIndex(8);
            limpiarTable();
            listarClientes();
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

    private void limpiar() {
        views.txtNombreCli.setText("");
        views.txtTelefonoCli.setText("");
        views.txtDireccionCli.setText("");
        views.txtIdCli.setText("");
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txtBuscarCli) {
            limpiarTable();
            listarClientes();
        }
    }

    private void llenarClientes() {
        List<Clientes> lista = clDao.ListaClientes(views.txtBuscarCli.getText());
        for (int i = 0; i < lista.size(); i++) {
            int id = lista.get(i).getId();
            String nombre = lista.get(i).getNombre();
            views.cbxClienteVentas.addItem(new Combo(id, nombre));
        }
    }

}
