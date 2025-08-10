package Entities;

public class ProductoPorUnidad extends Producto{

    public ProductoPorUnidad(String productName, int amount, double widthMeters, double heightMeters, double priceBuyxMeters, double priceSellxMeters) {
        super(productName, amount, widthMeters, heightMeters, priceBuyxMeters, priceSellxMeters);
    }

    @Override
    public double getGanancia() {
        if(getGastosMovilidad() > 0){
            return  ( getPriceSellxMeters() - (getPriceBuyxMeters() + getGastosMovilidad()) ) * getAmount();
        }
        return getMontoDeVenta()-getMontoDeCompra();
    }

    @Override
    public double getMontoDeVenta() {
        return getAmount() * getPriceSellxMeters();
    }

    @Override
    public double getMontoDeCompra() {
        return getAmount() * getPriceBuyxMeters();
    }
}
