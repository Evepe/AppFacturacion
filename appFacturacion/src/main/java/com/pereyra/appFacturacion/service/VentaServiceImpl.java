package com.pereyra.appFacturacion.service;
import com.pereyra.appFacturacion.dtos.ClienteDto;
import com.pereyra.appFacturacion.dtos.VentaDto;
import com.pereyra.appFacturacion.dtos.VentaRequestDto;
import com.pereyra.appFacturacion.dtos.VersionVentaRequestDto;
import com.pereyra.appFacturacion.entity.Cliente;
import com.pereyra.appFacturacion.entity.Producto;
import com.pereyra.appFacturacion.entity.ProductoVersion;
import com.pereyra.appFacturacion.entity.Venta;
import com.pereyra.appFacturacion.repository.ClienteRespository;
import com.pereyra.appFacturacion.repository.ProductoRepository;
import com.pereyra.appFacturacion.repository.ProductoVersionRespository;
import com.pereyra.appFacturacion.repository.VentaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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

    @Autowired
    private ProductoVersionRespository productoVersionRespository;

    @PersistenceContext
    private EntityManager entityManager;



    @Override
   public Venta agregarVenta(Venta venta) {


        // Verificar existencia de cliente y asignacion a la venta de existir
        Optional<Cliente> clienteOptional = clienteRepository.findById(venta.getCliente().getIdCliente());
        if (!clienteOptional.isPresent()) {
            throw new NoSuchElementException("Cliente ID " + venta.getCliente().getIdCliente() + " no encontrado.");
        }

        Cliente cliente = clienteOptional.get();

        venta.setFechaHoracreacion(fechaService.obtenerFecha());
        venta.setCliente(cliente);
        venta.setCompleta(false);

        // Verificar existencia de producto y asignacion a la venta de existir
        List<ProductoVersion> productoVersion = new ArrayList<>();

        for (ProductoVersion pv : venta.getVersiones()) {

            Producto productoDemandado = pv.getProducto();
            if (productoDemandado == null) {
                throw new IllegalArgumentException("El producto en ProductoVersion es nulo.");
            }

            Long idUltimaVersion = obtenerUltimaVersionProducto(productoDemandado.getIdProducto());
            ProductoVersion versionUltima = productoVersionRespository.findById(idUltimaVersion)
                    .orElseThrow(() -> new NoSuchElementException("Versión no encontrada para el producto ID " + productoDemandado.getIdProducto()));

            int stock = pv.getProducto().getStockProducto();
            int unidadesPorVenta = pv.getProducto().getUnidadPorVenta();

            if (stock < unidadesPorVenta) {
                throw new NoSuchElementException("No hay stock disponible para el producto ID " + productoDemandado.getIdProducto());
            }


            productoDemandado.setStockProducto(stock - unidadesPorVenta);
            productoDemandado.setCantidadVendida(productoDemandado.getCantidadVendida() + unidadesPorVenta);
            productoDemandado.setUnidadPorVenta(pv.getProducto().getUnidadPorVenta());


            productoVersion.add(versionUltima);
        }


        // Calcular total de la venta
        BigDecimal totalVenta = productoVersion.stream()
                .map(version -> version.getPrecioProducto().multiply(new BigDecimal(version.getProducto().getUnidadPorVenta())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        venta.setTotalVenta(totalVenta);
        venta.getVersiones().addAll(productoVersion);

        venta.marcarCompleta();

        return ventaRepository.save(venta);

   }


//    public VentaDto crearVenta(VentaRequestDto ventaRequestDto) {
//        // ... (validaciones y creación de cliente)
//        // Validar que el cliente exista
//        Long idCliente = ventaRequestDto.getCliente().getIdClienteDto();
//        ClienteDto clienteDto = clienteService.obtenerClientePorId(idCliente);
//        if (clienteDto == null) {
//            throw new NoSuchElementException("Cliente con ID " + idCliente + " no encontrado.");
//        }
//
//        // Crear la entidad Cliente a partir del DTO
//        Cliente cliente = new Cliente();
//        cliente.setIdCliente(clienteDto.getIdClienteDto());
//        cliente.setNombreCliente(clienteDto.getNombreClienteDto());
//        cliente.setApellidoCliente(clienteDto.getApellidoClienteDto());
//        // Crear la entidad Venta y asignar el cliente
//        Venta venta = new Venta();
//        venta.setCliente(cliente);
//        venta.setFechaHoracreacion(fechaService.obtenerFecha());
//        venta.setTotalVenta(BigDecimal.ZERO); // Inicializar el precio total
//
//        // Procesar las versiones de productos
//        List<ProductoVersion> productoVersionList = new ArrayList<>();
//        for (VersionVentaRequestDto versionDto : ventaRequestDto.getVersiones()) {
//            // Validar que la versión del producto exista
//
//            Long idProducto= versionDto.getProducto().getIdProductoDto();
//            Long idVersion = obtenerUltimaVersionProducto(idProducto);
//            ProductoVersion productoVersion = productoVersionRespository.findById(idVersion)
//                    .orElseThrow(() -> new NoSuchElementException("Versión de producto con ID " + idVersion + " no encontrada."));
//
//            // Procesar la cantidad de productos y actualizar el stock
//            int cantidad = versionDto.getCantidad();
//            Producto producto = productoVersion.getProducto();
//            if (producto.getStockProducto() < cantidad) {
//                throw new NoSuchElementException("No hay suficiente stock disponible para el producto ID " + producto.getIdProducto());
//            }
//
//            producto.setStockProducto(producto.getStockProducto() - cantidad);
//            producto.setCantidadVendida(producto.getCantidadVendida() + cantidad);
//
//            // Verificar si el precio de la versión es null
//            BigDecimal precioUnitario = productoVersion.getPrecioProducto();
//            if (precioUnitario == null) {
//                throw new NullPointerException("Precio de producto con ID " + producto.getIdProducto() + " es nulo.");
//            }
//
//            // Actualizar el precio total de la venta
//            BigDecimal subtotal = precioUnitario.multiply(BigDecimal.valueOf(cantidad));
//            venta.setTotalVenta(venta.getPrecioTotal().add(subtotal));
//
//            // Agregar la ProductoVersion a la lista
//            productoVersionList.add(productoVersion);
//        }
//
//        // Asignar la lista de ProductoVersion a la Venta
//        venta.setVersiones(productoVersionList);
//
//        // Guardar la Venta en la base de datos
//        Venta ventaGuardada = ventaRepository.save(venta);
//
//        // Crear el DTO de respuesta
//        VentaDto ventaDtoRespuesta = new VentaDto();
//        ventaDtoRespuesta.setIdVentaDto(ventaGuardada.getIdVenta());
//        ventaDtoRespuesta.setFechaCreacionVentaDto(ventaGuardada.getFechaHoracreacion());
//        ventaDtoRespuesta.setPrecioTotal(ventaGuardada.getPrecioTotal());
//        // ... (setear otros campos según sea necesario)
//
//        return ventaDtoRespuesta;
//    }




    @Override
    public List<Venta> mostrarListadodeVentas() {
        return ventaRepository.findAll();

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


    private Long obtenerUltimaVersionProducto(Long idProducto) {
        Producto productoExiste = productoRepository.findById(idProducto)
                .orElseThrow(() -> new NoSuchElementException("Producto ID: " + idProducto + " no encontrado."));

        List<ProductoVersion> versiones = productoExiste.getProductoVersionList();

        if (versiones.isEmpty()) {
            throw new NoSuchElementException("No hay versiones para el producto ID: " + idProducto);
        }

        ProductoVersion ultimaVersion = versiones.get(versiones.size() - 1);
        return ultimaVersion.getIdVs();
    }


}

