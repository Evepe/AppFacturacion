package com.pereyra.appFacturacion.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaRequestDto {
    private ClienteDto cliente;
    private List<VersionVentaRequestDto> versiones;
}
