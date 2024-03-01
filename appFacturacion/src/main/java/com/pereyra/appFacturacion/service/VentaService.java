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

    /**
     * Agrega una nueva venta al sistema.
     *
     * @param venta La venta a agregar.
     * @return ResponseEntity que indica el resultado de la operación.
     */
    public ResponseEntity <?> agregarVenta(@Param("venta") Venta venta);

    /**
     * Obtiene una lista de todas las ventas con detalles almacenadas en el sistema.
     *
     * @return Lista de ventas con detalles.
     */
    List<Venta> findAllWithVentaDetalles();

    /**
     * Obtiene una lista de todas las ventas con detalles almacenadas en el sistema.
     *
     * @return Lista de ventas con detalles.
     */

    ResponseEntity<?> mostrarVentaPorId(@Param ("idVenta") Long idVenta);

    /**
     * Obtiene una lista de ventas asociadas a un cliente por su ID.
     *
     * @param idCliente ID del cliente.
     * @return Lista de ventas asociadas al cliente.
     */

   ResponseEntity<?> findVentasByIdCliente(@Param("idCliente") Long idCliente);

    /**
     * Obtiene una lista de ventas asociadas a un cliente por su DNI.
     *
     * @param dniCliente DNI del cliente.
     * @return Lista de ventas asociadas al cliente.
     */
    ResponseEntity <?>findVentasByDniCliente(@Param("dniCliente") int dniCliente);

    /**
     * Obtiene una lista de ventas asociadas a un producto por su ID.
     *
     * @param idProducto ID del producto.
     * @return Lista de ventas asociadas al producto.
     */
    ResponseEntity <?> findVentasByIdProducto(@Param("idProducto") Long idProducto);

    /**
     * Obtiene una venta por su ID.
     *
     * @param idVenta ID de la venta.
     * @return La venta encontrada.
     */
    Venta findByIdVenta(@Param("idVenta") Long idVenta);




}
