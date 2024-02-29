package com.pereyra.appFacturacion.dtos;


import com.pereyra.appFacturacion.entity.VentaDetalle;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductoDto {

    //Atributos

    private Long idProducto;


    private String codigoProducto;


    private String marcaProducto;


    private String modeloProducto;


    private String caracProducto;


    private int StockProducto;


    private double precioProducto;


    private int cantidadVendida;


    private int cantidadTotalVendido;


    private List<VentaDetalle> ventaDetalles;

    public ProductoDto() {
        this.ventaDetalles=new ArrayList<>();
    }

    public ProductoDto(Long idProducto, String codigoProducto, String marcaProducto, String modeloProducto, String caracProducto, int stockProducto, double precioProducto, int cantidadVendida, int cantidadTotalVendido, List<VentaDetalle> ventaDetalles) {
        this.idProducto = idProducto;
        this.codigoProducto = codigoProducto;
        this.marcaProducto = marcaProducto;
        this.modeloProducto = modeloProducto;
        this.caracProducto = caracProducto;
        StockProducto = stockProducto;
        this.precioProducto = precioProducto;
        this.cantidadVendida = cantidadVendida;
        this.cantidadTotalVendido = cantidadTotalVendido;
        this.ventaDetalles = ventaDetalles;
    }
}
