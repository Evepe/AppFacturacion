package com.pereyra.appFacturacion.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa un producto en el sistema
 */
@Entity
@Data
@Table (name= "productos") //Genera tabla producto en BD
public class Producto {
    @Serial
    private static final long serialVersionUID = 1L;


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
    private double precioProducto;

    @Column(name = "cantidad_vendida")
    private int cantidadVendida;

    @Column(name="Total_venta")
    private int cantidadTotalVendido;


    public Producto() {

    }

    public Producto(Long idProducto, String codigoProducto, String marcaProducto, String modeloProducto, String caracProducto, int stockProducto, double precioProducto, int cantidadVendida, int cantidadTotalVendido) {
        this.idProducto = idProducto;
        this.codigoProducto = codigoProducto;
        this.marcaProducto = marcaProducto;
        this.modeloProducto = modeloProducto;
        this.caracProducto = caracProducto;
        StockProducto = stockProducto;
        this.precioProducto = precioProducto;
        this.cantidadVendida = cantidadVendida;
        this.cantidadTotalVendido = cantidadTotalVendido;

    }
}
