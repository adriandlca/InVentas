package Views;

import javax.smartcardio.Card;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {
    private JPanel panelPrincipal;
    private JButton inventarioButton;
    private JButton ventasButton;
    private JButton salirButton;
    private JPanel panelContenedor;
    private JPanel panelMenu;

    Inventario inventarioPanel = new Inventario();
    private CardLayout cardLayout;
    private JFrame este = this;

    public MenuPrincipal(){
        isUndecorated();
        cardLayout = (CardLayout) panelContenedor.getLayout();
        vistaDefault();
        accionBtnInventario();
        accionBtnSalir();
    }

    public void accionBtnInventario(){
        inventarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelContenedor.add(inventarioPanel.getPanelInventario(),"Inventario");
                cardLayout.show(panelContenedor,"Inventario");
                SwingUtilities.updateComponentTreeUI(este);
                panelContenedor.revalidate();
                panelContenedor.repaint();
                pack();
                este.repaint();
            }
        });
    }

    public void vistaDefault(){
        panelContenedor.add(inventarioPanel.getPanelInventario(),"Inventario");
        cardLayout.show(panelContenedor,"Inventario");
        SwingUtilities.updateComponentTreeUI(este);
        panelContenedor.revalidate();
        panelContenedor.repaint();
        pack();
        este.repaint();
    }

    public void accionBtnSalir(){
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }

    public JPanel getVentana(){
        return panelPrincipal;
    }
}
