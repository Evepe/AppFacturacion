package com.pereyra.appFacturacion.service;
import com.pereyra.appFacturacion.entity.Producto;
import com.pereyra.appFacturacion.repository.ProductoRepository;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
/**
 * Servicios relacionados con la entidad Producto.
 * Contiene operaciones CRUD para gestionar productos.
 */

@Transactional
@Service
public class ProductoServiceImpl implements ProductoService{

    private final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);
    @Autowired
    private ProductoRepository productoRepository;

    /**
     * Crea un nuevo producto en el sistema.
     *
     * @param producto El producto a crear.
     * @return ResponseEntity que indica el resultado de la operación.
     */
    @Override
    public ResponseEntity<?> crearProducto(Producto producto) {
        try{
            if(StringUtils.isEmpty(producto.getCodigoProducto()) || StringUtils.isEmpty(producto.getMarcaProducto())|| StringUtils.isEmpty(producto.getModeloProducto())|| StringUtils.isEmpty(producto.getCaracProducto())||StringUtils.isEmpty(producto.getStockProducto())||StringUtils.isEmpty(producto.getPrecioProducto())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Faltan completar los campos obligatorios.");
            }
            productoRepository.save(producto);

            return ResponseEntity.status(HttpStatus.CONFLICT).body("Producto agregado exitosamente.");
        } catch (PersistenceException e) {
            log.error("Error al crear producto: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al guardar producto en la base de datos.");
        } catch (ConstraintViolationException e) {
            log.error("Error al crear producto: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Datos duplicados o violación de restricción.");
        } catch (Exception e) {
            log.error("Error inesperado al crear producto: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al crear producto.");
        }
    }

    /**
     * Obtiene la lista de todos los productos almacenados en el sistema.
     *
     * @return ResponseEntity que contiene la lista de productos o un mensaje indicando que no hay productos.
     */

    @Override
    public ResponseEntity<?> mostrarListadoProducto() {
        try {
            List<Producto> productoListado = productoRepository.findAll();
            return productoListado.isEmpty()
                    ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay productos almacenados en este momento.")
                    : ResponseEntity.status(HttpStatus.OK).body(productoListado);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ERROR INESPERADO. No se puede acceder a la lista de productos almacenados.");
        }
    }


    /**
     * Obtiene un producto por su ID.
     *
     * @param idProducto ID del producto a obtener.
     * @return ResponseEntity que contiene el producto o un mensaje indicando que el producto no fue encontrado.
     */
    @Override
    public ResponseEntity <?> mostrarProductoPorId(Long idProducto) {
        try{
            Optional<Producto> productoOptional= productoRepository.findById(idProducto);
            if(productoOptional.isPresent()){
                Producto producto=productoOptional.get();
                return ResponseEntity.status(HttpStatus.OK).body(producto);
            } else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto con Id: " + idProducto + " no encontrado.");
            }

        }catch(DataAccessException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error inesperado. No se pudo procesar la operacion");
        }
    }

    /**
     * Modifica los detalles de un producto existente por su ID.
     *
     * @param idProducto ID del producto a modificar.
     * @param producto   Los nuevos detalles del producto.
     * @return ResponseEntity que indica el resultado de la operación.
     */

    public ResponseEntity<?> modificarProductoPorId(Long idProducto, Producto producto) {
        try{
            Producto productoAModificar= productoRepository.findById(idProducto).orElse(null);
            if(productoAModificar !=null){
                productoAModificar.setCodigoProducto(producto.getCodigoProducto());
                productoAModificar.setMarcaProducto(producto.getMarcaProducto());
                productoAModificar.setModeloProducto(producto.getModeloProducto());
                productoAModificar.setCaracProducto(producto.getCaracProducto());
                productoAModificar.setStockProducto(producto.getStockProducto());
                productoAModificar.setPrecioProducto(producto.getPrecioProducto());

                productoRepository.save(productoAModificar);

                return ResponseEntity.status(HttpStatus.OK).body("Producto ID: " + idProducto + " actualizado correctamente.");
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto ID: " + idProducto + " no encontrado.");

            }
        }catch(DataAccessException e){
            log.error("ERROR al obtener el producto buscado. {}" + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error interno al actualizar el producto");

        }
    }

    /**
     * Elimina un producto por su ID.
     *
     * @param idProducto ID del producto a eliminar.
     * @return ResponseEntity que indica el resultado de la operación.
     */

    @Override
    public ResponseEntity<String> eliminarProductoPorId(Long idProducto) {
        try {
            Optional<Producto> productoOptional = productoRepository.findById(idProducto);
            if (productoOptional.isPresent()) {
                productoRepository.deleteById(idProducto);
                return ResponseEntity.status(HttpStatus.OK).body("Producto con Id: " + idProducto + " eliminado.");

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto con Id: " + idProducto + " no encontrado.");
            }
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error inesperado. No se pudo procesar la solicitud.");
        }
    }



}
