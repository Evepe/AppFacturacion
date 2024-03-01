package com.pereyra.appFacturacion.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.io.Serial;
import java.math.BigDecimal;

/**
 * Representa los detalle de ventas realizadas. Generando una instantanea del producto para evitar futuras modificaciones
 */
@Entity
@Data
@Table(name="detalle_venta")
public class VentaDetalle {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID del detalle de venta", example = "1")
    private Long idVentaDetalle;

    @ManyToOne
    @JoinColumn(name = "id_venta")
    @JsonIgnore
    @Schema(description = "Se vincula con entidad venta", hidden = true)
    private Venta venta;

    @Column(name = "id_producto")
    @Schema(description = "ID del producto adquirido", example = "1")
    private Long idProducto;

    @Column
    @Schema(description = "Marca producto adquirido", example = "Samsung")
    private String marca;
    @Schema(description = "Modelo producto adquirido", example = "Tab A9")
    private String modelo;
    @Schema(description = "Caracteristica del producto", example = "Tablet- Memoria interna 125 GB")
    private String caracteristica;

    @Column(nullable = false)
    @Min(1)
    @Schema(description = "Cantidad de unidades adquirida por producto", example = "1")
    private Integer cantidad;

    @Column(nullable = false)
    @Schema(description = "Precio unitario del producto",example = "50.25")
    private double precio;

    public VentaDetalle() {
    }

    public VentaDetalle(Long idVentaDetalle, Venta venta, Long idProducto, String marca, String modelo, String caracteristica, Integer cantidad, double precio) {
        this.idVentaDetalle = idVentaDetalle;
        this.venta = venta;
        this.idProducto = idProducto;
        this.marca = marca;
        this.modelo = modelo;
        this.caracteristica = caracteristica;
        this.cantidad = cantidad;
        this.precio = precio;
    }
}
