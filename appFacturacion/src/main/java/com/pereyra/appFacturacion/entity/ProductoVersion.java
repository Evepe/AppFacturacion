package com.pereyra.appFacturacion.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="version_producto")
public class ProductoVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVs;

    @Column(name="version_producto")
    @Version
    private int version;


    @Column(name="marca_producto")
    private String marcaProducto;


    @Column(name="modelo_producto")
    private String modeloProducto;

    @Column(name= "caracteristicas_producto")
    private String caracteristicaProducto;

    @Column(name="precio_producto")
    private BigDecimal precioProducto;

    @Column(name="fecha_creacion_version")
    private LocalDate fechaCreacion;


    @ManyToOne
    @JoinColumn (name = "producto_id")
    private Producto producto;





}
