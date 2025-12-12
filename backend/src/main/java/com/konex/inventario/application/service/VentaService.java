package com.konex.inventario.application.service;

import com.konex.inventario.domain.model.Medicamento;
import com.konex.inventario.domain.model.Venta;
import com.konex.inventario.domain.port.in.VentaUseCasePort;
import com.konex.inventario.domain.port.out.MedicamentoRepositoryPort;
import com.konex.inventario.domain.port.out.VentaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Implementación de los casos de uso de Venta
 * Contiene la lógica de negocio para procesar ventas
 */
@Service
@RequiredArgsConstructor
@Transactional
public class VentaService implements VentaUseCasePort {

    private final VentaRepositoryPort ventaRepositoryPort;
    private final MedicamentoRepositoryPort medicamentoRepositoryPort;

    @Override
    public Venta realizarVenta(Long medicamentoId, Integer cantidad) {
        // Validar cantidad
        if (cantidad == null || cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }

        // Obtener medicamento
        Medicamento medicamento = medicamentoRepositoryPort.findById(medicamentoId)
                .orElseThrow(() -> new IllegalArgumentException("Medicamento no encontrado"));

        // Validar que no esté vencido
        if (medicamento.estaVencido()) {
            throw new IllegalArgumentException("No se puede vender un medicamento vencido");
        }

        // Validar stock disponible
        if (!medicamento.tieneStock(cantidad)) {
            throw new IllegalArgumentException("Stock insuficiente. Disponible: " + medicamento.getCantidadStock());
        }

        // Reducir stock
        medicamento.reducirStock(cantidad);
        medicamentoRepositoryPort.save(medicamento);

        // Crear y registrar venta
        Venta venta = Venta.crearVenta(medicamento, cantidad);
        return ventaRepositoryPort.save(venta);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Venta> listarVentas(Pageable pageable) {
        return ventaRepositoryPort.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Venta> filtrarVentasPorFechas(LocalDateTime fechaInicio,
                                              LocalDateTime fechaFin,
                                              Pageable pageable) {
        if (fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException("Las fechas son obligatorias");
        }

        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha fin debe ser posterior a la fecha inicio");
        }

        return ventaRepositoryPort.findByRangoFechas(fechaInicio, fechaFin, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Venta obtenerVentaPorId(Long id) {
        return ventaRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Venta no encontrada con ID: " + id));
    }
}