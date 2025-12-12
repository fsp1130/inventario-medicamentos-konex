package com.konex.inventario.infrastructure.adapter.in.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO para Request de Medicamento
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicamentoRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 200, message = "El nombre no puede exceder 200 caracteres")
    private String nombre;

    @NotBlank(message = "El laboratorio es obligatorio")
    @Size(max = 200, message = "El laboratorio no puede exceder 200 caracteres")
    private String laboratorio;

    @NotNull(message = "La fecha de fabricación es obligatoria")
    @PastOrPresent(message = "La fecha de fabricación debe ser pasada o presente")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaFabricacion;

    @NotNull(message = "La fecha de vencimiento es obligatoria")
    @Future(message = "La fecha de vencimiento debe ser futura")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaVencimiento;

    @NotNull(message = "La cantidad en stock es obligatoria")
    @Min(value = 0, message = "La cantidad en stock debe ser mayor o igual a cero")
    private Integer cantidadStock;

    @NotNull(message = "El valor unitario es obligatorio")
    @DecimalMin(value = "0.01", message = "El valor unitario debe ser mayor a cero")
    private BigDecimal valorUnitario;
}