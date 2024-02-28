package com.pereyra.appFacturacion.dtos;

import com.pereyra.appFacturacion.entity.Producto;
import com.pereyra.appFacturacion.entity.ProductoVersion;
import com.pereyra.appFacturacion.entity.Venta;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que se encarga del armado de comprobante de venta
 */
public class Comprobante {


//    public static ComprobanteDto comprobanteVenta(Venta venta) {
//       ComprobanteDto comprobanteDto= new ComprobanteDto();
//
//       //Asignacion de ventas a comprobante
//
//        VentaDto ventaDto= new VentaDto();
//
//        ventaDto.setIdVentaDto(venta.getIdVenta());
//        ventaDto.setFechaCreacionVentaDto(LocalDate.parse(venta.getFechaHoracreacion()));
//        //Asignacion de cliente vinculado a venta
//
//
//        ClienteDto clienteDto= new ClienteDto();
//
//        clienteDto.setIdClienteDto(venta.getCliente().getIdCliente());
//        clienteDto.setNombreClienteDto(venta.getCliente().getNombreCliente());
//        clienteDto.setApellidoClienteDto(venta.getCliente().getApellidoCliente());
//        clienteDto.setDniClienteDto(venta.getCliente().getDniCliente());
//
//        //Asignacion de Productos adquiridos
//
//        List <ProductoDto> productos= new ArrayList<>();
//
//        for (ProductoVersion vs : venta.getVersiones()){
//        ProductoDto productoDto= new ProductoDto();
//
//        productoDto.setIdProductoDto(vs.getProducto().getIdProducto());
//        productoDto.setMarcaProducto(vs.getMarcaProducto());
//        productoDto.setModeloProducto(vs.getModeloProducto());
//        productoDto.setCaracteristicaProducto(vs.getCaracteristicaProducto());
//        productoDto.setPrecioUnitario(vs.getPrecioProducto());
//        productoDto.setCantidadProductoDto(vs.getProducto().getCantidadVendida());
//
//        productos.add(productoDto);
//
//
//
//        }
//
//
//
//        ventaDto.setPrecioTotal(venta.getTotalVenta());
//
//        comprobanteDto.setCliente(clienteDto);
//        comprobanteDto.setProductoDto(productos);
//        comprobanteDto.setVentaDto(ventaDto);
//
//        return comprobanteDto;
//
//
//
//
//
//
//
//
//    }
}
