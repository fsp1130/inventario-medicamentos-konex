package com.konex.inventario.infrastructure.adapter.out.persistence;

import com.konex.inventario.domain.model.Medicamento;
import com.konex.inventario.domain.port.out.MedicamentoRepositoryPort;
import com.konex.inventario.infrastructure.adapter.out.persistence.mapper.MedicamentoMapper;
import com.konex.inventario.infrastructure.adapter.out.persistence.repository.MedicamentoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Adaptador que implementa el puerto de persistencia de Medicamento
 */
@Component
@RequiredArgsConstructor
public class MedicamentoRepositoryAdapter implements MedicamentoRepositoryPort {

    private final MedicamentoJpaRepository jpaRepository;
    private final MedicamentoMapper mapper;

    @Override
    public Medicamento save(Medicamento medicamento) {
        var entity = mapper.toEntity(medicamento);
        var savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Medicamento> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Page<Medicamento> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Page<Medicamento> findByFiltros(String nombre, String laboratorio, Pageable pageable) {
        return jpaRepository.findByFiltros(nombre, laboratorio, pageable)
                .map(mapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }
}