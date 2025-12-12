package com.konex.inventario.infrastructure.adapter.in.web.controller;

import com.konex.inventario.domain.port.in.MedicamentoUseCasePort;
import com.konex.inventario.infrastructure.adapter.in.web.dto.MedicamentoRequestDTO;
import com.konex.inventario.infrastructure.adapter.in.web.dto.MedicamentoResponseDTO;
import com.konex.inventario.infrastructure.adapter.in.web.mapper.MedicamentoDTOMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para Medicamentos
 */
@RestController
@RequestMapping("/api/medicamentos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MedicamentoController {

    private final MedicamentoUseCasePort useCasePort;
    private final MedicamentoDTOMapper mapper;

    @PostMapping
    public ResponseEntity<MedicamentoResponseDTO> crear(@Valid @RequestBody MedicamentoRequestDTO request) {
        var medicamento = mapper.toDomain(request);
        var created = useCasePort.crearMedicamento(medicamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponseDTO(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicamentoResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody MedicamentoRequestDTO request) {
        var medicamento = mapper.toDomain(request);
        var updated = useCasePort.actualizarMedicamento(id, medicamento);
        return ResponseEntity.ok(mapper.toResponseDTO(updated));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoResponseDTO> obtenerPorId(@PathVariable Long id) {
        var medicamento = useCasePort.obtenerMedicamentoPorId(id);
        return ResponseEntity.ok(mapper.toResponseDTO(medicamento));
    }

    @GetMapping
    public ResponseEntity<Page<MedicamentoResponseDTO>> listar(Pageable pageable) {
        var medicamentos = useCasePort.listarMedicamentos(pageable);
        var response = medicamentos.map(mapper::toResponseDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/filtrar")
    public ResponseEntity<Page<MedicamentoResponseDTO>> filtrar(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String laboratorio,
            Pageable pageable) {
        var medicamentos = useCasePort.filtrarMedicamentos(nombre, laboratorio, pageable);
        var response = medicamentos.map(mapper::toResponseDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        useCasePort.eliminarMedicamento(id);
        return ResponseEntity.noContent().build();
    }
}