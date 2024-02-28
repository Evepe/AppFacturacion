package com.pereyra.appFacturacion.controllers;


import com.pereyra.appFacturacion.entity.Cliente;
import com.pereyra.appFacturacion.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Endpoints para crud cliente
 */
@RestController
@RequestMapping("/facturacion/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/alta")
    public ResponseEntity<String> altaCliente(@Valid @RequestBody Cliente cliente) {
        return clienteService.agregarCliente(cliente);
    }

    @GetMapping("/listado")
    public ResponseEntity<?> mostrarClientes() {
        return clienteService.mostrarCliente();

    }

    @GetMapping("/mostrarporid/{idCliente}")

    public ResponseEntity<?> mostrarClientePorId(@PathVariable Long idCliente) {

        return clienteService.mostrarClientePorId(idCliente);

    }


    @GetMapping("/mostrarpordni/{dniCliente}")

    public ResponseEntity <?> mostrarClientePorDni(@PathVariable int dniCliente){
        return clienteService.buscarClientePorDni(dniCliente);
    }

    @PutMapping("/modificar/{idCliente}")

    public ResponseEntity <?> modificarCliente(@PathVariable Long idCliente, @Valid @RequestBody Cliente cliente){
        return clienteService.modificarClientePorId(idCliente, cliente);
    }


    @DeleteMapping("/eliminar/{idCliente}")
    public ResponseEntity <?> eliminarCliente(@PathVariable Long idCliente){
        return clienteService.eliminarClientePorId(idCliente);
    }



}
