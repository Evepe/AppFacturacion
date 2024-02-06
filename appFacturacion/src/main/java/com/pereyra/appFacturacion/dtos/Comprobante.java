package com.pereyra.appFacturacion.dtos;

import com.pereyra.appFacturacion.entity.Producto;
import com.pereyra.appFacturacion.entity.Venta;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que se encarga del armado de comprobante de venta
 */
public class Comprobante {


    public static ComprobanteDto comprobanteVenta(Venta venta) {
        ComprobanteDto comprobanteDto = new ComprobanteDto();
        comprobanteDto.setIdVentas(venta.getIdVenta());
        comprobanteDto.setFecha(venta.getFechaHoracreacion());
        comprobanteDto.setNombreCliente(venta.getCliente().getNombreCliente());
        comprobanteDto.setApellidoCliente(venta.getCliente().getApellidoCliente());
        comprobanteDto.setDniCliente(venta.getCliente().getDniCliente());


       List <ProductoDto> productoDto= new ArrayList<>();

        for (Producto producto : venta.getProductoList()) {
            ProductoDto productoUnitario= new ProductoDto();
            productoUnitario.setMarcaProducto(producto.getMarcaProducto());
            productoUnitario.setModeloProducto(producto.getModeloProducto());
            productoUnitario.setCaracteristicaProducto(producto.getCaracProducto());
            productoUnitario.setPrecioUnitario(producto.getPrecioProducto());
            productoDto.add(productoUnitario);

        }

        comprobanteDto.setProductos(productoDto);



        comprobanteDto.setTotalCompra(venta.getTotalVenta());

        return comprobanteDto;




    }
}
