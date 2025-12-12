package com.konex.inventario;

import com.konex.inventario.application.service.MedicamentoService;
import com.konex.inventario.domain.model.Medicamento;
import com.konex.inventario.domain.port.out.MedicamentoRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicamentoServiceTest {

    @Mock
    private MedicamentoRepositoryPort repositoryPort;

    @InjectMocks
    private MedicamentoService service;

    private Medicamento medicamento;

    @BeforeEach
    void setUp() {
        medicamento = Medicamento.builder()
                .id(1L)
                .nombre("Ibuprofeno")
                .laboratorio("Bayer")
                .fechaFabricacion(LocalDate.now().minusMonths(6))
                .fechaVencimiento(LocalDate.now().plusYears(2))
                .cantidadStock(100)
                .valorUnitario(new BigDecimal("5000.00"))
                .build();
    }

    @Test
    void crearMedicamento_DeberiaCrearExitosamente() {
        when(repositoryPort.save(any(Medicamento.class))).thenReturn(medicamento);

        Medicamento resultado = service.crearMedicamento(medicamento);

        assertNotNull(resultado);
        assertEquals("Ibuprofeno", resultado.getNombre());
        verify(repositoryPort, times(1)).save(any(Medicamento.class));
    }

    @Test
    void crearMedicamento_ConNombreVacio_DeberiaLanzarExcepcion() {
        medicamento.setNombre("");

        assertThrows(IllegalArgumentException.class,
                () -> service.crearMedicamento(medicamento));
        verify(repositoryPort, never()).save(any());
    }

    @Test
    void crearMedicamento_ConFechasInvalidas_DeberiaLanzarExcepcion() {
        medicamento.setFechaVencimiento(LocalDate.now().minusYears(1));

        assertThrows(IllegalArgumentException.class,
                () -> service.crearMedicamento(medicamento));
    }

    @Test
    void obtenerMedicamentoPorId_DeberiaRetornarMedicamento() {
        when(repositoryPort.findById(1L)).thenReturn(Optional.of(medicamento));

        Medicamento resultado = service.obtenerMedicamentoPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void obtenerMedicamentoPorId_NoExistente_DeberiaLanzarExcepcion() {
        when(repositoryPort.findById(999L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class,
                () -> service.obtenerMedicamentoPorId(999L));
    }

    @Test
    void listarMedicamentos_DeberiaRetornarPaginaDeResultados() {
        Page<Medicamento> page = new PageImpl<>(List.of(medicamento));
        when(repositoryPort.findAll(any(PageRequest.class))).thenReturn(page);

        Page<Medicamento> resultado = service.listarMedicamentos(PageRequest.of(0, 10));

        assertNotNull(resultado);
        assertEquals(1, resultado.getTotalElements());
    }

    @Test
    void eliminarMedicamento_Existente_DeberiaEliminar() {
        when(repositoryPort.existsById(1L)).thenReturn(true);

        assertDoesNotThrow(() -> service.eliminarMedicamento(1L));
        verify(repositoryPort, times(1)).deleteById(1L);
    }
}