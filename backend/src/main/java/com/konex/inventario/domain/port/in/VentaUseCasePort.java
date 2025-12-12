package com.konex.inventario.domain.port.in;

import com.konex.inventario.domain.model.Venta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

/**
 * Puerto de entrada para casos de uso de Venta
 */
public interface VentaUseCasePort {

    Venta realizarVenta(Long medicamentoId, Integer cantidad);

    Page<Venta> listarVentas(Pageable pageable);

    Page<Venta> filtrarVentasPorFechas(LocalDateTime fechaInicio,
                                       LocalDateTime fechaFin,
                                       Pageable pageable);

    Venta obtenerVentaPorId(Long id);
}