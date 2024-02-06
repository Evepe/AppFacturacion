package com.pereyra.appFacturacion.dtos;

import com.pereyra.appFacturacion.entity.Producto;

import java.math.BigDecimal;
import java.util.List;


public class ComprobanteDto {

    private Long idVentas;

    private String fecha;

    private String nombreCliente;

    private String apellidoCliente;

    private int dniCliente;

    private List<ProductoDto> productos;

    private BigDecimal totalCompra;



    public ComprobanteDto() {
    }

    public ComprobanteDto(Long idVentas, String fecha, String nombreCliente, String apellidoCliente, int dniCliente, BigDecimal totalCompra, String marcaProducto, String modeloProducto, String caracteristicaProducto) {
        this.idVentas = idVentas;
        this.fecha = fecha;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.dniCliente = dniCliente;
        this.totalCompra = totalCompra;
    }



    public Long getIdVentas() {
        return idVentas;
    }

    public void setIdVentas(Long idVentas) {
        this.idVentas = idVentas;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }


    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public int getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(int dniCliente) {
        this.dniCliente = dniCliente;
    }

    public List<ProductoDto> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoDto> productos) {
        this.productos = productos;
    }

    public BigDecimal getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(BigDecimal totalCompra) {
        this.totalCompra = totalCompra;
    }
}
