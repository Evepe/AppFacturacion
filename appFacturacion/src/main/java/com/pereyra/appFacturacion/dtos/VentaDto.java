package com.pereyra.appFacturacion.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaDto {

    private Long idVentaDto;


    private String fechaCreacionVentaDto;


    private BigDecimal precioTotal;





}
