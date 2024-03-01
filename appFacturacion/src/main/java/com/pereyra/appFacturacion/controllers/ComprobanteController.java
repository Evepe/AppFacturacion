package com.pereyra.appFacturacion.controllers;

import com.pereyra.appFacturacion.dtos.ComprobanteDto;
import com.pereyra.appFacturacion.entity.Venta;
import com.pereyra.appFacturacion.repository.VentaRepository;
import com.pereyra.appFacturacion.service.ComprobanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

/**
 * Endpoint para acceder a comprobantes por numero de ventas
 */
@RestController
@RequestMapping("facturacion/ventas/comprobante")
public class ComprobanteController {

    @Autowired
    private ComprobanteService comprobanteService;

    @Autowired
    private VentaRepository ventaRepository;

    @GetMapping("/{idVenta}")
    @Operation(summary = "Genera comprobantes de ventas creadas", description = "Permite la creación de cpmprobante.")
    @ApiResponse(responseCode = "200", description = "Operacion existosa.", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "Operación fallida.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity<ComprobanteDto> comprobanteVenta(@PathVariable Long idVenta){
        try{
            Venta venta= ventaRepository.findById(idVenta).get();
            ComprobanteDto comprobanteDto= comprobanteService.generarComprobante(venta);
            return ResponseEntity.status(HttpStatus.OK).body(comprobanteDto);
        } catch(DataAccessException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        }
    }



}
