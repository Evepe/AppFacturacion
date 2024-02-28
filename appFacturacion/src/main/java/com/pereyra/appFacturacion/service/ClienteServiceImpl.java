package com.pereyra.appFacturacion.service;
import com.pereyra.appFacturacion.dtos.ClienteDto;
import com.pereyra.appFacturacion.entity.Cliente;
import com.pereyra.appFacturacion.repository.ClienteRespository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
          if (StringUtils.isEmpty(cliente.getNombreCliente()) || StringUtils.isEmpty(cliente.getApellidoCliente()) || cliente.getDniCliente() == 0)  {
              return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al guardar cliente. Complete los campos obligatorios: Nombre, Apellido y DNI.");
          }
            clienteRepository.save(cliente);
            return ResponseEntity.ok("Cliente agregado correctamente." + "\n" + cliente);

           //Manejo de excepciones
        } catch (DataAccessException e) {
            log.error("Error al agregar cliente. {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ERROR INESPERADO. No se pudo agregar el cliente.");
        }
    }

    @Override
    public ResponseEntity<?> mostrarCliente() {
        try {
            List<Cliente> listaCliente = clienteRepository.findAll();
            return listaCliente.isEmpty()
                    ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("No cuentan con clientes almacenados" + Collections.emptyList())
                    : ResponseEntity.status(HttpStatus.OK).body(listaCliente);
        } catch (DataAccessException e) {
            log.error("Error al acceder al listado de clientes. {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ERROR INESPERADO. No se puede acceder a la lista de clientes almacenados.");
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
                return ResponseEntity.status(HttpStatus.OK).body("Cliente ID " + idCliente + " correctamente modificado.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente con Id: " + idCliente + " no encontrado.");
            }
        } catch (DataAccessException e) {
            log.error("Error al obtener cliente. {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error interno al actualizar el cliente.");
        }
    }

    public ClienteDto obtenerClientePorId(Long idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new NoSuchElementException("Cliente con ID " + idCliente + " no encontrado."));

        ClienteDto clienteDto = new ClienteDto();
        BeanUtils.copyProperties(cliente, clienteDto);

        return clienteDto;
    }
    @Override
    public ResponseEntity <?> mostrarClientePorId(Long id) {
        try{
            //Busca al cliente en BD si lo encuentra lo muestra
            Optional <Cliente> clienteOptional= clienteRepository.findById(id);

            return ResponseEntity.status(HttpStatus.OK).body(clienteOptional);
            //Manejo excepciones

            } catch (EmptyResultDataAccessException e){
                return ResponseEntity.status(HttpStatus.OK).body("Cliente con Id: " + id + " no encontrado.");
        } catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error inesperado. No se puede procesar la peticion.");
        }
    }

    @Override
    public ResponseEntity<String> eliminarClientePorId(Long id) {
        try {
            Optional <Cliente> clienteOptional=clienteRepository.findById(id);

            if(clienteOptional.isPresent()) {
                //Busca determinado cliente por Id y lo elimina
                clienteRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body("Cliente con ID: " + id + " eliminado.");
            }
            else {
                return ResponseEntity.ok("Cliente con ID: " + id + " no encontrado.");
            }
            //Manejo de excepciones
        } catch (DataAccessException e) {
            log.error("Error al eliminar cliente. {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error inesperado. No se pudo procesar la operacion.");
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
