package com.pereyra.appFacturacion.controllers;

import com.pereyra.appFacturacion.entity.Producto;
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
    public ResponseEntity<String> agregarProducto(@Valid @RequestBody Producto producto) {
        return productoService.crearProducto(producto);
    }

    @GetMapping("/mostrar")
    public ResponseEntity<?> mostrarProductos() {
        try {
            List<Producto> productosList = productoService.mostrarListadoProducto();
            if(!productosList.isEmpty()){
                return ResponseEntity.ok(productosList);
            } else{
                return ResponseEntity.ok("Aun no se almacenaron productos");
            }

        } catch (Exception e) {
            log.error("Error al mostrar productos", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno al mostrar productos");
        }
    }

    @GetMapping("/mostrar/{idProducto}")
    public ResponseEntity <?> mostrarProductoPorId (@PathVariable Long idProducto){
        return productoService.mostrarProductoPorId(idProducto);
    }

    @PutMapping("/modificar/{idProducto}")
    public ResponseEntity <String> modificarProducto(@PathVariable Long idProducto,@Valid @RequestBody Producto producto){
        return productoService.modificarProductoPorId(idProducto, producto);
    }

    @DeleteMapping("/eliminar/{idProducto}")
    public ResponseEntity <String> eliminarCliente( @PathVariable Long idProducto){
        return productoService.eliminarProductoPorId(idProducto);
    }


}
