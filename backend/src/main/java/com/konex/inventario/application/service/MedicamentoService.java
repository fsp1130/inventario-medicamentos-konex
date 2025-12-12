package com.konex.inventario.application.service;

import com.konex.inventario.domain.model.Medicamento;
import com.konex.inventario.domain.port.in.MedicamentoUseCasePort;
import com.konex.inventario.domain.port.out.MedicamentoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de los casos de uso de Medicamento
 * Contiene la lógica de negocio
 */
@Service
@RequiredArgsConstructor
@Transactional
public class MedicamentoService implements MedicamentoUseCasePort {

    private final MedicamentoRepositoryPort repositoryPort;

    @Override
    public Medicamento crearMedicamento(Medicamento medicamento) {
        validarMedicamento(medicamento);
        return repositoryPort.save(medicamento);
    }

    @Override
    public Medicamento actualizarMedicamento(Long id, Medicamento medicamento) {
        if (!repositoryPort.existsById(id)) {
            throw new IllegalArgumentException("Medicamento no encontrado con ID: " + id);
        }
        validarMedicamento(medicamento);
        medicamento.setId(id);
        return repositoryPort.save(medicamento);
    }

    @Override
    @Transactional(readOnly = true)
    public Medicamento obtenerMedicamentoPorId(Long id) {
        return repositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Medicamento no encontrado con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Medicamento> listarMedicamentos(Pageable pageable) {
        return repositoryPort.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Medicamento> filtrarMedicamentos(String nombre, String laboratorio, Pageable pageable) {
        return repositoryPort.findByFiltros(nombre, laboratorio, pageable);
    }

    @Override
    public void eliminarMedicamento(Long id) {
        if (!repositoryPort.existsById(id)) {
            throw new IllegalArgumentException("Medicamento no encontrado con ID: " + id);
        }
        repositoryPort.deleteById(id);
    }

    private void validarMedicamento(Medicamento medicamento) {
        if (medicamento.getNombre() == null || medicamento.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del medicamento es obligatorio");
        }

        if (medicamento.getLaboratorio() == null || medicamento.getLaboratorio().trim().isEmpty()) {
            throw new IllegalArgumentException("El laboratorio es obligatorio");
        }

        if (!medicamento.fechasValidas()) {
            throw new IllegalArgumentException("La fecha de vencimiento debe ser posterior a la fecha de fabricación");
        }

        if (medicamento.getCantidadStock() == null || medicamento.getCantidadStock() < 0) {
            throw new IllegalArgumentException("La cantidad en stock debe ser mayor o igual a cero");
        }

        if (medicamento.getValorUnitario() == null || medicamento.getValorUnitario().signum() <= 0) {
            throw new IllegalArgumentException("El valor unitario debe ser mayor a cero");
        }
    }
}