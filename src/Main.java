import Views.MenuPrincipal;
import Views.Principal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("InVentas");
            MenuPrincipal form = new MenuPrincipal();

            frame.setUndecorated(true);
            frame.setContentPane(form.getVentana());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
