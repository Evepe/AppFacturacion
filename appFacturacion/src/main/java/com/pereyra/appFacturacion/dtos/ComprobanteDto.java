package com.pereyra.appFacturacion.dtos;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Clase que representa el objeto de transferencia de datos (DTO) para el comprobante de venta.
 * Contiene información sobre el cliente, los detalles de la venta y la información general de la venta.
 */

@Getter
@Setter
public class ComprobanteDto {



    /** Lista de detalles de venta asociados al comprobante. */
    private List<VentaDetalleDto> ventaDetalleDto;

    /** Información general de la venta asociada al comprobante. */
    private VentaDto ventaDto;

    /**
     * Constructor vacío de la clase.
     */
    public ComprobanteDto() {
    }

    /**
     * Constructor con parámetros para inicializar un objeto ComprobanteDto.
     *

     * @param ventaDetalleDto Lista de detalles de venta asociados al comprobante.
     * @param ventaDto      Información general de la venta asociada al comprobante.
     */
    public ComprobanteDto(List<VentaDetalleDto> ventaDetalleDto, VentaDto ventaDto) {

        this.ventaDetalleDto = ventaDetalleDto;
        this.ventaDto = ventaDto;
    }


}
