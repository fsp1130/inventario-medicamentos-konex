package com.konex.inventario.infrastructure.adapter.out.persistence.mapper;

import com.konex.inventario.domain.model.Medicamento;
import com.konex.inventario.infrastructure.adapter.out.persistence.entity.MedicamentoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * Mapper para convertir entre Medicamento (dominio) y MedicamentoEntity (persistencia)
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MedicamentoMapper {

    /**
     * Convierte de entidad de dominio a entidad JPA
     */
    MedicamentoEntity toEntity(Medicamento medicamento);

    /**
     * Convierte de entidad JPA a entidad de dominio
     */
    Medicamento toDomain(MedicamentoEntity entity);
}