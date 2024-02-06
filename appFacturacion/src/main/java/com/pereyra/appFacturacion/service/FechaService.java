package com.pereyra.appFacturacion.service;

/**
 * Interfaz de determina los metodos en relacion a la Fecha de la venta
 */

public interface FechaService {

    /**
     * Obtiene fecha desde Servidor externo
     * @return String fecha actual
     */
    public String obtenerFechaServidorExterno();

    /**
     * Obtiene fecha actual por medio der service externo o DataTime
     * @return String fecha actual
     */

    public String obtenerFecha();
}
