import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { VentaService } from '../../services/venta.service';
import { Venta } from '../../../../core/models/venta.model';

@Component({
  selector: 'app-ventas-list',
  templateUrl: './ventas-list.component.html',
  styleUrls: ['./ventas-list.component.scss']
})
export class VentasListComponent implements OnInit {
  ventas: Venta[] = [];
  totalRecords = 0;
  loading = false;
  
  // Propiedad para la fecha máxima en el calendario
  maxDateCalendar: Date = new Date();
  
  // Filtros de fecha
  fechaInicio: Date | null = null;
  fechaFin: Date | null = null;
  
  // Paginación
  first = 0;
  rows = 10;

  constructor(
    private ventaService: VentaService,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.cargarVentas();
  }

  cargarVentas(event?: any): void {
    this.loading = true;
    const page = event ? event.first / event.rows : 0;
    const size = event ? event.rows : this.rows;

    if (this.fechaInicio && this.fechaFin) {
      // Formatear fechas con hora de inicio y fin del día
      const fechaInicioStr = this.formatearFechaInicio(this.fechaInicio);
      const fechaFinStr = this.formatearFechaFin(this.fechaFin);
      
      console.log('Filtrando ventas:', { fechaInicioStr, fechaFinStr }); // Debug
      
      this.ventaService.filtrarPorFechas(fechaInicioStr, fechaFinStr, page, size)
        .subscribe({
          next: (data) => {
            this.ventas = data.content;
            this.totalRecords = data.totalElements;
            this.loading = false;
            console.log('Ventas cargadas:', data); // Debug
          },
          error: (error) => {
            console.error('Error al filtrar ventas:', error); // Debug
            this.mostrarError('Error al cargar ventas: ' + (error.error?.message || error.message));
            this.loading = false;
          }
        });
    } else {
      this.ventaService.listar(page, size).subscribe({
        next: (data) => {
          this.ventas = data.content;
          this.totalRecords = data.totalElements;
          this.loading = false;
        },
        error: (error) => {
          console.error('Error al listar ventas:', error); // Debug
          this.mostrarError('Error al cargar ventas');
          this.loading = false;
        }
      });
    }
  }

  aplicarFiltros(): void {
    if (this.fechaInicio && this.fechaFin) {
      if (this.fechaFin < this.fechaInicio) {
        this.mostrarError('La fecha fin debe ser posterior a la fecha inicio');
        return;
      }
      this.first = 0;
      this.cargarVentas();
    } else if (this.fechaInicio || this.fechaFin) {
      this.mostrarError('Debe seleccionar ambas fechas');
    } else {
      this.cargarVentas();
    }
  }

  limpiarFiltros(): void {
    this.fechaInicio = null;
    this.fechaFin = null;
    this.first = 0;
    this.cargarVentas();
  }

  /**
   * Formatea la fecha de inicio al comienzo del día (00:00:00)
   */
  private formatearFechaInicio(fecha: Date): string {
    const year = fecha.getFullYear();
    const month = String(fecha.getMonth() + 1).padStart(2, '0');
    const day = String(fecha.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}T00:00:00`;
  }

  /**
   * Formatea la fecha de fin al final del día (23:59:59)
   */
  private formatearFechaFin(fecha: Date): string {
    const year = fecha.getFullYear();
    const month = String(fecha.getMonth() + 1).padStart(2, '0');
    const day = String(fecha.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}T23:59:59`;
  }

  calcularTotalVentas(): number {
    return this.ventas.reduce((total, venta) => total + venta.valorTotal, 0);
  }

  private mostrarError(mensaje: string): void {
    this.messageService.add({ 
      severity: 'error', 
      summary: 'Error', 
      detail: mensaje 
    });
  }
}