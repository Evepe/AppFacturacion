package com.pereyra.appFacturacion.repository;

import com.pereyra.appFacturacion.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRespository extends JpaRepository<Cliente, Long> {

    /**
     * Encuentra un cliente por su DNI.
     *
     * @param dniCliente El DNI del cliente a buscar.
     * @return Un cliente opcional si se encuentra, de lo contrario, vacío.
     */
    Optional<Cliente> findByDniCliente(int dniCliente);
}
