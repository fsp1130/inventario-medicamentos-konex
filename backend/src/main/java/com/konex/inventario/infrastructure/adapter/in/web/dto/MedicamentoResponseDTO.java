package com.konex.inventario.infrastructure.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO para Response de Medicamento
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicamentoResponseDTO {
    private Long id;
    private String nombre;
    private String laboratorio;
    private LocalDate fechaFabricacion;
    private LocalDate fechaVencimiento;
    private Integer cantidadStock;
    private BigDecimal valorUnitario;
    private boolean vencido;
}