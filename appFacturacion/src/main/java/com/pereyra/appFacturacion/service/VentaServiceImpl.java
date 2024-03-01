package com.pereyra.appFacturacion.service;
import com.pereyra.appFacturacion.entity.*;
import com.pereyra.appFacturacion.repository.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * Servicios relacionados con la entidad Venta.
 * Contiene operaciones CRUD para gestionar ventas y detalles de ventas.
 */

@Transactional
@Service
public class VentaServiceImpl implements VentaService{
    final Logger log = LoggerFactory.getLogger(VentaServiceImpl.class);

    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private ClienteRespository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private FechaService fechaService;

    @Autowired
    private VentaDetalleRepository ventaDetalleRepository;

    /**
     * Agrega una nueva venta al sistema, realiza la validación de existencia de cliente y productos.
     *
     * @param venta La venta a agregar.
     * @return ResponseEntity que indica el resultado de la operación.
     */
    @Override
    public ResponseEntity<?> agregarVenta(Venta venta) {
        try {
            //Se almacena el id del cliente
            Long idCliente = venta.getCliente().getIdCliente();

            //Se valida existencia de cliente
            Cliente cliente = clienteRepository.findById(idCliente)
                    .orElseThrow(() -> new NoSuchElementException("Cliente no encontrado."));

            //Se verifica que el cliente no sea nulo
            if (cliente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado.");
            } else {

                venta.setCliente(cliente);
                venta.setFechaHoracreacion(fechaService.obtenerFecha());
                venta.setCompleta(false);
            }

            List<VentaDetalle> detalles = new ArrayList<>();
            double totalVenta = 0;

            int historicoCentidadVendida;
            for (VentaDetalle vd : venta.getVentaDetalles()) {
                Long idProducto = vd.getIdProducto();
                //Se verifica existencia del producto
                Producto producto = productoRepository.findById(idProducto).orElseThrow(() -> new NoSuchElementException("Producto inexistente."));
                //Se comprueba stock del producto solicitado
                if (producto.getStockProducto() < vd.getCantidad()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("Stock insuficiente para el producto " + producto.getIdProducto());
                }

                historicoCentidadVendida=producto.getCantidadTotalVendido();

                int cantidadTotalVendida = producto.getCantidadTotalVendido() + vd.getCantidad();
                producto.setCantidadTotalVendido(cantidadTotalVendida);
                producto.setStockProducto(producto.getStockProducto() - vd.getCantidad());
                productoRepository.save(producto);

                double precioUnidad = producto.getPrecioProducto();
                vd.setPrecio(precioUnidad);


                vd.setMarca(producto.getMarcaProducto());
                vd.setModelo(producto.getModeloProducto());
                vd.setCaracteristica(producto.getCaracProducto());

                totalVenta += precioUnidad * vd.getCantidad();
                vd.setVenta(venta);
                detalles.add(vd);
            }

            venta.setTotalVenta(totalVenta);
            venta.marcarCompleta();


            ventaRepository.save(venta);
            Long idVenta = venta.getIdVenta();


            return ResponseEntity.ok(venta);
    //Manejo de excepciones
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al agregar venta.");
        }
    }

    /**
     * Obtiene una lista de todas las ventas con detalles almacenadas en el sistema.
     *
     * @return Lista de ventas con detalles.
     * @throws NoSuchElementException Si no se encuentran ventas.
     */

    @Override
    public List<Venta> findAllWithVentaDetalles() {
        List<Venta> ventas = ventaRepository.findAllWithVentaDetalles();
        if (ventas.isEmpty()) {
            throw new NoSuchElementException("Al momento no registra venta alguna.");
        }
        return ventas;
    }

    /**
     * Obtiene una venta por su ID.
     *
     * @param idVenta ID de la venta a obtener.
     * @return ResponseEntity que contiene la venta o un mensaje indicando que la venta no fue encontrada.
     */

    @Override
    public ResponseEntity<?> mostrarVentaPorId(Long idVenta) {
        try {
            Optional<Venta> ventaOptional = ventaRepository.findById(idVenta);
            if (ventaOptional.isPresent()) {
                Venta venta = ventaOptional.get();
                return ResponseEntity.status(HttpStatus.OK).body(venta);

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venta con Id: " + idVenta + " no encontrado.");
            }
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error inesperado.");
        }
    }


    /**
     * Obtiene una lista de ventas asociadas a un cliente por su ID.
     *
     * @param idCliente ID del cliente.
     *@return ResponseEntity que contiene la venta o un mensaje indicando que la venta no fue encontrada.
     */

    @Override
    public ResponseEntity <?> findVentasByIdCliente(Long idCliente) {
        try {

            Optional<Cliente> clienteOptional = clienteRepository.findById(idCliente);

            if (!clienteOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con ID: " + idCliente);
            }

            Cliente cliente = clienteOptional.get();


            List<Venta> ventas = ventaRepository.findVentasByIdCliente(cliente.getIdCliente());


            if (ventas.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body("Cliente ID " + idCliente + " no registra compra alguna.");

            }

            return ResponseEntity.ok(ventas);
        } catch (DataAccessException e) {
            log.error("Error al buscar ventas por ID de cliente: {}", idCliente, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor. Contacte al administrador.");
        }

    }




    /**
     * Obtiene una lista de ventas asociadas a un cliente por su DNI.
     *
     * @param dniCliente DNI del cliente.
     *@return ResponseEntity que contiene ventas asociadas o un mensaje indicando que la venta no fue encontrada.
     */

        @Override
        public ResponseEntity<?> findVentasByDniCliente (int dniCliente){
            try {

                Optional<Cliente> clienteOptional = clienteRepository.findByDniCliente(dniCliente);


                if (clienteOptional.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
                }

                Cliente cliente = clienteOptional.get();


                List<Venta> ventas = ventaRepository.findVentasByIdCliente(cliente.getIdCliente());


                if (ventas.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.OK).body(Collections.emptyList());
                }

                return ResponseEntity.ok(ventas);

            } catch (DataAccessException e) {
                log.error("Error al buscar ventas por DNI de cliente. {}", e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor.");
            }
        }

    /**
     * Obtiene una lista de ventas asociadas a un producto por su ID.
     *
     * @param idProducto ID del producto.
     * @return ResponseEntity que contiene la venta o un mensaje indicando que la venta no fue encontrada.
     */

    @Override
    public ResponseEntity<?> findVentasByIdProducto(Long idProducto) {
        try {

            Optional<Producto> productoOptional = productoRepository.findById(idProducto);


            if (!productoOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado con ID: " + idProducto);
            }

            Producto producto = productoOptional.get();


            List<Venta> ventas = ventaRepository.findVentasByIdProducto(producto.getIdProducto());


            if (ventas.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(Collections.emptyList());
            }

            return ResponseEntity.ok(ventas);

        } catch (DataAccessException e) {
            log.error("Error al obtener las ventas del producto con ID: {}", idProducto, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor. Contacte al administrador.");
        }
    }

    /**
     * Obtiene una venta por su ID.
     *
     * @param idVenta ID de la venta.
     * @return La venta encontrada.
     * @throws NoSuchElementException Si no se encuentra la venta.
     */

    @Override
    public Venta findByIdVenta(Long idVenta) {
        Optional<Venta> ventaOptional = ventaRepository.findById(idVenta);

        if (!ventaOptional.isPresent()) {
            throw new NoSuchElementException("Venta no encontrada con ID: " + idVenta);
        }

        return ventaOptional.get();
    }

}

