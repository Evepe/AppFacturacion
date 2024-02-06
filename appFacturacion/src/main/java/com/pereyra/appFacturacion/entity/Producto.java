package com.pereyra.appFacturacion.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * Representa un producto en el sistema
 */
@Entity
@Data //Genera Getters & Setters
@NoArgsConstructor //Genera constructor vacio requerido por JPA
@AllArgsConstructor // Genera constructor con todos los atributos
@Table (name= "productos") //Genera tabla producto en BD
public class Producto {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column (name="codigo_producto")
    @JsonProperty("codigo_producto")
    @NotNull
    private String codigoProducto;

    @Column(name= "marca")
    @JsonProperty("marca")
    @NotNull
    private String marcaProducto;

    @Column (name="modelo")
    @JsonProperty("modelo")
    private String modeloProducto;

    @Column (name="caracteristicas")
    @JsonProperty("caracteristicas")
    private String caracProducto;

    @Column (name="stock")
    @JsonProperty("stock")
    @Min(0)
    private int StockProducto;

    @Column (name="precio")
    @JsonProperty("precio")
    @NotNull
    private BigDecimal precioProducto;

    @ManyToMany(mappedBy = "productoList")
    @JsonIgnore
    private List<Venta> ventas;

    @Column(name = "cantidad_vendida")
    private int cantidadVendida;










}

