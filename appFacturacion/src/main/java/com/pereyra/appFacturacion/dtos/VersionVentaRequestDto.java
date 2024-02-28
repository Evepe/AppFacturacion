package com.pereyra.appFacturacion.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VersionVentaRequestDto {
    private ProductoDto producto;
    private int cantidad;
}
