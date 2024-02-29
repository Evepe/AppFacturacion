package com.pereyra.appFacturacion.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

    /**
     * Representa un venta en el sistema
     */

    @Entity
    @Data
    @Table(name = "ventas")
    public class Venta {
        @Serial
        private static final long serialVersionUID = 1L;


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idVenta;

        @Column(name = "fecha_creacion")
        @NotNull
        private String fechaHoracreacion;


        @ManyToOne
        @JoinColumn(name = "id_cliente")
        private Cliente cliente;


        @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
        private List<VentaDetalle> ventaDetalles;


        @Column(name = "total_venta")
        private double totalVenta;

        @Getter
        @Column(name = "completa")
        private boolean completa;

        public Venta() {
            this.ventaDetalles = new ArrayList<>();
        }


        public Venta(Long idVenta, String fechaHoracreacion, Cliente cliente, List<VentaDetalle> ventaDetalles, double totalVenta, boolean completa) {
            this.idVenta = idVenta;
            this.fechaHoracreacion = fechaHoracreacion;
            this.cliente = cliente;
            this.ventaDetalles = ventaDetalles;
            this.totalVenta = totalVenta;
            this.completa = completa;
        }

        public void marcarCompleta() {
            this.completa = true;
        }



    }


