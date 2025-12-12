package com.konex.inventario.domain.port.in;

import com.konex.inventario.domain.model.Medicamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Puerto de entrada para casos de uso de Medicamento
 */
public interface MedicamentoUseCasePort {

    Medicamento crearMedicamento(Medicamento medicamento);

    Medicamento actualizarMedicamento(Long id, Medicamento medicamento);

    Medicamento obtenerMedicamentoPorId(Long id);

    Page<Medicamento> listarMedicamentos(Pageable pageable);

    Page<Medicamento> filtrarMedicamentos(String nombre, String laboratorio, Pageable pageable);

    void eliminarMedicamento(Long id);
}
