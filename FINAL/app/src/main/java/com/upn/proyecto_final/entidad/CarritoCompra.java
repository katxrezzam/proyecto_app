package com.upn.proyecto_final.entidad;

public class CarritoCompra {

    private String idCliente;
    private int idOferta;
    private int cantidad;

    public CarritoCompra(String idCliente, int idOferta, int cantidad) {
        this.idCliente = idCliente;
        this.idOferta = idOferta;
        this.cantidad = cantidad;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
