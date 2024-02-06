package com.pereyra.appFacturacion.controllers;

import com.pereyra.appFacturacion.entity.Venta;
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


    @PostMapping("/crear")

    public ResponseEntity<?> agregarVenta(@RequestBody Venta venta) {
        try {
            Venta ventaCreada = ventaService.agregarVenta(venta);
            return ResponseEntity.ok(ventaCreada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al crear la venta: " + e.getMessage());
        }
    }


    @GetMapping("/listadoventa")

    public ResponseEntity <?> mostrarLista(){
        try{
            List<Venta> venta=ventaService.mostrarListadodeVentas();
            if(!venta.isEmpty()){
                return ResponseEntity.ok(venta);
            } else{
                return ResponseEntity.ok("Lista vacia.");
            }
        } catch(DataAccessException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error inesperado.");
        }
    }


    @GetMapping("/buscarventa/{idVenta}")
    public ResponseEntity <?> buscarVentaPorId(@PathVariable Long idVenta){
        return ventaService.mostrarVentaPorId(idVenta);
    }

}
