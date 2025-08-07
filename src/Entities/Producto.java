package Entities;

public abstract class Producto {

    private String productName;
    private int amount;
    private double widthMeters, heightMeters;
    private double priceSellxMeters;
    private double priceBuyxMeters;


    public Producto(String productName, int amount, double widthMeters, double heightMeters, double priceBuyxMeters, double priceSellxMeters) {
        this.productName = productName;
        this.amount = amount;
        this.widthMeters = widthMeters;
        this.heightMeters = heightMeters;
        this.priceSellxMeters = priceSellxMeters;
        this.priceBuyxMeters = priceBuyxMeters;
    }

    public String getProductName() { return productName; }
    public int getAmount() { return amount; }
    public double getWidthMeters() { return widthMeters; }
    public double getHeightMeters() { return heightMeters; }
    public double getPriceBuyxMeters() { return priceBuyxMeters; }
    public double getPriceSellxMeters() { return priceSellxMeters; }

    public double getGanancia() {
        return getMontoDeVenta() - getMontoDeCompra();
    }
    public abstract double getMontoDeVenta();
    public abstract double getMontoDeCompra();

}
