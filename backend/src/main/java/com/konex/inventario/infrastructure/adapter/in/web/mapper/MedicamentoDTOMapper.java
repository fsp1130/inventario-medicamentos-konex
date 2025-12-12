package com.konex.inventario.infrastructure.adapter.in.web.mapper;

import com.konex.inventario.domain.model.Medicamento;
import com.konex.inventario.infrastructure.adapter.in.web.dto.MedicamentoRequestDTO;
import com.konex.inventario.infrastructure.adapter.in.web.dto.MedicamentoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

/**
 * Mapper para convertir entre Medicamento (dominio) y DTOs web
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MedicamentoDTOMapper {

    /**
     * Convierte de DTO de request a entidad de dominio
     */
    Medicamento toDomain(MedicamentoRequestDTO dto);

    /**
     * Convierte de entidad de dominio a DTO de response
     * Incluye el cálculo del campo 'vencido'
     */
    @Mapping(target = "vencido", expression = "java(medicamento.estaVencido())")
    MedicamentoResponseDTO toResponseDTO(Medicamento medicamento);
}