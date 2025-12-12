package com.konex.inventario;

import com.konex.inventario.application.service.VentaService;
import com.konex.inventario.domain.model.Medicamento;
import com.konex.inventario.domain.model.Venta;
import com.konex.inventario.domain.port.out.MedicamentoRepositoryPort;
import com.konex.inventario.domain.port.out.VentaRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VentaServiceTest {

    @Mock
    private VentaRepositoryPort ventaRepositoryPort;

    @Mock
    private MedicamentoRepositoryPort medicamentoRepositoryPort;

    @InjectMocks
    private VentaService service;

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
    void realizarVenta_DeberiaCrearVentaYActualizarStock() {
        when(medicamentoRepositoryPort.findById(1L)).thenReturn(Optional.of(medicamento));
        when(medicamentoRepositoryPort.save(any())).thenReturn(medicamento);
        when(ventaRepositoryPort.save(any())).thenAnswer(i -> i.getArgument(0));

        Venta resultado = service.realizarVenta(1L, 10);

        assertNotNull(resultado);
        assertEquals(10, resultado.getCantidad());
        assertEquals(90, medicamento.getCantidadStock());
        verify(ventaRepositoryPort, times(1)).save(any());
    }

    @Test
    void realizarVenta_StockInsuficiente_DeberiaLanzarExcepcion() {
        when(medicamentoRepositoryPort.findById(1L)).thenReturn(Optional.of(medicamento));

        assertThrows(IllegalArgumentException.class,
                () -> service.realizarVenta(1L, 150));
    }

    @Test
    void realizarVenta_MedicamentoVencido_DeberiaLanzarExcepcion() {
        medicamento.setFechaVencimiento(LocalDate.now().minusDays(1));
        when(medicamentoRepositoryPort.findById(1L)).thenReturn(Optional.of(medicamento));

        assertThrows(IllegalArgumentException.class,
                () -> service.realizarVenta(1L, 10));
    }

    @Test
    void realizarVenta_CantidadInvalida_DeberiaLanzarExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> service.realizarVenta(1L, 0));
        assertThrows(IllegalArgumentException.class,
                () -> service.realizarVenta(1L, -5));
    }
}