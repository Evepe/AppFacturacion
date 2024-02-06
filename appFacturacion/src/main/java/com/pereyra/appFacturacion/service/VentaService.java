package com.pereyra.appFacturacion.service;

import com.pereyra.appFacturacion.entity.Venta;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * nterfaz que define los servicios relacionados con la entidad Venta
 */
public interface VentaService {

    public Venta agregarVenta(Venta venta);

    public List<Venta> mostrarListadodeVentas();


    public ResponseEntity<?> mostrarVentaPorId(Long idVenta);


}
