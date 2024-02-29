package com.pereyra.appFacturacion.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class ClienteDto {


    private Long idClienteDto;


    private String nombreClienteDto;


    private String apellidoClienteDto;

    private int dniClienteDto;




    public ClienteDto() {
    }

    public ClienteDto(Long idClienteDto, String nombreClienteDto, String apellidoClienteDto, int dniClienteDto) {
        this.idClienteDto = idClienteDto;
        this.nombreClienteDto = nombreClienteDto;
        this.apellidoClienteDto = apellidoClienteDto;
        this.dniClienteDto = dniClienteDto;
    }



    public Long getIdClienteDto() {
        return idClienteDto;
    }

    public void setIdClienteDto(Long idClienteDto) {
        this.idClienteDto = idClienteDto;
    }

    public String getNombreClienteDto() {
        return nombreClienteDto;
    }

    public void setNombreClienteDto(String nombreClienteDto) {
        this.nombreClienteDto = nombreClienteDto;
    }

    public String getApellidoClienteDto() {
        return apellidoClienteDto;
    }

    public void setApellidoClienteDto(String apellidoClienteDto) {
        this.apellidoClienteDto = apellidoClienteDto;
    }

    public int getDniClienteDto() {
        return dniClienteDto;
    }

    public void setDniClienteDto(int dniClienteDto) {
        this.dniClienteDto = dniClienteDto;
    }



}
