package com.pereyra.appFacturacion.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "ID del producto", example="1")
    private Long idProducto;

    @Column(name = "codigo_producto")
    @JsonProperty("codigo_producto")
    @NotNull
    @Schema(description = "Codigo del producto", example = "Pc1001")
    private String codigoProducto;

    @Column(name = "marca")
    @JsonProperty("marca")
    @NotNull
    @Schema(description = "Marca del producto", example = "Acer")
    private String marcaProducto;

    @Column(name = "modelo")
    @JsonProperty("modelo")
    @NotNull
    @Schema(description = "Modelo del producto", example = "Aspire 3")
    private String modeloProducto;

    @Column(name = "caracteristicas")
    @JsonProperty("caracteristicas")
    @NotNull
    @Schema(description = "Caracteristicas del producto", example = "Procesador Rizen 5, Memoria RAM 16 GB")
    private String caracProducto;

    @Column(name = "stock")
    @JsonProperty("stock")
    @Min(0)
    @NotNull
    @Schema(description = "Cantidad disponible del producto para la venta", example = "50")
    private int stockProducto;

    @Column(name = "precio")
    @JsonProperty("precio")
    @NotNull
    @Schema(description = "Precio del producto", example = "340000,00")
    private double precioProducto;

    @Column(name = "cantidad_vendida")
    @Min(1)
    @Schema(description = "Cantidad de producto solicitado por el cliente en una compra", example = "5")
    private int cantidadVendida;

    @Column(name="Total_venta")
    @Schema(description = "Conteo de total de venta de un producto", hidden = true, example = "50")
    private int cantidadTotalVendido;


    public Producto() {

    }

    public Producto(Long idProducto, String codigoProducto, String marcaProducto, String modeloProducto, String caracProducto, int stockProducto, double precioProducto, int cantidadVendida, int cantidadTotalVendido) {
        this.idProducto = idProducto;
        this.codigoProducto = codigoProducto;
        this.marcaProducto = marcaProducto;
        this.modeloProducto = modeloProducto;
        this.caracProducto = caracProducto;
        this.stockProducto = stockProducto;
        this.precioProducto = precioProducto;
        this.cantidadVendida = cantidadVendida;
        this.cantidadTotalVendido = cantidadTotalVendido;

    }
}
