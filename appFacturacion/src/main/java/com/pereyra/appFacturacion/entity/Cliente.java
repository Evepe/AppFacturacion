package com.pereyra.appFacturacion.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Representa un cliente en el sistema.
 */
@Entity
@Getter
@Setter
@Table(name="clientes", //Genera tabla y se asigna nombre Cliente
        uniqueConstraints = @UniqueConstraint(columnNames = "DNI")
)
@Builder
public class Cliente  {



    //Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID del cliente", example = "1")
    private Long idCliente;

    @Column (name="nombre")
    @NotNull
    @NotEmpty
@NotBlank
    @Schema(description = "Nombre del cliente", example = "Maria")
    private String nombreCliente;

    @Column (name="apellido")
    @NotNull
    @Schema(description = "Apellido del cliente", example = "Perez")
    private String apellidoCliente;

    @Column (name="DNI")
    @NotNull
    @Schema(description = "DNI del cliente. No se puede repetir", example = "32658951")
    //@Digits(integer=8, fraction = 0, message = "El DNI debe wer un numero de hasta 8 digitos")
    //@Size(min=7, max = 8, message = "El DNI debe contener entre 7 y 8 digitos")
    private int dniCliente;

    @Column (name= "correo_electronico")
    @JsonProperty("correo_electronico")
    @Email(message = "El formato de correo electronico es invalido.")
    @Schema(description = "Correo electronico del cliente", example="example@example.com")
    private String eMail;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Schema(hidden = true)
    private List<Venta> ventas;

    public Cliente() {
        this.ventas=new ArrayList<>();
    }

    public Cliente(Long idCliente, String nombreCliente, String apellidoCliente, int dniCliente, String eMail, List<Venta> ventas) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.dniCliente = dniCliente;
        this.eMail = eMail;
        this.ventas = ventas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return dniCliente == cliente.dniCliente && Objects.equals(idCliente, cliente.idCliente) && Objects.equals(nombreCliente, cliente.nombreCliente) && Objects.equals(apellidoCliente, cliente.apellidoCliente) && Objects.equals(eMail, cliente.eMail) && Objects.equals(ventas, cliente.ventas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCliente, nombreCliente, apellidoCliente, dniCliente, eMail, ventas);
    }
}
