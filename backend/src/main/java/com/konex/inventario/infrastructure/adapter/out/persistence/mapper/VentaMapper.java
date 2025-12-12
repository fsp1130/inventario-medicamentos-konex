package com.konex.inventario.infrastructure.adapter.out.persistence.mapper;

import com.konex.inventario.domain.model.Venta;
import com.konex.inventario.infrastructure.adapter.out.persistence.entity.VentaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * Mapper para convertir entre Venta (dominio) y VentaEntity (persistencia)
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface VentaMapper {

    /**
     * Convierte de entidad de dominio a entidad JPA
     */
    VentaEntity toEntity(Venta venta);

    /**
     * Convierte de entidad JPA a entidad de dominio
     */
    Venta toDomain(VentaEntity entity);
}