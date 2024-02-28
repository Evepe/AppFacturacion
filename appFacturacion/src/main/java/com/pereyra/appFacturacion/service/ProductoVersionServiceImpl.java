package com.pereyra.appFacturacion.service;

import com.pereyra.appFacturacion.entity.Producto;
import com.pereyra.appFacturacion.entity.ProductoVersion;
import com.pereyra.appFacturacion.repository.ProductoRepository;
import com.pereyra.appFacturacion.repository.ProductoVersionRespository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ProductoVersionServiceImpl implements ProductoVersionService{

    @Autowired
    private ProductoVersionRespository productoVersionRespository;

    @Autowired
    private ProductoRepository productoRepository;
    @Override
    public ProductoVersion findUltimaVersion() {
        return productoVersionRespository.findFirstByOrderByFechaCreacionDesc();

    }

    @Override
    public ProductoVersion findByVersionProducto(int version) {
        ProductoVersion versionProducto = productoVersionRespository.findByVersion(version);
        if (versionProducto == null) {
            throw new NoSuchElementException("Version con código " + version + " no encontrada");
        }
        return versionProducto;


    }


}
