package com.pereyra.appFacturacion.controllers;

import com.pereyra.appFacturacion.dtos.VentaDto;
import com.pereyra.appFacturacion.entity.Venta;
import com.pereyra.appFacturacion.service.ComprobanteService;
import com.pereyra.appFacturacion.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<?> agregarVenta(@RequestBody Venta venta) {
        try {

            return ResponseEntity.ok(ventaService.agregarVenta(venta));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al crear la venta: " + e.getMessage());
        }
    }


    @GetMapping("/listadoventa")

    public ResponseEntity <?> mostrarLista(){
        try{
            List<Venta> venta=ventaService.findAllWithVentaDetalles();
            if(!venta.isEmpty()){
                return ResponseEntity.ok(venta);
            } else{
                return ResponseEntity.ok("Lista vacia.");
            }
        } catch(DataAccessException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error inesperado al acceder a historial de venta: " + e.getMessage());
        }
    }


    @GetMapping("/buscarventa/{idVenta}")
    public ResponseEntity <?> buscarVentaPorId(@PathVariable Long idVenta){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ventaService.mostrarVentaPorId(idVenta));
        }catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error inesperado. No se pudo procesar la solicitud " + e.getMessage());
        }
    }

    @GetMapping("/buscarventa/dni/{dniCliente}")
    public ResponseEntity <?> buscarVentaPorDni(@PathVariable int dniCliente){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ventaService.findVentasByDniCliente(dniCliente));
        } catch(DataAccessException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error inesperado. No se pudo procesar la solicitud " + e.getMessage());
        }
    }

    @GetMapping("/buscarventa/idCliente/{idCliente}")
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
