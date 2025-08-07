package Entities;

import java.util.ArrayList;

public class Usuario {
    private String username;
    private String password;
    private ArrayList<Producto> listaProductosVendidos = new ArrayList<Producto>();

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Producto> getListaProductosVendidos() {
        return listaProductosVendidos;
    }

    public void setListaProductosVendidos(ArrayList<Producto> listaProductosVendidos) {
        this.listaProductosVendidos = listaProductosVendidos;
    }
}
