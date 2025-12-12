import { Component, OnInit } from '@angular/core';
import { MessageService, ConfirmationService } from 'primeng/api';
import { MedicamentoService } from '../../services/medicamento.service';
import { VentaService } from '../../../ventas/services/venta.service';
import { Medicamento, MedicamentoRequestDTO } from '../../../../core/models/medicamento.model';
import { VentaRequest } from '../../../../core/models/venta.model';

@Component({
  selector: 'app-medicamentos-list',
  templateUrl: './medicamentos-list.component.html',
  styleUrls: ['./medicamentos-list.component.scss']
})
export class MedicamentosListComponent implements OnInit {
  medicamentos: Medicamento[] = [];
  totalRecords = 0;
  loading = false;
  
  // Filtros
  nombreFiltro = '';
  laboratorioFiltro = '';
  
  // Paginación
  first = 0;
  rows = 10;
  
  // Modal de medicamento
  displayDialog = false;
  medicamentoSeleccionado: Medicamento | null = null;
  esNuevo = false;
  
  // Modal de venta
  displayVentaDialog = false;
  medicamentoParaVenta: Medicamento | null = null;
  cantidadVenta = 1;
  valorTotalVenta = 0;

  // Propiedades para fechas
  maxDate: Date = new Date();
  minDate: Date = new Date();

  constructor(
    private medicamentoService: MedicamentoService,
    private ventaService: VentaService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService
  ) {}

  ngOnInit(): void {
    this.cargarMedicamentos();
  }

  cargarMedicamentos(event?: any): void {
    this.loading = true;
    const page = event ? event.first / event.rows : 0;
    const size = event ? event.rows : this.rows;

    if (this.nombreFiltro || this.laboratorioFiltro) {
      this.medicamentoService.filtrar(this.nombreFiltro, this.laboratorioFiltro, page, size)
        .subscribe({
          next: (data) => {
            this.medicamentos = data.content;
            this.totalRecords = data.totalElements;
            this.loading = false;
          },
          error: () => {
            this.mostrarError('Error al cargar medicamentos');
            this.loading = false;
          }
        });
    } else {
      this.medicamentoService.listar(page, size).subscribe({
        next: (data) => {
          this.medicamentos = data.content;
          this.totalRecords = data.totalElements;
          this.loading = false;
        },
        error: () => {
          this.mostrarError('Error al cargar medicamentos');
          this.loading = false;
        }
      });
    }
  }

  aplicarFiltros(): void {
    this.first = 0;
    this.cargarMedicamentos();
  }

  limpiarFiltros(): void {
    this.nombreFiltro = '';
    this.laboratorioFiltro = '';
    this.aplicarFiltros();
  }

  nuevoMedicamento(): void {
    this.medicamentoSeleccionado = {
      nombre: '',
      laboratorio: '',
      fechaFabricacion: '',
      fechaVencimiento: '',
      cantidadStock: 0,
      valorUnitario: 0
    };
    this.esNuevo = true;
    this.displayDialog = true;
  }

  editarMedicamento(medicamento: Medicamento): void {
    this.medicamentoSeleccionado = { ...medicamento };
    this.esNuevo = false;
    this.displayDialog = true;
  }

  guardarMedicamento(): void {
    if (!this.medicamentoSeleccionado) return;

    const medicamentoRequest: MedicamentoRequestDTO = {
      ...this.medicamentoSeleccionado,
      fechaFabricacion: new Date(this.medicamentoSeleccionado.fechaFabricacion).toISOString().split('T')[0],
      fechaVencimiento: new Date(this.medicamentoSeleccionado.fechaVencimiento).toISOString().split('T')[0]
    };

    if (this.esNuevo) {
      this.medicamentoService.crear(medicamentoRequest).subscribe({
        next: () => {
          this.mostrarExito('Medicamento creado exitosamente');
          this.displayDialog = false;
          this.cargarMedicamentos();
        },
        error: (error) => {
          this.mostrarError(error.error?.message || 'Error al crear medicamento');
        }
      });
    } else {
      this.medicamentoService.actualizar(this.medicamentoSeleccionado.id!, medicamentoRequest)
        .subscribe({
          next: () => {
            this.mostrarExito('Medicamento actualizado exitosamente');
            this.displayDialog = false;
            this.cargarMedicamentos();
          },
          error: (error) => {
            this.mostrarError(error.error?.message || 'Error al actualizar medicamento');
          }
        });
    }
  }

  eliminarMedicamento(medicamento: Medicamento): void {
    this.confirmationService.confirm({
      message: `¿Está seguro que desea eliminar ${medicamento.nombre}?`,
      header: 'Confirmar eliminación',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.medicamentoService.eliminar(medicamento.id!).subscribe({
          next: () => {
            this.mostrarExito('Medicamento eliminado exitosamente');
            this.cargarMedicamentos();
          },
          error: (error) => {
            this.mostrarError(error.error?.message || 'Error al eliminar medicamento');
          }
        });
      }
    });
  }

  abrirModalVenta(medicamento: Medicamento): void {
    if (medicamento.vencido) {
      this.mostrarError('No se puede vender un medicamento vencido');
      return;
    }
    if (medicamento.cantidadStock === 0) {
      this.mostrarError('No hay stock disponible');
      return;
    }

    this.medicamentoParaVenta = medicamento;
    this.cantidadVenta = 1;
    this.calcularValorTotal();
    this.displayVentaDialog = true;
  }

  calcularValorTotal(): void {
    if (this.medicamentoParaVenta) {
      this.valorTotalVenta = this.medicamentoParaVenta.valorUnitario * this.cantidadVenta;
    }
  }

  confirmarVenta(): void {
    if (!this.medicamentoParaVenta) return;

    if (this.cantidadVenta > this.medicamentoParaVenta.cantidadStock) {
      this.mostrarError('Cantidad solicitada supera el stock disponible');
      return;
    }

    const ventaRequest: VentaRequest = {
      medicamentoId: this.medicamentoParaVenta.id!,
      cantidad: this.cantidadVenta
    };

    this.ventaService.realizar(ventaRequest).subscribe({
      next: (venta) => {
        this.mostrarExito(`Venta realizada. Total: $${venta.valorTotal.toFixed(2)}`);
        this.displayVentaDialog = false;
        this.cargarMedicamentos();
      },
      error: () => {
        this.mostrarError('Error al realizar la venta');
      }
    });
  }

  private mostrarExito(mensaje: string): void {
    this.messageService.add({ 
      severity: 'success', 
      summary: 'Éxito', 
      detail: mensaje 
    });
  }

  private mostrarError(mensaje: string): void {
    this.messageService.add({ 
      severity: 'error', 
      summary: 'Error', 
      detail: mensaje 
    });
  }
}
