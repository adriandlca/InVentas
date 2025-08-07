package Validators;

import Exceptions.InvalidNumberFieldException;

public class ValidarFormularioDelProducto {
    public boolean CamposDeNumeros(String dato) throws InvalidNumberFieldException {
        if(!dato.matches("\\d+(\\.\\d+)?")){
            throw new InvalidNumberFieldException("El dato '"+ dato +"' no admite letras");
        }
        return true;
    }
}
