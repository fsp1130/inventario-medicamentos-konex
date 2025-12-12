export interface Medicamento {
  id?: number;
  nombre: string;
  laboratorio: string;
  fechaFabricacion: string;
  fechaVencimiento: string;
  cantidadStock: number;
  valorUnitario: number;
  vencido?: boolean;
}

// Este es el tipo para las peticiones (sin ID ni vencido)
export interface MedicamentoRequest {
  nombre: string;
  laboratorio: string;
  fechaFabricacion: string;
  fechaVencimiento: string;
  cantidadStock: number;
  valorUnitario: number;
}

// Alias para compatibilidad
export type MedicamentoRequestDTO = MedicamentoRequest;