package com.pereyra.appFacturacion.dtos;

import com.pereyra.appFacturacion.entity.Venta;
import com.pereyra.appFacturacion.entity.VentaDetalle;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que se encarga del armado de comprobante de venta
 */
public class Comprobante {

    /**
     * Genera un objeto {@link ComprobanteDto} a partir de una venta.
     *
     * @param venta Venta de la cual se generará el comprobante.
     * @return Objeto {@link ComprobanteDto} que representa el comprobante de la venta.
     */
    public static ComprobanteDto comprobanteVenta(Venta venta) {
        ComprobanteDto comprobanteDto = new ComprobanteDto();

        //Asignacion de ventas a comprobante

        VentaDto ventaDto = new VentaDto();

        ventaDto.setIdVentaDto(venta.getIdVenta());
        ventaDto.setFechaCreacionVentaDto(venta.getFechaHoracreacion());
        //Asignacion de cliente vinculado a venta


        ventaDto.setIdCliente(venta.getCliente().getIdCliente());
        ventaDto.setNombreCliente(venta.getCliente().getNombreCliente());
        ventaDto.setApellidoCliente(venta.getCliente().getApellidoCliente());


        //Asignacion de Productos adquiridos

        List<VentaDetalleDto> productos = new ArrayList<>();

        for (VentaDetalle vd : venta.getVentaDetalles()) {

            VentaDetalleDto ventaDetalleDto = new VentaDetalleDto();
            ventaDetalleDto.setIdProducto(vd.getIdProducto());
            ventaDetalleDto.setMarca(vd.getMarca());
            ventaDetalleDto.setModelo(vd.getModelo());
            ventaDetalleDto.setCaracteristica(vd.getCaracteristica());
            ventaDetalleDto.setCantidad(vd.getCantidad());
            ventaDetalleDto.setPrecio(vd.getPrecio());
            productos.add(ventaDetalleDto);


        }
        ventaDto.getDetalleVenta().addAll(productos);
        ventaDto.setPrecioTotal(venta.getTotalVenta());
        comprobanteDto.setVentaDto(ventaDto);


        return comprobanteDto;


    }
}
