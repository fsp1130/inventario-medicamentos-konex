package com.konex.inventario.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entidad JPA para Venta
 */
@Entity
@Table(name = "ventas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "medicamento_id", nullable = false)
    private Long medicamentoId;

    @Column(name = "nombre_medicamento", nullable = false, length = 200)
    private String nombreMedicamento;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "valor_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorUnitario;

    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;
}