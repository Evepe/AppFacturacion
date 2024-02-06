package com.pereyra.appFacturacion.service;
import com.pereyra.appFacturacion.entity.Cliente;
import com.pereyra.appFacturacion.repository.ClienteRespository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Servicios relacionados con entidad cliente
 */
@Transactional
@Service
public class ClienteServiceImpl implements ClienteService{
    private final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);

    @Autowired
    private ClienteRespository clienteRepository;


    @Override
    public ResponseEntity<String> agregarCliente(Cliente cliente) {
        try {
            //Guarda el cliente en la BD
            clienteRepository.save(cliente);
            return ResponseEntity.ok("Cliente agregado correctamente.");
            //Manejo de excepciones
        } catch (DataAccessException e) {
            log.error("Error al agregar cliente. {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ERROR. Cliente no agregado.");
        }
    }

    @Override
    public List<Cliente> mostrarClientes() {
        try {
            //Devuelve Lista de Clientes
            return clienteRepository.findAll();
            //Manejo de excepciones
        } catch (NoSuchElementException e) {
            log.error("Error al obtener clientes. {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public ResponseEntity<String> modificarClientePorId(Long idCliente, Cliente cliente) {
        try {
            //Busca Id cliente en Bd
            Optional<Cliente> clienteOptional = clienteRepository.findById(idCliente);
            //Si lo encuentra modifica los campos necesarios
            if (clienteOptional.isPresent()) {
                Cliente clienteAModificar = clienteOptional.get();
                clienteAModificar.setNombreCliente(cliente.getNombreCliente());
                clienteAModificar.setApellidoCliente(cliente.getApellidoCliente());
                clienteAModificar.setDniCliente(cliente.getDniCliente());
                clienteAModificar.setEMail(cliente.getEMail());
                clienteRepository.save(clienteAModificar);
                return ResponseEntity.ok().body("Cliente id: " + idCliente + " actualizado correctamente.");
            } else {
                return ResponseEntity.ok("Cliente con Id: "+idCliente+ " no encontrado.");
            }
            //Manejo de excepciones
        } catch (DataAccessException e) {
            log.error("Error al obtener cliente. {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error interno al actualizar el cliente.");
        }
    }

    @Override
    public ResponseEntity <?> mostrarClientePorId(Long id) {
        try{
            //Busca al cliente en BD si lo encuentra lo muestra
            Optional <Cliente> clienteOptional= clienteRepository.findById(id);
            if(clienteOptional.isPresent()){
                Cliente cliente=clienteOptional.get();
                return ResponseEntity.ok(cliente);
            } else{
                return ResponseEntity.ok("Cliente con Id: " + id + " no encontrado.");
            }
            //Manejo excepciones
        } catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error inesperado. No se puede procesar la peticion.");
        }
    }

    @Override
    public ResponseEntity<String> eliminarClientePorId(Long id) {
        try {
            //Busca determinado cliente por Id y lo elimina
            clienteRepository.deleteById(id);
            return ResponseEntity.ok("Cliente con ID: " + id + " eliminado.");
            //Manejo de excepciones en caso de no encontrarlo
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.ok("Cliente con Id: " + id +" no encontrado.");
            //Manejo de excepciones
        } catch (DataAccessException e) {
            log.error("Error al eliminar cliente. {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error interno al eliminar el cliente.");
        }
    }

    @Override
    public ResponseEntity<String> buscarClientePorDni(int dniCliente) {
        try {
            //Busca cliente en base de datos por su DNI
            Optional<Cliente> clienteOptional = clienteRepository.findByDniCliente(dniCliente);

            if (clienteOptional.isPresent()) {
                Cliente cliente = clienteOptional.get();
                return ResponseEntity.ok("Cliente encontrado: " + cliente);
            } else {
                return ResponseEntity.ok("Cliente no encontrado en la base de datos.");
            }
            //Manejo excepciones
        } catch (DataAccessException e) {
            log.error("Error inesperado{}", e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error inesperado.");
        }
    }
}
