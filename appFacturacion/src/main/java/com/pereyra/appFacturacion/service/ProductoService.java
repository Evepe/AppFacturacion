package com.pereyra.appFacturacion.service;

import com.pereyra.appFacturacion.entity.Producto;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Interfaz que define los servicios relacionados con la entidad Producto
 */
public interface ProductoService {

    /**
     * Agrega Producto al sistema
     * @param producto entidad producto
     * @return ResponseEntity con un mensaje indicando resultado de la transaccion
     */
    public ResponseEntity<String> crearProducto(Producto producto);

    /**
     * Muestra lista de productos existentes en el sistema
     * @return List lista de Productos
     */
    public List<Producto> mostrarListadoProducto();

    /**
     * Muestra producto determinado por Id
     * @param idProducto Identificador unico PK de producto
     * @return ResponseEntity con un mensaje indicando resultado de la transaccion
     */
    public ResponseEntity <?>  mostrarProductoPorId(Long idProducto);

    /**
     * Actualizacion de producto
     * @param idProducto idProducto a actualizar
     * @param producto nuevos valores a asignar
     * @return ResponseEntity con un mensaje indicando resultado de la transaccion
     */

    public ResponseEntity<String> modificarProductoPorId(Long idProducto, Producto producto);

    /**
     * Elimina producto por Id
     * @param idProducto Pk cliente
     * @return ResponseEntity con un mensaje indicando resultado de la transaccion
     */
    public ResponseEntity <String> eliminarProductoPorId(Long idProducto);
}
