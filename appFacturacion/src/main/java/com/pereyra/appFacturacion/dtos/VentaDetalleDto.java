package com.pereyra.appFacturacion.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
public class VentaDetalleDto {


    private Long idProducto;

    private String marca;

    private String modelo;

    private String caracteristica;
    private int cantidad;
    private double precio;


    public VentaDetalleDto() {
    }

    public VentaDetalleDto(Long idProducto, String marca, String modelo, String caracteristica, int cantidad, double precio) {
        this.idProducto = idProducto;
        this.marca = marca;
        this.modelo = modelo;
        this.caracteristica = caracteristica;
        this.cantidad = cantidad;
        this.precio = precio;
    }
}
