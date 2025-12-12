package com.konex.inventario.domain.port.out;

import com.konex.inventario.domain.model.Medicamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Puerto de salida para persistencia de Medicamentos
 * Define el contrato que debe implementar el adaptador de persistencia
 */
public interface MedicamentoRepositoryPort {

    Medicamento save(Medicamento medicamento);

    Optional<Medicamento> findById(Long id);

    Page<Medicamento> findAll(Pageable pageable);

    Page<Medicamento> findByFiltros(String nombre, String laboratorio, Pageable pageable);

    void deleteById(Long id);

    boolean existsById(Long id);
}