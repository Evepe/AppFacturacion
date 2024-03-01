package com.pereyra.appFacturacion.controllers;

import com.pereyra.appFacturacion.dtos.VentaDto;
import com.pereyra.appFacturacion.entity.Venta;
import com.pereyra.appFacturacion.service.ComprobanteService;
import com.pereyra.appFacturacion.service.VentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Endpoint para crud ventas
 */

@RestController
@RequestMapping("/facturacion/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private ComprobanteService comprobanteService;


    @PostMapping("/crear")
    @Operation(summary = "Crea una nueva venta", description = "Permite la creación de una venta.")
    @ApiResponse(responseCode = "200", description = "Operacion existosa.", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "Operación fallida.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity<?> agregarVenta(@RequestBody Venta venta) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ventaService.agregarVenta(venta));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al crear la venta: " + e.getMessage());
        }
    }


    @GetMapping("/listadoventa")
    @Operation(summary = "Mostrar lista de ventas", description = "Permite acceder a las ventas almacenadas en BD.")
    @ApiResponse(responseCode = "200", description = "Operacion existosa.", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "Operacion fallida.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity <?> mostrarLista(){
        try{
            List<Venta> venta=ventaService.findAllWithVentaDetalles();
            if(!venta.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(venta);
            } else{
                return ResponseEntity.status(HttpStatus.OK).body("Lista vacia.");
            }
        } catch(DataAccessException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error inesperado al acceder a historial de venta: " + e.getMessage());
        }
    }


    @GetMapping("/buscarventa/{idVenta}")
    @Operation(summary = "Obtiene venta por ID", description = "Permite la obtencion de venta por ID.")
    @ApiResponse(responseCode = "200", description = "Operacion existosa.", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "Operación fallida.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity <?> buscarVentaPorId(@PathVariable Long idVenta){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ventaService.mostrarVentaPorId(idVenta));
        }catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error inesperado. No se pudo procesar la solicitud " + e.getMessage());
        }
    }

    @GetMapping("/buscarventa/dni/{dniCliente}")
    @Operation(summary = "Obtiene ventas por DNI de cliente", description = "Permite la obtencion de todas las ventas a un cliente especifico.")
    @ApiResponse(responseCode = "200", description = "Operacion existosa.", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "Operación fallida.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity <?> buscarVentaPorDni(@PathVariable int dniCliente){
        try {

            return ResponseEntity.status(HttpStatus.OK).body(ventaService.findVentasByDniCliente(dniCliente));
        } catch(DataAccessException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error inesperado. No se pudo procesar la solicitud " + e.getMessage());
        }
    }

    @GetMapping("/buscarventa/idCliente/{idCliente}")
    @Operation(summary = "Obtiene totalidad de ventas por ID del cliente", description = "Permite la la obtencion de totalidad de ventas a un cliente especifico.")
    @ApiResponse(responseCode = "200", description = "Operacion existosa.", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "Operación fallida.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity <?> buscarVentaPorIdCliente(@PathVariable Long idCliente) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ventaService.findVentasByIdCliente(idCliente));
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error inesperado. No se pudo procesar la solicitud " + e.getMessage());
        }
    }


    @GetMapping("/buscarVentas/idVenta")

    public ResponseEntity <?> buscarVentaporIdVenta(@PathVariable Long idVenta){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(ventaService.findByIdVenta(idVenta));
        }catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error inesperado. No se pudo procesar la solicitud " + e.getMessage());
        }

    }


}
