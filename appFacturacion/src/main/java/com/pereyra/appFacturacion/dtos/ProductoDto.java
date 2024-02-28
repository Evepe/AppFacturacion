package com.pereyra.appFacturacion.dtos;

import java.math.BigDecimal;

public class ProductoDto {

    private Long idProductoDto;
    private String marcaProducto;

    private String modeloProducto;

    private String caracteristicaProducto;

    private BigDecimal precioUnitario;

    private int cantidadProductoDto;

    private int stock;

    public ProductoDto() {
    }

    public ProductoDto(Long idProductoDto, String marcaProducto, String modeloProducto, String caracteristicaProducto, BigDecimal precioUnitario, int cantidadProductoDto) {
        this.idProductoDto = idProductoDto;
        this.marcaProducto = marcaProducto;
        this.modeloProducto = modeloProducto;
        this.caracteristicaProducto = caracteristicaProducto;
        this.precioUnitario = precioUnitario;
        this.cantidadProductoDto = cantidadProductoDto;
    }

    public Long getIdProductoDto() {
        return idProductoDto;
    }

    public void setIdProductoDto(Long idProductoDto) {
        this.idProductoDto = idProductoDto;
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

    public int getCantidadProductoDto() {
        return cantidadProductoDto;
    }

    public void setCantidadProductoDto(int cantidadProductoDto) {
        this.cantidadProductoDto = cantidadProductoDto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
