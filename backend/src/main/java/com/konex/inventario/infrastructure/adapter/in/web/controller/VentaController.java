package com.konex.inventario.infrastructure.adapter.in.web.controller;

import com.konex.inventario.domain.port.in.VentaUseCasePort;
import com.konex.inventario.infrastructure.adapter.in.web.dto.VentaRequestDTO;
import com.konex.inventario.infrastructure.adapter.in.web.dto.VentaResponseDTO;
import com.konex.inventario.infrastructure.adapter.in.web.mapper.VentaDTOMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Controlador REST para Ventas
 */
@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VentaController {

    private final VentaUseCasePort useCasePort;
    private final VentaDTOMapper mapper;

    @PostMapping
    public ResponseEntity<VentaResponseDTO> realizar(@Valid @RequestBody VentaRequestDTO request) {
        var venta = useCasePort.realizarVenta(request.getMedicamentoId(), request.getCantidad());
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponseDTO(venta));
    }

    @GetMapping
    public ResponseEntity<Page<VentaResponseDTO>> listar(Pageable pageable) {
        var ventas = useCasePort.listarVentas(pageable);
        var response = ventas.map(mapper::toResponseDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/filtrar")
    public ResponseEntity<Page<VentaResponseDTO>> filtrarPorFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin,
            Pageable pageable) {
        var ventas = useCasePort.filtrarVentasPorFechas(fechaInicio, fechaFin, pageable);
        var response = ventas.map(mapper::toResponseDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaResponseDTO> obtenerPorId(@PathVariable Long id) {
        var venta = useCasePort.obtenerVentaPorId(id);
        return ResponseEntity.ok(mapper.toResponseDTO(venta));
    }
}