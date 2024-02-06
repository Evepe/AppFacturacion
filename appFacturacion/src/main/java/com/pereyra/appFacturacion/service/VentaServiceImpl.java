package com.pereyra.appFacturacion.service;
import com.pereyra.appFacturacion.entity.Cliente;
import com.pereyra.appFacturacion.entity.Producto;
import com.pereyra.appFacturacion.entity.Venta;
import com.pereyra.appFacturacion.repository.ClienteRespository;
import com.pereyra.appFacturacion.repository.ProductoRepository;
import com.pereyra.appFacturacion.repository.VentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class VentaServiceImpl implements VentaService{

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




@Override
    public Venta agregarVenta(Venta venta) {


        // Obtiene el cliente
        Long idCliente = venta.getCliente().getIdCliente();
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + idCliente));
        venta.setCliente(cliente);

        // Obtiene los productos y ajusta el stock
        List<Producto> productos = new ArrayList<>();
        for (Producto producto : venta.getProductoList()) {
            Long idProducto = producto.getIdProducto();
            int cantidadVendida = producto.getCantidadVendida();

            Producto productoExistente = productoRepository.findById(idProducto)
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + idProducto));

            if (cantidadVendida > productoExistente.getStockProducto()) {
                throw new IllegalArgumentException("Stock insuficiente para el producto con ID: " + idProducto);
            }

            productoExistente.setStockProducto(productoExistente.getStockProducto() - cantidadVendida);
            productoExistente.setCantidadVendida(cantidadVendida);
            productoService.modificarProductoPorId(idProducto, productoExistente);

            productos.add(productoExistente);
        }

        venta.setProductoList(productos);

        // Calcular el precio total de la venta
        BigDecimal totalVenta = calcularPrecioTotalVenta(productos);
        venta.setTotalVenta(totalVenta);
        String fecha= fechaService.obtenerFecha();
        venta.setFechaHoracreacion(fecha);

         venta.marcarCompleta();

        // Guardar la venta en la base de datos
        return ventaRepository.save(venta);



    }


    @Override
    public List<Venta> mostrarListadodeVentas() {
        List <Venta> venta=ventaRepository.findAll();
            return venta;

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



    private BigDecimal calcularPrecioTotalVenta(List<Producto> productos) {
        return productos.stream()
                .map(Producto::getPrecioProducto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}

