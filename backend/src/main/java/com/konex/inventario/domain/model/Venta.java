package com.konex.inventario.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entidad de dominio Venta
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Venta {

    private Long id;
    private LocalDateTime fechaHora;
    private Long medicamentoId;
    private String nombreMedicamento;
    private Integer cantidad;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;

    /**
     * Crea una venta a partir de un medicamento
     */
    public static Venta crearVenta(Medicamento medicamento, Integer cantidad) {
        return Venta.builder()
                .fechaHora(LocalDateTime.now())
                .medicamentoId(medicamento.getId())
                .nombreMedicamento(medicamento.getNombre())
                .cantidad(cantidad)
                .valorUnitario(medicamento.getValorUnitario())
                .valorTotal(medicamento.calcularValorTotal(cantidad))
                .build();
    }
}