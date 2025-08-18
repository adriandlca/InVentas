package Controller;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConectionController {

    private static final String url = "jdbc:sqlite:D:/Proyectos IDEA/InventVentas/src/Db/InVentasDB.db/";

    public static Connection conectarDB(){
        Connection conn  = null;
        try{
            conn = DriverManager.getConnection(url);
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"Error!",JOptionPane.ERROR_MESSAGE);
        }
    }
}
