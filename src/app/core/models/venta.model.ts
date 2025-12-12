export interface Venta {
  id?: number;
  fechaHora: string;
  medicamentoId: number;
  nombreMedicamento: string;
  cantidad: number;
  valorUnitario: number;
  valorTotal: number;
}

export interface VentaRequest {
  medicamentoId: number;
  cantidad: number;
}