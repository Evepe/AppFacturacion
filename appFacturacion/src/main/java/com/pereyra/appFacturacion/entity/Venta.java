package com.pereyra.appFacturacion.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

    /**
     * Representa un venta en el sistema
     */

    @Entity
    @Data //Genera getters & setters de cada atributo
    @NoArgsConstructor //Genera constructor vacio requerido por JPA
    @AllArgsConstructor //Genera constructor con totalidad de atributos
    @Table(name="ventas") //Genera tabla venta en BD

    public class Venta {

        //Atributos

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idVenta;

        @Column(name="fecha_creacion")
        @NotNull
        private String fechaHoracreacion;

        @Column (name="precio_total")
        private BigDecimal totalVenta;

        @Column(name = "completa")
        private boolean completa;

        @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE})
        @JoinColumn(name="Cliente")
        private Cliente cliente;

        @ManyToMany(cascade = {CascadeType.PERSIST})
        @JoinTable(
                name= "Ventas_Productos",
                joinColumns = @JoinColumn(name = "idProducto"),
                inverseJoinColumns = @JoinColumn(name="idVenta")
        )
        private List<Producto> productoList;

        /**
         * MarcarCompleta registra las ventas exitosas.
         */
        public void marcarCompleta() {
            this.completa = true;
        }

        /**
         * Verifica si la venta está completa.
         *
         * @return true si la venta está completa, false de lo contrario.
         */
        public boolean isCompleta() {
            return completa;
        }





    }


