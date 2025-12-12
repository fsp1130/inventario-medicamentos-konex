
// VentaRepositoryAdapter.java
package com.konex.inventario.infrastructure.adapter.out.persistence;

import com.konex.inventario.domain.model.Venta;
import com.konex.inventario.domain.port.out.VentaRepositoryPort;
import com.konex.inventario.infrastructure.adapter.out.persistence.mapper.VentaMapper;
import com.konex.inventario.infrastructure.adapter.out.persistence.repository.VentaJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Adaptador que implementa el puerto de persistencia de Venta
 */
@Component
@RequiredArgsConstructor
public class VentaRepositoryAdapter implements VentaRepositoryPort {

    private final VentaJpaRepository jpaRepository;
    private final VentaMapper mapper;

    @Override
    public Venta save(Venta venta) {
        var entity = mapper.toEntity(venta);
        var savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Venta> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Page<Venta> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Page<Venta> findByRangoFechas(LocalDateTime fechaInicio,
                                         LocalDateTime fechaFin,
                                         Pageable pageable) {
        return jpaRepository.findByRangoFechas(fechaInicio, fechaFin, pageable)
                .map(mapper::toDomain);
    }
}