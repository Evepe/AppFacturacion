package com.pereyra.appFacturacion.service;
import com.pereyra.appFacturacion.entity.Producto;
import com.pereyra.appFacturacion.entity.ProductoVersion;
import com.pereyra.appFacturacion.repository.ProductoRepository;
import com.pereyra.appFacturacion.repository.ProductoVersionRespository;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;


@Transactional
@Service
public class ProductoServiceImpl implements ProductoService{

    private final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);
    @Autowired
    private ProductoRepository productoRepository;

   @Autowired
    private ProductoVersionRespository productoVersionRespository;



    @Override
    public ResponseEntity<?> crearProducto(Producto producto) {
        try{

            if(producto.getProductoVersionList()==null) {
                producto.setProductoVersionList(new ArrayList<>());
            }
            if(producto.getProductoVersionList().isEmpty()) {
                ProductoVersion productoVersion = new ProductoVersion();
                productoVersion.setProducto(producto);
                productoVersion.setFechaCreacion(LocalDate.now());
                productoVersion.setMarcaProducto(producto.getMarcaProducto());
                productoVersion.setModeloProducto(producto.getModeloProducto());
                productoVersion.setCaracteristicaProducto(producto.getCaracProducto());
                productoVersion.setPrecioProducto(producto.getPrecioProducto());
                productoVersion.setVersion(1); // Establece la versión 1 explícitamente

                producto.getProductoVersionList().add(productoVersion);
                System.out.println("Se creo version.");
            }else{
                System.out.println("No se creo version.");
            }
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
        try {
            Optional<Producto> productoOptional = productoRepository.findById(idProducto);
            boolean crearVersion = false;
            if (productoOptional.isPresent()) {
                Producto productoNuevo = productoOptional.get();
                Producto productoCopia = new Producto();
                BeanUtils.copyProperties(productoNuevo, productoCopia);

                if (!Objects.equals(producto.getStockProducto(), productoCopia.getStockProducto())) {
                    productoNuevo.setStockProducto(producto.getStockProducto());
                    System.out.println("Se modifico stock.");
                }

                if (!Objects.equals(producto.getCodigoProducto(), productoCopia.getCodigoProducto())) {
                    productoNuevo.setCodigoProducto(producto.getCodigoProducto());
                    crearVersion = true;
                    System.out.println("Se modifico el codigo.");
                }

                if (!Objects.equals(producto.getMarcaProducto(), productoCopia.getMarcaProducto())) {
                    productoNuevo.setMarcaProducto(producto.getMarcaProducto());
                    crearVersion = true;
                    System.out.println("Se modifico la marca.");
                }

                if (!Objects.equals(producto.getModeloProducto(), productoCopia.getModeloProducto())) {
                    productoNuevo.setModeloProducto(producto.getModeloProducto());
                    crearVersion = true;
                    System.out.println("Se modifico la modelo.");

                }

                if (!Objects.equals(producto.getCaracProducto(), productoCopia.getCaracProducto())) {
                    productoNuevo.setCaracProducto(producto.getCaracProducto());
                    crearVersion = true;
                    System.out.println("Se modifico la Caracteristicas.");
                }

                System.out.println("Antes del IF. producto: " + producto.getPrecioProducto() + " Copia: " + productoCopia.getPrecioProducto());


                if(producto.getPrecioProducto().compareTo(productoCopia.getPrecioProducto()) != 0){
                    productoNuevo.setPrecioProducto(producto.getPrecioProducto());
                    crearVersion = true;
                    System.out.println("Se modifico precio.");
                }



                if (crearVersion) {
                    ProductoVersion version = crearNuevaVersion(productoNuevo);
                    productoNuevo.getProductoVersionList().add(version);
                    productoVersionRespository.save(version);
                    System.out.println("Se creo Version.");
                }

                productoRepository.save(productoNuevo);


            }
            return ResponseEntity.status(HttpStatus.OK).body("Producto modificado correctamente.");
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al modificar producto.");
        }
    }



    public ResponseEntity<String> modificarPrecioProductoPorId(Long idProducto, BigDecimal precioProducto) {
        try {
            Producto productoExistente = productoRepository.findById(idProducto).orElseThrow(() -> new NoSuchElementException("Producto ID: " + idProducto + " inexistente."));

            if (precioProducto.compareTo(productoExistente.getPrecioProducto()) != 0) {
                productoExistente.setPrecioProducto(precioProducto);
                ProductoVersion version= crearNuevaVersion(productoExistente);
                productoExistente.getProductoVersionList().add(version);
                productoRepository.save(productoExistente);

            } else {
              return ResponseEntity.status(HttpStatus.OK).body("El precio ingresado es igual al precio original del producto.");
            }

            return ResponseEntity.status(HttpStatus.OK).body("El precio del producto ID " + idProducto + " actualizado correctamente.");

        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error inesperado. No se pudo actualizar el precio.");
        }
    }

    public ResponseEntity<String> modificarStockProductoPorId(Long idProducto, int stock) {
        try {
            Producto productoExistente = productoRepository.findById(idProducto).orElseThrow(() -> new NoSuchElementException("Producto ID: " + idProducto + " inexistente."));

            if (!Objects.equals(productoExistente.getStockProducto(), stock)) {
                productoExistente.setStockProducto(stock);
                productoRepository.save(productoExistente);

            } else {
                return ResponseEntity.status(HttpStatus.OK).body("El stock ingresado es igual al precio original del producto.");
            }

            return ResponseEntity.status(HttpStatus.OK).body("Stock del producto ID " + idProducto + " actualizado correctamente.");

        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error inesperado. No se pudo actualizar el precio.");
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



    public List <ProductoVersion> mostrarVersionesProducto(Long idProducto){
        Producto producto= productoRepository.findById(idProducto).orElseThrow(()-> new NoSuchElementException("Producto ID " + idProducto +" no encontrado."));

        return  producto.getProductoVersionList();
    }


    private ProductoVersion crearNuevaVersion(Producto producto) {
        int version=1;
        ProductoVersion nuevaVersion = new ProductoVersion();
        if (producto.getProductoVersionList()!=null){
          version= producto.getProductoVersionList().size()+1;
        }
        nuevaVersion.setVersion(version);
        nuevaVersion.setFechaCreacion(LocalDate.now());
        nuevaVersion.setMarcaProducto(producto.getMarcaProducto());
        nuevaVersion.setModeloProducto(producto.getModeloProducto());
        nuevaVersion.setCaracteristicaProducto(producto.getCaracProducto());
        nuevaVersion.setPrecioProducto(producto.getPrecioProducto());

        nuevaVersion.setProducto(producto);


        return nuevaVersion;
    }



}
