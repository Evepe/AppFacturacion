package com.pereyra.appFacturacion.dtos;


import lombok.Data;

/**
 * Clase de transferencia de datos (DTO) que representa los detalles de una venta.
 * Se utiliza para transportar información entre las capas de servicio y presentación.
 */

@Data
public class VentaDetalleDto {

    /** Identificador del producto asociado a la venta. */
    private Long idProducto;

    /** Marca del producto vendido. */
    private String marca;

    /** Modelo del producto vendido. */
    private String modelo;

    /** Características del producto vendido. */
    private String caracteristica;

    /** Cantidad de unidades del producto vendidas. */
    private int cantidad;

    /** Precio unitario del producto en la venta. */
    private double precio;

    /**
     * Constructor vacío de la clase.
     */
    public VentaDetalleDto() {
    }

    /**
     * Constructor con parámetros para inicializar un objeto VentaDetalleDto.
     *
     * @param idProducto     Identificador del producto asociado a la venta.
     * @param marca          Marca del producto vendido.
     * @param modelo         Modelo del producto vendido.
     * @param caracteristica Características del producto vendido.
     * @param cantidad       Cantidad de unidades del producto vendidas.
     * @param precio         Precio unitario del producto en la venta.
     */
    public VentaDetalleDto(Long idProducto, String marca, String modelo, String caracteristica, int cantidad, double precio) {
        this.idProducto = idProducto;
        this.marca = marca;
        this.modelo = modelo;
        this.caracteristica = caracteristica;
        this.cantidad = cantidad;
        this.precio = precio;
    }
}

