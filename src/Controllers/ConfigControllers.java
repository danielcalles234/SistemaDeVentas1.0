
package Controllers;

import Modelo.Configuracion;
import Modelo.UsuarioDao;
import Views.PanelAdmin;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class ConfigControllers implements MouseListener{
    
    private Configuracion cof;
    private UsuarioDao Cdao;
    private PanelAdmin views;

    public ConfigControllers(Configuracion cof, UsuarioDao Cdao, PanelAdmin views) {
    this.cof = cof ;
    this.Cdao = Cdao;
    this.views = views;
    this.views.jLabelCat.addMouseListener(this);
    this.views.jLabelClientes.addMouseListener(this);
    this.views.jLabelConfig.addMouseListener(this);
    this.views.jLabelMedidas.addMouseListener(this);
    this.views.jLabelNuevaCompra.addMouseListener(this);
    this.views.jLabelNuevaVenta.addMouseListener(this);
    this.views.jLabelProveedor.addMouseListener(this);
    this.views.jLabelUsers.addMouseListener(this);
    this.views.jLabelProductos.addMouseListener(this);
    cof = Cdao.getConfig();
    views.txtIdEmpresa.setText(""+cof.getId());
    views.txtRucEmpresa.setText(cof.getRuc());
    views.txtTelefonoEmpresa.setText(cof.getTelefono());
    views.txtNombreEmpresa.setText(cof.getNombre());
    views.txtDireccionEmpresa.setText(cof.getDireccion());
    views.txtMensaje.setText(cof.getMensaje());

  
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == views.jLabelConfig){
            views.jTabbedPane1.setSelectedIndex(6);
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
    if (e.getSource() == views.jLabelCat) {
        views.JPanelCategorias.setBackground(new Color(255, 51, 51));
    } else if (e.getSource() == views.jLabelClientes) {
        views.JPanelClientes.setBackground(new Color(255, 51, 51));
    }else if (e.getSource() == views.jLabelConfig) {
        views.JPanelConfig.setBackground(new Color(255, 51, 51));
    }else if (e.getSource() == views.jLabelMedidas) {
        views.JPanelMedidas.setBackground(new Color(255, 51, 51));
    }else if (e.getSource() == views.jLabelNuevaCompra) {
        views.JPanelNuevaCompra.setBackground(new Color(255, 51, 51));
    }else if (e.getSource() == views.jLabelNuevaVenta) {
        views.JPanelNuevaVenta.setBackground(new Color(255, 51, 51));
    }else if (e.getSource() == views.jLabelProveedor) {
        views.JPanelProveedor.setBackground(new Color(255, 51, 51));
    }else if (e.getSource() == views.jLabelUsers) {
        views.JPanelUsers.setBackground(new Color(255, 51, 51));
    }else{
        views.JPanelProductos.setBackground(new Color(255, 51, 51));
    }
}

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == views.jLabelCat) {
        views.JPanelCategorias.setBackground(new Color(102,102,102));
    } else if (e.getSource() == views.jLabelClientes) {
        views.JPanelClientes.setBackground(new Color(102,102,102));
    }
        else if (e.getSource() == views.jLabelConfig) {
        views.JPanelConfig.setBackground(new Color(102,102,102));
    }else if (e.getSource() == views.jLabelMedidas) {
        views.JPanelMedidas.setBackground(new Color(102,102,102));
    }else if (e.getSource() == views.jLabelNuevaCompra) {
        views.JPanelNuevaCompra.setBackground(new Color(102,102,102));
    }else if (e.getSource() == views.jLabelNuevaVenta) {
        views.JPanelNuevaVenta.setBackground(new Color(102,102,102));
    }else if (e.getSource() == views.jLabelProveedor) {
        views.JPanelProveedor.setBackground(new Color(102,102,102));
    }else if (e.getSource() == views.jLabelUsers) {
        views.JPanelUsers.setBackground(new Color(102,102,102));
    }else{
        views.JPanelProductos.setBackground(new Color(102,102,102));
    }
    }
    
}
