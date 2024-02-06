package com.pereyra.appFacturacion.service;

import com.pereyra.appFacturacion.entity.Cliente;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Interfaz que define los servicios relacionados a clientes
 */

public interface ClienteService {

    /**
     * Agrega un cliente en el sistema
     * @param cliente datos del cliente a insertar
     * @return ResponseEntity devuelve resultado de la operacion
     */
    public ResponseEntity<String> agregarCliente(Cliente cliente);

    /**
     * Muestra los clientes existentes en el sistema
     * @return List listado de clientes
     */
    public List<Cliente> mostrarClientes();

    /**
     * Modifica determinado cliente
     * @param idCliente identificador unico de cliente PK
     * @param cliente Informacion de cliente para modificar
     * @return ResponseEntity devuelve resultado de la operacion
     */
    public ResponseEntity <String> modificarClientePorId(Long idCliente, Cliente cliente);

    /**
     * Muestra cliente determinado
     * @param id identificador unico de cliente PK
     * @return ResponseEntity devuelve resultado de la operacion
     */
    public ResponseEntity <?> mostrarClientePorId(Long id);

    /**
     * Elimina un Cliente por Id
     * @param id identificador unico para eliminar cliente
     * @return
     */
    public ResponseEntity <?> eliminarClientePorId(Long id);

    /**
     * Buscar determinado cliente por su DNI
     * @param dniCliente Dni de cliente(Valor unico)
     * @return ResponseEntity devuelve resultado de la operacion
     */
    public ResponseEntity<String> buscarClientePorDni(int dniCliente);
}
