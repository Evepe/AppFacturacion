package com.pereyra.appFacturacion.service;

import com.pereyra.appFacturacion.dtos.ClienteDto;
import com.pereyra.appFacturacion.entity.Producto;
import com.pereyra.appFacturacion.entity.ProductoVersion;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
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
    public ResponseEntity<?> crearProducto(Producto producto);

    /**
     * Muestra lista de productos existentes en el sistema
     * @return List lista de Productos
     */
    public ResponseEntity <?> mostrarListadoProducto();

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

    ResponseEntity<?> modificarProductoPorId(Long idProducto, Producto producto);

    /**
     * Elimina producto por Id
     * @param idProducto Pk cliente
     * @return ResponseEntity con un mensaje indicando resultado de la transaccion
     */
    public ResponseEntity <String> eliminarProductoPorId(Long idProducto);



    public ResponseEntity<String> modificarPrecioProductoPorId(Long idProducto, BigDecimal precioProducto);

    public ResponseEntity<String> modificarStockProductoPorId(Long idProducto, int stock);

    public List <ProductoVersion> mostrarVersionesProducto(Long idProducto);


}
