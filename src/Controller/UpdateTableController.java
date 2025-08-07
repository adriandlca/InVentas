package Controller;

import Entities.Producto;

import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class UpdateTableController {
    public void UpdateTable(ArrayList<Producto> listaProductos, DefaultTableModel miModelo){
        int num = 0;
        for(Producto prod: listaProductos){
            num++;
            String cantidad, largo, ancho, precioVenta, precioCompra, ganancia, montoVenta, montoCompra;
            DecimalFormat df2 = new DecimalFormat("####.00");
            cantidad = df2.format(prod.getAmount());
            largo = df2.format(prod.getWidthMeters());
            ancho = df2.format(prod.getHeightMeters());
            precioCompra = df2.format(prod.getPriceBuyxMeters());
            precioVenta = df2.format(prod.getPriceSellxMeters());
            ganancia = df2.format(prod.getGanancia());
            montoVenta = df2.format(prod.getMontoDeVenta());
            montoCompra = df2.format(prod.getMontoDeCompra());
            //cabecera ={"N°","Nombre del Producto","Cantidad","Ancho","Largo","P. Compra unitario","P. Venta Unitaria","Costo total","Ingreso total","Ganancia"};
            Object [] fila = {num, prod.getProductName(),cantidad,ancho,largo, precioCompra, precioVenta, montoCompra, montoVenta, ganancia};
            miModelo.addRow(fila);
        }
        num = 0;
    }
}
