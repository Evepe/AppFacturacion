package com.pereyra.appFacturacion.service;

import com.pereyra.appFacturacion.dtos.VentaDto;
import com.pereyra.appFacturacion.entity.Venta;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * nterfaz que define los servicios relacionados con la entidad Venta
 */
public interface VentaService {

    public ResponseEntity <?> agregarVenta(Venta venta);

    List<Venta> findAllWithVentaDetalles();


    ResponseEntity<?> mostrarVentaPorId(Long idVenta);


    List<Venta> findVentasByIdCliente(@Param("idCliente") Long idCliente);

    List<Venta> findVentasByDniCliente(@Param("dniCliente") int dniCliente);

    List<Venta> findVentasByIdProducto(@Param("idProducto") Long idProducto);

    Venta findByIdVenta(Long idVenta);




}
