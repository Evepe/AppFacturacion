package com.pereyra.appFacturacion.controllers;

import com.pereyra.appFacturacion.entity.Producto;
import com.pereyra.appFacturacion.service.ClienteServiceImpl;
import com.pereyra.appFacturacion.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
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
    @Operation(summary = "Crea un nuevo producto", description = "Permite la creación de un producto.")
    @ApiResponse(responseCode = "200", description = "Operacion existosa.", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "Operación fallida.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno en servidor.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity<?> agregarProducto(@Valid @RequestBody Producto producto) {
        return productoService.crearProducto(producto);
    }

    @GetMapping("/mostrar")
    @Operation(summary = "Mostrar lista de productos", description = "Permite acceder a los productos almacenados en BD.")
    @ApiResponse(responseCode = "200", description = "Operacion existosa.", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404", description = "producto inexistente", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "Operacion fallida.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity<?> mostrarProductos() {
        return productoService.mostrarListadoProducto();
    }

    @GetMapping("/mostrar/{idProducto}")
    @Operation(summary = "Obtiene producto por ID.", description = "Obtiene producto especifico.")
    @ApiResponse(responseCode = "200", description = "Operacion existosa.", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404", description = "producto inexistente", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "Operacion fallida.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity <?> mostrarProductoPorId (@PathVariable Long idProducto){
        return productoService.mostrarProductoPorId(idProducto);
    }

    @PutMapping("/modificar/{idProducto}")
    @Operation(summary = "Modifica producto por id", description = "Permite la modificacion de un producto especifico.")
    @ApiResponse(responseCode = "200", description = "Operacion existosa.", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404", description = "producto inexistente", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "Operacion fallida.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity <?> modificarProducto(@PathVariable Long idProducto,@Valid @RequestBody Producto producto){
        return productoService.modificarProductoPorId(idProducto, producto);
    }



    @DeleteMapping("/eliminar/{idProducto}")
    @Operation(summary = "Elimina cliente por id", description = "Permite la eliminacion de un cliente especifico.")
    @ApiResponse(responseCode = "200", description = "Operacion existosa.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404", description = "Cliente inexistente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "Operacion fallida.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity <String> eliminarCliente( @PathVariable Long idProducto){
        return productoService.eliminarProductoPorId(idProducto);
    }


}
