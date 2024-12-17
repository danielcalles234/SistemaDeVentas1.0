
package Controllers;

import Views.PanelAdmin;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class ConfigControllers implements MouseListener{
    
    private PanelAdmin views;

    public ConfigControllers(PanelAdmin views) {
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
