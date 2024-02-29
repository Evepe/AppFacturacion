package com.pereyra.appFacturacion.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data

public class VentaDto {

    private Long idVentaDto;

    private Long idCliente;

    private String nombreCliente;

    private String apellidoCliente;

    private List <VentaDetalleDto> detalleVenta;


    private String fechaCreacionVentaDto;


    private double precioTotal;


    public VentaDto() {
        this.detalleVenta=new ArrayList<>();
    }

    public VentaDto(Long idVentaDto, Long idCliente, String nombreCliente, String apellidoCliente, List<VentaDetalleDto> detalleVenta, String fechaCreacionVentaDto, double precioTotal) {
        this.idVentaDto = idVentaDto;
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.detalleVenta = detalleVenta;
        this.fechaCreacionVentaDto = fechaCreacionVentaDto;
        this.precioTotal = precioTotal;
    }
}
