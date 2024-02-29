package com.pereyra.appFacturacion.dtos;

import com.pereyra.appFacturacion.entity.Venta;
import com.pereyra.appFacturacion.entity.VentaDetalle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que se encarga del armado de comprobante de venta
 */
public class Comprobante {


    public static ComprobanteDto comprobanteVenta(Venta venta) {
       ComprobanteDto comprobanteDto= new ComprobanteDto();

       //Asignacion de ventas a comprobante

        VentaDto ventaDto= new VentaDto();

        ventaDto.setIdVentaDto(venta.getIdVenta());
        ventaDto.setFechaCreacionVentaDto(venta.getFechaHoracreacion());
        //Asignacion de cliente vinculado a venta


        ClienteDto clienteDto= new ClienteDto();

        clienteDto.setIdClienteDto(venta.getCliente().getIdCliente());
        clienteDto.setNombreClienteDto(venta.getCliente().getNombreCliente());
        clienteDto.setApellidoClienteDto(venta.getCliente().getApellidoCliente());
        clienteDto.setDniClienteDto(venta.getCliente().getDniCliente());

        //Asignacion de Productos adquiridos

        List<VentaDetalleDto> productos= new ArrayList<>();

        for (VentaDetalle vd : venta.getVentaDetalles()){

            VentaDetalleDto ventaDetalleDto= new VentaDetalleDto();
            ventaDetalleDto.setIdProducto(vd.getIdProducto());
            ventaDetalleDto.setMarca(vd.getMarca());
            ventaDetalleDto.setModelo(vd.getModelo());
            ventaDetalleDto.setCaracteristica(vd.getCaracteristica());
            ventaDetalleDto.setPrecio(vd.getPrecio());
            ventaDetalleDto.setCantidad(vd.getCantidad());

            productos.add(ventaDetalleDto);



        }



        ventaDto.setPrecioTotal(venta.getTotalVenta());
        comprobanteDto.setCliente(clienteDto);
        comprobanteDto.setVentaDto(ventaDto);

        return comprobanteDto;








    }
}
