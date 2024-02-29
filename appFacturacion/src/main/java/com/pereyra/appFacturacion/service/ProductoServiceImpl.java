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

import java.util.*;


@Transactional
@Service
public class ProductoServiceImpl implements ProductoService{

    private final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);
    @Autowired
    private ProductoRepository productoRepository;





    @Override
    public ResponseEntity<?> crearProducto(Producto producto) {
        try{
            productoRepository.save(producto);

            return ResponseEntity.ok("Producto agregado exitosamente.");
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

    @Override
    public ResponseEntity <?> mostrarListadoProducto() {
        try{
            List <Producto> productoListado= productoRepository.findAll();
            return productoListado.isEmpty()
                    ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("No cuenta con productos almacenados." +Collections.emptyList())
                    : ResponseEntity.status(HttpStatus.OK).body(productoListado);
        } catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ERROR INESPERADO. No se puede acceder a la lista de productos almacenados.");
        }
    }

    @Override
    public ResponseEntity <?> mostrarProductoPorId(Long idProducto) {
        try{
            Optional<Producto> productoOptional= productoRepository.findById(idProducto);
            if(productoOptional.isPresent()){
                Producto producto=productoOptional.get();
                return ResponseEntity.ok(producto);
            } else{
                return ResponseEntity.ok("Producto con Id: " + idProducto + " no encontrado.");
            }

        }catch(DataAccessException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error inesperado.");
        }
    }



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
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto ID: " + idProducto + "no encontrado.");

            }
        }catch(DataAccessException e){
            log.error("ERROR al obtener el producto buscado. {}" + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error interno al actualizar el producto");

        }
    }


    @Override
    public ResponseEntity<String> eliminarProductoPorId(Long idProducto) {
        try {
            Optional<Producto> productoOptional = productoRepository.findById(idProducto);
            if (productoOptional.isPresent()) {
                productoRepository.deleteById(idProducto);
                return ResponseEntity.status(HttpStatus.OK).body("Producto con Id: " + idProducto + " eliminado.");

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado.");
            }
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error inesperado. No se pudo procesar la solicitud.");
        }
    }



}
