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
import java.util.List;

/**
 * Representa un cliente en el sistema.
 */
@Entity
@Data //Genera getters & setters
@NoArgsConstructor //Genera constructor sin argumento requerido por JPA
@AllArgsConstructor //Genera constructor con totalidad de argumento
@Table(name="clientes", //Genera tabla y se asigna nombre Cliente
        uniqueConstraints = @UniqueConstraint(columnNames = "DNI")
)
@Builder
public class Cliente {

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


    @OneToMany( mappedBy = "cliente",
            cascade = CascadeType.ALL
    )
    @JsonIgnore
    private List<Venta> ventas;






}
