package Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ClearTableController {
    public void Clear(DefaultTableModel miModelo, JTable tabla){
        int filas = tabla.getRowCount();
        for(int i=0;i<filas;i++){
            miModelo.removeRow(0);
        }
    }
}
