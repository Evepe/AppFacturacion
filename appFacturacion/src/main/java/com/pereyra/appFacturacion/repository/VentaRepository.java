package com.pereyra.appFacturacion.repository;

import com.pereyra.appFacturacion.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    @Query("SELECT DISTINCT v FROM Venta v LEFT JOIN FETCH v.ventaDetalles")
    List<Venta> findAllWithVentaDetalles();

    @Query("SELECT v FROM Venta v WHERE v.cliente.idCliente = :idCliente")
    List<Venta> findVentasByIdCliente(@Param("idCliente") Long idCliente);

    @Query("SELECT v FROM Venta v WHERE v.cliente.dniCliente = :dniCliente")
    List<Venta> findVentasByDniCliente(@Param("dniCliente") int dniCliente);


    @Query("SELECT v FROM Venta v JOIN v.ventaDetalles vd WHERE vd.idProducto = :idProducto")
    List<Venta> findVentasByIdProducto(@Param("idProducto") Long idProducto);



}
