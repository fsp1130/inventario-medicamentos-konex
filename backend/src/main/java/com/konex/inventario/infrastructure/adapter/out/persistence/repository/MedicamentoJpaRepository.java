package com.konex.inventario.infrastructure.adapter.out.persistence.repository;

import com.konex.inventario.infrastructure.adapter.out.persistence.entity.MedicamentoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositorio Spring Data JPA para Medicamento
 * Extiende JpaRepository para operaciones CRUD automáticas
 */
@Repository
public interface MedicamentoJpaRepository extends JpaRepository<MedicamentoEntity, Long> {

    /**
     * Busca medicamentos por nombre y/o laboratorio usando LIKE
     * Los parámetros pueden ser null para no filtrar por ese campo
     */
    @Query("SELECT m FROM MedicamentoEntity m WHERE " +
            "(:nombre IS NULL OR LOWER(m.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) AND " +
            "(:laboratorio IS NULL OR LOWER(m.laboratorio) LIKE LOWER(CONCAT('%', :laboratorio, '%')))")
    Page<MedicamentoEntity> findByFiltros(
            @Param("nombre") String nombre,
            @Param("laboratorio") String laboratorio,
            Pageable pageable
    );
}
