package com.konex.inventario.infrastructure.adapter.in.web.mapper;

import com.konex.inventario.domain.model.Venta;
import com.konex.inventario.infrastructure.adapter.in.web.dto.VentaResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * Mapper para convertir entre Venta (dominio) y DTOs web
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface VentaDTOMapper {

    /**
     * Convierte de entidad de dominio a DTO de response
     */
    VentaResponseDTO toResponseDTO(Venta venta);
}
