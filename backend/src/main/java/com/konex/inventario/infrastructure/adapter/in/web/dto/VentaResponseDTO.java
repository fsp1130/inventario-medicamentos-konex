package com.konex.inventario.infrastructure.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO para Response de Venta
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VentaResponseDTO {
    private Long id;
    private LocalDateTime fechaHora;
    private Long medicamentoId;
    private String nombreMedicamento;
    private Integer cantidad;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;
}