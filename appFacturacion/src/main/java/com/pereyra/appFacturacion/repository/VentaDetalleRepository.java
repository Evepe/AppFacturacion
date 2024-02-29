package com.pereyra.appFacturacion.repository;

import com.pereyra.appFacturacion.entity.VentaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaDetalleRepository extends JpaRepository<VentaDetalle, Long> {
}
