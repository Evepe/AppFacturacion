package com.pereyra.appFacturacion.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa un cliente en el sistema.
 */
@Entity
@Data //Genera getters & setters
@Table(name="clientes", //Genera tabla y se asigna nombre Cliente
        uniqueConstraints = @UniqueConstraint(columnNames = "DNI")
)
@Builder
public class Cliente {

    @Serial
    private static final long serialVersionUID = 1L;

    //Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Column (name="nombre")
    @NotNull
    private String nombreCliente;

    @Column (name="apellido")
    @NotNull
    private String apellidoCliente;

    @Column (name="DNI")
    @NotNull
    //@Digits(integer=8, fraction = 0, message = "El DNI debe wer un numero de hasta 8 digitos")
    //@Size(min=7, max = 8, message = "El DNI debe contener entre 7 y 8 digitos")
    private int dniCliente;

    @Column (name= "correo_electronico")
    @JsonProperty("correo_electronico")
    @Email (message = "El formato de correo electronico es invalido.")
    private String eMail;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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
}
