package com.pereyra.appFacturacion.controllers;

import com.pereyra.appFacturacion.Requests.PrecioRequest;
import com.pereyra.appFacturacion.Requests.StockRequest;
import com.pereyra.appFacturacion.entity.Producto;
import com.pereyra.appFacturacion.entity.ProductoVersion;
import com.pereyra.appFacturacion.service.ClienteServiceImpl;
import com.pereyra.appFacturacion.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @PutMapping("/modificar/stock/{idProducto}")
    public ResponseEntity <String> modificarStockProducto(@PathVariable Long idProducto, @RequestBody StockRequest request){
        return productoService.modificarStockProductoPorId(idProducto, request.getStock());

    }

    @PutMapping ("/modificar/precio/{idProducto}")
    public ResponseEntity <String> modificarPrecioProducto(@PathVariable Long idProducto, @RequestBody PrecioRequest request){
        return productoService.modificarPrecioProductoPorId(idProducto, request.getPrecio());
    }

    @GetMapping ("mostrar/versiones/{idProducto}")
    public ResponseEntity <?> mostrarVersiones(@PathVariable Long idProducto){
       ;
        return ResponseEntity.ok().body( productoService.mostrarVersionesProducto(idProducto));
    }

    @DeleteMapping("/eliminar/{idProducto}")
    public ResponseEntity <String> eliminarCliente( @PathVariable Long idProducto){
        return productoService.eliminarProductoPorId(idProducto);
    }


}
