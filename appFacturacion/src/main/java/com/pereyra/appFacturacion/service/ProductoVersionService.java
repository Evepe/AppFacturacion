package com.pereyra.appFacturacion.service;

import com.pereyra.appFacturacion.entity.Producto;
import com.pereyra.appFacturacion.entity.ProductoVersion;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ProductoVersionService {

   ProductoVersion findUltimaVersion();

   ProductoVersion findByVersionProducto(int version);





}
