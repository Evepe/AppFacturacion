package com.pereyra.appFacturacion.dtos;

import com.pereyra.appFacturacion.entity.VentaDetalle;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un objeto de transferencia de datos (DTO) para la entidad Producto.
 * Contiene información detallada sobre un producto, incluyendo sus atributos y detalles de ventas asociados.
 */

@Data
public class ProductoDto {

    // Atributos

    /** Identificador único del producto. */
    private Long idProducto;

    /** Código del producto. */
    private String codigoProducto;

    /** Marca del producto. */
    private String marcaProducto;

    /** Modelo del producto. */
    private String modeloProducto;

    /** Características del producto. */
    private String caracProducto;

    /** Stock disponible del producto. */
    private int stockProducto;

    /** Precio unitario del producto. */
    private double precioProducto;

    /** Cantidad de unidades vendidas del producto. */
    private int cantidadVendida;

    /** Cantidad total de unidades vendidas del producto. */
    private int cantidadTotalVendido;

    /** Lista de detalles de ventas asociados al producto. */
    private List<VentaDetalle> ventaDetalles;

    /**
     * Constructor vacío de la clase. Inicializa la lista de detalles de ventas.
     */
    public ProductoDto() {
        this.ventaDetalles = new ArrayList<>();
    }

    /**
     * Constructor con parámetros para inicializar un objeto ProductoDto.
     *
     * @param idProducto          Identificador único del producto.
     * @param codigoProducto      Código del producto.
     * @param marcaProducto       Marca del producto.
     * @param modeloProducto      Modelo del producto.
     * @param caracProducto       Características del producto.
     * @param stockProducto       Stock disponible del producto.
     * @param precioProducto      Precio unitario del producto.
     * @param cantidadVendida      Cantidad de unidades vendidas del producto.
     * @param cantidadTotalVendido Cantidad total de unidades vendidas del producto.
     * @param ventaDetalles       Lista de detalles de ventas asociados al producto.
     */
    public ProductoDto(Long idProducto, String codigoProducto, String marcaProducto, String modeloProducto,
                       String caracProducto, int stockProducto, double precioProducto, int cantidadVendida,
                       int cantidadTotalVendido, List<VentaDetalle> ventaDetalles) {
        this.idProducto = idProducto;
        this.codigoProducto = codigoProducto;
        this.marcaProducto = marcaProducto;
        this.modeloProducto = modeloProducto;
        this.caracProducto = caracProducto;
        this.stockProducto = stockProducto;
        this.precioProducto = precioProducto;
        this.cantidadVendida = cantidadVendida;
        this.cantidadTotalVendido = cantidadTotalVendido;
        this.ventaDetalles = ventaDetalles;
    }
}
