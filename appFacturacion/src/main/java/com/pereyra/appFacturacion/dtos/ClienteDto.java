package com.pereyra.appFacturacion.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
/**
 * DTO (Data Transfer Object) que representa la información de un cliente para ser transferida entre capas.
 */

@Setter
@Getter
public class ClienteDto {

    /**
     * Identificador único del cliente.
     * -- GETTER --
     *  Obtiene el identificador único del cliente.
     *
     * @return Identificador único del cliente.

     */
    private Long idClienteDto;

    /**
     * Nombre del cliente.
     * -- GETTER --
     *  Obtiene el nombre del cliente.
     *
     * @return Nombre del cliente.

     */
    private String nombreClienteDto;

    /**
     * Apellido del cliente.
     * -- GETTER --
     *  Obtiene el apellido del cliente.
     *
     * @return Apellido del cliente.

     */
    private String apellidoClienteDto;

    /**
     * DNI (Documento Nacional de Identidad) del cliente.
     * -- GETTER --
     *  Obtiene el DNI del cliente.
     *
     * @return DNI del cliente.

     */
    private int dniClienteDto;

    /**
     * Constructor por defecto.
     */
    public ClienteDto() {
    }

    /**
     * Constructor que permite inicializar todos los atributos de la clase.
     *
     * @param idClienteDto      Identificador único del cliente.
     * @param nombreClienteDto  Nombre del cliente.
     * @param apellidoClienteDto Apellido del cliente.
     * @param dniClienteDto     DNI (Documento Nacional de Identidad) del cliente.
     */
    public ClienteDto(Long idClienteDto, String nombreClienteDto, String apellidoClienteDto, int dniClienteDto) {
        this.idClienteDto = idClienteDto;
        this.nombreClienteDto = nombreClienteDto;
        this.apellidoClienteDto = apellidoClienteDto;
        this.dniClienteDto = dniClienteDto;
    }


}