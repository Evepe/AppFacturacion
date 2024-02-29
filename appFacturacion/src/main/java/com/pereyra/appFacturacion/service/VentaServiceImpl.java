package com.pereyra.appFacturacion.service;
import com.pereyra.appFacturacion.dtos.VentaDetalleDto;
import com.pereyra.appFacturacion.dtos.VentaDto;
import com.pereyra.appFacturacion.entity.*;
import com.pereyra.appFacturacion.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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


    @Override
    public ResponseEntity<?> agregarVenta(Venta venta) {
        try {
            Long idCliente= venta.getCliente().getIdCliente();
            Cliente cliente = clienteRepository.findById(idCliente)
                    .orElseThrow(() -> new NoSuchElementException("Cliente no encontrado."));
            System.out.println(cliente);
            if (cliente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado.");
            } else {
                venta.setCliente(cliente);
                venta.setFechaHoracreacion(fechaService.obtenerFecha());
                venta.setCompleta(false);
            }

            List<VentaDetalle> detalles = new ArrayList<>();
            double totalVenta =0;



            for (VentaDetalle vd : venta.getVentaDetalles()) {
                Long idProducto=vd.getIdProducto();

                Producto producto= productoRepository.findById(idProducto).orElseThrow(()-> new NoSuchElementException("Producto inexistente."));
                System.out.println(producto);
                if (producto.getStockProducto() < vd.getCantidad()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("Stock insuficiente para el producto " + producto.getIdProducto());
                }

                int cantidadTotalVendida =  + producto.getCantidadVendida() + vd.getCantidad();
                producto.setCantidadTotalVendido(cantidadTotalVendida);
                producto.setStockProducto(producto.getStockProducto() - vd.getCantidad());
                productoRepository.save(producto);

                double precioUnidad= producto.getPrecioProducto();
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

             venta.getVentaDetalles().addAll(detalles);

            ventaRepository.save(venta);
            Long idVenta=venta.getIdVenta();

            System.out.println("Id venta: " + idVenta);
            VentaDto respuesta = new VentaDto();
            respuesta.setIdVentaDto(idVenta);
            respuesta.setIdCliente(cliente.getIdCliente());
            respuesta.setNombreCliente(cliente.getNombreCliente());
            respuesta.setApellidoCliente(cliente.getApellidoCliente());
            respuesta.setDetalleVenta(new ArrayList<>());

            for (VentaDetalle vd : venta.getVentaDetalles()) {
                VentaDetalleDto detalle = new VentaDetalleDto();
                Long idProducto=vd.getIdProducto();
                detalle.setIdProducto(idProducto);
                detalle.setMarca(vd.getMarca());
                detalle.setModelo(vd.getModelo());
                detalle.setCaracteristica(vd.getCaracteristica());
                detalle.setPrecio(vd.getPrecio());
                respuesta.getDetalleVenta().add(detalle);
            }

            respuesta.setPrecioTotal(totalVenta);
            respuesta.setFechaCreacionVentaDto(fechaService.obtenerFecha());

            return ResponseEntity.ok(respuesta);

        } catch (DataAccessException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al agregar venta.");
        }
    }

    @Override
    public List<Venta> findAllWithVentaDetalles(){

         List<Venta> ventas = ventaRepository.findAllWithVentaDetalles();
         if(ventas.isEmpty()){
             throw new NoSuchElementException("Al momento no registra venta alguna.");
         }
        return ventas;
    }


    @Override
    public ResponseEntity <?> mostrarVentaPorId(Long idVenta){
        try{
            Optional <Venta> ventaOptional= ventaRepository.findById(idVenta);
            if (ventaOptional.isPresent()){
                Venta venta=ventaOptional.get();
                return ResponseEntity.ok(venta);

            } else{
                return ResponseEntity.ok("Venta con Id: " + idVenta + " no encontrado.");
            }
        } catch(DataAccessException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error inesperado.");
        }
    }

    @Override
    public List<Venta> findVentasByIdCliente(Long idCliente) {

        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new NoSuchElementException("Cliente no encontrado."));


        List<Venta> ventas = ventaRepository.findVentasByIdCliente(idCliente);

        if (ventas.isEmpty()) {

            throw new NoSuchElementException("No se encontraron ventas para el cliente con ID: " + idCliente);
        }

        return ventas;
    }

    @Override
    public List<Venta> findVentasByDniCliente(int dniCliente) {

        Cliente cliente = clienteRepository.findByDniCliente(dniCliente)
                .orElseThrow(() -> new NoSuchElementException("Cliente no encontrado con DNI: " + dniCliente));

        List<Venta> ventas = ventaRepository.findVentasByIdCliente(cliente.getIdCliente());

        if (ventas.isEmpty()) {

            throw new NoSuchElementException("No se encontraron ventas para el cliente con DNI: " + dniCliente);
        }

        return ventas;
    }


    @Override
    public List<Venta> findVentasByIdProducto(Long idProducto) {

        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado con ID: " + idProducto));


        List<Venta> ventas = ventaRepository.findVentasByIdProducto(idProducto);

        if (ventas.isEmpty()) {

            throw new NoSuchElementException("No se encontraron ventas para el producto con ID: " + idProducto);
        }

        return ventas;
    }


    @Override
    public Venta findByIdVenta(Long idVenta) {

        Venta venta = ventaRepository.findById(idVenta)
                .orElseThrow(() -> new NoSuchElementException("Venta no encontrada con ID: " + idVenta));



        return venta;
    }

}

