package com.konex.inventario.infrastructure.adapter.out.persistence.repository;

import com.konex.inventario.infrastructure.adapter.out.persistence.entity.VentaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * Repositorio Spring Data JPA para Venta
 * Extiende JpaRepository para operaciones CRUD automáticas
 */
@Repository
public interface VentaJpaRepository extends JpaRepository<VentaEntity, Long> {

    /**
     * Busca ventas dentro de un rango de fechas
     * @param fechaInicio fecha y hora de inicio del rango
     * @param fechaFin fecha y hora de fin del rango
     * @param pageable configuración de paginación
     * @return página de ventas encontradas
     */
    @Query("SELECT v FROM VentaEntity v WHERE v.fechaHora BETWEEN :fechaInicio AND :fechaFin ORDER BY v.fechaHora DESC")
    Page<VentaEntity> findByRangoFechas(
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin,
            Pageable pageable
    );
}