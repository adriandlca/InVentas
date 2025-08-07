package Entities;

public class ProductoPorUnidad extends Producto{
    public ProductoPorUnidad(String productName, int amount, double widthMeters, double heightMeters, double priceBuyxMeters, double priceSellxMeters) {
        super(productName, amount, widthMeters, heightMeters, priceBuyxMeters, priceSellxMeters);
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
