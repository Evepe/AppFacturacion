package com.pereyra.appFacturacion.dtos;

import com.pereyra.appFacturacion.entity.Producto;
import com.pereyra.appFacturacion.entity.Venta;

import java.math.BigDecimal;
import java.util.List;


public class ComprobanteDto {

    private ClienteDto cliente;

    private List <ProductoDto> productoDto;

    private VentaDto ventaDto;

    public ComprobanteDto() {
    }

    public ComprobanteDto(ClienteDto cliente, List<ProductoDto> productoDto, VentaDto ventaDto) {
        this.cliente = cliente;
        this.productoDto = productoDto;
        this.ventaDto = ventaDto;
    }

    public ClienteDto getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDto cliente) {
        this.cliente = cliente;
    }

    public List<ProductoDto> getProductoDto() {
        return productoDto;
    }

    public void setProductoDto(List<ProductoDto> productoDto) {
        this.productoDto = productoDto;
    }

    public VentaDto getVentaDto() {
        return ventaDto;
    }

    public void setVentaDto(VentaDto ventaDto) {
        this.ventaDto = ventaDto;
    }
}
