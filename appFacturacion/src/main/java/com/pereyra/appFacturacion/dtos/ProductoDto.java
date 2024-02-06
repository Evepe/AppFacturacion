package com.pereyra.appFacturacion.dtos;

import java.math.BigDecimal;

public class ProductoDto {


    private String marcaProducto;

    private String modeloProducto;

    private String caracteristicaProducto;

    private BigDecimal precioUnitario;

    public ProductoDto() {
    }

    public ProductoDto(String marcaProducto, String modeloProducto, String caracteristicaProducto, BigDecimal precioUnitario) {
        this.marcaProducto = marcaProducto;
        this.modeloProducto = modeloProducto;
        this.caracteristicaProducto = caracteristicaProducto;
        this.precioUnitario = precioUnitario;
    }

    public String getMarcaProducto() {
        return marcaProducto;
    }

    public void setMarcaProducto(String marcaProducto) {
        this.marcaProducto = marcaProducto;
    }

    public String getModeloProducto() {
        return modeloProducto;
    }

    public void setModeloProducto(String modeloProducto) {
        this.modeloProducto = modeloProducto;
    }

    public String getCaracteristicaProducto() {
        return caracteristicaProducto;
    }

    public void setCaracteristicaProducto(String caracteristicaProducto) {
        this.caracteristicaProducto = caracteristicaProducto;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
