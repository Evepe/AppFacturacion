package com.pereyra.appFacturacion.repository;

import com.pereyra.appFacturacion.entity.Producto;
import com.pereyra.appFacturacion.entity.ProductoVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ProductoVersionRespository extends JpaRepository<ProductoVersion, Long> {


   ProductoVersion findFirstByOrderByFechaCreacionDesc();

   ProductoVersion findByVersion(int version);




}
