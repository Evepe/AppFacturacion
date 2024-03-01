package com.pereyra.appFacturacion.repository;

import com.pereyra.appFacturacion.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    /**
     * Muestra listado de Ventas.
     *
     *
     * @return Lista con totalidad de ventas registradas.
     */
    @Query("SELECT DISTINCT v FROM Venta v LEFT JOIN FETCH v.ventaDetalles")
    List<Venta> findAllWithVentaDetalles();

    /**
     * Encuentra las ventas realizadas por un Cliente.
     *
     * @param idCliente El ID del cliente a buscar.
     * @return Lista de ventas.
     */

    @Query("SELECT v FROM Venta v WHERE v.cliente.idCliente = :idCliente")
    List<Venta> findVentasByIdCliente(@Param("idCliente") Long idCliente);

    /**
     * Encuentra las ventas por DNI de un cliente.
     *
     * @param dniCliente El DNI del cliente a buscar.
     * @return Lista de ventas.
     */

    @Query("SELECT v FROM Venta v WHERE v.cliente.dniCliente = :dniCliente")
    List<Venta> findVentasByDniCliente(@Param("dniCliente") int dniCliente);

    /**
     * Encuentra ventas asocidas a un producto especifico.
     *
     * @param idProducto El ID del producto a buscar.
     * @return Listado de ventas asociadas al producto.
     */

    @Query("SELECT v FROM Venta v JOIN v.ventaDetalles vd WHERE vd.idProducto = :idProducto")
    List<Venta> findVentasByIdProducto(@Param("idProducto") Long idProducto);



}
