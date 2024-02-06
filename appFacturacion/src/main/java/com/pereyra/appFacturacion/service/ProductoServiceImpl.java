package com.pereyra.appFacturacion.service;
import com.pereyra.appFacturacion.entity.Producto;
import com.pereyra.appFacturacion.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ProductoServiceImpl implements ProductoService{

    private final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);
    @Autowired
    private ProductoRepository productoRepository;


    @Override
    public ResponseEntity<String> crearProducto(Producto producto) {
        try{
            productoRepository.save(producto);
            return ResponseEntity.ok("Producto agregado existosamente.");
        } catch(DataAccessException e){
            log.error("Error al agregar cliente. {}" + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ERROR INESPERADO. Producto no agregado.");
        }
    }

    @Override
    public List<Producto> mostrarListadoProducto() {

            List <Producto> productoList= productoRepository.findAll();
            return productoList;
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

    //Modificar metodo modificarProductoPorId mediante entidades
    // ProductosComprados y ProductosAlter generando un codigo unico de articulo
    //para evitar el cambio de datos del stock de producto disponible
    @Override
    public ResponseEntity<String> modificarProductoPorId(Long idProducto, Producto producto) {
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

                return ResponseEntity.ok("Producto ID: " + idProducto + " actualizado correctamente.");
            }else{
                return ResponseEntity.notFound().build();

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
                return ResponseEntity.ok("Producto con Id: " + idProducto + " eliminado.");

            } else {
                return ResponseEntity.ok("Producto no encontrado.");
            }
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error inesperado. No se pudo procesar la solicitud.");
        }
    }



}
