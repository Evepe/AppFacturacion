package com.pereyra.appFacturacion.service;
import com.pereyra.appFacturacion.dtos.Comprobante;
import com.pereyra.appFacturacion.dtos.ComprobanteDto;
import com.pereyra.appFacturacion.entity.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase que define metodo para generar comprobante de venta
 */
@Service
public class ComprobanteService {


    private Comprobante comprobante;
    public ComprobanteDto generarComprobante(Venta venta){
        return comprobante.comprobanteVenta(venta);
    }
}
