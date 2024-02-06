package com.pereyra.appFacturacion.repository;

import com.pereyra.appFacturacion.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRespository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByDniCliente(int dniCliente);
}
