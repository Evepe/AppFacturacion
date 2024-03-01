package com.pereyra.appFacturacion.dtos;


import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de transferencia de datos (DTO) que representa una venta.
 * Se utiliza para transportar información entre las capas de servicio y presentación.
 */
@Data
public class VentaDto {

    /** Identificador único de la venta. */
    private Long idVentaDto;

    /** Identificador del cliente asociado a la venta. */
    private Long idCliente;

    /** Nombre del cliente asociado a la venta. */
    private String nombreCliente;

    /** Apellido del cliente asociado a la venta. */
    private String apellidoCliente;

    /** Detalles de los productos vendidos en esta venta. */
    private List<VentaDetalleDto> detalleVenta;

    /** Fecha de creación de la venta en formato de texto. */
    private String fechaCreacionVentaDto;

    /** Precio total de la venta. */
    private double precioTotal;

    /**
     * Constructor vacío de la clase.
     * Inicializa la lista de detalles de venta como una nueva ArrayList.
     */
    public VentaDto() {
        this.detalleVenta = new ArrayList<>();
    }

    /**
     * Constructor con parámetros para inicializar un objeto VentaDto.
     *
     * @param idVentaDto             Identificador único de la venta.
     * @param idCliente              Identificador del cliente asociado a la venta.
     * @param nombreCliente          Nombre del cliente asociado a la venta.
     * @param apellidoCliente        Apellido del cliente asociado a la venta.
     * @param detalleVenta           Detalles de los productos vendidos en esta venta.
     * @param fechaCreacionVentaDto  Fecha de creación de la venta en formato de texto.
     * @param precioTotal            Precio total de la venta.
     */
    public VentaDto(Long idVentaDto, Long idCliente, String nombreCliente, String apellidoCliente,
                    List<VentaDetalleDto> detalleVenta, String fechaCreacionVentaDto, double precioTotal) {
        this.idVentaDto = idVentaDto;
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.detalleVenta = detalleVenta;
        this.fechaCreacionVentaDto = fechaCreacionVentaDto;
        this.precioTotal = precioTotal;
    }
}
