package com.konex.inventario.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para Request de Venta
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VentaRequestDTO {

    @NotNull(message = "El ID del medicamento es obligatorio")
    private Long medicamentoId;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private Integer cantidad;
}