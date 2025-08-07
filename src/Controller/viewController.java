package Controller;

import Views.Principal;

import javax.swing.*;

public class viewController {

    public void cambiarVentana(String nombreVentana){
        switch (nombreVentana){
            case "Inventario":
                ventanaInventario();
                break;
            case "Ventas":
                ventanaVentas();
                break;
            default:
                JOptionPane.showMessageDialog(null,"No existe la ventana");
        }

    }

    private static void ventanaVentas() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Ventas");
            //Ventas form = new Ventas();

            //frame.setContentPane(form.getVentana());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private static void ventanaInventario() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Inventario");
            Principal form = new Principal();

            frame.setContentPane(form.getVentana());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

}
