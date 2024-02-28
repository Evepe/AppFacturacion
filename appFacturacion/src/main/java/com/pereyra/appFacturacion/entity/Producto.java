package com.pereyra.appFacturacion.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pereyra.appFacturacion.dtos.ContextoVersion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Representa un producto en el sistema
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table (name= "productos") //Genera tabla producto en BD
public class Producto {


    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column(name = "codigo_producto")
    @JsonProperty("codigo_producto")
    @NotNull
    private String codigoProducto;

    @Column(name = "marca")
    @JsonProperty("marca")
    @NotNull
    private String marcaProducto;

    @Column(name = "modelo")
    @JsonProperty("modelo")
    @NotNull
    private String modeloProducto;

    @Column(name = "caracteristicas")
    @JsonProperty("caracteristicas")
    @NotNull
    private String caracProducto;

    @Column(name = "stock")
    @JsonProperty("stock")
    @Min(0)
    @NotNull
    private int StockProducto;

    @Column(name = "precio")
    @JsonProperty("precio")
    @NotNull
    private BigDecimal precioProducto;

    @Column(name = "cantidad_vendida")
    @JsonIgnore
    private int cantidadVendida;

    @Column(name="unidad_Vendida")
    private int unidadPorVenta;


    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("version ASC")
    @JsonIgnore
    private List<ProductoVersion> productoVersionList;

    @JsonIgnore
    private boolean seCreoVersion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return StockProducto == producto.StockProducto && cantidadVendida == producto.cantidadVendida && Objects.equals(idProducto, producto.idProducto) && Objects.equals(codigoProducto, producto.codigoProducto) && Objects.equals(marcaProducto, producto.marcaProducto) && Objects.equals(modeloProducto, producto.modeloProducto) && Objects.equals(caracProducto, producto.caracProducto) && Objects.equals(precioProducto, producto.precioProducto) && Objects.equals(productoVersionList, producto.productoVersionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto, codigoProducto, marcaProducto, modeloProducto, caracProducto, StockProducto, precioProducto, cantidadVendida, productoVersionList);
    }

    public boolean isNuevaVersionCreada() {
        return seCreoVersion;
    }


}
