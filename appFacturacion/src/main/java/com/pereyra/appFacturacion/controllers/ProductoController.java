package com.pereyra.appFacturacion.controllers;

import com.pereyra.appFacturacion.entity.Producto;
import com.pereyra.appFacturacion.service.ClienteServiceImpl;
import com.pereyra.appFacturacion.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Endpoint para Crud de producto
 */
@RestController
@RequestMapping("facturacion/producto")
public class ProductoController {

    private final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);
    @Autowired
    private ProductoService productoService;

    @PostMapping("/agregar")
    public ResponseEntity<?> agregarProducto(@Valid @RequestBody Producto producto) {
        return productoService.crearProducto(producto);
    }

    @GetMapping("/mostrar")
    public ResponseEntity<?> mostrarProductos() {
        return productoService.mostrarListadoProducto();
    }

    @GetMapping("/mostrar/{idProducto}")
    public ResponseEntity <?> mostrarProductoPorId (@PathVariable Long idProducto){
        return productoService.mostrarProductoPorId(idProducto);
    }

    @PutMapping("/modificar/{idProducto}")
    public ResponseEntity <?> modificarProducto(@PathVariable Long idProducto,@Valid @RequestBody Producto producto){
        return productoService.modificarProductoPorId(idProducto, producto);
    }



    @DeleteMapping("/eliminar/{idProducto}")
    public ResponseEntity <String> eliminarCliente( @PathVariable Long idProducto){
        return productoService.eliminarProductoPorId(idProducto);
    }


}
