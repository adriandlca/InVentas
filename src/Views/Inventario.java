package Views;

import Controller.ClearTableController;
import Controller.UpdateTableController;
import Entities.Producto;
import Entities.ProductoPorMetro;
import Entities.ProductoPorUnidad;
import Exceptions.InvalidNumberFieldException;
import Validators.ValidarFormularioDelProducto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

public class Inventario extends JPanel{

    private JButton modificarProductoButton;
    private JTextField txtNombreProducto;
    private JButton eliminarProductoButton;
    private JTextField txtPrecioCompraxMetro;
    private JTextField txtCantidad;
    private JTextField txtPrecioVentaxMetro;
    private JTextField txtAncho;
    private JComboBox comboBox1;
    private JTextField txtLargo;
    private JButton agregarProductoButton;
    private JRadioButton porUnidadRadioButton;
    private JRadioButton porMetroRadioButton;
    private JTable tblTablaDeProductos;
    private JPanel panelInventario;
    private JCheckBox cbGastosMovilidad;
    private JTextField txtCostoMovilidad;
    private JTextField txtCantidadDeProductos;


    DefaultTableModel miModelo;
    String[] cabecera ={"N°","Nombre del Producto","Cantidad","Ancho","Largo","P. Compra unitario","P. Venta Unitaria", "Gastos","Costo total","Ingreso total","Ganancia"};
    String[][] data={};
    ButtonGroup group = new ButtonGroup();

    private ArrayList<Producto> listaProductos = new ArrayList<Producto>();

    public Inventario() {
        miModelo = new DefaultTableModel(data, cabecera);
        tblTablaDeProductos.setModel(miModelo);

        group.add(porMetroRadioButton);
        group.add(porUnidadRadioButton);

        estadoCamposGastosMovilidad(false);
        gastosMovilidadAction();

        agregarProductoBtnAction();
        txtCostoMovilidad.setForeground(Color.GRAY);
        txtCostoMovilidadAction();

    }
    //ACTIVAR O DESACTIVAR CAMPOS Y PLACEHOLDER DE COSTOMOVILIDAD
    private boolean hayGastosPorMovilidad(){
        if(cbGastosMovilidad.isSelected() == false){
            return false;
        }
        return true;
    }
    private void estadoCamposGastosMovilidad(boolean estado){
            txtCostoMovilidad.setEnabled(estado);
            txtCantidadDeProductos.setEnabled(estado);
    }
    private void gastosMovilidadAction(){
        cbGastosMovilidad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(hayGastosPorMovilidad()){
                    estadoCamposGastosMovilidad(true);
                }else{
                    estadoCamposGastosMovilidad(false);
                }
            }
        });
    }
    private void txtCostoMovilidadAction(){
        txtCostoMovilidad.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(txtCostoMovilidad.getText().equals("Ingrese costo de movilidad")){
                    txtCostoMovilidad.setText("");
                    txtCostoMovilidad.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if(txtCostoMovilidad.getText().isEmpty()){
                    txtCostoMovilidad.setForeground(Color.GRAY);
                    txtCostoMovilidad.setText("Ingrese costo de movilidad");
                }
            }
        });
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

        String costoMovilidad="";
        String cantidadProductosTotal="";

        if(hayGastosPorMovilidad()){
            costoMovilidad = txtCostoMovilidad.getText();
            cantidadProductosTotal = txtCantidadDeProductos.getText();
        }

        ValidarFormularioDelProducto validar = new ValidarFormularioDelProducto();
        if(!nombreProducto.isEmpty() && !cantidad.isEmpty() && !precioCompraxMetro.isEmpty() && !precioVentaxMetro.isEmpty() && !ancho.isEmpty() && !largo.isEmpty()){
            try{
                if(validar.CamposDeNumeros(cantidad) && validar.CamposDeNumeros(precioCompraxMetro) && validar.CamposDeNumeros(precioVentaxMetro) && validar.CamposDeNumeros(ancho) && validar.CamposDeNumeros(largo)){

                    Producto producto;
                    int cantidadV = Integer.parseInt(cantidad);
                    double precioVentaxMetroV = Double.parseDouble(precioVentaxMetro);
                    double precioCompraxMetroV = Double.parseDouble(precioCompraxMetro);
                    double anchoV = Double.parseDouble(ancho);
                    double largoV = Double.parseDouble(largo);

                    double costoMovilidadV = 0;
                    int cantidadProductosTotalV = 0;

                    if( hayGastosPorMovilidad() ) {
                        if( !costoMovilidad.isEmpty() && !cantidadProductosTotal.isEmpty() ){
                            if( validar.CamposDeNumeros(costoMovilidad) && validar.CamposDeNumeros(cantidadProductosTotal) ){
                                costoMovilidadV = Double.parseDouble(costoMovilidad);
                                cantidadProductosTotalV = Integer.parseInt(cantidadProductosTotal);
                            }else{ JOptionPane.showMessageDialog(null,"Ingrese datos validos (solo números)","Error de datos ingresados",JOptionPane.WARNING_MESSAGE);}
                        }else{JOptionPane.showMessageDialog(null,"los campos no deben de estar vacios","Error de campos",JOptionPane.WARNING_MESSAGE);}
                    }

                    if(group.isSelected(porMetroRadioButton.getModel())){
                        producto = new ProductoPorMetro(nombreProducto,cantidadV,largoV,anchoV,precioCompraxMetroV,precioVentaxMetroV);
                        producto.agregasGastosMovilidad(costoMovilidadV,cantidadProductosTotalV);
                        listaProductos.add(producto);
                    } else if (group.isSelected(porUnidadRadioButton.getModel())) {
                        producto = new ProductoPorUnidad(nombreProducto,cantidadV,largoV,anchoV,precioCompraxMetroV,precioVentaxMetroV);
                        listaProductos.add(producto);
                    }
                }
            }catch (InvalidNumberFieldException e){
                JOptionPane.showMessageDialog(null,e.getMessage(),"Dato invalido",JOptionPane.WARNING_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null,"Ningún campo debe estar vacio","Campos Vacios",JOptionPane.WARNING_MESSAGE);
        }
    }

    public JPanel getPanelInventario() {
        return panelInventario;
    }

    public JTable getTblTablaDeProductos() {
        return tblTablaDeProductos;
    }
}
