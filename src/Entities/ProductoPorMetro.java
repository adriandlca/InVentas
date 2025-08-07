package Entities;

public class ProductoPorMetro extends Producto {

    public ProductoPorMetro(String productName, int amount, double widthMeters, double heightMeters, double priceBuyxMeters, double priceSellxMeters) {
        super(productName, amount, widthMeters, heightMeters, priceBuyxMeters, priceSellxMeters);
    }

    @Override
    public double getMontoDeCompra() {
        return getWidthMeters()*getPriceBuyxMeters();
    }

    @Override
    public double getMontoDeVenta() {
        return getWidthMeters() * getPriceSellxMeters();
    }
}
