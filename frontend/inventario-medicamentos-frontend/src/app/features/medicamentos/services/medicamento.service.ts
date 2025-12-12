import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Medicamento, MedicamentoRequest } from '../../../core/models/medicamento.model';
import { Page } from '../../../core/models/page.model';
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MedicamentoService {
  private apiUrl = `${environment.apiUrl}/medicamentos`;

  constructor(private http: HttpClient) {}

  crear(medicamento: MedicamentoRequest): Observable<Medicamento> {
    return this.http.post<Medicamento>(this.apiUrl, medicamento);
  }

  actualizar(id: number, medicamento: MedicamentoRequest): Observable<Medicamento> {
    return this.http.put<Medicamento>(`${this.apiUrl}/${id}`, medicamento);
  }

  obtenerPorId(id: number): Observable<Medicamento> {
    return this.http.get<Medicamento>(`${this.apiUrl}/${id}`);
  }

  listar(page: number = 0, size: number = 10): Observable<Page<Medicamento>> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    return this.http.get<Page<Medicamento>>(this.apiUrl, { params });
  }

  filtrar(nombre?: string, laboratorio?: string, page: number = 0, size: number = 10): Observable<Page<Medicamento>> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    
    if (nombre) {
      params = params.set('nombre', nombre);
    }
    if (laboratorio) {
      params = params.set('laboratorio', laboratorio);
    }

    return this.http.get<Page<Medicamento>>(`${this.apiUrl}/filtrar`, { params });
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}