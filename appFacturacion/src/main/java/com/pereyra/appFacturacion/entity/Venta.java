package com.pereyra.appFacturacion.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
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

        @Column
        private BigDecimal precioTotal;

        @Column (name="total_venta")
        private BigDecimal totalVenta;

        /**
         * -- GETTER --
         *  Verifica si la venta está completa.
         *
         * @return true si la venta está completa, false de lo contrario.
         */
        @Getter
        @Column(name = "completa")
        private boolean completa;

        @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE})
        @JoinColumn(name="Cliente")
        private Cliente cliente;

        @ManyToOne
        @JoinColumn(name = "idProducto")
        private Producto producto;

        @OneToMany(cascade = CascadeType.ALL)
        private List <ProductoVersion> Versiones;


        /**
         * MarcarCompleta registra las ventas exitosas.
         */
        public void marcarCompleta() {
            this.completa = true;
        }


    }


