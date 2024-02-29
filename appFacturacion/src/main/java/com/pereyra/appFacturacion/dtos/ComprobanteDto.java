package com.pereyra.appFacturacion.dtos;

import com.pereyra.appFacturacion.entity.Producto;
import com.pereyra.appFacturacion.entity.Venta;

import java.math.BigDecimal;
import java.util.List;


public class ComprobanteDto {

    private ClienteDto cliente;

    private List <VentaDetalleDto> ventaDetalleDto;

    private VentaDto ventaDto;

    public ComprobanteDto() {
    }

    public ComprobanteDto(ClienteDto cliente, List<VentaDetalleDto> ventaDetalleDto, VentaDto ventaDto) {
        this.cliente = cliente;
        this.ventaDetalleDto = ventaDetalleDto;
        this.ventaDto = ventaDto;
    }

    public ClienteDto getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDto cliente) {
        this.cliente = cliente;
    }

    public List<VentaDetalleDto> getVentaDetalleDto() {
        return ventaDetalleDto;
    }

    public void setVentaDetalleDto(List<VentaDetalleDto> ventaDetalleDto) {
        this.ventaDetalleDto = ventaDetalleDto;
    }

    public VentaDto getVentaDto() {
        return ventaDto;
    }

    public void setVentaDto(VentaDto ventaDto) {
        this.ventaDto = ventaDto;
    }
}
