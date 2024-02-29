package com.pereyra.appFacturacion.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.math.BigDecimal;

@Entity
@Data
@Table(name="detalle_venta")
public class VentaDetalle {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVentaDetalle;

    @ManyToOne
    @JoinColumn(name = "id_venta")
    @JsonIgnore
    private Venta venta;

    @Column(name = "id_producto")
    private Long idProducto;

    private String marca;
    private String modelo;
    private String caracteristica;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
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
