package com.konex.inventario.domain.port.out;

import com.konex.inventario.domain.model.Venta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Puerto de salida para persistencia de Ventas
 */
public interface VentaRepositoryPort {

    Venta save(Venta venta);

    Optional<Venta> findById(Long id);

    Page<Venta> findAll(Pageable pageable);

    Page<Venta> findByRangoFechas(LocalDateTime fechaInicio,
                                  LocalDateTime fechaFin,
                                  Pageable pageable);
}