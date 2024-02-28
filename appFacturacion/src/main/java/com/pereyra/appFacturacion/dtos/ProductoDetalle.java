package com.pereyra.appFacturacion.dtos;


import com.pereyra.appFacturacion.entity.Producto;

public class ProductoDetalle {

    private ProductoDto productoDto;


    public ProductoDto productoCreado(Producto producto){
        ProductoDto productoDtoCreado= new ProductoDto();
        productoDtoCreado.setIdProductoDto(producto.getIdProducto());
        productoDtoCreado.setMarcaProducto(producto.getMarcaProducto());
        productoDtoCreado.setModeloProducto(producto.getModeloProducto());
        productoDtoCreado.setCaracteristicaProducto(producto.getCaracProducto());
        productoDtoCreado.setStock(producto.getStockProducto());
        productoDtoCreado.setPrecioUnitario(producto.getPrecioProducto());


        return productoDtoCreado;
    }





}
