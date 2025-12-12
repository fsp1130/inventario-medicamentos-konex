package com.konex.inventario.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entidad de dominio Medicamento
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Medicamento {

    private Long id;
    private String nombre;
    private String laboratorio;
    private LocalDate fechaFabricacion;
    private LocalDate fechaVencimiento;
    private Integer cantidadStock;
    private BigDecimal valorUnitario;

    /**
     * Valida si el medicamento está vencido
     */

    public boolean estaVencido() {
        return fechaVencimiento != null && fechaVencimiento.isBefore(LocalDate.now());
    }

    /**
     * Valida si hay stock disponible
     */

    public boolean tieneStock(Integer cantidadSolicitada) {
        return cantidadStock != null && cantidadStock >= cantidadSolicitada;
    }

    /**
     * Reduce el stock después de una venta
     */
    public void reducirStock(Integer cantidad) {
        if (!tieneStock(cantidad)) {
            throw new IllegalArgumentException("Stock insuficiente");
        }
        this.cantidadStock -= cantidad;
    }

    /**
     * Calcula el valor total para una cantidad
     */
    public BigDecimal calcularValorTotal(Integer cantidad) {
        if (valorUnitario == null || cantidad == null) {
            return BigDecimal.ZERO;
        }
        return valorUnitario.multiply(BigDecimal.valueOf(cantidad));
    }

    /**
     * Valida que las fechas sean coherentes
     */
    public boolean fechasValidas() {
        if (fechaFabricacion == null || fechaVencimiento == null) {
            return false;
        }
        return fechaVencimiento.isAfter(fechaFabricacion);
    }
}