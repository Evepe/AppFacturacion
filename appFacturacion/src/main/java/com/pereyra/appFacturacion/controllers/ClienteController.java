package com.pereyra.appFacturacion.controllers;


import com.pereyra.appFacturacion.entity.Cliente;
import com.pereyra.appFacturacion.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * Endpoints para CRUD de clientes.
 * Se utilizan para gestionar las operaciones relacionadas con la entidad Cliente.
 */
@RestController
@RequestMapping("/facturacion/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/alta")
    @Operation(summary = "Crea un nuevo cliente", description = "Permite la creación de un cliente.")
    @ApiResponse(responseCode = "200", description = "Operacion existosa.", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "Operación fallida.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity<String> altaCliente(@Valid @RequestBody Cliente cliente) {
        try {
            return clienteService.agregarCliente(cliente);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al agregar cliente.");
        }
    }

    @GetMapping("/listado")
    @Operation(summary = "Mostrar lista de clientes", description = "Permite acceder a los clientes almacenados en BD.")
    @ApiResponse(responseCode = "200", description = "Operacion existosa.", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404", description = "Cliente inexistente", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "Operacion fallida.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity<?> mostrarClientes() {
        return clienteService.mostrarCliente();

    }


    @GetMapping("/mostrarporid/{idCliente}")
    @Operation(summary = "Obtiene cliente por ID", description = "Permite la obtencion de un cliente especifico.")
    @ApiResponse(responseCode = "200", description = "Operacion existosa.", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404", description = "Cliente inexistente", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "Operacion fallida.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity<?> mostrarClientePorId(@PathVariable Long idCliente) {

        return clienteService.mostrarClientePorId(idCliente);

    }


    @GetMapping("/mostrarpordni/{dniCliente}")
    @Operation(summary = "Obtiene cliente por DNI", description = "Permite la obtencion de un cliente especifico.")
    @ApiResponse(responseCode = "200", description = "Operacion existosa.", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404", description = "Cliente inexistente", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "Operacion fallida.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity<?> mostrarClientePorDni(@PathVariable int dniCliente) {
        return clienteService.buscarClientePorDni(dniCliente);
    }

    @PutMapping("/modificar/{idCliente}")
    @Operation(summary = "Modifica cliente por id", description = "Permite la modificacion de un cliente especifico.")
    @ApiResponse(responseCode = "200", description = "Operacion existosa.", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404", description = "Cliente inexistente", content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "Operacion fallida.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity<?> modificarCliente(@PathVariable Long idCliente, @Valid @RequestBody Cliente cliente) {
        return clienteService.modificarClientePorId(idCliente, cliente);
    }


    @DeleteMapping("/eliminar/{idCliente}")
    @Operation(summary = "Elimina cliente por id", description = "Permite la eliminacion de un cliente especifico.")
    @ApiResponse(responseCode = "200", description = "Operacion existosa.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "404", description = "Cliente inexistente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "409", description = "Operacion fallida.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    public ResponseEntity<?> eliminarCliente(@PathVariable Long idCliente) {
        return clienteService.eliminarClientePorId(idCliente);
    }



}
