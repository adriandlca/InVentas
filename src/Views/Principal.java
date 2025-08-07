package Views;

import Controller.ClearTableController;
import Controller.UpdateTableController;
import Entities.Producto;
import Entities.ProductoPorMetro;
import Exceptions.InvalidNumberFieldException;
import Validators.ValidarFormularioDelProducto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Principal {
    private JPanel Ventana;
    private JButton inventarioButton;
    private JButton ventasButton;
    private JTable tblTablaDeProductos;
    private JComboBox comboBox1;
    private JButton agregarProductoButton;
    private JButton eliminarProductoButton;
    private JButton modificarProductoButton;
    private JTextField txtNombreProducto;
    private JTextField txtCantidad;
    private JTextField txtPrecioVentaxMetro;
    private JTextField txtPrecioCompraxMetro;
    private JTextField txtAncho;
    private JTextField txtLargo;
    private JRadioButton porMetroRadioButton;
    private JRadioButton porUnidadRadioButton;

    DefaultTableModel miModelo;
    String[] cabecera ={"N°","Nombre del Producto","Cantidad","Ancho","Largo","P. Compra unitario","P. Venta Unitaria","Costo total","Ingreso total","Ganancia"};
    String[][] data={};

    private ArrayList<Producto> listaProductos = new ArrayList<Producto>();

    public Principal() {
        miModelo = new DefaultTableModel(data,cabecera);
        tblTablaDeProductos.setModel(miModelo);
        agregarProductoBtnAction();
    }


    private void agregarProductoBtnAction() {
        agregarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //intanciación de las clases
                UpdateTableController actualizarTablaInventario = new UpdateTableController();
                ClearTableController limpiarTabla = new ClearTableController();
                //Usando metodos
                try {
                    registrarProducto();
                } catch (InvalidNumberFieldException ex) {
                    throw new RuntimeException(ex);
                }
                limpiarCampos();
                limpiarTabla.Clear(miModelo,tblTablaDeProductos);
                actualizarTablaInventario.UpdateTable(listaProductos,miModelo);
            }
        });
    }

    private void limpiarCampos(){
        txtNombreProducto.setText("");
        txtCantidad.setText("");
        txtPrecioVentaxMetro.setText("");
        txtPrecioCompraxMetro.setText("");
        txtAncho.setText("");
        txtLargo.setText("");
    }

    private void registrarProducto() throws InvalidNumberFieldException {
        String nombreProducto = txtNombreProducto.getText().trim();
        String cantidad = txtCantidad.getText();
        String precioVentaxMetro = txtPrecioVentaxMetro.getText();
        String precioCompraxMetro = txtPrecioCompraxMetro.getText();
        String ancho = txtAncho.getText();
        String largo = txtLargo.getText();

        ValidarFormularioDelProducto validar = new ValidarFormularioDelProducto();

        if(!nombreProducto.isEmpty() && !cantidad.isEmpty() && !precioCompraxMetro.isEmpty() && !precioVentaxMetro.isEmpty() && !ancho.isEmpty() && !largo.isEmpty()){
            try{
                if(validar.CamposDeNumeros(cantidad) && validar.CamposDeNumeros(precioCompraxMetro) && validar.CamposDeNumeros(precioVentaxMetro) && validar.CamposDeNumeros(ancho) && validar.CamposDeNumeros(largo)){
                    int cantidadV = Integer.parseInt(txtCantidad.getText());
                    double precioVentaxMetroV = Double.parseDouble(txtPrecioVentaxMetro.getText());
                    double precioCompraxMetroV = Double.parseDouble(txtPrecioCompraxMetro.getText());
                    double anchoV = Double.parseDouble(txtAncho.getText());
                    double largoV = Double.parseDouble(txtLargo.getText());
                    Producto producto = new ProductoPorMetro(nombreProducto,cantidadV,largoV,anchoV,precioCompraxMetroV,precioVentaxMetroV);
                    listaProductos.add(producto);
                }
            }catch (InvalidNumberFieldException e){
                JOptionPane.showMessageDialog(null,e.getMessage(),"Dato invalido",JOptionPane.WARNING_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null,"Ningún campo debe estar vacio","Campos Vacios",JOptionPane.WARNING_MESSAGE);
        }
    }

    public JPanel getVentana(){
        return Ventana;
    }

    public JTable getTblTablaDeProductos() {
        return tblTablaDeProductos;
    }
}
